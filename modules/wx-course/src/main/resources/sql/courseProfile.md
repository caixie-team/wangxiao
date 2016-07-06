sample
===
* 注释

	select #use("cols")# from edu_course_profile where #use("condition")#

cols
===

	id,course_id,buycount,viewcount,commentcount,questiongcount,notecount,playcount,watchpersoncount

updateSample
===

	`id`=#id#,`course_id`=#courseId#,`buycount`=#buycount#,`viewcount`=#viewcount#,`commentcount`=#commentcount#,`questiongcount`=#questiongcount#,`notecount`=#notecount#,`playcount`=#playcount#,`watchpersoncount`=#watchpersoncount#

condition
===

	1 = 1  
	@if(!isEmpty(courseId)){
	 and `course_id`=#courseId#
	@}
	@if(!isEmpty(buycount)){
	 and `buycount`=#buycount#
	@}
	@if(!isEmpty(viewcount)){
	 and `viewcount`=#viewcount#
	@}
	@if(!isEmpty(commentcount)){
	 and `commentcount`=#commentcount#
	@}
	@if(!isEmpty(questiongcount)){
	 and `questiongcount`=#questiongcount#
	@}
	@if(!isEmpty(notecount)){
	 and `notecount`=#notecount#
	@}
	@if(!isEmpty(playcount)){
	 and `playcount`=#playcount#
	@}
	@if(!isEmpty(watchpersoncount)){
	 and `watchpersoncount`=#watchpersoncount#
	@}
	
