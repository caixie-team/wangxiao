dtos
===
   
    select *, subject.subject_name 
        from edu_library as lib
        left join sys_subject as subject on lib.subject_id=subject.subject_id
        where
        1 = 1
        @if(!isEmpty(name)){
            and lib.`name` like CONCAT('%',#name#,'%')
        @}
        @if(subjectId != null && subjectId > 0){
            and (lib.`subject_id`=#subjectId# or subject.`parent_Id`=#subjectId#)
        @}
        @if(orderNum !=null && orderNum == 1){
            order by lib.`broswe_num` DESC
        @}
        @if(orderNum == 0 || orderNum == 2 || orderNum == null){
            order by lib.`ID` DESC
        @}
    
commonsample
===
* 注释

	select #use("cols")# from edu_library where #use("condition")#

cols
===

	ID,subject_id,type,browse_num,intro,pdf_url,img_url,name,add_time

updateSample
===

	`id`=#id#,`subject_id`=#subjectId#,`type`=#type#,`browse_num`=#browseNum#,`intro`=#intro#,`pdf_url`=#pdfUrl#,`img_url`=#imgUrl#,`name`=#name#,`add_time`=#addTime#

condition
===

	1 = 1  
	@if(!isEmpty(subject_id)){
	 and `subject_id`=#subject_id#
	@}
	@if(!isEmpty(type)){
	 and `type`=#type#
	@}
	@if(!isEmpty(browse_num)){
	 and `browse_num`=#browse_num#
	@}
	@if(!isEmpty(intro)){
	 and `intro`=#intro#
	@}
	@if(!isEmpty(pdf_url)){
	 and `pdf_url`=#pdf_url#
	@}
	@if(!isEmpty(img_url)){
	 and `img_url`=#img_url#
	@}
	@if(!isEmpty(name)){
	 and `name`=#name#
	@}
	@if(!isEmpty(add_time)){
	 and `add_time`=#add_time#
	@}