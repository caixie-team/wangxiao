<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>选课中心</title>
<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/course/course.js"></script>
<script type="text/javascript">
$(function (){
	//课程搜索内容放入上部搜索框
	$(".tscInp").val('${queryCourse.name}');
	
	var order = '${queryCourse.order}';
	if(order=='1'||order=='2'){
		$("#orderAid"+order).parent('li').find('a').removeClass('current');
		$("#orderAid"+order).addClass('current');
	}else{
		if(order=='3'){
			$("#orderAid3").addClass('current');
			$(".u-jg").css('background-position','-149px -270px');
		}else if(order=='4'){
			$("#orderAid3").addClass('current');
			$(".u-jg").css('background-position','-134px -270px');
		}
	};
	$(".s-c-list>li:nth-child(4n)").css({"margin-right" : "0px"});
	//sort suMore
	$(".select-box2-right .suMore").each(function() {
		var _this = $(this),
			_uList = _this.parent().siblings(".select-box2-mid"),
			_uLw = _uList.height();
		if (_uLw <= "40") {
			_this.hide();
		} else {
			_uList.css("height","40px");
			_this.click(function() {
				if(_this.html() == "更多↓") {
					_uList.css("height","auto");
					_this.text("收起↑");
				} else {
					_uList.css("height" , "40px");
					_this.text("更多↓");
				}
			})
		}
	});
});
</script>
<style type="text/css">
</style>
</head>
<body>
	<!-- /ads-1000x120 结束 -->
	<!-- /课程列表 -->
	<div>
		<section class="w1000">
			<div class="mt30 pr">
				<div class="pathwray">
					<ol class="clearfix c-master f-fM fsize14">
						<li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
						<li><span>公开课</span></li>
					</ol>
				</div>
				<section class="c-search pa">
					<div class=" clearfix">
						<section class="fr s-inp-wrap">
							<form id="searchForm" action="${ctx}/front/showPublicCourselist" method="post">
								<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
								<input type="hidden" id="hiddenMemberId" name="queryCourse.membertype" value="${queryCourse.membertype}"/>
								<input type="hidden" id="hiddenSubjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId}"/>
								<input type="hidden" id="hiddenTeacherIds" name="queryCourse.teacherId" value="${queryCourse.teacherId}"/> 
								<input type="hidden" id="hiddenorder" name="queryCourse.order" value="${queryCourse.order}"/>
								<input type="hidden" id="hiddencourseName" name="queryCourse.name" value="${queryCourse.name}"/>
							 </form>
						</section>
					</div>
				</section>
			</div>
			<div class="mt30">
				<section class="mb20">
                	   <div class="select">
                            <div class="select-box1">
                                <span><em class="icon16 all-c vam">&nbsp;</em><tt class="vam ml5 c-fff fsize16 f-fM mr10">课程分类</tt>-<tt class="vam ml5 c-fff fsize16 f-fM mr10">课程检索</tt></span>
                            </div>
                            <c:if test="${(queryCourse.subjectId!=null and queryCourse.subjectId!=0) || (queryCourse.teacherId!=null and queryCourse.teacherId!=0) || (queryCourse.membertype!=null and queryCourse.membertype!=0 and saleMap.sale.verifyMember=='ON') }">
	                            <div class="select-box2">
	                                <div class="select-box2-left">
	                                    <p class="btName">已选条件:</p>
	                                </div>
	                                <div class="select-box2-right">
                                		<c:if test="${queryCourse.subjectId!=null and queryCourse.subjectId!=0 }">
		                                	<ul class="f-list">
		                                    	<li><a title="${subject.subjectName}" href="javascript:void(0)"><span class="a-text1">按班型：</span><span class="a-text2">${subject.subjectName}</span><span class="a-img" onclick="clickSearch('subject',0)"></span></a></li>
		                                    </ul>
                                		</c:if>
                                		<c:if test="${queryCourse.teacherId!=null and queryCourse.teacherId!=0 }">
		                                    <ul class="f-list">
		                                    	<li><a title="${teacher.name}" href="javascript:void(0)"><span class="a-text1">按讲师：</span><span class="a-text2">${teacher.name}</span><span class="a-img" onclick="clickSearch('teacher',0)"></span></a></li>
		                                    </ul>
                                		</c:if>
                                		<c:if test="${queryCourse.membertype!=null and queryCourse.membertype!=0 and saleMap.sale.verifyMember=='ON'}">
		                                    <ul class="f-list">
		                                    	<li><a title="${memberType.title}" href="javascript:void(0)"><span class="a-text1">按会员：</span><span class="a-text2">${memberType.title}</span><span class="a-img" onclick="clickSearch('memberType',0)"></span></a></li>
		                                    </ul>
                                		</c:if>
	                                     <div class="select-box2-dele">
	                                        <span class="select-unfolt"><a title="全部撤销" onclick="clickSearch('clear',0)" href="javascript:void(0);">全部撤销</a></span>
	                                    </div>
	                                </div>
	                            </div>
                            </c:if>
                            <div class="select-box2">
                                <div class="select-box2-left">
                                    <p class="btName">按班型:</p>
                                </div>
                                <div class="select-box2-right">
                                    <ul class="select-box2-mid">
                                    	<c:forEach items="${subjectList }"  var ="subject">
                                        <li><a onclick="clickSearch('subject',${subject.subjectId})" title="${subject.subjectName }" href="javascript:void(0);">${subject.subjectName }</a></li>
                                    	</c:forEach>
                                    </ul>
                                    <div class="select-box2-RT">
                                        <a class="select-unfolt suMore" href="javascript:void(0)">更多↓</a>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${sonSubjectList!=null&&sonSubjectList.size()>0 }">
                            	<div class="select-box2">
	                                <div class="select-box2-left">
	                                    <p class="btName"></p>
	                                </div>
	                                <div class="select-box2-right">
	                                    <ul class="select-box2-mid">
	                                    	<c:forEach items="${sonSubjectList }"  var ="sonSubject">
	                                        <li><a onclick="clickSearch('subject',${sonSubject.subjectId})" title="${sonSubject.subjectName }" href="javascript:void(0);">${sonSubject.subjectName }</a></li>
	                                    	</c:forEach>
	                                    </ul>
	                                    <div class="select-box2-RT">
	                                        <a class="select-unfolt suMore" href="javascript:void(0)">更多↓</a>
	                                    </div>
	                                </div>
	                            </div>
                            </c:if>
                            <div class="select-box3">
                                <div class="select-box2-left">
                                    <p class="btName">按类型:</p>
                                </div>
                                <div class="select-box2-right">
                                    <ul class="select-box2-mid">
                                    	<c:forEach items="${teacherList }" var="teacher">
                                        <li><a onclick="clickSearch('teacher',${teacher.id})" title="${teacher.name }" href="javascript:void(0);"> ${ teacher.name} </a></li>
                                    	</c:forEach>
                                     </ul>
                                      <div class="select-box2-RT">
                                         <a class="select-unfolt suMore" href="javascript:void(0)">更多↓</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                </section>
				<section class="comm-title-2" style="box-shadow: 3px 3px 0 rgba(0,0,0,.05);">
					<div class="clearfix pl20 pr20">
						<section class="fr c-999">
							<span class="vam disIb mr20">
								<font class="c-666 fsize12">检索： 共搜索到<tt class="c-red ml5 mr5">${page.totalResultSize}</tt>条结果</font>
							</span>
							|
							<span class="vam disIb ml20">
								<tt class="vam c-666 fsize14"><font class="c-red">${page.currentPage}</font>/${page.totalPageSize}</tt>
								<tt class="vam u-d-page ml5">
									<a href="" title="上一页" class="shang">上一页</a>
									<a class="xia" href="" title="下一页">下一页</a>
								</tt>
							</span>
							
						</section>
						<script type="text/javascript">
								var str = "";
								if('${page.currentPage}'>=2){
								str +='goPage(${page.currentPage-1})';
								}else{
									str +='void(0)';
								}
								$(".shang").attr("href","javascript:"+str);
								str = "";
								if('${page.currentPage+1}'<='${page.totalPageSize}'){
									str+='goPage(${page.currentPage+1})';
								}else{
									str +='void(0)';
								}
								$(".xia").attr("href","javascript:"+str);
						</script>
						<section class="fl">
							<ul class="of sub-sort">
							 	<li><span class="c-999">排序：</span></li>
							 	<li class="sub-s-wrap">
							 		<a id="orderAid1" href="javascript:void(0)" onclick="orderByQuery(this,1)" title="关注度">关注度</a>
							 		<a id="orderAid2" href="javascript:void(0)" onclick="orderByQuery(this,2)" title="最新">最新</a>
							 	</li>
							 </ul>
						</section>
					</div>
				</section>
			</div>
			<div class="mb50">
				<c:if test="${publicCourseList.size()==0}">
				<section class="comm-tips-1">
					<p>
						<em class="vam c-tips-1">&nbsp;</em>
						<font class="c-999 fsize12 vam">对不起，此条件下还没有相关课程！</font>
					</p>
				</section>
				</c:if>
				<c:if test="${publicCourseList.size()>0}">
				<section id="SL-container">
					<div class="mt40">
						<ol class="s-c-list">
							<c:forEach items="${publicCourseList }" var="coursePage" varStatus="index">
							<li>
								<div class="pr s-c-pics" style="cursor: pointer;" >
                                    <a title="${coursePage.name}" href='${ctx}/front/couinfo/${coursePage.id}' target="_blank">
									<c:choose>
									<c:when test="${not empty coursePage.logo}">
									<img xSrc="<%=staticImageServer%>${coursePage.logo}" src="<%=imagesPath%>/static/edu/images/default/c-logo.gif" height="116" width="154" alt="${coursePage.name}">
									</c:when>
									<c:otherwise>
									<img xSrc="<%=imagesPath%>/static/edu/images/default/default_goods.jpg" src="<%=imagesPath%>/static/edu/images/default/c-logo.gif" height="116" width="154" alt="${courseOne.name}">
									</c:otherwise>
									</c:choose>
                                    </a>
									<div class="pa s-c-name">
										<a target="_blank" class="fsize14 c-fff" title="${coursePage.name}" href="${ctx}/front/couinfo/${coursePage.id}">${coursePage.name}</a>
									</div>
								</div>
								<section class="pl10 pr10 of">
									<div class="s-c-desc pt10 pb10">
										<p class="c-666">${coursePage.title}</p>
									</div>
									<div class="of mt10 mb20">
										<section class="fl w50pre">
											<div class="tac">
												<p class="c-999">课时：${coursePage.lessionnum}</p>
												<p class="mt10">
													<a class="gray-btn" title="开通课程" href="${ctx}/front/couinfo/${coursePage.id}"><tt class="vam c-333">开通课程</tt><em class="ml5 r-arrow icon16 vam">&nbsp;</em></a>	
												</p>
											</div>
										</section>
										<section class="fl w50pre">
											<div class="tac">
												<p class="c-999">价格<strong class="c-master">${coursePage.currentprice}￥</strong></p>
												<p class="mt10">
													<a href="javascript:void(0)" onclick="house(${coursePage.id})" title="收藏课程" class="c-orange">收藏课程</a>
												</p>
											</div>
										</section>
									</div>
								</section>
							</li>
							</c:forEach>
						</ol>
						<div class="clear"></div>
					</div>
				</section>
				<section>
					<div class="pagination pagination-large tac">
                  		<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
					</div>
				</section>
				</c:if>
			</div>
		</section>
	</div>
	<!-- /课程列表 结束 -->
	<script type="text/javascript" src="<%=imagesPath%>/static/common/scrollLoad.js"></script>
</body>
</html>
