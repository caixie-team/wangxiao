package com.atdld.os.core.service.gain;

/**
 * 
 * @ClassName com.supergenius.sns.service.common.GuidGeneratorService
 * @description
 * @author :
 * @Create Date : 2013-12-18 下午7:37:44
 */
public interface GuidGeneratorService {
    /**
     * 根据关键字获得唯一值
     * 
     * @param type
     * @return
     */
    public String getGuid(String type);

    /**
     * 根据关键字默认的GUID获得唯一值 GUID+ip+date 00000000 GB
     * 
     * @param type
     * @return
     */
    public String getGuid();

    /**
     * 根据关键字获得唯一值
     * 
     * @param prefix
     * @param append 是否把prefix一起返回
     * @return
     */
    public String gainCode(String prefix,boolean append);

    /**
     * 根据关键字和补充长度
     * 
     * @param prefix
     *            关键字
     * @param length
     *            补足长度补0
      * @param append 是否把prefix一起返回
     * @return
     */
    public String gainCode(String prefix, int length,boolean append);

    /**
     * 获得群组的ID
     * 
     * @param prefix
     *            前缀
     * @param length
     *            最小默认长度，不足时前面补充0
     * @return
     */
    public String gainDisCode(String prefix, int length);

}
