package com.atdld.os.exam.service.impl.snsapi;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.atdld.os.exam.service.snsapi.SnsApiService;

/**
 * sns接口实现
 *
 * @author :
 * @ClassName com.atdld.os.exam.service.impl.snsapi.SnsApiServiceImpl
 * @description
 * @Create Date : 2014-5-21 上午9:47:09
 */
@Service("snsApiService")
public class SnsApiServiceImpl implements SnsApiService {

    /**
     * 添加sns动态
     *
     * @return
     */
    public void addDynamic(Map<String, String> map) {
        //HttpUtil.doThreadPost(ExamConstants.apiserver+"/api/yzl/addlearn", map);
    }


}
