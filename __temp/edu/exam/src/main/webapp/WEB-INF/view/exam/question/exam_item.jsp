<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>考试系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/exam/images/exam-img/animate.css">
</head>
<body>
	<div class="animate-enter">
		<div class="ei-item-box of">
			<section class="w1000">
				<div class="mb50 pb50 pt50">
					<ul class="ei-item-list">
                        <c:forEach items="${professionalList}" var="pro">
                            <li>
                                <a href="javascript: void(0)" class="ei-i-a" professionalId="${pro.professionalId}" name="${pro.colour}"><span>${pro.professionalName}</span></a>
                            </li>
                        </c:forEach>
					</ul>
					<div class="clear"></div>
				</div>
			</section>
		</div>
		<!-- 二级分类弹出层 开始 -->
		<div class="ei-i-dialog pr">
			<a href="javascript: void(0)" class="ei-i-close" title="关闭"><img src="${ctx}/static/exam/images/exam-img/ei-i-close.png" width="45" height="45" /></a>
			<section class="w1000">
				<div class="mt50">
					<ol class="ei-i-sub-list subjecthtml">
					</ol>
					<div class="clear"></div>
				</div>
			</section>
		</div>
		<!-- 二级分类弹出层 结束 -->
	</div>
	<script type="text/javascript">
		$(function() {
			var wH = $(window).height(),
				_eiD = $(".ei-i-dialog"),
				_eiC = $(".ei-i-close");
			$(".ei-item-box").css("minHeight" , wH-276 + "px");
			_eiD.css({
				"opacity" : 0,
				"zIndex" : -1
			});
			$(".ei-item-list>li").each(function() {
				var _this = $(this),
					_oA = _this.find(".ei-i-a"),
					_bc = _oA.attr("name");
				_oA.css("backgroundColor" , _bc);
				_oA.click(function() {
                    var id = $(this).attr("professionalId");
                    $(".subjecthtml").html("");

                    $.ajax({
                        type:"POST",
                        dataType:"json",
                        url:"${ctx}/quest/querySubject",
                        data:{"id":id},
                        async:false,
                        success:function(result){
                            if(result.success){
                                var str = "";
                                var examSubjectList = result.entity;
                                $(".subjecthtml").html("");
                                for(var i=0;i<examSubjectList.length;i++){
                                    var examSubject = examSubjectList[i];
                                    str +='<li>';
                                    str +='<a href="/subj/addSubjectCookies?subject.subjectId='+examSubject.subjectId+'">';
                                    str +='<p class="fsize18 mt20">'+examSubject.subjectName+'</p>';
                                    str +='<p class="fsize14 mt10">共'+examSubject.qstNum+'道</p>';
                                    str +='</a>';
                                    str +='</li>';
                                }
                                $(".subjecthtml").html(str);
                            }
                        }
                    });

					if(navigator.userAgent.indexOf("MSIE")>0) {
						_eiD.css({
							"backgroundColor" : _bc
						});
						_eiD.animate({
							"opacity" : "1",
							"zIndex" : "999999"
						}, 200);
					} else {
						_eiD.removeClass("animated lightSpeedOut").css({
							"backgroundColor" : _bc,
							"opacity" : "1",
							"zIndex" : "999999"
						}).addClass("animated lightSpeedIn");
					}
				});
				_eiC.click(function() {
					if(navigator.userAgent.indexOf("MSIE")>0) { 
						_eiD.animate({
							"opacity" : "0",
							"zIndex" : "-1"
						}, 200);
					} else {
						_eiD.removeClass("animated lightSpeedIn").addClass("animated lightSpeedOut");
					}
				})
			})
		})
	</script>
</body>
</html>