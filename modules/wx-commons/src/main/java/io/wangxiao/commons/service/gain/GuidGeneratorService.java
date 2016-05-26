package io.wangxiao.commons.service.gain;

public interface GuidGeneratorService {
    /**
     * 根据关键字获得唯一值
     *
     * @param type
     * @return
     */
    String getGuid(String type);

    /**
     * 根据关键字默认的GUID获得唯一值 GUID+ip+date 00000000 GB
     *
     * @return
     */
    String getGuid();

    /**
     * 根据关键字获得唯一值
     *
     * @param prefix
     * @param append 是否把prefix一起返回
     * @return
     */
    String gainCode(String prefix, boolean append);

    /**
     * 根据关键字和补充长度
     *
     * @param prefix 关键字
     * @param length 补足长度补0
     * @param append 是否把prefix一起返回
     * @return
     */
    String gainCode(String prefix, int length, boolean append);

    /**
     * 获得群组的ID
     *
     * @param prefix 前缀
     * @param length 最小默认长度，不足时前面补充0
     * @return
     */
    String gainDisCode(String prefix, int length);

}
