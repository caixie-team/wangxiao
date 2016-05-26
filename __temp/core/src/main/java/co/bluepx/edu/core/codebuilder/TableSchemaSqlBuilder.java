package co.bluepx.edu.core.codebuilder;


import co.bluepx.edu.core.metadata.ModelMappingManager;
import co.bluepx.edu.core.metadata.PropertyInfo;
import co.bluepx.edu.core.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据实体类名生成建表语句
 * 
 */
public class TableSchemaSqlBuilder extends UniversalCodeBuilder {

	@Override
	public <T> String buildByClass(Class<T> clazz) {

		return createTableSchema(clazz);
	}

	private List<String> pkList = new ArrayList<String>();

    private String prefix;
	/**
	 * 功能:建表语句调用方法
	 * <p>
	 * @param <T>
	 * @param clazz
	 *            要生成建表Schema的实体名
	 * @return
	 */
	private <T> String createTableSchema(Class<T> clazz) {
		pkList.clear();
		pkList.add("id");
		String tableName = ClassUtils.getLowerFirstLetterSimpleClassName(clazz
                .getSimpleName());
        if (prefix != null)
        {
            tableName = prefix + "_" +tableName;
        }
		List<String> columns = new ArrayList<String>();
		StringBuilder colStr = new StringBuilder();
		List<PropertyInfo> propertyinfos = ModelMappingManager.getBeanInfo(clazz).getProperties();
		for(PropertyInfo propertyinfo : propertyinfos){
			Class<?> returnClazz = propertyinfo.getReturnType();
			
			String returnType = returnClazz.getName();
			String mName = propertyinfo.getPropertyName();
			if(mName.equals("id")) continue;
			
			if(mName.equalsIgnoreCase("createDate")){
				colStr.append(mName).append(" ")
				.append("datetime");
			}else{
				colStr.append(mName).append(" ")
					.append(javaTypeToMysqlType(returnType));
			}

			colStr.append(" COMMENT '").append(propertyinfo.getChineseName()).append("'");
			columns.add(colStr.toString());
			colStr.delete(0, colStr.length());

			
		}
		return createTableSql(tableName, columns , ModelMappingManager.getBeanInfo(clazz).getChineseName());
	}

	/**
	 * 功能:生成建表语句
	 * <p>
	 * @param tableName
	 * @param columns
	 * @return
	 */
	private String createTableSql(String tableName, List<String> columns,String tableDescription) {
		StringBuilder tableSql = new StringBuilder();
		tableSql.append("DROP TABLE IF EXISTS ").append(tableName).append(";").append(NEW_LINE_BREAK);
		tableSql.append("CREATE TABLE ").append(tableName).append("(").append(NEW_LINE_BREAK);
		tableSql.append("`id` bigint(20) NOT NULL AUTO_INCREMENT ,");
		for (String column : columns) {
			tableSql.append(NEW_LINE_BREAK);
			tableSql.append(column).append(",");
		}
		if (pkList.size() > 0) {
			String pkStr = pkList.toString();
			pkStr = pkStr.substring(1, pkStr.length() - 1);
			tableSql.append(" PRIMARY KEY (").append(pkStr).append(")");
		} else {
			tableSql.deleteCharAt(tableSql.length() - 1);
		}
		tableSql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8");
		tableSql.append(" COMMENT='").append(tableDescription).append("';");
		return tableSql.toString();
	}

	/**
	 * 功能:java类型到mysql类型转换
	 * <p>
	 * @param type
	 * @return
	 */

	private String javaTypeToMysqlType(String type) {
		String result = null;
        switch (type) {
            case "java.lang.String":
                result = "varchar(32)";
                break;
            case "int":
                result = "int";
                break;
            case "java.lang.Integer":
                result = "int";
                break;
            case "long":
                result = "int";
                break;
            case "java.lang.Long":
                result = "bigint(20)";
                break;
            case "java.util.Date":
                result = "date";
                break;
            case "java.sql.Date":
                result = "date";
                break;
            case "java.sql.Time":
                result = "time";
                break;
            case "java.util.Timer":
                result = "time";
                break;
            case "java.sql.Timestamp":
                result = "datetime";
                break;
            case "float":
                result = "float";
                break;
            case "java.lang.Float":
                result = "float";
                break;
            case "java.math.BigDecimal":
                result = "decimal(18,2)";
                break;
            default:
                result = "int";
                break;
        }
		return result;
	}

	public static void main(String[] args) {
//		System.out.println(new TableSchemaSqlBuilder().buildByClass(Sample.class) );
//		System.out.println(new DynamicEntityFieldTempleBuilder().buildByClass(Sample.class));
	}

    public String getPrefix() {
        return prefix;
    }

    public TableSchemaSqlBuilder setPrefix(String prefix) {
        this.prefix = prefix;

        return this;
    }
}
