package io.wangxiao.edu.app.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.AppWebsiteCourseDetailDao;
import io.wangxiao.edu.app.entity.website.AppWebsiteCourseDetail;
import io.wangxiao.edu.app.entity.website.AppWebsiteCourseDetailDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * AppWebsiteCourseDetail
 */
@Repository("appWebsiteCourseDetailDao")
public class AppWebsiteCourseDetailDaoImpl extends GenericDaoImpl implements AppWebsiteCourseDetailDao {

    /**
     * 添加推荐课程
     */
    public void addWebsiteCourseDetail(List<AppWebsiteCourseDetail> appWebsiteCourseDetails) {
        this.insert("AppWebsiteCourseDetailMapper.createWebsiteCourseDetail", appWebsiteCourseDetails);
    }

    /**
     * 查询推荐课程列表
     */
    public List<AppWebsiteCourseDetailDTO> queryWebsiteCourseDetailList(AppWebsiteCourseDetailDTO websiteCourseDetailDTO, PageEntity page) {
        return this.queryForListPage("AppWebsiteCourseDetailMapper.queryWebsiteCourseDetailList", websiteCourseDetailDTO, page);
    }

    /**
     * 查询推荐课程列表
     *
     * @return
     */
    public List<AppWebsiteCourseDetailDTO> queryWebsiteCourseDetailList(AppWebsiteCourseDetail appWebsiteCourseDetail) {
        return this.selectList("AppWebsiteCourseDetailMapper.queryWebsiteCourseDetail", appWebsiteCourseDetail);
    }

    /**
     * 根据id删除推荐课程
     *
     * @param id
     * @return Long
     * @throws Exception
     */
    public void deleteWebsiteCourseDetail(Long id) {
        this.delete("AppWebsiteCourseDetailMapper.delWebsiteCourseDetailById", id);
    }

    /**
     * 查询单个推荐课程分类
     */
    public AppWebsiteCourseDetailDTO queryWebsiteCourseDetailById(Long id) {
        return this.selectOne("AppWebsiteCourseDetailMapper.getWebsiteCourseDetailDTOById", id);
    }

    /**
     * 更新推荐课程
     */
    public void updateWebsiteCourseDetail(AppWebsiteCourseDetail websiteCourseDetail) {
        this.update("AppWebsiteCourseDetailMapper.updateWebsiteCourseDetail", websiteCourseDetail);
    }

    /**
     * @param id
     */
    public List<AppWebsiteCourseDetail> getWebsiteCourseDetails(Long id) {
        return this.selectList("AppWebsiteCourseDetailMapper.getWebsiteCourseDetails", id);
    }

    /**
     * web推荐课程集合
     */
    public List<AppWebsiteCourseDetailDTO> getWebWebsiteCourseDetails(Map<String, Object> map) {
        return this.selectList("AppWebsiteCourseDetailMapper.getWebWebsiteCourseDetails", map);
    }

    /**
     * 根据条件删除推荐课程
     */
    public void deleteWebsiteCourseDetail(AppWebsiteCourseDetailDTO websiteCourseDetailDTO) {
        this.delete("AppWebsiteCourseDetailMapper.deleteWebsiteCourseDetail", websiteCourseDetailDTO);
    }
}
