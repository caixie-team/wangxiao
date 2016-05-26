package io.wangxiao.edu.home.service.impl.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.dao.website.WebsiteCourseDetailDao;
import io.wangxiao.edu.home.entity.user.GroupMiddleCourse;
import io.wangxiao.edu.home.entity.website.WebsiteCourseDetail;
import io.wangxiao.edu.home.entity.website.WebsiteCourseDetailDTO;
import io.wangxiao.edu.home.service.website.WebsiteCourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("websiteCourseDetailService")
public class WebsiteCourseDetailServiceImpl implements WebsiteCourseDetailService {
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private WebsiteCourseDetailDao websiteCourseDetailDao;

    /**
     * 添加推荐课程
     *
     * @param websiteCourseDetail
     */
    public void addWebsiteCourseDetail(List<WebsiteCourseDetail> websiteCourseDetails) {
        websiteCourseDetailDao.addWebsiteCourseDetail(websiteCourseDetails);
        cacheKit.remove(MemConstans.RECOMMEND_COURSE);
    }

    /**
     * 查询推荐课程列表
     *
     * @param websiteCourseDetail
     * @param page
     * @return
     */
    public List<WebsiteCourseDetailDTO> queryWebsiteCourseDetailList(WebsiteCourseDetailDTO websiteCourseDetailDTO,
                                                                     PageEntity page) {
        return websiteCourseDetailDao.queryWebsiteCourseDetailList(websiteCourseDetailDTO, page);
    }

    /**
     * 根据id删除推荐课程
     *
     * @param id
     * @return Long
     * @throws Exception
     */
    public void deleteWebsiteCourseDetail(Long id) {
        websiteCourseDetailDao.deleteWebsiteCourseDetail(id);
        cacheKit.remove(MemConstans.RECOMMEND_COURSE);
    }

    /**
     * 查询单个推荐课程分类
     *
     * @param id
     * @return
     */
    public WebsiteCourseDetailDTO queryWebsiteCourseDetailById(Long id) {
        return websiteCourseDetailDao.queryWebsiteCourseDetailById(id);
    }

    /**
     * 更新推荐课程
     *
     * @param reSortId
     * @return Long
     */
    public void updateWebsiteCourseDetail(WebsiteCourseDetail websiteCourseDetail) {
        websiteCourseDetailDao.updateWebsiteCourseDetail(websiteCourseDetail);
        cacheKit.remove(MemConstans.RECOMMEND_COURSE);
    }

    /**
     * 分类查找推荐课程集合
     *
     * @param id
     */
    public List<WebsiteCourseDetail> getWebsiteCourseDetails(Long id) {
        return websiteCourseDetailDao.getWebsiteCourseDetails(id);
    }

    /**
     * web推荐课程集合
     *
     * @param id
     */
    public Map<String, Object> showWebsiteCourseDetails() {

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) cacheKit.get(MemConstans.RECOMMEND_COURSE);
        if (ObjectUtils.isNotNull(map)) {
            return map;
        }
        map = new HashMap<String, Object>();
        // 查询的结果必须以recommendId排序
        List<WebsiteCourseDetailDTO> websiteCourseDetails = websiteCourseDetailDao.getWebWebsiteCourseDetails();

        if (ObjectUtils.isNotNull(websiteCourseDetails)) {
            Long recommendId = websiteCourseDetails.get(0).getRecommendId();
            List<WebsiteCourseDetailDTO> list = new ArrayList<WebsiteCourseDetailDTO>();
            for (WebsiteCourseDetailDTO websiteCourseDetailDTO : websiteCourseDetails) {
                if (recommendId != websiteCourseDetailDTO.getRecommendId()) {
                    map.put(recommendId.toString(), list);
                    recommendId = websiteCourseDetailDTO.getRecommendId();
                    list = new ArrayList<WebsiteCourseDetailDTO>();
                    list.add(websiteCourseDetailDTO);
                } else {
                    list.add(websiteCourseDetailDTO);
                }
            }
            if (ObjectUtils.isNotNull(list)) {
                map.put(recommendId.toString(), list);
            }
        }
        if (ObjectUtils.isNotNull(map)) {
            cacheKit.set(MemConstans.RECOMMEND_COURSE, map, MemConstans.RECOMMEND_COURSE_TIME);
        }
        return map;
    }

    /**
     * 根据条件删除推荐课程
     */
    public void deleteWebsiteCourseDetail(WebsiteCourseDetailDTO websiteCourseDetailDTO) {
        websiteCourseDetailDao.deleteWebsiteCourseDetail(websiteCourseDetailDTO);
        cacheKit.remove(MemConstans.RECOMMEND_COURSE);
    }

    /**
     * 删除学员组课程
     */
    public void deleteGroupCourseList(Long courseId) {
        // TODO Auto-generated method stub
        websiteCourseDetailDao.delGroupMiddleCourseList(courseId);
    }

    /**
     * 添加学员组课程
     */
    public void addGroupCourseList(List<GroupMiddleCourse> groupMiddleCourseList) {
        websiteCourseDetailDao.addGroupMiddleCourseList(groupMiddleCourseList);
    }

    /**
     * 获取课程下的学员组
     */
    public List<GroupMiddleCourse> getGroupMiddleCourse(GroupMiddleCourse groupMiddleCourse) {
        return websiteCourseDetailDao.getGroupMiddleCourse(groupMiddleCourse);
    }

    public void deleteGroupCourse(GroupMiddleCourse groupMiddleCourse) {
        websiteCourseDetailDao.deleteGroupCourse(groupMiddleCourse);
    }
}