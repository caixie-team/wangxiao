package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.user.UserAccountOptRecordDao;
import io.wangxiao.edu.home.entity.user.UserAccountOptRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userAccountOptRecordDao")
public class UserAccountOptRecordDaoImpl extends GenericDaoImpl implements UserAccountOptRecordDao {
    /**
     * 添加UserAccountOptRecord
     *
     * @param userAccountOptRecord 要添加的UserAccountOptRecord
     * @return id
     */
    public Long addUserAccountOptRecord(UserAccountOptRecord userAccountOptRecord) {
        return this.insert("UserAccountOptRecordMapper.createUserAccountOptRecord", userAccountOptRecord);
    }

    /**
     * 根据id删除一个UserAccountOptRecord
     *
     * @param id 要删除的id
     */
    public void deleteUserAccountOptRecordById(Long id) {
        this.delete("UserAccountOptRecordMapper.deleteUserAccountOptRecordById", id);
    }

    /**
     * 修改UserAccountOptRecord
     *
     * @param userAccountOptRecord 要修改的UserAccountOptRecord
     */
    public void updateUserAccountOptRecord(UserAccountOptRecord userAccountOptRecord) {
        this.update("UserAccountOptRecordMapper.updateUserAccountOptRecord", userAccountOptRecord);
    }

    /**
     * 根据id获取单个UserAccountOptRecord对象
     *
     * @param id 要查询的id
     * @return UserAccountOptRecord
     */
    public UserAccountOptRecord getUserAccountOptRecordById(Long id) {
        return this.selectOne("UserAccountOptRecordMapper.getUserAccountOptRecordById", id);
    }

    /**
     * 根据条件获取UserAccountOptRecord列表
     *
     * @param userAccountOptRecord 查询条件
     * @return List<UserAccountOptRecord>
     */
    public List<UserAccountOptRecord> getUserAccountOptRecordList(UserAccountOptRecord userAccountOptRecord) {
        return this.selectList("UserAccountOptRecordMapper.getUserAccountOptRecordList", userAccountOptRecord);
    }
}
