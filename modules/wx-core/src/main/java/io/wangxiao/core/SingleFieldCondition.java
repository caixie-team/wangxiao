package io.wangxiao.core;


import io.wangxiao.core.util.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class SingleFieldCondition extends Condition implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4466041047921086476L;
    private String field;
    private Object value;
    private Object newValue;
    private String operator;
    private String type;


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getNewValue() {
        return newValue;
    }

    /**
     * 校验是否 操作符
     */
    private static boolean isOperator(String str) {
        return "gt".equals(str) || "lt".equals(str) || "eq".equals(str) || "nq".equals(str) || "like".equals(str) || "in".equals(str);
    }


    /**
     * 返回sql操作符
     */
    private static String getOperator(String str) {
        switch (str) {
            case "gt":
                return ">=";
            case "lt":
                return "<=";
            case "in":
                return "in";
            case "nq":
                return "!=";
            case "like":
                return "like";
            default: //eq 或其他
                return "=";
        }
    }


    /**
     * 设置value值
     */
    public Condition setValue(Object value) {
        this.value = value;
        if (value == null || value.toString().trim().equals("")) {
            return this;
        }
        //字符串类型
        switch (type) {
            case "string":
                switch (operator) {
                    case "like":
                        //对通配符进行转移
                        String thisVal = value.toString().trim()
                                .replaceAll("_", "\\\\_")            //替换'_'
                                .replaceAll("%", "\\\\%")            //替换'%'
                                ;
                        this.newValue = "%" + thisVal + "%";
                        break;
                    case "in":
                        if (this.value instanceof String) {
                            this.newValue = value.toString().trim().split(",");
                        } else {
                            this.newValue = this.value;
                        }
                        break;
                    default:
                        this.newValue = value.toString().trim();
                        break;
                }
                break;
            //日期类型
            case "date":

                Date date = null;
                try {
                    //gt 大于一个日期 + " 00:00:00"
                    if (getOperator("gt").equals(operator)) {
                        date = DateUtils.parse(value.toString().trim() + " 00:00:00", DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS);
                        //减一秒操作
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.SECOND, -1);
                        date = calendar.getTime();
                    } else if (getOperator("lt").equals(operator)) { //lt 小于一个日期 + " 23:59:59"
                        date = DateUtils.parse(value.toString().trim() + " 23:59:59", DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS);
                    } else {
                        date = DateUtils.parse(value.toString().trim(), DateUtils.PATTERN_YYYY_MM_DD);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                this.newValue = date;
                break;
            case "float":
                this.newValue = Float.parseFloat(value.toString().trim());
                break;
            default:  //整型
                //其他 int 或  string 用,隔开的 字符串  转化为 String [] 类型
                if ("in".equals(operator)) {
                    if (this.value instanceof String) {
                        this.newValue = value.toString().trim().split(",");
                    } else {
                        this.newValue = this.value;
                    }
                } else {
                    this.newValue = Integer.valueOf(this.value.toString().trim());
                }

                break;
        }

        return this;
    }

    @Override
    protected void parse(String parameter) {
        String[] array = parameter.split(FIELD_INTERNAL_DELIMETER);
        String field = array[0];
        setField(field);

        //只有一个参数(字符串模糊查询) 字段
        if (array.length == 1) {
            setType("string");
            setOperator("like");
        } else if (array.length == 2) {  //只有二个参数            字段_操作符/字段_数据类型
            //第2个是操作符，则默认是字符类型
            if (isOperator(array[1])) {
                setOperator(getOperator(array[1]));
                setType("string");
            } else {    //字段_数据类型
                setOperator("=");
                setType(array[1]);
            }
        } else if (array.length == 3) { //有三个参数(非字符串查询)   字段名_类型_操作符
            setType(array[1]);
            setOperator(getOperator(array[2]));
        }

    }

    @Override
    public String toString() {
        return "SingleFieldCondition [field=" + field + ", value=" + value + ", newValue="
                + newValue + ", operator=" + operator + ", type=" + type + "]";
    }


    @Override
    public int hashCode() {
        return toString().hashCode();
    }


    public static void main(String[] args) {
        String s = "baidu.360.aaa.ca";
        String a = s.replaceAll(".", "\\\\_");
        System.out.println(a);

		/*
		Condition c = Condition.parseCondition("sex_int_gt");
		c.setValue("1");
		System.out.println(c.toString());
		*/
    }

}
