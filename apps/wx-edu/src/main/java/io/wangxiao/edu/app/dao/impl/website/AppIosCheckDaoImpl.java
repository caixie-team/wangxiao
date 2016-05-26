package io.wangxiao.edu.app.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.app.dao.website.AppIosCheckDao;
import io.wangxiao.edu.app.entity.website.AppIosCheck;
import org.springframework.stereotype.Repository;

/**
 * AppIosCheck 管理类
 */
@Repository("appIosCheckDao")
public class AppIosCheckDaoImpl extends GenericDaoImpl implements AppIosCheckDao {

    /**
     * 添加AppIosCheck
     */
    public void saveIOSReceipt(AppIosCheck appIosCheck) {
        this.insert("AppIosCheckMapper.createAppIosCheck", appIosCheck);
    }

    /**
     * 根据密文查询单个AppIosCheck对象
     *
     * @param md5_receipt 查询条件
     * @return
     */
    public AppIosCheck isExistsIOSReceipt(String md5Receipt) {
        return this.selectOne("AppIosCheckMapper.getAppCourseByMd5Receipt", md5Receipt);
    }

}
