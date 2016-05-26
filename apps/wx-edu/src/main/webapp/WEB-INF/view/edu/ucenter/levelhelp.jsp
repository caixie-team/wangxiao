<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"> 
<title>积分等级规则-中国在线教育平台第一品牌</title>
</head>
<body>
		<article class="u-m-c-w837 u-m-center">
				<section class="u-m-c-wrap">
					<section class="u-m-c-head">
						<ul class="fl u-m-c-h-txt">
							<li class="current"><a title="等级规则说明：" href="javascript:void(0)">等级规则说明：</a></li>
						</ul>
						<div class="clear"></div>
					</section>
					<!-- /u-m-c-head -->
					<section class="line1">
						<div class="pl20 pr15">
							<!-- <section class="mt40 pb20 line2">
								<h4 class="hLh30">
									<span class="c-333 fsize14">等级规则说明：</span>
								</h4>
							</section> -->
							<section class="mt30">
								<h5 class="c-333 fsize14"></h5>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab-integral mt10">
									<thead>
										<tr>
											<th width="20%">图标</th>
											<th width="20%">等级</th>
											<th width="30%">头衔</th>
											<th width="30%">所需经验</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${userLevelist}" var="level" varStatus="index">
										<tr align="center">
												<td><b class="u-grade" title="等级：1级" id="levelSpan" style="background-position: -${(index.index+1)*36}px 50%;">&nbsp;</b></td>
												<td>LV${level.level }</td>
												<td>${level.title}</td>
												<td>${level.exp}</td>
											</tr>
										
										</c:forEach>
										</tbody>
								</table>
								
							</section>
						</div>
					</section>
				</section>
			</article>
	<!-- /u-main end -->
	
</body>
</html>
