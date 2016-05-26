package io.wangxiao.edu.app.service.website;

import io.wangxiao.edu.app.entity.website.AppIosCheck;

/**
 * AppIosCheck 管理类
 */
public interface AppIosCheckService {
    /**
     * 添加
     *
     * @param appIosCheck
     */
    void saveIOSReceipt(AppIosCheck appIosCheck);

    /**
     * 根据密文查询单个AppIosCheck对象
     *
     * @param md5Receipt 查询条件
     * @return
     */
    boolean isExistsIOSReceipt(String md5Receipt);
}
