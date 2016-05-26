package io.wangxiao.edu.home.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.website.WebsiteCourseDetailDao;
import io.wangxiao.edu.home.entity.user.GroupMiddleCourse;
import io.wangxiao.edu.home.entity.website.WebsiteCourseDetail;
import io.wangxiao.edu.home.entity.website.WebsiteCourseDetailDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("websiteCourseDetailDao")
public class WebsiteCourseDetailDaoImpl extends GenericDaoImpl implements WebsiteCourseDetailDao {

    /**
     * 添加推荐课程
     *
     * @param websiteCourseDetail
     */
    public void addWebsiteCourseDetail(List<WebsiteCourseDetail> websiteCourseDetails) {
        this.insert("WebsiteCourseDetailMapper.createWebsiteCourseDetail", websiteCourseDetails);
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
        return this.queryForListPage("WebsiteCourseDetailMapper.queryWebsiteCourseDetailList", websiteCourseDetailDTO,
                page);
    }

    /**
     * 根据id删除推荐课程
     *
     * @param id
     * @return Long
     * @throws Exception
     */
    public void deleteWebsiteCourseDetail(Long id) {
        this.delete("WebsiteCourseDetailMapper.delWebsiteCourseDetailById", id);
    }

    /**
     * 查询单个推荐课程分类
     *
     * @param id
     * @return
     */
    public WebsiteCourseDetailDTO queryWebsiteCourseDetailById(Long id) {
        return this.selectOne("WebsiteCourseDetailMapper.getWebsiteCourseDetailDTOById", id);
    }

    /**
     * 更新推荐课程
     *
     * @param reSortId
     * @return Long
     */
    public void updateWebsiteCourseDetail(WebsiteCourseDetail websiteCourseDetail) {
        this.update("WebsiteCourseDetailMapper.updateWebsiteCourseDetail", websiteCourseDetail);
    }

    /**
     * @param id
     */
    public List<WebsiteCourseDetail> getWebsiteCourseDetails(Long id) {
        return this.selectList("WebsiteCourseDetailMapper.getWebsiteCourseDetails", id);
    }

    /**
     * web推荐课程集合
     *
     * @param id
     */
    public List<WebsiteCourseDetailDTO> getWebWebsiteCourseDetails() {
        return this.selectList("WebsiteCourseDetailMapper.getWebWebsiteCourseDetails", 0);
    }

    /**
     * 根据条件删除推荐课程
     */
    public void deleteWebsiteCourseDetail(WebsiteCourseDetailDTO websiteCourseDetailDTO) {
        this.delete("WebsiteCourseDetailMapper.deleteWebsiteCourseDetail", websiteCourseDetailDTO);
    }

    /**
     * 添加学员组课程
     */
    public void addGroupMiddleCourseList(List<GroupMiddleCourse> GroupMiddleCourseList) {
        this.insert("UserGroupMiddleMapper.createUserGroupCourse", GroupMiddleCourseList);
    }

    /**
     * 删除学员组课程
     */
    public void delGroupMiddleCourseList(Long courseId) {
        this.delete("UserGroupMiddleMapper.deleteCourseGroupMiddle", courseId);
    }

    /**
     * 获取课程下的学员组
     */
    public List<GroupMiddleCourse> getGroupMiddleCourse(GroupMiddleCourse groupMiddleCourse) {
        return this.selectList("UserGroupMiddleMapper.getUserGroupCourse", groupMiddleCourse);
    }

    @Override
    public void deleteGroupCourse(GroupMiddleCourse GroupMiddleCourse) {
        this.insert("UserGroupMiddleMapper.deleteGroupCourse", GroupMiddleCourse);
    }

    @Override
    public void deleteWebsiteCourseDetails(String ids) {
        // TODO Auto-generated method stub
        this.delete("UserGroupMiddleMapper.deleteCourseGroupMiddles", ids.replaceAll(" ", "").split(","));

    }
}
