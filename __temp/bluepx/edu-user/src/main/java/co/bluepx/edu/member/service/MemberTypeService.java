package co.bluepx.edu.member.service;

import co.bluepx.edu.core.BaseService;
import co.bluepx.edu.core.Condition;
import co.bluepx.edu.member.dao.MemberTypeDao;
import co.bluepx.edu.member.entity.MemberType;
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
