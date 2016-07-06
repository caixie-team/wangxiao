sample
===
* 注释

	select #use("cols")# from edu_course_teacher where #use("condition")#

cols
===

	id,course_id,teacher_id

updateSample
===

	`id`=#id#,`course_id`=#courseId#,`teacher_id`=#teacherId#

condition
===

	1 = 1  
	@if(!isEmpty(courseId)){
	 and `course_id`=#courseId#
	@}
	@if(!isEmpty(teacherId)){
	 and `teacher_id`=#teacherId#
	@}
	

