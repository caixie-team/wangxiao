* 根据推荐类别ID查询推荐课程
    
findRecommendCoursesById
===
    select #use("homeCourseCols")# from edu_website_course_detail join edu_course on edu_website_course_detail.course_id=edu_course.id left join edu_course_profile on edu_course.id=edu_course_profile.course_Id where edu_course.isavaliable=0
    @if(recommendId != 0){
      and edu_website_course_detail.recommendId=#recommendId#
    @}
    order by edu_website_course_detail.recommendId,edu_website_course_detail.orderNum desc, edu_website_course_detail.id