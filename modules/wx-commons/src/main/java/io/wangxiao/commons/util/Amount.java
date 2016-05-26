package io.wangxiao.commons.util;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName Amount
 * @description 金额精度处理，四舍五入等
 */
public class Amount implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7701994538764594242L;
    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 2;

    // 这个类不能实例化

    private Amount() {

    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */

    public static double add(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.add(b2).doubleValue();

    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */

    public static double sub(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.subtract(b2).doubleValue();

    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */

    public static double mul(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.multiply(b2).doubleValue();

    }

    /**
     * 乘法取整
     *
     * @param v1
     * @param v2
     * @return
     */
    public static int mulByInt(double v1, int v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.multiply(b2).intValue();

    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * <p/>
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */

    public static double div(double v1, double v2) {

        return div(v1, v2, DEF_DIV_SCALE);

    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * <p/>
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */

    public static double div(double v1, double v2, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException(

                    "The scale must be a positive integer or zero");

        }

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */

    public static double round(double v, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException(

                    "The scale must be a positive integer or zero");

        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * @param v     需要截断的数字
     * @param scale 小数点后保留几位
     * @return 截断的结果
     */
    public static double cutOff(double v, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException(

                    "The scale must be a positive integer or zero");

        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * if a==b return true, else return false;
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equal(double a, double b) {
        if ((a - b > -0.001) && (a - b) < 0.001) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * if a>＝b return true, else return false;
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean compare(double a, double b) {
        if (a - b > -0.001)
            return true;
        else
            return false;
    }

    /**
     * if a>b return true, else return false;
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean bigger(double a, double b) {
        if (a - b > 0.001)
            return true;
        else
            return false;
    }

}
