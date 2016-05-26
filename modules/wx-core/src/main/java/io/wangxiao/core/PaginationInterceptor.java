package io.wangxiao.core;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 分页查询。 通过 BoundSqlWrapper 实现SQL语句的动态替换。通过Executor调用Mybatis的查询，不直接操纵JDBC
 */
//只拦截select部分
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})})
@SuppressWarnings({"rawtypes"})
public class PaginationInterceptor implements Interceptor {
    private Pattern groupByFieldPattern = Pattern.compile("[^, ]+\\s*,?");
    private Pattern orderByFieldPattern = Pattern.compile("[^, ]+\\s*(desc|asc){0,1}\\s*,?");
    private static final Map<String, String> countSqlMap = new HashMap<String, String>();

    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        if (boundSql == null || StringUtils.isEmpty(boundSql.getSql()))
            return null;

        Object parameterObject = boundSql.getParameterObject();

        Pagination pagination = null;
        if (parameterObject != null) {
            pagination = (Pagination) getParameterByType(parameterObject, Pagination.class);
        }

        if (pagination != null) {
            String originalSql = boundSql.getSql().trim();
            Integer totalCount = pagination.getTotalCount();
            // 得到总记录数
            if (totalCount <= 0) {
                //查询记录总数。
                String countSql = getCountSql(boundSql.getSql());
                BoundSqlWrapper newBoundSql = new BoundSqlWrapper(boundSql, countSql, mappedStatement.getConfiguration());
                ResultMap map = new ResultMap.Builder(mappedStatement.getConfiguration(), "qq", Integer.class, new ArrayList<ResultMapping>()).build();
                List<ResultMap> mapList = new ArrayList<ResultMap>();
                mapList.add(map);
                MappedStatement newMs = QueryIntercepterUtils.copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql), mapList);
                List<?> rows = ((Executor) invocation.getTarget()).query(newMs, parameterObject, (RowBounds) invocation.getArgs()[2], null);
                //在 group by sometimes return null , not 0 when the resultset is empty.
                if (rows.size() == 0) {
                    totalCount = 0;
                } else {
                    totalCount = (Integer) rows.get(0);
                }
            }

            // 分页计算
            pagination.init(totalCount, pagination.getPageSize(), pagination.getCurrentPage());


            // 分页查询 本地化对象 修改数据库注意修改实现
            String pagesql = getLimitString(originalSql, pagination.getPageSize() * (pagination.getCurrentPage() - 1), pagination.getPageSize());

            invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);

            BoundSqlWrapper newBoundSql = new BoundSqlWrapper(boundSql, pagesql, mappedStatement.getConfiguration());
            MappedStatement newMs = QueryIntercepterUtils.copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
            invocation.getArgs()[0] = newMs;
        }
