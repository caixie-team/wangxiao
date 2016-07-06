sample
===
* 注释

	select #use("cols")# from edu_course where #use("condition")#

cols
===

	id,name,isavaliable,addTime,sourcePrice,currentPrice,title,context,total_lessionnum,lessionnum,coursetag,logo,update_time,losetype,lose_abs_time,lose_time,updateuser,page_buycount,page_viewcount,freeurl,sell_type,live_end_time,live_begin_time,is_pay,lecture_time,type_subject_id,company_sell_type,sys_user_id,class_type_id,show_type,video_password,teacher_video_url,isreserve,order,is_restrict,restrictnum,play_time,minutes

updateSample
===

	`id`=#id#,`name`=#name#,`isavaliable`=#isavaliable#,`addtime`=#addtime#,`sourceprice`=#sourceprice#,`currentprice`=#currentprice#,`title`=#title#,`context`=#context#,`total_lessionnum`=#totalLessionnum#,`lessionnum`=#lessionnum#,`coursetag`=#coursetag#,`logo`=#logo#,`update_time`=#updateTime#,`losetype`=#losetype#,`lose_abs_time`=#loseAbsTime#,`lose_time`=#loseTime#,`updateuser`=#updateuser#,`page_buycount`=#pageBuycount#,`page_viewcount`=#pageViewcount#,`freeurl`=#freeurl#,`sell_type`=#sellType#,`live_end_time`=#liveEndTime#,`live_begin_time`=#liveBeginTime#,`is_pay`=#isPay#,`lecture_time`=#lectureTime#,`type_subject_id`=#typeSubjectId#,`company_sell_type`=#companySellType#,`sys_user_id`=#sysUserId#,`class_type_id`=#classTypeId#,`show_type`=#showType#,`video_password`=#videoPassword#,`teacher_video_url`=#teacherVideoUrl#,`isreserve`=#isreserve#,`order`=#order#,`is_restrict`=#isRestrict#,`restrictnum`=#restrictnum#,`play_time`=#playTime#,`minutes`=#minutes#

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
	
