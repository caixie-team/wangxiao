package co.bluepx.edu.course.service;

import co.bluepx.edu.core.BaseService;
import co.bluepx.edu.core.Condition;
import co.bluepx.edu.course.dao.SubjectDao;
import co.bluepx.edu.course.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bison on 1/3/16.
 */
@Service
public class SubjectService extends BaseService<Subject, SubjectDao> {

    /**
     * 查询第一层级专业
     *
     * @return
     */
    public List<Subject> findOneLevelSubject() {

        return this.find(
                null,
                Condition.parseCondition("status-parent_id.int.eq").setValue(0)
        );
    }


    /**
     * 查询全部专业信息
     *
     * @return
     */
    // TODO: 待修改为遍历查询
    public List<Subject> findSubjectTree() {

        List<Condition> conditions = Arrays.asList(
                Condition.parseCondition("status-parent_id.int.eq").setValue(0)
//                Condition.parseCondition("level_int_eq").setValue(1)
        );


        return baseDao.find(null, conditions);
    }
}
//    private void _querySubjectList(QueryCourse queryCourse, ModelAndView modelAndView){
//        查询条件专业,子专业
//        if (ObjectUtils.isNotNull(queryCourse.getSubjectId())) {
//            Subject subject = new Subject();
//            subject.setSubjectId(queryCourse.getSubjectId());
//            subject = subjectService.getSubjectBySubjectId(subject);
//            modelAndView.addObject("subject", subject);
//            查询子专业
//            List<Subject> sonSubjectList = null;
//            if (subject.getParentId() != 0) {//条件为二级专业
//                sonSubjectList = subjectService.getSubjectListByOne(subject.getParentId());
//            } else {//条件为一级专业
//                sonSubjectList = subjectService.getSubjectListByOne(subject.getSubjectId());
//            }
//            modelAndView.addObject("sonSubjectList", sonSubjectList);
//        }
//    }