//        else{
//            BoundSqlWrapper newBoundSql = new BoundSqlWrapper(boundSql, boundSql.getSql() ,mappedStatement.getConfiguration());
//            MappedStatement newMs = QueryIntercepterUtils.copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));            
//            invocation.getArgs()[0] = newMs;	
//        }
        return invocation.proceed();

    }

    public static class BoundSqlWrapper extends BoundSql {
        private BoundSql sourceBoundSql;
        private String sql;

        public BoundSqlWrapper(BoundSql sourceBoundSql, String sql, Configuration configuration) {
            super(configuration, null, null, null);
//			if(sql.toLowerCase().trim().startsWith("select")){
//				this.sql = "select SQL_NO_CACHE " + sql.substring(7);
//			}else{
//				this.sql = sql;
//			}
            this.sql = sql;
            this.sourceBoundSql = sourceBoundSql;

        }

        @Override
        public String getSql() {
            return this.sql;
        }

        @Override
        public List<ParameterMapping> getParameterMappings() {
            return this.sourceBoundSql.getParameterMappings();
        }

        @Override
        public Object getParameterObject() {
            return this.sourceBoundSql.getParameterObject();
        }

        @Override
        public boolean hasAdditionalParameter(String name) {
            return this.sourceBoundSql.hasAdditionalParameter(name);
        }

        @Override
        public void setAdditionalParameter(String name, Object value) {
            this.sourceBoundSql.setAdditionalParameter(name, value);
        }

        @Override
        public Object getAdditionalParameter(String name) {
            return this.sourceBoundSql.getAdditionalParameter(name);
        }
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {

            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }


    /**
     * 取参数中的类型
     *
     * @param parameterObject
     * @param parameterType
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T getParameterByType(Object parameterObject, Class<T> parameterType) {
        if (parameterObject instanceof Map) {
            Map map = (Map) parameterObject;
            for (Object parameter : map.values()) {
                if (parameter == null) continue;
                if (parameterType.isAssignableFrom(parameter.getClass())) {
                    return (T) parameter;
                }
            }

        } else if (parameterType.isAssignableFrom(parameterObject.getClass())) {
            return (T) parameterObject;
        }
        return null;
    }

    /**
     * 得到分页的SQL
     *
     * @param offset 偏移量
     * @param limit  位置
     *
     * @return 分页SQL
     */
    private String getLimitString(String querySelect, int offset, int limit) {
        if (StringUtils.isEmpty(querySelect)) {
            return querySelect;
        }
        querySelect = getLineSql(querySelect);
        //String sql =  querySelect.replaceAll("[^\\s,]+\\.", "") +" limit "+ offset +" ,"+ limit;
        String sql = querySelect + " limit " + offset + " ," + limit;
        return sql;

    }

    /**
     * 将SQL语句变成一条语句，并且每个单词的间隔都是1个空格
     *
     * @param sql SQL语句
     *
     * @return 如果sql是NULL返回空，否则返回转化后的SQL
     */
    private String getLineSql(String sql) {
        return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
    }

    /**
     * 得到求总数的SQL
     * 以下可能会出问题：
     * '(' ')' ' from'在from前的常量字段中
     *
     * @param sql
     *
     * @return
     */
    private String getCountSql(String sql) {
        String originalSql = sql;
        //拼一次，后面有正则什么的，还可以省点时间
        if (countSqlMap.containsKey(sql)) {
            return countSqlMap.get(sql);
        }

        sql = getLineSql(sql).trim();
        String lowercaseSQL = sql.toLowerCase();//先规范SQL
        int fromPosition = lowercaseSQL.indexOf(" from"); //注意from前有空格
        int lbraketPosition = lowercaseSQL.indexOf("(");
        //如果from前没有左括号，可以认为是一个简单的sql
        while (lbraketPosition != -1 && lbraketPosition < fromPosition) {
            int lbraketCount = 1;
            int rbraketCount = 0;
            int currentPosition = lbraketPosition;
            //找到所有的左括号和右括号数目相等
            while (lbraketCount != rbraketCount) {
                char chr = lowercaseSQL.charAt(++currentPosition);
                if (chr == '(') {
                    lbraketCount++;
                } else if (chr == ')') {
                    rbraketCount++;
                }
            }
            //再找后面一个from的位置,注意from前有空格
            fromPosition = lowercaseSQL.indexOf(" from", currentPosition);
            lbraketPosition = lowercaseSQL.indexOf("(", currentPosition);
        }

        String groupBy = " group by";
        int groupByPosition = lowercaseSQL.lastIndexOf(groupBy);
        int rbracketPosition = lowercaseSQL.lastIndexOf(")");
        boolean distinct = lowercaseSQL.startsWith("select distinct");
        //distinct 求和时不能加上原来的所有字段，在mysql中结果不对！所以只能按主键求
        //同时如果在SQL最后如果有分组，也去掉分组
        if (distinct || (rbracketPosition < groupByPosition)) {//如果有distinct 或者 最后有分组，都要重新处理SQL
            String[] parts = sql.split("[, ]+");
            String distinctField;
            if (distinct) {
                distinctField = parts[2].replaceAll("\\*", "id");
            } else {
                distinctField = parts[1].replaceAll("\\*", "id");
            }

            String fromToEndSql = sql.substring(fromPosition);
            if (rbracketPosition < groupByPosition) {
                distinctField = "";
                fromToEndSql = sql.substring(groupByPosition + groupBy.length());
                Matcher m = groupByFieldPattern.matcher(fromToEndSql);
                int endPosition = -1;
                while (m.find()) {
                    distinctField += m.group();
                    if (!m.group().endsWith(",")) {
                        endPosition = m.end();
                        break;
                    }
                }
                fromToEndSql = sql.substring(fromPosition, groupByPosition) + " " + sql.substring(endPosition + groupByPosition + groupBy.length());
            }

            sql = "select count( distinct " + distinctField + ") " + fromToEndSql;
        } else {
            sql = "select count(*) " + sql.substring(fromPosition);
        }
        sql = trimOrderBy(sql);
        countSqlMap.put(originalSql, sql);
        return sql;
    }

    /**
     * 删除求和SQL中的order by 部分
     *
     * @param sql
     *
     * @return
     */
    private String trimOrderBy(String sql) {
        String lowercaseSQL = sql.toLowerCase();
        String orderBy = " order by";
        int orderByPosition = lowercaseSQL.lastIndexOf(orderBy);
        int rbracketPosition = lowercaseSQL.lastIndexOf(")");
        if (rbracketPosition < orderByPosition) {
            String orderbyToEndSql = lowercaseSQL.substring(orderByPosition + orderBy.length());

            Matcher m = orderByFieldPattern.matcher(orderbyToEndSql);
            int endPosition = -1;
            while (m.find()) {
                if (!m.group().endsWith(",")) {
                    endPosition = m.end();
                    break;
                }
            }
            sql = sql.substring(0, orderByPosition) + " " + sql.substring(endPosition + orderByPosition + orderBy.length());
        }
        return sql;
    }

    public static void main(String[] args) {
        PaginationInterceptor ic = new PaginationInterceptor();
        String sql = "select a.id as attentionCount from customer a inner join customerRelation b on a.id=b.customerId where b.customerFriendId=? and a.customerStatus=0 group by a.id,b.id , ccc having order by a desc,b desc limit 10";
        //String sql = "select 			q.*, cu.nickName, cu.email, 			(				SELECT					count(questionId)				FROM					answer				WHERE					q.id = answer.questionId			) AS replySumCount,			(				SELECT					IFNULL(sum(agreeCount), 0)				FROM					answer				WHERE					q.id = answer.questionId			) AS agreeSumCount,			(				SELECT					IFNULL(sum(disagreeCount), 0)				FROM					answer				WHERE					q.id = answer.questionId			) AS disagreeSumCount			from question q 	     	inner join customer cu on cu.id = q.createBy ";
        System.out.println(ic.getCountSql(sql));
        String scriptPath = "E:\\workspace\\support\\db-script\\";
        File f = new File(scriptPath + "DDL");
        String[] files = f.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("sql");
            }
        });

        f = new File(scriptPath + "DML");

        String[] files1 = f.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("sql");
            }
        });

        try {
            FileWriter writer = new FileWriter("d:/all.sql");
            for (String fName : files) {

                //FileReader reader = new FileReader(fName);
                //reader.re
                writer.write(FileUtils.readFileToString(new File(scriptPath + fName)));
                writer.write("\n\r");
            }
            for (String fName : files1) {

                //FileReader reader = new FileReader(fName);
                //reader.re
                writer.write(FileUtils.readFileToString(new File(scriptPath + fName)));
                writer.write("\n\r");
            }
            writer.flush();
            writer.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}