<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>问答</title>
<script type="text/javascript">
	$(function() {
		$(".c-box-wrap>textarea").focusin(function() {
			$(this).parent(".c-box-wrap").addClass("cmmFocus");
		})
		$(".c-box-wrap>textarea").focusout(function() {
			$(this).parent(".c-box-wrap").removeClass("cmmFocus");
		})
	})
</script>
</head>
<body class="W-body">
	<div class="Ques-main">
		<div class="Ques-banner"></div>
		<section class="w1000">
			<div class="mt10 pr">
				<section class="pathwray clearfix c-master f-fM fsize14 unBr">
					<ol class="clearfix c-master f-fM fsize14"> <li><a class="c-master" title="首页" href="/">首页</a> &gt;</li> <li><span>问答</span></li> </ol>
					<div class="clear"></div>
				</section>
			</div>
			<div class="mt30">
				<header class="scommTitle"><aside class="live-i-ico Ques-i-ico">&nbsp;</aside>
				<div class="sct-txt">
					<section class="mt15 fl"><span class="fsize24 f-fM c-333">专业答疑，随时解惑 / Online Quiz</span></section>
					<div class="clear">
					</div>
					<div class="v-i-tab-a">
						<a href="javascript:clickSearch('subject','')" id="clickSubjcet" class="current">全部问答</a>&nbsp;/&nbsp; <a href="javascript:clickSearch('subject','165')" id="clickSubjcet165">职业资格</a>&nbsp;/&nbsp; <a href="javascript:clickSearch('subject','202')" id="clickSubjcet202">外语考试</a>&nbsp;/&nbsp; <a href="javascript:clickSearch('subject','208')" id="clickSubjcet208">教师资格证</a>&nbsp;/&nbsp; <a href="javascript:clickSearch('subject','210')" id="clickSubjcet210">公务员</a>&nbsp;/&nbsp; <a href="javascript:clickSearch('subject','227')" id="clickSubjcet227">银行</a>&nbsp;/&nbsp; <a href="javascript:clickSearch('subject','230')" id="clickSubjcet230">考研</a>&nbsp;/&nbsp; <a href="javascript:clickSearch('subject','233')" id="clickSubjcet233">计算机教学</a>&nbsp;/&nbsp; <a href="javascript:clickSearch('subject','239')" id="clickSubjcet239">测试</a>&nbsp;/&nbsp; <a href="javascript:clickSearch('subject','242')" id="clickSubjcet242">天天来学院</a>&nbsp;/&nbsp; <a href="javascript:clickSearch('subject','243')" id="clickSubjcet243">人力资源管理</a>&nbsp;/&nbsp; <a href="javascript:clickSearch('subject','244')" id="clickSubjcet244">初中</a>
					</div>
					<div class="sub-v-i-tab-a">
					</div>
				</div>
				</header>
			</div>
			<div class="mt50 pt50">
				<div class="Ques-box">
					<div class="comment-box">
						<span class="c-q-img-1">
							<img width="60" height="60" src="/static/edu/images/default/default_head.jpg" class="unBr" alt="">
							<p class="hLh20 of tac" style="width: 60px;"><tt class="c-master">李小明</tt></p>
						</span>
						<section class="c-box-wrap i-cmm-txt pr">
							<em class="icon16 c-q-sj">&nbsp;</em>
							<textarea name="" style="width: 99%"></textarea>
						</section>
						<section class="tar mt5">
							<span class="fl mt5">
								<tt class="c-red">请输入你要提问的内容！</tt>
							</span>
							<span class="fr">
								<a class="question-btn ml10" title="" href="">我要提问</a>
							</span>
							<div class="clear"></div>
						</section>
					</div>
					<!-- 问答 开始 -->
					<div class="mt20">
						<section class="pr articleListTitle"> 
							<h3 class="fl f-fM mt5 tac" style="width: 102px;"><strong class="c-333 fsize18">问答列表</strong></h3> 
							<h5 class="fl current"> 
								<a href="" target="_self" id="" class="onClick">全部</a> 
								<a href="" target="_self" id="">已回复</a> 
								<a href="" target="_self" id="">未回复</a>
								<a href="" target="_self" id="">精华问答</a>
								<a href="" target="_self" id="">热门问答</a>  
							</h5> 
							<div class="clear"></div> 
						</section>
						<section class="comment-question mt20">
							<section class="comm-tips-1">
								<p>
									<em class="vam c-tips-1">&nbsp;</em>
									<font class="c-999 fsize12 vam">还没有相关问答，赶快去提问哦~~~</font>
								</p>
							</section>
							<dl>
								<dt>
									<span class="c-q-img-1"><img width="60" height="60" alt="" src="images/pics/9.jpg" class="unBr"></span>
									<div class="clearfix">
										<span class="fr"><font class="fsize12 c-666">2013-10-15 09:43</font></span>
										<span><strong class="fsize14 c-master vam">张三三</strong><tt class="c-666 vam ml10">提问：</tt></span>
									</div>
									<div class="mt5">
										<p class="c-999">基本方针生物是正确了解身体，学习人和环境（植物，动物，自然界）之间关系的科目不要盲目记忆，跟生活中的经验联系起来理解？</p>
									</div>
									<div class="mt10 clearfix">
										<span class="fr c-ccc"><tt class="f-fM c-666 vam mr5">3条答复</tt>|<a class="vam ml10 c-master" title="" href="">回复</a></span>
									</div>
								</dt>
								<dd>
									<span class="c-q-img-2"><img width="85" height="64" alt="" src="images/pics/10.jpg" class="unBr"></span>
									<div class="clearfix">
										<span class="fr"><font class="fsize12 c-666">2013-10-15 11:30</font></span>
										<span><strong class="fsize14 c-orange vam">李玉玲讲师</strong><tt class="c-666 vam ml10">回答：</tt></span>
									</div>
									<div class="mt5">
										<p class="c-999">基本方针生物是正确了解身体，学习人和环境（植物，动物，自然界）之间关系的科目不要盲目记忆，跟生活中的经验联系起来理解基本方针生物是正确了解身体，学习人和环境（植物，动物，自然界）之间关系的科目不要盲目记忆，跟生活中的经验联系起来理解</p>
									</div>
								</dd>
								<dd>
									<span class="c-q-img-3"><img width="50" height="50" alt="" src="images/pics/11.jpg" class="unBr"></span>
									<div class="clearfix">
										<span class="fr"><font class="fsize12 c-666">2013-10-15 02:38</font></span>
										<span><strong class="fsize14 c-master vam">cCp</strong><tt class="c-666 vam ml10">回答：</tt></span>
									</div>
									<div class="mt5">
										<p class="c-999">基本方针生物是正确了解身体，学习人和环境（植物，动物，自然界）</p>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>
									<span class="c-q-img-1"><img width="60" height="60" alt="" src="images/pics/9.jpg" class="unBr"></span>
									<div class="clearfix">
										<span class="fr"><font class="fsize12 c-666">2013-10-15 09:43</font></span>
										<span><strong class="fsize14 c-master vam">张三三</strong><tt class="c-666 vam ml10">提问：</tt></span>
									</div>
								<div class="mt5">
									<p class="c-999">基本方针生物是正确了解身体，学习人和环境（植物，动物，自然界）之间关系的科目不要盲目记忆，跟生活中的经验联系起来理解？</p>
								</div>
								<div class="mt10 clearfix">
									<span class="fr c-ccc"><tt class="f-fM c-666 vam mr5">0条答复</tt>|<a class="vam ml10 c-master" title="" href="">回复</a></span>
								</div>
							</dt>
						</dl>
					</section>
					</div>
					<!-- 问答 结束 -->
					
					
					<section class=""> <div class="pagination pagination-large tac mt50 pt50"> <div class="pagination pagination-large tac "> <div class="pagination pagination-large"> <ul class="b-fff"> <li class="disabled"><a href="#">首页</a></li> <li id="backpage" class="disabled"><a href="javascript:void(0)">←上一页</a></li> <li class="active"><a href="javascript:void(0)">1</a></li><li id="nextpage" class="disabled"><a href="javascript:void(0)">下一页→</a></li> <li class="disabled"><a href="javascript:void(0)">尾页 </a></li> </ul> </div> <div class="pageDesc fl"> </div></div> </div> </section>
					
					</div>
			</div>
		</section>
	</div>
</body>
</html>