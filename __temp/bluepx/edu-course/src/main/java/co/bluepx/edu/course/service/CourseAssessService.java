package co.bluepx.edu.course.service;

import co.bluepx.edu.core.BaseService;
import co.bluepx.edu.course.dao.CourseAssessDao;
import co.bluepx.edu.course.entity.CourseAssess;
import co.bluepx.edu.course.entity.QueryCourseAssess;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * CourseAssess管理接口
 */
@Service
public class CourseAssessService extends BaseService<CourseAssess, CourseAssessDao> {

    /**
     * 根据条件获取CourseAssess分页列表
     *
     * @param courseId 课程ID
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return List<QueryCourseAssess>
     */
    public PageInfo<QueryCourseAssess> findCourseAssessByPage(Long courseId, int pageNum, int pageSize) {
        Map map = new HashMap<>();
        map.put("courseId", courseId);
        return PageHelper
                .startPage(pageNum, pageSize)
                .doSelectPageInfo(
                        () -> baseDao.findCourseAssessList(map)
                );
    }

    /**
     * 如果课程为套餐课程，查询该套餐课程下所有课程的评论
     *
     * @return List<QueryCourseAccess>
     */
    public PageInfo<QueryCourseAssess> findPackageCourseAssessByPage(String courseIds, int pageNum, int pageSize) {
        return PageHelper
                .startPage(pageNum, pageSize)
                .doSelectPageInfo(
                        () -> baseDao.findPackageCourseAssessList(courseIds)
                );
    }
//	@Autowired
//	private UserExpandService userExpandService;
//	@Autowired
//	private CourseProfileService courseProfileService;
    /**k
     * 添加CourseAssess
     *
     * @param courseAssess
     *            要添加的CourseAssess
     * @return id
     */
//	public Long addCourseAssess(CourseAssess courseAssess) {
//		更新课程的笔记数量
//        courseProfileService.updateCourseProfile(CourseProfileType.commentcount.toString(), courseAssess.getCourseId(), 1L,CourseProfile.ADD);
//		return courseAssessDao.addCourseAssess(courseAssess);
//	}

    /**
     * 根据id删除一个CourseAssess
     *
     * @param id
     *            要删除的id
     */
//	public void deleteCourseAssessById(Long id) {
//		courseAssessDao.deleteCourseAssessById(id);
//	}

    /**
     * 修改CourseAssess
     *
     * @param courseAssess
     *            要修改的CourseAssess
     */
//	public void updateCourseAssess(CourseAssess courseAssess) {
//		courseAssessDao.updateCourseAssess(courseAssess);
//	}

    /**
     * 根据id获取单个CourseAssess对象
     *
     * @param id
     *            要查询的id
     * @return CourseAssess
     */
//	public QueryCourseAssess getCourseAssessById(Long id) {
//		return courseAssessDao.getCourseAssessById(id);
//	}

    /**
     * 根据条件获取CourseAssess列表
     *
     * @param courseAssess
     *            查询条件
     * @return List<CourseAssess>
     */
//	public List<CourseAssess> getCourseAssessList(CourseAssess courseAssess) {
//		return courseAssessDao.getCourseAssessList(courseAssess);
//	}

    /**
     * 根据条件获取CourseAssess列表
     *
     * @param courseAssess
     *            查询条件
     * @return List<CourseAssess>
     */
//	public List<QueryCourseAssess> getCourseAssessListPage(CourseAssess courseAssess, PageEntity page) {
//		return courseAssessDao.getCourseAssessListPage(courseAssess, page);
//	}
    /**
     * 后台查询课程评论列表
     *
     * @param queryCourseAssess
     * @param page
     * @return
     * @throws Exception
     */
//	public List<QueryCourseAssess> getAdminCourseAssessList(QueryCourseAssess queryCourseAssess, PageEntity page) throws Exception {
//		List<QueryCourseAssess> queryCourseAssessList=courseAssessDao.getAdminCourseAssessList(queryCourseAssess, page);
//		return queryCourseAssessList;
//	}
    /**
     * 删除课程评论
     *
     * @param courseAssessIds
     */
//	public void delCourseAssessBatch(String courseAssessIds) {
//		courseAssessDao.delCourseAssessBatch(courseAssessIds);
//	}

    /**
     * 如果课程为套餐课程，查询该套餐课程下所有课程的评论
     * @param courseAssess
     * @param page
     * @return
     */
//	public List<QueryCourseAssess> getAllCourseAssessListPage(CourseAssess courseAssess, PageEntity page){
//		return courseAssessDao.getAllCourseAssessListPage(courseAssess, page);
//	}
}