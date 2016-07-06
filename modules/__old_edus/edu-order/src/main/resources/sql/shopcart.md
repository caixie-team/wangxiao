dtos
===

    select cart.*,course.* from edu_shopcart as cart left join edu_course as course on cart.goodsid = course.id where 
    cart.userid = #userId# and cart.type = #type#
    
shopcars
===
* 注释

	select #use("cols")# from edu_shopcart where #use("condition")#

cols
===

	id,goodsid,userid,type,add_time

updateSample
===

	`id`=#id#,`goodsid`=#goodsid#,`userid`=#userid#,`type`=#type#,`add_time`=#addTime#

condition
===

	1 = 1  
	@if(!isEmpty(goodsid)){
	 and `goodsid`=#goodsid#
	@}
	@if(!isEmpty(userid)){
	 and `userid`=#userid#
	@}
	@if(!isEmpty(type)){
	 and `type`=#type#
	@}
	@if(!isEmpty(add_time)){
	 and `add_time`=#add_time#
	@}