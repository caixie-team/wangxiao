sample
===
* 注释

	select #use("cols")# from edu_course_subject where #use("condition")#

cols
===

	id,course_id,subject_id

updateSample
===

	`id`=#id#,`course_id`=#courseId#,`subject_id`=#subjectId#

condition
===

	1 = 1  
	@if(!isEmpty(courseId)){
	 and `course_id`=#courseId#
	@}
	@if(!isEmpty(subjectId)){
	 and `subject_id`=#subjectId#
	@}
	
