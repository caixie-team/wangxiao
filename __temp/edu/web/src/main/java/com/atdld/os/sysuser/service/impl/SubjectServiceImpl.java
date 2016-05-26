package com.atdld.os.sysuser.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sysuser.dao.SubjectDao;
import com.atdld.os.sysuser.entity.QuerySubject;
import com.atdld.os.sysuser.entity.Subject;
import com.atdld.os.sysuser.service.SubjectService;

/**
 * @author :
 * @ClassName com.atdld.os.sysuser.service.impl.SubjectServiceImpl
 * @description
 * @Create Date : 2014年6月9日 上午10:32:04
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    private MemCache memCache = MemCache.getInstance();

    @Override
    public Long addOneSubject(Subject subject) {
        // 添加时，如果上级目录 没有子的项目。需要把父目录以前的考点，添加到本项目下.
        QuerySubject querySubject = new QuerySubject();
        querySubject.setParentId(subject.getParentId());
        // 查找是否有子项目
        List<Subject> list = subjectDao.getSubjectList(querySubject);
        if (list == null || list.size() == 0) {
            // 添加项目
            subjectDao.addOneSubject(subject);
        } else {// 以前已经有子项目直接添加
            subjectDao.addOneSubject(subject);
        }
        memCache.remove(MemConstans.MEM_ALL_SUBECJT);
        memCache.remove(MemConstans.MEM_ALL_SUBECJT+"0");
        return 0L;
    }

    // 查询所有项目
    public List<Subject> getSubjectList(QuerySubject querySubject) {
        return subjectDao.getSubjectList(querySubject);
    }

    /**
     * 查询所有一级项目
     *
     * @return
     */
    public List<Subject> getSubjectOneList() {
        return subjectDao.getSubjectOneList();
    }

    /**
     * 根据父级ID查找子项目集合
     */
    public List<Subject> getSubjectListByOne(Long subjectId) {

        return subjectDao.getSubjectListByOne(subjectId);
    }

    /**
     * 根据parentId，parentId查询
     * @param querySubject：parentId，subjectId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Subject> getSubjectListByLevel(QuerySubject querySubject) {
        List<Subject> subjectList = (List<Subject>) memCache.get(MemConstans.MEM_ALL_SUBECJT+querySubject.getParentId());
        if (ObjectUtils.isNull(subjectList)) {
            subjectList = subjectDao.getSubjectList(querySubject);
            if (!ObjectUtils.isNull(subjectList)) {
                memCache.set(MemConstans.MEM_ALL_SUBECJT+querySubject.getParentId(), subjectList, MemConstans.MEM_COMMON_TIME);
            }
        }
        return subjectList;
    }
    /**
     * 获得所有的专业
     * @return List<Subject>
     */
    public List<Subject> getAllSubjectList(){
        List<Subject> subjectList = (List<Subject>) memCache.get(MemConstans.MEM_ALL_SUBECJT);

        if (ObjectUtils.isNull(subjectList)) {
            subjectList = subjectDao.getSubjectList(new QuerySubject()); //查询出的必须是按父级排序
            if (!ObjectUtils.isNull(subjectList)) {
                Map<String, Subject> parentMap_one = new HashMap<String, Subject>(subjectList.size());
                Map<String, Subject> parentMap_two = new HashMap<String, Subject>(subjectList.size());//二级的
                Map<String, Subject> parentMap_three = new HashMap<String, Subject>(subjectList.size());//三级的

                //第一次循环把parentId=0的筛选出来，并删除掉(生成了1级的)
                //System.out.println("subjectList1111:"+subjectList.size());
                for (int i=0,len=subjectList.size();i<len;i++) {
                    Subject subject=  subjectList.get(i);
                    String thiskey = subject.getSubjectId().toString();
                    if (subject.getParentId().longValue() == 0) {
                        parentMap_one.put(thiskey, subject);
                        subjectList.remove(i);
                        i--;len--;
                    }
                }
                //生成二级的初始list,三级的初始list
                for (Subject subject : subjectList) {
                    String thiskey = subject.getSubjectId().toString();
                    if (subject.getParentId().longValue() == 0) {
                        parentMap_one.put(thiskey, subject);
                    } else {
                        Subject parentSubject = parentMap_one.get( subject.getParentId().toString());
                        if (parentSubject != null) {//代表父级别是1级
                            parentMap_two.put( subject.getSubjectId().toString(), subject);
                        } else {
                            parentMap_three.put(subject.getSubjectId().toString(), subject);
                        }
                    }
                }
                //三级的放到二级的子级中
                for (String key : parentMap_three.keySet()) {
                    Subject subject = parentMap_three.get(key);
                    Subject parentSubject = parentMap_two.get( subject.getParentId().toString());
                    if (parentSubject != null) {
                        List<Subject> childSubjectList = parentSubject.getChildSubjectList();
                        if (ObjectUtils.isNull(childSubjectList)) {
                            childSubjectList = new ArrayList<Subject>();
                        }
                        childSubjectList.add(subject);
                        parentSubject.setChildSubjectList(childSubjectList);
                        parentMap_two.put( parentSubject.getSubjectId().toString(), parentSubject);
                    }
                }
                //二级的放到1级中
                for (String key : parentMap_two.keySet()) {
                    Subject subject = parentMap_two.get(key);
                    Subject parentSubject = parentMap_one.get( subject.getParentId().toString());
                    if (parentSubject != null) {
                        List<Subject> childSubjectList = parentSubject.getChildSubjectList();
                        if (ObjectUtils.isNull(childSubjectList)) {
                            childSubjectList = new ArrayList<Subject>();
                        }
                        childSubjectList.add(subject);
                        parentSubject.setChildSubjectList(childSubjectList);
                        parentMap_one.put("" + parentSubject.getSubjectId(), parentSubject);
                    }
                }
                subjectList.clear();
                subjectList.addAll(parentMap_one.values());
                if (!ObjectUtils.isNull(subjectList)) {
                    memCache.set(MemConstans.MEM_ALL_SUBECJT, subjectList,MemConstans.MEM_ALL_SUBECJT_TIME);
                }
            }
        }
        return subjectList;
    };
    @Override
    public void delSubjects(List<Long> ids) {
        if (ids.size() > 0) {
            List<Subject> subjectList = new ArrayList<Subject>();
            for (Long id : ids) {
                Subject subject = new Subject();
                subject.setSubjectId(id);
                subjectList.add(subject);
                memCache.remove(MemConstans.MEM_SUBECJT+id);
            }
            memCache.remove(MemConstans.MEM_ALL_SUBECJT);
            memCache.remove(MemConstans.MEM_ALL_SUBECJT+"0");
            subjectDao.delSubjects(subjectList);

        }
    }

    /**
     * id查询项目
     */
    public Subject getSubjectBySubjectId(Subject subject) {
        return subjectDao.getSubjectBySubjectId(subject);
    }

    @Override
    public void updateSubjectBySubjectId(Subject subject) {
        subjectDao.updateSubjectBySubjectId(subject);
        memCache.remove(MemConstans.MEM_SUBECJT+subject.getSubjectId());
        memCache.remove(MemConstans.MEM_ALL_SUBECJT);
        memCache.remove(MemConstans.MEM_ALL_SUBECJT+"0");

    }

}
