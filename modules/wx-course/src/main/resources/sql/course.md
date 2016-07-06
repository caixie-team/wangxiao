sample
===
* 注释

	select #use("cols")# from edu_course where #use("condition")#

cols
===

	id,name,isavaliable,addTime,sourcePrice,currentPrice,title,context,total_lessionnum,lessionnum,coursetag,logo,update_time,losetype,lose_abs_time,lose_time,updateuser,page_buycount,page_viewcount,freeurl,sell_type,live_end_time,live_begin_time,is_pay,lecture_time,type_subject_id,company_sell_type,sys_user_id,class_type_id,show_type,video_password,teacher_video_url,isreserve,order,is_restrict,restrictnum,play_time,minutes

homeCourseCols
===
    		edu_course.id,
    		edu_course.name,
    		edu_course.isavaliable,
    		edu_course.addtime,
    		edu_course.is_pay,
    		edu_course.sourceprice,
    		edu_course.currentprice,
    		edu_course.title,
    		edu_course.lessionnum,
    		edu_course.coursetag,
    		edu_course.logo,
    		edu_course.update_time,
    		edu_course.losetype,
    		edu_course.lose_abs_time,
    		edu_course.lose_time,
    		edu_course.updateuser,
    		edu_course.page_buycount,
    		edu_course.page_viewcount,
    		edu_course.freeurl,
    		edu_course.sell_type,
    		edu_course.live_begin_time,
    		edu_course.live_end_time,
    		edu_website_course_detail.recommendId,
    		IFNULL(edu_course_profile.buycount,0) buycount,
    		IFNULL(edu_course_profile.viewcount,0) viewcount,
    		IFNULL(edu_course_profile.commentcount,0) commentcount,
    		IFNULL(edu_course_profile.questiongcount,0) questiongcount,
    		IFNULL(edu_course_profile.notecount,0) notecount,
    		IFNULL(edu_course_profile.playcount,0) playcount
    		
updateSample
===

	`id`=#id#,`name`=#name#,`isavaliable`=#isavaliable#,`addtime`=#addtime#,`sourceprice`=#sourceprice#,`currentprice`=#currentprice#,`title`=#title#,`context`=#context#,`total_lessionnum`=#totalLessionnum#,`lessionnum`=#lessionnum#,`coursetag`=#coursetag#,`logo`=#logo#,`update_time`=#updateTime#,`losetype`=#losetype#,`lose_abs_time`=#loseAbsTime#,`lose_time`=#loseTime#,`updateuser`=#updateuser#,`page_buycount`=#pageBuycount#,`page_viewcount`=#pageViewcount#,`freeurl`=#freeurl#,`sell_type`=#sellType#,`live_end_time`=#liveEndTime#,`live_begin_time`=#liveBeginTime#,`is_pay`=#isPay#,`lecture_time`=#lectureTime#,`type_subject_id`=#typeSubjectId#,`company_sell_type`=#companySellType#,`sys_user_id`=#sysUserId#,`class_type_id`=#classTypeId#,`show_type`=#showType#,`video_password`=#videoPassword#,`teacher_video_url`=#teacherVideoUrl#,`isreserve`=#isreserve#,`order`=#order#,`is_restrict`=#isRestrict#,`restrictnum`=#restrictnum#,`play_time`=#playTime#,`minutes`=#minutes#

findById
===
    select * from edu_course where `id`=#id#

* 根据推荐类别ID查询推荐课程
    
findRecommendCoursesById
===
    select #use("homeCourseCols")# from edu_website_course_detail join edu_course on edu_website_course_detail.course_id=edu_course.id left join edu_course_profile on edu_course.id=edu_course_profile.course_Id where edu_course.isavaliable=0
    @if(recommendId != 0){
      and edu_website_course_detail.recommendId=#recommendId#
    @}
    order by edu_website_course_detail.recommendId,edu_website_course_detail.orderNum desc, edu_website_course_detail.id

    
condition
===

	1 = 1  
	@if(!isEmpty(name)){
	 and `name`=#name#
	@}
	@if(!isEmpty(isavaliable)){
	 and `isavaliable`=#isavaliable#
	@}
	@if(!isEmpty(addtime)){
	 and `addtime`=#addtime#
	@}
	@if(!isEmpty(sourceprice)){
	 and `sourceprice`=#sourceprice#
	@}
	@if(!isEmpty(currentprice)){
	 and `currentprice`=#currentprice#
	@}
	@if(!isEmpty(title)){
	 and `title`=#title#
	@}
	@if(!isEmpty(context)){
	 and `context`=#context#
	@}
	@if(!isEmpty(totalLessionnum)){
	 and `total_lessionnum`=#totalLessionnum#
	@}
	@if(!isEmpty(lessionnum)){
	 and `lessionnum`=#lessionnum#
	@}
	@if(!isEmpty(coursetag)){
	 and `coursetag`=#coursetag#
	@}
	@if(!isEmpty(logo)){
	 and `logo`=#logo#
	@}
	@if(!isEmpty(updateTime)){
	 and `update_time`=#updateTime#
	@}
	@if(!isEmpty(losetype)){
	 and `losetype`=#losetype#
	@}
	@if(!isEmpty(loseAbsTime)){
	 and `lose_abs_time`=#loseAbsTime#
	@}
	@if(!isEmpty(loseTime)){
	 and `lose_time`=#loseTime#
	@}
	@if(!isEmpty(updateuser)){
	 and `updateuser`=#updateuser#
	@}
	@if(!isEmpty(pageBuycount)){
	 and `page_buycount`=#pageBuycount#
	@}
	@if(!isEmpty(pageViewcount)){
	 and `page_viewcount`=#pageViewcount#
	@}
	@if(!isEmpty(freeurl)){
	 and `freeurl`=#freeurl#
	@}
	@if(!isEmpty(sellType)){
	 and `sell_type`=#sellType#
	@}
	@if(!isEmpty(liveEndTime)){
	 and `live_end_time`=#liveEndTime#
	@}
	@if(!isEmpty(liveBeginTime)){
	 and `live_begin_time`=#liveBeginTime#
	@}
	@if(!isEmpty(isPay)){
	 and `is_pay`=#isPay#
	@}
	@if(!isEmpty(lectureTime)){
	 and `lecture_time`=#lectureTime#
	@}
	@if(!isEmpty(typeSubjectId)){
	 and `type_subject_id`=#typeSubjectId#
	@}
	@if(!isEmpty(companySellType)){
	 and `company_sell_type`=#companySellType#
	@}
	@if(!isEmpty(sysUserId)){
	 and `sys_user_id`=#sysUserId#
	@}
	@if(!isEmpty(classTypeId)){
	 and `class_type_id`=#classTypeId#
	@}
	@if(!isEmpty(showType)){
	 and `show_type`=#showType#
	@}
	@if(!isEmpty(videoPassword)){
	 and `video_password`=#videoPassword#
	@}
	@if(!isEmpty(teacherVideoUrl)){
	 and `teacher_video_url`=#teacherVideoUrl#
	@}
	@if(!isEmpty(isreserve)){
	 and `isreserve`=#isreserve#
	@}
	@if(!isEmpty(order)){
	 and `order`=#order#
	@}
	@if(!isEmpty(isRestrict)){
	 and `is_restrict`=#isRestrict#
	@}
	@if(!isEmpty(restrictnum)){
	 and `restrictnum`=#restrictnum#
	@}
	@if(!isEmpty(playTime)){
	 and `play_time`=#playTime#
	@}
	@if(!isEmpty(minutes)){
	 and `minutes`=#minutes#
	@}
	
