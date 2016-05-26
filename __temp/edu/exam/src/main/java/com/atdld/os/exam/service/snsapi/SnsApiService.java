package com.atdld.os.exam.service.snsapi;

import java.util.Map;


/**
 * 考试调用sns接口
 *
 * @author :
 * @ClassName com.atdld.os.exam.service.snsapi.SnsApiService
 * @description
 * @Create Date : 2014-5-21 上午9:43:11
 */
public interface SnsApiService {
    /**
     * 添加sns动态
     *
     * @return
     */
    public void addDynamic(Map<String, String> map);

}
