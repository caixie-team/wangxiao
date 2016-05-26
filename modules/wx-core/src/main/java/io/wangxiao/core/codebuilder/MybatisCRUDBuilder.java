package io.wangxiao.core.codebuilder;

import io.wangxiao.core.metadata.ModelMappingManager;
import io.wangxiao.core.metadata.PropertyInfo;
import io.wangxiao.core.model.Deleted;
import io.wangxiao.core.util.ClassUtils;
import io.wangxiao.core.util.StringUtils;
import io.wangxiao.core.util.VelocityTemplateUtils;
import com.google.common.base.CaseFormat;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import javax.persistence.Table;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据类名生成BaseDao中定义的Mybatis的映射SQL语句
 * 现在支持动态insert
 */
public class MybatisCRUDBuilder extends UniversalCodeBuilder {

    private String tablePrefix;

    @Override
    public <T> String buildByClass(Class<T> clazz) {
        return this.createSqlByEntity(clazz);
    }

    public String mergeTemplate(VelocityContext context) {
        Template template = null;
        StringWriter writer = null;
        try {
            String templateFile = "createSql.vm";
            template = VelocityTemplateUtils.getTemplate(templateFile);
            writer = new StringWriter();
            if (template != null)
                template.merge(context, writer);
            writer.flush();

        } catch (ResourceNotFoundException | ParseErrorException rnfe) {
            rnfe.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //TODO: 打印自动生成的SQL语句，这里会做成方法供测试使用。@basil
//        System.out.println(        writer.toString()        );
        return writer != null ? writer.toString() : null;
    }


    /******************************************************************************/

    private List<String> pkList = new ArrayList<String>();

    /**
     * 功能:初始化类字段属性
     * <p>
     *
     * @param clazz 要转换的实体类
     * @return 如果type=file返回xml文件绝对路径，否则返回生成的xml内容
     */
    //TODO: 表前缀，或者用属性文件规范，或都用注解类，待定。@Basil Liu
    public <T> String createSqlByEntity(Class<T> clazz) {
        String tableName = ClassUtils.getLowerFirstLetterSimpleClassName(clazz
                .getSimpleName());


        //TODO:这里造成H2数据库表无法识别，不过已经设置成 mysql mode了还是不可以，待修改；@basil
//		tableName = "`leap_" + tableName + "`";

//        System.out.println(StringUtils.camel2underscore(tableName) + "--------");
        if (tablePrefix != null) {
            if (clazz.getAnnotation(Table.class) != null) {
                tableName = clazz.getAnnotation(Table.class).name();
            } else {
                ///TODO: if table model is "underscore"
                tableName = tablePrefix + "_" + StringUtils.camel2underscore(tableName);
                /// else
                // tableName = tablePrefix + "_" + tableName;
            }
        }
        pkList.clear();
        List<String> columns = new ArrayList<>();
        List<PropertyInfo> propertyinfos = ModelMappingManager.getBeanInfo(
                clazz).getProperties();


        for (PropertyInfo propertyinfo : propertyinfos) {
            String mName = propertyinfo.getPropertyName();

            mName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, mName);
            columns.add(mName);

            // 判断是否是主键
            if (mName.equalsIgnoreCase("id"))
                pkList.add(mName);

        }
        String entityClassName = clazz.getName();
        String findByIdSql = findByIdSql(tableName, columns);
        String inserSql = insertSql(tableName, columns);

        String updateSql = updateSql(tableName, columns);
        VelocityContext context = new VelocityContext();

        String deleteSql;
        String deleteByIdsSql;

        if (Deleted.class.isAssignableFrom(clazz)) {
            deleteSql = deleteDeletedSql(tableName, false);
            deleteByIdsSql = deleteDeletedSql(tableName, true);

        } else {
            deleteSql = deleteSql(tableName, columns);
            deleteByIdsSql = "delete from " + tableName
                    + " WHERE id in <include refid=\"common.idsForEach\"/>";

        }

        context.put("delete", deleteSql);
        context.put("deleteById", deleteSql);
        context.put("deleteByIdsSql", deleteByIdsSql);

        context.put("findById", findByIdSql);
        context.put("findSql", findSql(tableName, clazz, columns));

        context.put("insert", inserSql);
        context.put("update", updateSql);
        context.put("updateBatchSql", updateBatchSql(tableName, columns));

        context.put("tableName", tableName);
        // 把model替换成Dao
        String daoClassName = getDaoClassName(entityClassName);

        context.put("daoClass", daoClassName);
        context.put("entityClass", entityClassName);
        //去除缓存
        /**
         if (clazz.isAnnotationPresent(CacheNamespace.class)) {
         context.put("cacheClass",
         "<cache type=\"com.haixue.modules.extend.MemcachedCache\"/>");
         context.put("cached", true);
         } else if(clazz.isAnnotationPresent(CacheNamespaceRef.class)){

         context.put("cacheClass", "<cache-ref namespace=\"" + clazz.getAnnotation(CacheNamespaceRef.class).value().getName() + "\"/>");
         context.put("cached", true);
         } else {
         context.put("cacheClass", "");
         context.put("cached", false);
         }**/
        context.put("cacheClass", "");
        context.put("cached", false);

        return mergeTemplate(context);

    }

    private String getDaoClassName(String entityClassName) {
        return entityClassName.replace("entity", "dao") + "Dao";
    }

