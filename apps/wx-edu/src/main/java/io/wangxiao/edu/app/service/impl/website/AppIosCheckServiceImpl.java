package io.wangxiao.edu.app.service.impl.website;

import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.app.dao.website.AppIosCheckDao;
import io.wangxiao.edu.app.entity.website.AppIosCheck;
import io.wangxiao.edu.app.service.website.AppIosCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AppIosCheck 管理类
 */
@Service("appIosCheckService")
public class AppIosCheckServiceImpl implements AppIosCheckService {

    @Autowired
    private AppIosCheckDao appIosCheckDao;

    /**
     * 添加AppIosCheck
     */
    public void saveIOSReceipt(AppIosCheck appIosCheck) {
        appIosCheckDao.saveIOSReceipt(appIosCheck);
    }

    /**
     * 根据密文查询单个AppIosCheck对象
     *
     * @param md5Receipt 查询条件
     */
    public boolean isExistsIOSReceipt(String md5Receipt) {
        AppIosCheck appIosCheck = appIosCheckDao.isExistsIOSReceipt(md5Receipt);

        return ObjectUtils.isNotNull(appIosCheck);
    }

}
