dtos
===

    select *, sys_subject.SUBJECT_NAME from edu_book left join sys_subject on edu_book.book_subjectid = sys_subject
    .SUBJECT_ID where edu_book.status=1
   	@if(!isEmpty(bookSubjectid)){
   	 and `book_subjectid`=#bookSubjectid#
   	@}
   	@if(!isEmpty(sujectIds)){
   	 and `book_subjectid` in (#sujectIds#)
  	@}
   	@if(!isEmpty(bookName)){
   	 and `book_name` like CONCAT('%',#bookName#,'%')
   	@}
   	@if(!isEmpty(disProperty)){
   	 and (`disProperty` like ('%#disProperty#%')
   	@}
   	@if(orderNum == 1){
   	 order by `book_id` DESC
   	@}
   	@if(orderNum == 3){
   	 order by `now_price` ASC
   	@}
   	@if(orderNum == 4){
   	 order by `now_price` DESC
   	@}			
   	@if(orderNum == 5 || isEmpty(orderNum)){
   	 order by `ADD_TIME` DESC
   	@}   	
   	

commonsample
===
* 注释

	select #use("cols")# from edu_book where #use("condition")#

cols
===

	book_id,price,now_price,rebate_price,book_name,book_type,book_img,book_smallimg,stock_num,author,press,publish_time,revision,print_num,page_num,word_num,book_package,pager,weight,isbn,remarks,book_info,directory,STATUS,ADD_TIME,SHOP_STATE,DROP_TIME,UP_TIME,VALIDITY_TIME,UPDATE_TIME,UPDATE_USER,book_subjectid,book_size,disProperty,book_pay_url

updateSample
===

	`book_id`=#book_id#,`price`=#price#,`now_price`=#now_price#,`rebate_price`=#rebate_price#,`book_name`=#book_name#,`book_type`=#book_type#,`book_img`=#book_img#,`book_smallimg`=#book_smallimg#,`stock_num`=#stock_num#,`author`=#author#,`press`=#press#,`publish_time`=#publish_time#,`revision`=#revision#,`print_num`=#print_num#,`page_num`=#page_num#,`word_num`=#word_num#,`book_package`=#book_package#,`pager`=#pager#,`weight`=#weight#,`isbn`=#isbn#,`remarks`=#remarks#,`book_info`=#book_info#,`directory`=#directory#,`STATUS`=#STATUS#,`ADD_TIME`=#ADD_TIME#,`SHOP_STATE`=#SHOP_STATE#,`DROP_TIME`=#DROP_TIME#,`UP_TIME`=#UP_TIME#,`VALIDITY_TIME`=#VALIDITY_TIME#,`UPDATE_TIME`=#UPDATE_TIME#,`UPDATE_USER`=#UPDATE_USER#,`book_subjectid`=#book_subjectid#,`book_size`=#book_size#,`disProperty`=#disProperty#,`book_pay_url`=#book_pay_url#

condition
===

	1 = 1  
	@if(!isEmpty(price)){
	 and `price`=#price#
	@}
	@if(!isEmpty(now_price)){
	 and `now_price`=#now_price#
	@}
	@if(!isEmpty(rebate_price)){
	 and `rebate_price`=#rebate_price#
	@}
	@if(!isEmpty(book_name)){
	 and `book_name`=#book_name#
	@}
	@if(!isEmpty(book_type)){
	 and `book_type`=#book_type#
	@}
	@if(!isEmpty(book_img)){
	 and `book_img`=#book_img#
	@}
	@if(!isEmpty(book_smallimg)){
	 and `book_smallimg`=#book_smallimg#
	@}
	@if(!isEmpty(stock_num)){
	 and `stock_num`=#stock_num#
	@}
	@if(!isEmpty(author)){
	 and `author`=#author#
	@}
	@if(!isEmpty(press)){
	 and `press`=#press#
	@}
	@if(!isEmpty(publish_time)){
	 and `publish_time`=#publish_time#
	@}
	@if(!isEmpty(revision)){
	 and `revision`=#revision#
	@}
	@if(!isEmpty(print_num)){
	 and `print_num`=#print_num#
	@}
	@if(!isEmpty(page_num)){
	 and `page_num`=#page_num#
	@}
	@if(!isEmpty(word_num)){
	 and `word_num`=#word_num#
	@}
	@if(!isEmpty(book_package)){
	 and `book_package`=#book_package#
	@}
	@if(!isEmpty(pager)){
	 and `pager`=#pager#
	@}
	@if(!isEmpty(weight)){
	 and `weight`=#weight#
	@}
	@if(!isEmpty(isbn)){
	 and `isbn`=#isbn#
	@}
	@if(!isEmpty(remarks)){
	 and `remarks`=#remarks#
	@}
	@if(!isEmpty(book_info)){
	 and `book_info`=#book_info#
	@}
	@if(!isEmpty(directory)){
	 and `directory`=#directory#
	@}
	@if(!isEmpty(STATUS)){
	 and `STATUS`=#STATUS#
	@}
	@if(!isEmpty(ADD_TIME)){
	 and `ADD_TIME`=#ADD_TIME#
	@}
	@if(!isEmpty(SHOP_STATE)){
	 and `SHOP_STATE`=#SHOP_STATE#
	@}
	@if(!isEmpty(DROP_TIME)){
	 and `DROP_TIME`=#DROP_TIME#
	@}
	@if(!isEmpty(UP_TIME)){
	 and `UP_TIME`=#UP_TIME#
	@}
	@if(!isEmpty(VALIDITY_TIME)){
	 and `VALIDITY_TIME`=#VALIDITY_TIME#
	@}
	@if(!isEmpty(UPDATE_TIME)){
	 and `UPDATE_TIME`=#UPDATE_TIME#
	@}
	@if(!isEmpty(UPDATE_USER)){
	 and `UPDATE_USER`=#UPDATE_USER#
	@}
	@if(!isEmpty(book_subjectid)){
	 and `book_subjectid`=#book_subjectid#
	@}
	@if(!isEmpty(book_size)){
	 and `book_size`=#book_size#
	@}
	@if(!isEmpty(disProperty)){
	 and `disProperty`=#disProperty#
	@}
	@if(!isEmpty(book_pay_url)){
	 and `book_pay_url`=#book_pay_url#
	@}