    private String deleteDeletedSql(String tableName, boolean batch) {
        StringBuilder updateSql = new StringBuilder();
        if (batch) {
            updateSql
                    .append("update ")
                    .append(tableName)
                    .append(" set deleted=1 where id in <include refid=\"common.idsForEach\"/>");
        } else {
            updateSql.append("update ").append(tableName)
                    .append(" set deleted=1 where id=#{id}");
        }
        return updateSql.toString();
    }

    /**
     * 功能:生成insert语句
     * <p>
     *
     * @param tableName
     * @param columns
     * @return
     */
    private String insertSql(String tableName, List<String> columns) {

        StringBuilder insertSql = new StringBuilder();
        StringBuilder valueSql = new StringBuilder();

        insertSql.append("insert into ").append(tableName).append("(");
        insertSql.append(" <trim suffix='' suffixOverrides=','>");
        valueSql.append(" <trim suffix='' suffixOverrides=','>");

        int i = 0;
        while (i < columns.size()) {
            String column = columns.get(i);

            //TODO: 将下划线格式字段转换成 驼峰格式 与 entity 的get 方法匹配
            // 这里完全是为了适配数据库字段与实体类
            String field = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column);


            insertSql.append("<if test=\"").append(field).append(" != null\" >");
            insertSql.append(column).append(",");
            insertSql.append("</if>");


            valueSql.append("<if test=\"").append(field).append(" != null\" >");

            valueSql.append("#{").append(field).append("}").append(",");
            valueSql.append("</if>");
            i++;

        }
        valueSql.append("</trim>");
        insertSql.append("</trim>) values (").append(valueSql).append(")");

        return insertSql.toString();
    }

    /**
     * 功能:生成update语句
     * <p>
     *
     * @param tableName
     * @param columns
     * @return
     */
    private String updateSql(String tableName, List<String> columns) {
        // <set>元素会动态前置 SET关键字,而且也会消除任意无关的逗号
        StringBuilder updateSql = updateFields(tableName, columns, false);
        if (pkList.size() > 0) {
            updateSql.append(pkWhereSqlStr());
        }
        return updateSql.toString();
    }

    private StringBuilder updateFields(String tableName, List<String> columns,
                                       boolean useAlias) {
        StringBuilder updateSql = new StringBuilder();
        updateSql.append("update ").append(tableName).append(" <set> ");
        for (String column : columns) {

            String field = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column);

            //TODO: 待处理 Field
            String columnAlias = useAlias ? "entity." + column : column;
            // if(useAlias) column = ;
            updateSql.append(" <if test=\"").append(field)
                    .append("!= null\"> ");
            updateSql.append(column).append("=#{").append(field)
                    .append("},");
            updateSql.append(" </if> ");
        }
        updateSql.append(" </set> ");
        return updateSql;
    }

    private String updateBatchSql(String tableName, List<String> columns) {
        StringBuilder updateSql = updateFields(tableName, columns, true);
        updateSql.append(NEW_LINE_BREAK).append(" WHERE id in ");
        updateSql.append("<include refid=\"common.idsForEach\"/>");
        return updateSql.toString();
    }

    /**
     * 功能:生成findById语句
     * <p>
     *
     * @param tableName
     * @param columns
     * @return
     */
    private String findByIdSql(String tableName, List<String> columns) {
        StringBuilder findByIdSql = new StringBuilder();
        findByIdSql.append("select * from ").append(tableName);
        findByIdSql.append(pkWhereSqlStr());
        return findByIdSql.toString();
    }

    private <T> String findSql(String tableName, Class<T> clazz, List<String> columns) {

        StringBuilder findSql = new StringBuilder();
//        findSql.append("select ");
//        for (String column : columns) {
//            findSql.append(column).append(" as ").append(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column)).append(",");
//        }
//        findSql.setLength(findSql.length() - 1);
//        findSql.append(" from ").append(tableName);
        findSql.append("select * from ").append(tableName);

        findSql.append(NEW_LINE_BREAK).append("<where>");
        findSql.append(NEW_LINE_BREAK).append(
                "<include refid=\"common.dynamicConditionsNoWhere\"/>");
        if (Deleted.class.isAssignableFrom(clazz)) {
            findSql.append(NEW_LINE_BREAK).append("AND Deleted = 0");
        }

        findSql.append(NEW_LINE_BREAK).append("</where>");
        String orderBy = ModelMappingManager.getBeanInfo(clazz).getOrderBy();
        if (!StringUtils.isEmpty(orderBy)) {
            findSql.append(NEW_LINE_BREAK);
            findSql.append(" ORDER BY ").append(orderBy);
        }

        return findSql.toString();
    }

    /**
     * 功能:生成delete语句
     * <p>
     *
     * @param tableName
     * @param columns
     * @return
     */
    private String deleteSql(String tableName, List<String> columns) {
        StringBuilder deleteSql = new StringBuilder();
        deleteSql.append("delete from ").append(tableName);
        deleteSql.append(pkWhereSqlStr());
        return deleteSql.toString();
    }

    /**
     * 主键where条件拼接
     *
     * @return
     */
    private String pkWhereSqlStr() {
        if (pkList.size() == 0)
            return "";
        StringBuilder pkStr = new StringBuilder();
        pkStr.append(" where ");
        for (String pk : pkList) {
            pkStr.append(pk).append("=").append("#{").append(pk).append("}")
                    .append(" and ");
        }

        return pkStr.delete(pkStr.length() - 4, pkStr.length()).toString();
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }
}
