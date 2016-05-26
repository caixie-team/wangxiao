package io.wangxiao.user.member.service;

import io.wangxiao.core.BaseService;
import io.wangxiao.core.Condition;
import io.wangxiao.user.member.dao.MemberTypeDao;
import io.wangxiao.user.member.entity.MemberType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bison on 1/6/16.
 *
 */
@Service
public class MemberTypeService extends BaseService<MemberType,MemberTypeDao>{

    public List<MemberType> findAll(){
        List<Condition> conditions = Arrays.asList(
                Condition.parseCondition("status.int.eq").setValue(0)
        );

        return baseDao.find(null, conditions);
    }
}
