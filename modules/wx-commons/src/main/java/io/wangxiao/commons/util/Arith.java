/**
 * ClassName  Arith
 *
 * History
 * Create User: Admin
 * Create Date: 2010-5-25
 * Update User:
 * Update Date:
 */
package io.wangxiao.commons.util;

import org.apache.commons.lang.StringUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
 * 
 * 确的浮点数运算，包括加减乘除和四舍五入。
 * 
 */
public class Arith {

    /** log */
    private static Logger log = LoggerFactory.getLogger(Arith.class);

    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 2;

    // 这个类不能实例化
    private Arith() {

    }

    /**
     * 提供精确的加法运算。
     * 
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    public static String add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);

        return b1.add(b2).toString();
    }

    /**
     * 提供精确的减法运算。
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    public static String sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);

        return b1.subtract(b2).toString();
    }

    /**
     * 提供精确的乘法运算。
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */

    public static String mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        b1 = b1.setScale(2, RoundingMode.HALF_UP);
        BigDecimal b2 = new BigDecimal(v2);

        return b1.multiply(b2).toString();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return 两个参数的商
     */
    public static String div(String v1, String v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static String div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }

        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);

        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();

    }

    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }

        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 将小写金额转成大写金额
     * 
     * @param value
     * @return
     */
    public static String Const(String value) {
        if (StringUtils.isBlank(value))
            return "零";
        int flag = 0;
        if (value.substring(0, 1).equals("-")) {
            flag = 1;
            value = value.substring(1);
        }
        String strCheck = null;
        String strFen = null;
        String strDW = null;
        String strNum = null;
        String strBig = null;
        String strNow = null;// , strArr
        double d = 0;
        try {
            d = Double.parseDouble(value);
        } catch (Exception e) {
            return "数据" + value + "非法！";
        }

        strCheck = value + ".";
        int dot = strCheck.indexOf(".");
        if (dot > 13) {
            return "数据" + value + "过大，无法处理！";
        }

        try {
            int i = 0;
            strBig = "";
            strDW = "";
            strNum = "";
            long intFen = (long) (d * 100);
            strFen = String.valueOf(intFen);
            int lenIntFen = strFen.length();
            while (lenIntFen != 0) {
                i++;
                switch (i) {
                case 1:
                    strDW = "分";
                    break;
                case 2:
                    strDW = "角";
                    break;
                case 3:
                    strDW = "元";
                    break;
                case 4:
                    strDW = "拾";
                    break;
                case 5:
                    strDW = "佰";
                    break;
                case 6:
                    strDW = "仟";
                    break;
                case 7:
                    strDW = "万";
                    break;
                case 8:
                    strDW = "拾";
                    break;
                case 9:
                    strDW = "佰";
                    break;
                case 10:
                    strDW = "仟";
                    break;
                case 11:
                    strDW = "亿";
                    break;
                case 12:
                    strDW = "拾";
                    break;
                case 13:
                    strDW = "佰";
                    break;
                case 14:
                    strDW = "仟";
                    break;
                case 15:
                    strDW = "万";
                    break;
                }
                switch (strFen.charAt(lenIntFen - 1)) // 选择数字
                {
                case '1':
                    strNum = "壹";
                    break;
                case '2':
                    strNum = "贰";
                    break;
                case '3':
                    strNum = "叁";
                    break;
                case '4':
                    strNum = "肆";
                    break;
                case '5':
                    strNum = "伍";
                    break;
                case '6':
                    strNum = "陆";
                    break;
                case '7':
                    strNum = "柒";
                    break;
                case '8':
                    strNum = "捌";
                    break;
                case '9':
                    strNum = "玖";
                    break;
                case '0':
                    strNum = "零";
                    break;
                }
                // 处理特殊情况
                strNow = strBig;
                // 分为零时的情况
                if ((i == 1) && (strFen.charAt(lenIntFen - 1) == '0'))
                    strBig = "整";
                // 角为零时的情况
                else if ((i == 2) && (strFen.charAt(lenIntFen - 1) == '0')) { // 角分同时为零时的情况
                    if (!strBig.equals("整"))
                        strBig = "零" + strBig;
                }
                // 元为零的情况
                else if ((i == 3) && (strFen.charAt(lenIntFen - 1) == '0'))
                    strBig = "元" + strBig;
                // 拾－仟中一位为零且其前一位（元以上）不为零的情况时补零
                else if ((i < 7) && (i > 3) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) != '零') && (strNow.charAt(0) != '元'))
                    strBig = "零" + strBig;
                // 拾－仟中一位为零且其前一位（元以上）也为零的情况时跨过
                else if ((i < 7) && (i > 3) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '零')) {
                }
                // 拾－仟中一位为零且其前一位是元且为零的情况时跨过
                else if ((i < 7) && (i > 3) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '元')) {
                }
                // 当万为零时必须补上万字
                else if ((i == 7) && (strFen.charAt(lenIntFen - 1) == '0'))
                    strBig = "万" + strBig;
                // 拾万－仟万中一位为零且其前一位（万以上）不为零的情况时补零
                else if ((i < 11) && (i > 7) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) != '零') && (strNow.charAt(0) != '万'))
                    strBig = "零" + strBig;
                // 拾万－仟万中一位为零且其前一位（万以上）也为零的情况时跨过
                else if ((i < 11) && (i > 7) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '万')) {
                }
                // 拾万－仟万中一位为零且其前一位为万位且为零的情况时跨过
                else if ((i < 11) && (i > 7) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '零')) {
                }
                // 万位为零且存在仟位和十万以上时，在万仟间补零
                else if ((i < 11) && (i > 8) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '万') && (strNow.charAt(2) == '仟'))
                    strBig = strNum + strDW + "万零" + strBig.substring(1, strBig.length());
                // 单独处理亿位
                else if (i == 11) {
                    // 亿位为零且万全为零存在仟位时，去掉万补为零
                    if ((strFen.charAt(lenIntFen - 1) == '0')
                            && (strNow.charAt(0) == '万') && (strNow.charAt(2) == '仟'))
                        strBig = "亿" + "零" + strBig.substring(1, strBig.length());
                    // 亿位为零且万全为零不存在仟位时，去掉万
                    else if ((strFen.charAt(lenIntFen - 1) == '0')
                            && (strNow.charAt(0) == '万') && (strNow.charAt(2) != '仟'))
                        strBig = "亿" + strBig.substring(1, strBig.length());
                    // 亿位不为零且万全为零存在仟位时，去掉万补为零
                    else if ((strNow.charAt(0) == '万') && (strNow.charAt(2) == '仟'))
                        strBig = strNum + strDW + "零"
                                + strBig.substring(1, strBig.length());
                    // 亿位不为零且万全为零不存在仟位时，去掉万
                    else if ((strNow.charAt(0) == '万') && (strNow.charAt(2) != '仟'))
                        strBig = strNum + strDW + strBig.substring(1, strBig.length());
                    // 其他正常情况
                    else
                        strBig = strNum + strDW + strBig;
                }
                // 拾亿－仟亿中一位为零且其前一位（亿以上）不为零的情况时补零
                else if ((i < 15) && (i > 11) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) != '零') && (strNow.charAt(0) != '亿'))
                    strBig = "零" + strBig;
                // 拾亿－仟亿中一位为零且其前一位（亿以上）也为零的情况时跨过
                else if ((i < 15) && (i > 11) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '亿')) {
                }
                // 拾亿－仟亿中一位为零且其前一位为亿位且为零的情况时跨过
                else if ((i < 15) && (i > 11) && (strFen.charAt(lenIntFen - 1) == '0')
                        && (strNow.charAt(0) == '零')) {
                }
                // 亿位为零且不存在仟万位和十亿以上时去掉上次写入的零
                else if ((i < 15) && (i > 11) && (strFen.charAt(lenIntFen - 1) != '0')
                        && (strNow.charAt(0) == '零') && (strNow.charAt(1) == '亿')
                        && (strNow.charAt(3) != '仟'))
                    strBig = strNum + strDW + strBig.substring(1, strBig.length());
                // 亿位为零且存在仟万位和十亿以上时，在亿仟万间补零
                else if ((i < 15) && (i > 11) && (strFen.charAt(lenIntFen - 1) != '0')
                        && (strNow.charAt(0) == '零') && (strNow.charAt(1) == '亿')
                        && (strNow.charAt(3) == '仟'))
                    strBig = strNum + strDW + "亿零" + strBig.substring(2, strBig.length());
                else
                    strBig = strNum + strDW + strBig;
                strFen = strFen.substring(0, lenIntFen - 1);
                lenIntFen--;
            }
            if (strBig.equals("整")) {
                strBig = "零元整";
            }
            if (flag == 1) {
                strBig = "负" + strBig;
            }
            return strBig;
        } catch (Exception e) {
            log.error("错误:", e);
            return "";
        }
    }

};
