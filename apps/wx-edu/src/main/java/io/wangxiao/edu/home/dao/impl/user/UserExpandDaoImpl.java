package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.dao.user.UserExpandDao;
import io.wangxiao.edu.home.entity.user.UserExpand;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userExpandDao")
public class UserExpandDaoImpl extends GenericDaoImpl implements UserExpandDao {
    public Long addUserExpand(UserExpand userExpand) {
        if (StringUtils.isNotEmpty(userExpand.getRegisterFrom())) {
            //存入 redis 注册
            //StatisticsUtil.incrActionOneDayOrActionCount(MemConstans.REGISTER_NUM, userExpand.getRegisterFrom());
        }
        return this.insert("UserExpandMapper.createUserExpand", userExpand);
    }

    public void deleteUserExpandById(Long id) {
        this.delete("UserExpandMapper.deleteUserExpandById", id);
    }

    public void updateUserExpand(UserExpand userExpand) {
        this.update("UserExpandMapper.updateUserExpand", userExpand);
    }

    public void updateUserExpandFromUser(UserExpand userExpand) {
        this.update("UserExpandMapper.updateUserExpandFromUser", userExpand);
    }

    /**
     * 修改UserExpand的上传头像
     */
    public void updateAvatarById(UserExpand userExpand) {
        this.update("UserExpandMapper.updateAvatarById", userExpand);
    }

    /**
     * 通过userId修改UserExpand的subjectId
     */
    public void updateUserExpandForSubject(UserExpand userExpand) {
        this.update("UserExpandMapper.updateUserExpandForSubject", userExpand);
    }

    public UserExpand getUserExpandByUserId(Long userId) {
        return this.selectOne("UserExpandMapper.getUserExpandByUserId", userId);
    }

    public List<UserExpand> getUserExpandList(UserExpand userExpand) {
        return this.selectList("UserExpandMapper.getUserExpandList", userExpand);
    }


    /**
     * ①单条增加 返回主键(可以不返回)
     *
     * @param customer 用户实体
     * @return
     */
    @Override
    public Long addOneUser(UserExpand customer) {
        return new Long(this.insert("UserExpandMapper.addOneUser", customer));
    }

    /**
     * ⑤查询单个实体
     *
     * @param cusId 用户实体 传入cusId
     * @return Customer 查出的用户实体
     */
    @Override
    public UserExpandDto queryUserByid(Long cusId) {
        List<UserExpandDto> customerList = this.selectList("UserExpandMapper.queryUserById", cusId);// 通过cusId查询单个实体
        if (customerList != null && customerList.size() > 0) {
            UserExpandDto expandDto = customerList.get(0);
            expandDto.setShowname(expandDto.getShowname());
            return expandDto;
        }
        return null;
    }


    /**
     * 通过showname 查询customer
     *
     * @param showName
     */
    public List<UserExpandDto> queryCustomerByShowName(String showName, int size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("showName", showName);
        map.put("size", size);
        return this.selectList("UserExpandMapper.queryCustomerByShowName", map);
    }


    /**
     * 通过集合cusId 查询customer 返回map
     *
     * @param cusIds
     * @return
     * @throws Exception
     */
    public List<UserExpandDto> queryUsersByIds(List<Long> cusIds) throws Exception {
        List<UserExpandDto> list = this.selectList("UserExpandMapper.queryCustomerInCusIds", cusIds);
        return setShowName(list);
    }


    /**
     * 查询全部好友
     *
     * @param customer 好友实体
     * @param page     分页参数
     * @return List<QueryCustomer>
     * @throws Exception
     */
    public List<UserExpandDto> queryAllCustomer(UserExpandDto customer, PageEntity page) {
        List<UserExpandDto> list = this.queryForListPage("UserExpandMapper.queryAllCustomerPage", customer, page);// 查询我的好友
        return setShowName(list);
    }

    /**
     * 通过标识更新未读数加一
     */
    public void updateUnReadMsgNumAddOne(String falg, Long cusId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("falg", falg);
        map.put("cusId", cusId);
        this.update("UserExpandMapper.updateUnReadMsgNumAddOne", map);
    }

    /**
     * 通过标识更新未读数清零
     */
    public void updateUnReadMsgNumReset(String falg, Long cusId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("falg", falg);
        map.put("cusId", cusId);
        this.update("UserExpandMapper.updateUnReadMsgNumReset", map);
    }

    /**
     * 设置showname
     *
     * @param list
     * @return
     */
    public List<UserExpandDto> setShowName(List<UserExpandDto> list) {
        if (ObjectUtils.isNotNull(list)) {
            for (UserExpandDto expandDto : list) {
                expandDto.setShowname(expandDto.getShowname());
            }
        }
        return list;
    }

    /**
     * 更新用户的上传统计系统消息时间
     */
    public void updateCusForLST(Long cusId, Date date) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cusId", cusId);
        map.put("date", date);
        this.update("UserExpandMapper.updateCusForLST", map);
    }

    /**
     * 要更新的数量类型
     * 修改UserExpand浏览次数
     *
     * @param type
     * @param userId
     * @param count
     * @param operation
     */
    public void updateUserExpandCount(String type, Long userId, Long count, String operation) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        map.put("userId", userId);
        map.put("count", count);
        map.put("operation", operation);
        this.update("UserExpandMapper.updateUserExpandCount", map);
    }

    /**
     * 通过showname 查询customer(精确搜索)
     */
    public List<UserExpandDto> queryCustomerByShowNameEquals(String showName) {
        return this.selectList("UserExpandMapper.queryCustomerByShowNameEquals", showName);
    }

    /**
     * 更新个人中心用户封面
     *
     * @param userId
     */
    public void updateUserExpandBannerUrl(Long userId, String bannerUrl) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("bannerUrl", bannerUrl);
        this.update("UserExpandMapper.updateUserExpandBannerUrl", map);
    }

    @Override
    public List<UserExpandDto> queryUserExpandDtoPage(UserExpandDto userExpandDto, PageEntity page) {
        return this.queryForListPage("UserExpandMapper.queryUserExpandDtoPage", userExpandDto, page);
    }
}
