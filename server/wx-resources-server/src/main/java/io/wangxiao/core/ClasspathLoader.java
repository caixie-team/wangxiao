package io.wangxiao.core;

import org.beetl.sql.core.BeetlSQLException;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLSource;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bison on 5/29/16.
 */
public class ClasspathLoader implements SQLLoader {
    private String path = "sql";


    private String lineSeparator = System.getProperty("line.separator", "\n");
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(Thread.currentThread().getContextClassLoader());

    private Map<String, SQLSource> sqlSourceMap = new ConcurrentHashMap<>();
    private Map<String, Integer> sqlSourceVersion = new ConcurrentHashMap<>();

    private DBStyle style = null;

    private boolean autoCheck = true;


    public ClasspathLoader(String path) {
        this(path, new MySqlStyle());
    }

    public ClasspathLoader(String path, DBStyle style) {
        this.path = path;
        this.style = style;
    }


    @Override
    public SQLSource getSQL(String id) {
        SQLSource ss = this.tryLoadSQL(id);

        if (ss == null) {
            throw new BeetlSQLException(BeetlSQLException.CANNOT_GET_SQL, "未能找到" + id + "对应的sql");
        }
        return ss;
    }

    private SQLSource tryLoadSQL(String id) {
        SQLSource ss = sqlSourceMap.get(id);
        boolean hasLoad = false;
        if (ss == null) {
            _loadSql(id);

            hasLoad = true;
        }
        if (!hasLoad && this.autoCheck && isModified(id)) {
            _loadSql(id);
        }

        //处理完后再次获取
        ss = sqlSourceMap.get(id);
        return ss;
    }

    @Override
    public boolean isModified(String id) {
        int index = id.indexOf('.');
        if (index != -1) {
            String sqlName = id.substring(index);
            if (sqlName.startsWith("._gen")) {
                return false;
            }
        }
        //如果db目录中有sql文件，直接使用db目录的文件判断版本（root中的文件会被db中的覆盖）
        InputStream is = this.getMdFileByStyle(id);
        if (is == null) {
            //db目录中没有文件，使用root下的
            is = this.getMdFile(id);
        }
        if (is != null) {
            Integer lastModify = is.hashCode();
            Integer oldVersion = sqlSourceVersion.get(id);
            if (oldVersion != null && oldVersion.equals(lastModify)) {
                return false;
            }
        }

        return true;
    }

    public boolean exist(String id) {
        return this.tryLoadSQL(id) != null;
    }

    @Override
    public void addGenSQL(String id, SQLSource source) {

        sqlSourceVersion.put(id, 0); //never change
        sqlSourceMap.put(id, source);

    }

    /***
     * 考虑到跨数据库支持，ClasspathLoader加载SQL顺序如下：
     * 首先根据DBStyle.getName() 找到对应的数据库名称，然后在ROOT/dbName 下找对应的sql，
     * 如果ROOT/dbName 文件目录不存在，或者相应的sql文件不存在，再搜索ROOT目录下的sql文件。
     * 如mysql 里查找user.select2,顺序如下：
     * - 先找ROOT/mysql/user.sql 文件，如果有此文件，且包含了select2，则返回此sql语句，
     * - 如果没有，下一步查找ROOT/mysql/user.md,如果有此文件，且包含了slect2，则返回sql语句
     * - 如果没有，下一步查找ROOT/user.sql,如果有此文件，且包含了slect2，则返回sql语句
     * - 如果没有，下一步查找ROOT/user.md,如果有此文件，且包含了slect2，则返回sql语句
     * - 都没有，抛错，告诉用户未在ROOT/,或者ROOT/mysql 下找到相关sql
     *
     * @return
     */
    private boolean _loadSql(String id) {
        //读取指定目录下的sql文件
        InputStream ins = this.getMdFile(id);
        boolean rootResult = readSqlFile(id, ins);
        //读取db目录下的sql文件，进行覆盖
        ins = this.getMdFileByStyle(id);

        boolean dbResult = readSqlFile(id, ins);
        if (rootResult || dbResult) {
            return true;
        } else {
            String modelName = id.substring(0, id.lastIndexOf("."));
            String path = modelName.replace('.', '/');
            String filePath0 = path + "/" + style.getName() + "/" + path + ".sql";
            String filePath1 = path + "/" + style.getName() + "/" + path + ".md";
            String filePath2 = path + "/" + path + ".sql";
            String filePath3 = path + "/" + path + ".md";
            throw new BeetlSQLException(BeetlSQLException.CANNOT_GET_SQL, "在 "
                    + filePath0 + " 和 "
                    + filePath1 + " 和 "
                    + filePath2 + " 和 "
                    + filePath3 + " 和 " +
                    " 未找到[id=" + id + "]相关的SQL");
        }
    }

