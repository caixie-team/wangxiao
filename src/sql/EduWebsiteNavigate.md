sample
===
* 注释

	select #use("cols")# from edu_website_navigate where #use("condition")#

cols
===

	id,name,url,newPage,type,ordernum,status

updateSample
===

	`id`=#id#,`name`=#name#,`url`=#url#,`newpage`=#newpage#,`type`=#type#,`ordernum`=#ordernum#,`status`=#status#

condition
===

	1 = 1  
	@if(!isEmpty(name)){
	 and `name`=#name#
	@}
	@if(!isEmpty(url)){
	 and `url`=#url#
	@}
	@if(!isEmpty(newpage)){
	 and `newpage`=#newpage#
	@}
	@if(!isEmpty(type)){
	 and `type`=#type#
	@}
	@if(!isEmpty(ordernum)){
	 and `ordernum`=#ordernum#
	@}
	@if(!isEmpty(status)){
	 and `status`=#status#
	@}
	
