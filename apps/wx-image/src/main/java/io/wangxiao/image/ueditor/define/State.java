package io.wangxiao.image.ueditor.define;


/**
 * 处理状态接口
 *
 */
public interface State {

    boolean isSuccess();

    void putInfo(String name, String val);

    void putInfo(String name, long val);

    String toJSONString();

}