    private boolean readSqlFile(String id, InputStream ins) {
        String modelName = id.substring(0, id.lastIndexOf(".") + 1);
        if (ins == null) return false;
        Integer lastModified = ins.hashCode();
        sqlSourceVersion.put(id, lastModified);
        LinkedList<String> list = new LinkedList<String>();
        BufferedReader bf = null;

        try {
            bf = new BufferedReader(new InputStreamReader(ins));
            String temp;
            StringBuilder sql;
            String key;
            int lineNum = 0;
            int findLineNum = 0;
            while ((temp = bf.readLine()) != null) {
                temp = temp.trim();
                lineNum++;
                if (temp.startsWith("===")) {// 读取到===号，说明上一行是key，下面是注释或者SQL语句
                    if (!list.isEmpty() && list.size() > 1) {// 如果链表里面有多个，说明是上一句的sql+下一句的key
                        String tempKey = list.pollLast();// 取出下一句sql的key先存着
                        sql = new StringBuilder();
                        key = list.pollFirst();
                        while (!list.isEmpty()) {// 拼装成一句sql
                            sql.append(list.pollFirst() + lineSeparator);
                        }
                        SQLSource source = new SQLSource(modelName + key, sql.toString().trim());
                        source.setLine(findLineNum);
                        sqlSourceMap.put(modelName + key, source);// 放入map
                        list.addLast(tempKey);// 把下一句的key又放进来
                        findLineNum = lineNum;
                    }
                    boolean sqlStart = false;
                    String tempNext = null;
                    while ((tempNext = bf.readLine()) != null) {//处理注释的情况
                        tempNext = tempNext.trim();
                        lineNum++;
                        if (tempNext.startsWith("*")) {//读到注释行，不做任何处理
                            continue;
                        } else if (!sqlStart && tempNext.trim().length() == 0) {
                            //注释的空格
                            continue;
                        } else {
                            sqlStart = true;
                            list.addLast(tempNext);//===下面不是*号的情况，是一条sql
                            break;//读到一句sql就跳出循环
                        }
                    }
                } else {
                    list.addLast(temp);
                }
            }
            // 最后一句sql
            sql = new StringBuilder();
            key = list.pollFirst();
            while (!list.isEmpty()) {
                sql.append(list.pollFirst() + lineSeparator);
            }
            SQLSource source = new SQLSource(modelName + key, sql.toString().trim());
            source.setLine(findLineNum);
            sqlSourceMap.put(modelName + key, source);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }


    /**
     * 获取默认的 MD SQL 文件
     *
     * @param id
     * @return
     */
    private InputStream getMdFile(String id) {

        return _mdFile(id, null);

    }

    /**
     * 获取与数据库类型一致的 MD SQL 文件
     *
     * @param id
     * @return
     */
    private InputStream getMdFileByStyle(String id) {

        return _mdFile(id, style.getName());
    }


    private InputStream _mdFile(String id, String dbType) {
        String modelName = id.substring(0, id.lastIndexOf("."));
        String modelPath = modelName.replace('.', '/');
        String mdFile = path + "/" + modelPath + ".md";

        if (dbType != null) mdFile = path + "/" + dbType + "/" + modelPath + ".md";

        InputStream is = null;

        try {
            Resource resource;

            resource = resolver.getResource("classpath:" + mdFile);
            if (resource.exists()) {
                is = resource.getInputStream();

                if (is == null) {
                    return null;
                }
            }
        } catch (IOException e) {
            throw new BeetlSQLException(BeetlSQLException.CANNOT_GET_SQL, "在 " + modelPath + " 未找到[id=" + id + "]相关的SQL");
//            e.printStackTrace();
        }
        return is;

    }


    @Override
    public boolean isAutoCheck() {
        return this.autoCheck;
    }

    @Override
    public void setAutoCheck(boolean check) {
        this.autoCheck = check;

    }

    @Override
    public SQLSource getGenSQL(String id) {
        return sqlSourceMap.get(id);
    }

}
