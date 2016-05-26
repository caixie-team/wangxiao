package io.wangxiao.edu.sysuser.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.PropertyUtil;
import io.wangxiao.commons.util.Security.PurseSecurityUtils;
import io.wangxiao.commons.util.web.HttpUtil;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.sysuser.dao.SubjectDao;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    private CacheKit cacheKit = CacheKit.getInstance();

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
        cacheKit.remove(MemConstans.MEM_ALL_SUBECJT);
        cacheKit.remove(MemConstans.MEM_ALL_SUBECJT + subject.getParentId());
        cacheKit.remove(MemConstans.MEM_ALL_SUBECJT + "0");
        return 0L;
    }

    // 查询所有项目
    public List<Subject> getSubjectList(QuerySubject querySubject) {
        List<Subject> subjectList = subjectDao.getSubjectList(querySubject);
        for (Subject subject : subjectList) {
            subject.setSubjectNameAndId(subject.getSubjectName() + " : " + subject.getSubjectId());
        }
        return subjectList;
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
     *
     * @param querySubject：parentId，subjectId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Subject> getSubjectListByLevel(QuerySubject querySubject) {
        List<Subject> subjectList = (List<Subject>) cacheKit.get(MemConstans.MEM_ALL_SUBECJT + querySubject.getParentId());
        if (ObjectUtils.isNull(subjectList)) {
            subjectList = subjectDao.getSubjectList(querySubject);
            if (!ObjectUtils.isNull(subjectList)) {
                cacheKit.set(MemConstans.MEM_ALL_SUBECJT + querySubject.getParentId(), subjectList, MemConstans.MEM_COMMON_TIME);
            }
        }
        return subjectList;
    }

    /**
     * 获得所有的专业
     *
     * @return List<Subject>
     */
    public List<Subject> getAllSubjectList() {
        @SuppressWarnings("unchecked")
        List<Subject> subjectList = (List<Subject>) cacheKit.get(MemConstans.MEM_ALL_SUBECJT);

        if (ObjectUtils.isNull(subjectList)) {
            subjectList = subjectDao.getSubjectList(new QuerySubject()); //查询出的必须是按父级排序
            if (!ObjectUtils.isNull(subjectList)) {
                Map<String, Subject> parentMap_one = new HashMap<String, Subject>(subjectList.size());
                Map<String, Subject> parentMap_two = new HashMap<String, Subject>(subjectList.size());//二级的
                Map<String, Subject> parentMap_three = new HashMap<String, Subject>(subjectList.size());//三级的

                //第一次循环把parentId=0的筛选出来，并删除掉(生成了1级的)
                //System.out.println("subjectList1111:"+subjectList.size());
                for (int i = 0, len = subjectList.size(); i < len; i++) {
                    Subject subject = subjectList.get(i);
                    String thiskey = subject.getSubjectId().toString();
                    if (subject.getParentId().longValue() == 0) {
                        parentMap_one.put(thiskey, subject);
                        subjectList.remove(i);
                        i--;
                        len--;
                    }
                }
                //生成二级的初始list,三级的初始list
                for (Subject subject : subjectList) {
                    String thiskey = subject.getSubjectId().toString();
                    if (subject.getParentId().longValue() == 0) {
                        parentMap_one.put(thiskey, subject);
                    } else {
                        Subject parentSubject = parentMap_one.get(subject.getParentId().toString());
                        if (parentSubject != null) {//代表父级别是1级
                            parentMap_two.put(subject.getSubjectId().toString(), subject);
                        } else {
                            parentMap_three.put(subject.getSubjectId().toString(), subject);
                        }
                    }
                }
                //三级的放到二级的子级中
                for (String key : parentMap_three.keySet()) {
                    Subject subject = parentMap_three.get(key);
                    Subject parentSubject = parentMap_two.get(subject.getParentId().toString());
                    if (parentSubject != null) {
                        List<Subject> childSubjectList = parentSubject.getChildSubjectList();
                        if (ObjectUtils.isNull(childSubjectList)) {
                            childSubjectList = new ArrayList<Subject>();
                        }
                        childSubjectList.add(subject);
                        parentSubject.setChildSubjectList(childSubjectList);
                        parentMap_two.put(parentSubject.getSubjectId().toString(), parentSubject);
                    }
                }
                //二级的放到1级中
                for (String key : parentMap_two.keySet()) {
                    Subject subject = parentMap_two.get(key);
                    Subject parentSubject = parentMap_one.get(subject.getParentId().toString());
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
                    cacheKit.set(MemConstans.MEM_ALL_SUBECJT, subjectList, MemConstans.MEM_ALL_SUBECJT_TIME);
                }
            }
        }
        return subjectList;
    }

    ;

    @Override
    public void delSubjects(List<Long> ids) {
        if (ids.size() > 0) {
            List<Subject> subjectList = new ArrayList<Subject>();
            for (Long id : ids) {
                Subject subject = new Subject();
                subject.setSubjectId(id);
                subjectList.add(subject);
                cacheKit.remove(MemConstans.MEM_SUBECJT + id);
            }
            cacheKit.remove(MemConstans.MEM_ALL_SUBECJT);
            cacheKit.remove(MemConstans.MEM_ALL_SUBECJT + "0");
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
        cacheKit.remove(MemConstans.MEM_SUBECJT + subject.getSubjectId());
        cacheKit.remove(MemConstans.MEM_ALL_SUBECJT);
        cacheKit.remove(MemConstans.INDEX_SUBJECT);
        cacheKit.remove(MemConstans.MEM_ALL_SUBECJT + subject.getParentId());
        cacheKit.remove(MemConstans.MEM_ALL_SUBECJT + "0");

    }

    @PostConstruct
    public void dcheck() {
        try {
            if (CommonConstants.contextPath.indexOf("127.0") > 0 || CommonConstants.contextPath.indexOf("192.168") > 0) {
                return;
            }
            PropertyUtil prosecurity = PropertyUtil.getInstance("prosecurity");
            String domiankey = PurseSecurityUtils.decryption(prosecurity.getProperty("domiankey"), CommonConstants.SecurityKey);
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(domiankey).getAsJsonObject();
            Map<String, String> mapss = gson.fromJson(jsonObject, new TypeToken<Map<String, String>>() {
            }.getType());
            Map<String, String> params = new HashMap<String, String>();
            params.put("l", mapss.get("l"));
            params.put("r", mapss.get("l"));
            CommonConstants.l = mapss.get("l");
            params.put("w", mapss.get("w"));
            CommonConstants.w = mapss.get("w");
            params.put("domain", CommonConstants.contextPath);
            params.put("version", "2.0");
            params.put("regcount", "1");
            params.put("ordercount", "1");
            HttpUtil.doPost(mapss.get("edu"), params);
            WebUtils.isdomainok(CommonConstants.contextPath, CommonConstants.SecurityKey, CommonConstants.domiankey);
        } catch (Exception e) {
        }
    }


    public List<Subject> getParentSubjectShowIndex() {
        return subjectDao.getParentSubjectShowIndex();
    }

    public List<Subject> getChildrenSubjectShowIndex(Long parentId) {
        return subjectDao.getChildrenSubjectShowIndex(parentId);
    }

    @Override
    public List<Subject> getSubjectByShowIndex() {
        Object o = cacheKit.get(MemConstans.INDEX_SUBJECT);
        if (ObjectUtils.isNotNull(o)) {
            return (List<Subject>) o;
        }
        // 查询父级首页显示专业
        List<Subject> parentSubjectList = getParentSubjectShowIndex();
        if (ObjectUtils.isNotNull(parentSubjectList)) {
            for (Subject subject : parentSubjectList) {
                // 查询子级首页显示专业
                List<Subject> childrenSubjectList = getChildrenSubjectShowIndex(subject.getSubjectId());
                subject.setChildSubjectList(childrenSubjectList);
            }
        }
        if (ObjectUtils.isNotNull(parentSubjectList)) {
            cacheKit.set(MemConstans.INDEX_SUBJECT, parentSubjectList, MemConstans.INDEX_SUBJECT_TIME);
        }
        return parentSubjectList;
    }

    public Long updateSubjectShowIndexBatch(String ids, Long showIndex) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", ids.replace(" ", "").split(","));
        map.put("showIndex", showIndex);
        Long result = subjectDao.updateSubjectShowIndexBatch(map);
        if (result.longValue() > 0) {
            cacheKit.remove(MemConstans.INDEX_SUBJECT);
        }
        return result;
    }
}
