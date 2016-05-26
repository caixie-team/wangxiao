<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>一些常用交互效果</title>
</head>
<body>
	<!-- 全局公共头部+导航  开始 -->
	<header class="NXB-header">
		<div class="NXB-container">
			<!-- logo end -->
			<h1 id="logo">
				<a title="xxx公司" href="/">
					<img alt="xxx公司" src="/static/common/admin/images/new-logo.png">
				</a>
			</h1>
			<!-- logo end -->
			<aside class="h-top-r">
				<ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
			      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
			      <li data-am-dropdown="" class="am-dropdown">
			        <a href="javascript:;" data-am-dropdown-toggle="" class="am-dropdown-toggle">
			          <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
			        </a>
			        <ul class="am-dropdown-content">
			          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
			          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
			          <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
			        </ul>
			      </li>
			      <li class="am-hide-sm-only"><a id="admin-fullscreen" href="javascript:;"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
			    </ul>
			</aside>
			<div class="clear"></div>
		</div>
	</header>
	<nav class="NXB-nav">
		<div class="NXB-container">
			<ul class="NXB-nav-ul fl">
				<li class="current"><a href="" class="首页">首 页</a></li>
				<li><a href="" class="系统设置">系统设置</a></li>
				<li><a href="" class="课程管理">课程管理</a></li>
				<li><a href="" class="测试测评">测试测评</a></li>
				<li><a href="" class="问答管理">问答管理</a></li>
				<li><a href="" class="资讯公告">资讯公告</a></li>
				<li>
					<div data-am-dropdown="" class="am-dropdown">
						<button data-am-dropdown-toggle="" class="am-btn am-btn-primary am-dropdown-toggle">更多 <span class="am-icon-caret-down"></span></button>
						<ul class="am-dropdown-content">
							<li><a href="#">线下活动</a></li>
							<li><a href="#">名家讲师</a></li>
							<li><a href="#">精彩专题</a></li>
						</ul>
					</div>
				</li>
			</ul>
			<aside class="nav-r fr mt5">
				<a href="" title="返回前台"><span class="am-icon-map-signs"></span> 返回前台</a>
			</aside>
			<div class="clear"></div>
		</div>
	</nav>
	<!-- 全局公共头部+导航  结束 -->
	
	<!-- 全局主体区  开始 -->
	<div class="NXB-main">
		<div class="NXB-container">
			<div class="NXB-bCrumb">
				<ol class="am-breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">分类</a></li>
				  <li class="am-active">内容</li>
				</ol>
			</div>
			<div class="am-cf NXB-m-box">
				<!-- 全局公共左侧菜单区  开始 -->
				<div id="admin-offcanvas" class="admin-sidebar am-offcanvas">
				    <div class="am-offcanvas-bar admin-offcanvas-bar">
				      <ul id="collapase-nav-1" class="NXB-l-menu am-list admin-sidebar-list">
						<li class="am-panel"><a href="#/" class="am-collapsed"><i class="am-icon-home"></i> 首页</a></li>
						<li class="am-panel"><a data-am-collapse="{parent: '#collapase-nav-1', target: '#user-nav'}" class=""><i class="am-icon-users"></i> 人员管理 <i class="am-icon-angle-right am-fr am-margin-right"></i></a>
						<ul id="user-nav" class="am-list admin-sidebar-sub am-collapse am-in" style="">
							<li><a href="#/userAdd"><i class="am-icon-user am-margin-left-sm"></i> 添加人员</a></li>
							<li><a href="#/userList/0"><i class="am-icon-user am-margin-left-sm"></i> 人员列表</a></li>
						</ul>
						</li>
						<li class="am-panel"><a data-am-collapse="{parent: '#collapase-nav-1', target: '#company-nav'}" class="am-collapsed"><i class="am-icon-table"></i> 单位（部门）管理 <i class="am-icon-angle-right am-fr am-margin-right"></i></a>
						<ul id="company-nav" class="am-list admin-sidebar-sub am-collapse" style="height: 0px;">
							<li><a href="#/companyAdd"><span class="am-icon-table am-margin-left-sm"></span> 添加单位（部门）</a></li>
							<li><a href="#/companyList/0"><span class="am-icon-table am-margin-left-sm"></span> 单位（部门）列表</a></li>
						</ul>
						</li>
						<li class="am-panel"><a data-am-collapse="{parent: '#collapase-nav-1', target: '#role-nav'}" class="am-collapsed"><i class="am-icon-table"></i> 角色管理 <i class="am-icon-angle-right am-fr am-margin-right"></i></a>
						<ul id="role-nav" class="am-list admin-sidebar-sub am-collapse" style="height: 0px;">
							<li><a href="#/roleAdd"><span class="am-icon-table am-margin-left-sm"></span> 添加角色</a></li>
							<li><a href="#/roleList/0"><span class="am-icon-table am-margin-left-sm"></span> 角色列表</a></li>
						</ul>
						</li>
						<li><a href="admin-table.html" class="am-collapsed"><span class="am-icon-table"></span> 表格</a></li>
			       	 	<li><a href="admin-form.html" class="am-collapsed"><span class="am-icon-pencil-square-o"></span> 表单</a></li>
			        	<li><a href="#" class="am-collapsed"><span class="am-icon-sign-out"></span> 注销</a></li>
					  </ul>
				      <div class="am-panel am-panel-default admin-sidebar-panel">
				        <div class="am-panel-bd">
				          <p><span class="am-icon-bookmark"></span> 公告</p>
				          <p>时光静好，与君语；细水流年，与君同。&mdash;&mdash; Amaze UI</p>
				        </div>
				      </div>
				
				      <div class="am-panel am-panel-default admin-sidebar-panel">
				        <div class="am-panel-bd">
				          <p><span class="am-icon-tag"></span> 链接</p>
				        </div>
				      </div>
				    </div>
				  </div>
				<!-- 全局公共左侧菜单区  结束 -->
				<!-- 全局公共右内容区  开始 -->
				<div class="admin-content">
					<div class="am-margin">
						<!-- 公共右侧标题样式 -->
						<div class="am-cf">
					      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">交互效果</strong> / <small>一些常用交互效果</small></div>
					    </div>
					    <hr>
					    <!-- 公共右侧标题样式 结束-->
					    
					    <!-- 警告框样式 开始-->
						<section class="mt20">
							<div class="am-alert am-alert-success" data-am-alert>
							  <button type="button" class="am-close">&times;</button>
							  <p>没什么可给你，但求凭这阙歌。谢谢你风雨里，都不退愿陪着我。</p>
							</div>
							
							<div class="am-alert am-alert-warning" data-am-alert>
							  <button type="button" class="am-close">&times;</button>
							  <p>没什么可给你，但求凭这阙歌。谢谢你风雨里，都不退愿陪着我。</p>
							</div>
							
							<div class="am-alert am-alert-danger" data-am-alert>
							  <button type="button" class="am-close">&times;</button>
							  <p>没什么可给你，但求凭这阙歌。谢谢你风雨里，都不退愿陪着我。</p>
							</div>
							
							<div class="am-alert am-alert-secondary" data-am-alert>
							  <button type="button" class="am-close">&times;</button>
							  <p>没什么可给你，但求凭这阙歌。谢谢你风雨里，都不退愿陪着我。</p>
							</div>
						</section>
						 <!-- 警告框样式 结束-->
						 
						 <section class="mt20">
						 	<button type="button" class="am-btn am-btn-primary btn-loading-example" data-am-loading="{spinner: 'circle-o-notch', loadingText: '加载中...', resetText: '加载过了'}">按钮 - button 元素</button>

							<input type="button" class="am-btn am-btn-secondary btn-loading-example" value="按钮 - input 元素" data-am-loading="{loadingText: '努力加载中...'}" />
						 </section>
						 
						 
						 <section class="mt20">
						 	<div class="am-dropdown" data-am-dropdown>
							  <button class="am-btn am-btn-primary am-dropdown-toggle" data-am-dropdown-toggle>下拉列表 <span class="am-icon-caret-down"></span></button>
							  <ul class="am-dropdown-content">
							    <li class="am-dropdown-header">标题</li>
							    <li><a href="#">快乐的方式不只一种</a></li>
							    <li class="am-active"><a href="#">最荣幸是</a></li>
							    <li><a href="#">谁都是造物者的光荣</a></li>
							    <li class="am-disabled"><a href="#">就站在光明的角落</a></li>
							    <li class="am-divider"></li>
							    <li><a href="#">天空海阔 要做最坚强的泡沫</a></li>
							  </ul>
							</div>
							<span class="ml10"></span>
							<div class="am-dropdown am-dropdown-up" data-am-dropdown>
							  <button class="am-btn am-btn-danger am-dropdown-toggle" data-am-dropdown-toggle>上拉列表 <span class="am-icon-caret-up"></span></button>
							  <ul class="am-dropdown-content">
							    <li class="am-dropdown-header">标题</li>
							    <li><a href="#">快乐的方式不只一种</a></li>
							    <li class="am-active"><a href="#">最荣幸是</a></li>
							    <li><a href="#">谁都是造物者的光荣</a></li>
							    <li class="am-disabled"><a href="#">就站在光明的角落</a></li>
							    <li class="am-divider"></li>
							    <li><a href="#">天空海阔 要做最坚强的泡沫</a></li>
							  </ul>
							</div>
							<span class="ml10"></span>
							<div class="am-dropdown" data-am-dropdown>
							  <button class="am-btn am-btn-success am-dropdown-toggle">Dropdown <span class="am-icon-caret-down"></span></button>
							  <div class="am-dropdown-content">
							    <h2>I am what I am</h2>
							    <p>
							      多么高兴 在琉璃屋中快乐生活
							      对世界说 甚么是光明和磊落
							      我就是我 是颜色不一样的烟火
							    </p>
							  </div>
							</div>
							<span class="ml10"></span>
							
							<select data-am-selected>
							  <option value="a">Apple</option>
							  <option value="b" selected>Banana</option>
							  <option value="o">Orange</option>
							  <option value="m">Mango</option>
							  <option value="d" disabled>禁用鸟</option>
							</select>
							<span class="ml10"></span>
							<select multiple data-am-selected>
							  <option value="a">Apple</option>
							  <option value="b">Banana</option>
							  <option value="o">Orange</option>
							  <option value="m">Mango</option>
							</select>
							<span class="ml10"></span>
							<p class="mt20"></p>
							<select multiple data-am-selected>
							  <option value="a">Apple</option>
							  <option value="b" selected>Banana</option>
							  <option value="o">Orange</option>
							  <option value="m" selected>Mango</option>
							</select>
							<span class="ml10"></span>
							<select multiple data-am-selected>
							  <optgroup label="水果">
							    <option value="a">Apple</option>
							    <option value="b">Banana</option>
							    <option value="o">Orange</option>
							    <option value="m">Mango</option>
							  </optgroup>
							  <optgroup label="装备">
							    <option value="phone">iPhone</option>
							    <option value="im">iMac</option>
							    <option value="mbp">Macbook Pro</option>
							  </optgroup>
							</select>
							<span class="ml10"></span>
							<select data-am-selected="{btnWidth: '40%', btnSize: 'sm', btnStyle: 'secondary'}">
							  <option value="a">Apple</option>
							  <option value="b">Banana</option>
							  <option value="o">Orange</option>
							  <option value="m">Mango</option>
							</select>
							<span class="ml10"></span>
							<p class="mt20"></p>
							<select data-am-selected="{maxHeight: 100}">
							  <option value="a">Apple</option>
							  <option value="b">Banana</option>
							  <option value="o">Orange</option>
							  <option value="m">Mango</option>
							  <option value="phone">iPhone</option>
							  <option value="im">iMac</option>
							  <option value="mbp">Macbook Pro</option>
							</select>
							<span class="ml10"></span>
							<select data-am-selected="{dropUp: 1}">
							  <option value="a">Apple</option>
							  <option value="b">Banana</option>
							  <option value="o">Orange</option>
							  <option value="m">Mango</option>
							  <option value="phone">iPhone</option>
							  <option value="im">iMac</option>
							  <option value="mbp">Macbook Pro</option>
							</select>
							<span class="ml10"></span>
							<select data-am-selected="{searchBox: 1}">
							  <option value="a">Apple</option>
							  <option value="b">Banana</option>
							  <option value="o">Orange</option>
							  <option value="m">Mango</option>
							  <option value="phone">iPhone</option>
							  <option value="im">iMac</option>
							  <option value="mbp">Macbook Pro</option>
							</select>
							<span class="ml10"></span>
							<select multiple data-am-selected minchecked="2" maxchecked="3" id="demo-maxchecked">
							  <option value="a">Apple</option>
							  <option value="b">Banana</option>
							  <option value="o">Orange</option>
							  <option value="m">Mango</option>
							</select>
							<span class="ml10"></span>
						 </section>
						 <!-- 下上拉列表形态  结束 -->
						 
						 <section class="mt20">
						 	<div>
							 	<button
								  type="button"
								  class="am-btn am-btn-primary"
								  data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 400, height: 225}">
								  Modal
								</button>
								
								<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
								  <div class="am-modal-dialog">
								    <div class="am-modal-hd">Modal 标题
								      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
								    </div>
								    <div class="am-modal-bd">
								      Modal 内容。本 Modal 无法通过遮罩层关闭。
								    </div>
								  </div>
								</div>
							</div>
							
							<div class="mt20">
							 	<button
								  type="button"
								  class="am-btn am-btn-primary"
								  data-am-modal="{target: '#my-alert'}">
								  Alert
								</button>
								
								<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
								  <div class="am-modal-dialog">
								    <div class="am-modal-hd">Amaze UI</div>
								    <div class="am-modal-bd">
								      Hello world！
								    </div>
								    <div class="am-modal-footer">
								      <span class="am-modal-btn">确定</span>
								    </div>
								  </div>
								</div>
							</div>
							
							
							<div class="mt20">
								<button
								  type="button"
								  class="am-btn am-btn-warning"
								  data-am-modal="{target: '#my-confirm'}">
								  Confirm
								</button>
								
								<div class="am-modal am-modal-alert" tabindex="-1" id="my-confirm">
								  <div class="am-modal-dialog">
								    <div class="am-modal-hd">Amaze UI</div>
								    <div class="am-modal-bd">
								      Hello world！
								    </div>
								    <div class="am-modal-footer">
								    	<span data-am-modal-cancel="" class="am-modal-btn">取消</span> 
								    	<span data-am-modal-confirm="" class="am-modal-btn">确定</span>
								    </div>
								  </div>
								</div>
							</div>
							
							<div class="mt20">
								<button class="am-btn am-btn-primary" data-am-popover="{content: '鄙是点击显示的 Popover'}">点击显示 Popover</button>
								<span class="ml10"></span>
								<button class="am-btn am-btn-success"
								        data-am-popover="{content: '鄙是点击 Hover 显示的 Popover', trigger: 'hover focus'}">
								  Hover 显示 Popover
								</button>
								<span class="ml10"></span>
								<!--蓝色的 Popover-->
								<button
								  class="am-btn am-btn-primary"
								  data-am-popover="{theme: 'primary', content: '点击显示的 Primary'}">
								  Primary
								</button>
								<span class="ml10"></span>
								<!--红色、sm Popover-->
								<button
								  class="am-btn am-btn-secondary"
								  data-am-popover="{theme: 'danger sm', content: '点击显示的 Danger & Small'}">
								  Danger
								</button>
								<span class="ml10"></span>
								<!--警示、lg Popover-->
								<button
								  class="am-btn am-btn-warning"
								  data-am-popover="{theme: 'warning lg', content: '点击显示的 Danger & Small'}">
								  Warning
								</button>
							</div>
							
						 </section>
						 <!-- 各种形态弹出框  结束 -->
						 <section class="mt20">
						 	<form action="" class="am-form" data-am-validator>
							  <p>
							  <input type="text" class="am-form-field" placeholder="日历组件" data-am-datepicker readonly required />
							  </p>
							  <p><button class="am-btn am-btn-primary">提交</button></p>
							</form>
							<p class="mt20"></p>
							<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'dd-mm-yyyy'}">
							  <input type="text" class="am-form-field" placeholder="日历组件" readonly>
							  <span class="am-input-group-btn am-datepicker-add-on">
							    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
							  </span>
							</div>
							<p class="mt20"></p>
							<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd', viewMode: 'years'}">
							  <input type="text" class="am-form-field" placeholder="日历组件" readonly>
							  <span class="am-input-group-btn am-datepicker-add-on">
							    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
							  </span>
							</div>
							<p class="mt20"></p>
							<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm', viewMode: 'years', minViewMode: 'months'}">
							  <input type="text" class="am-form-field" placeholder="日历组件" readonly>
							  <span class="am-input-group-btn am-datepicker-add-on">
							    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
							  </span>
							</div>
							
							<p class="mt20"></p>
							<div>
							  <input type="text" class="am-form-field" data-am-datepicker="{format: 'yyyy ', viewMode: 'years', minViewMode: 'years'}" placeholder="日历组件" data-am-datepicker readonly/>
							</div>
						 </section>
						 <!-- 日期日历组件  结束 -->
						 
						 <section class="mt20">
						 	<div class="am-g">
						      <div class="am-u-md-6">
						        <div class="am-panel am-panel-default">
						          <div data-am-collapse="{target: '#collapse-panel-1'}" class="am-panel-hd am-cf">文件上传<span class="am-icon-chevron-down am-fr"></span></div>
						          <div id="collapse-panel-1" class="am-panel-bd am-collapse am-in">
						            <ul class="am-list admin-content-file">
						              <li>
						                <strong><span class="am-icon-upload"></span> Kong-cetian.Mp3</strong>
						                <p>3.3 of 5MB - 5 mins - 1MB/Sec</p>
						                <div class="am-progress am-progress-striped am-progress-sm am-active">
						                  <div style="width: 82%" class="am-progress-bar am-progress-bar-success">82%</div>
						                </div>
						              </li>
						              <li>
						                <strong><span class="am-icon-check"></span> 好人-cetian.Mp3</strong>
						                <p>3.3 of 5MB - 5 mins - 3MB/Sec</p>
						              </li>
						              <li>
						                <strong><span class="am-icon-check"></span> 其实都没有.Mp3</strong>
						                <p>3.3 of 5MB - 5 mins - 3MB/Sec</p>
						              </li>
						            </ul>
						          </div>
						        </div>
						        <div class="am-panel am-panel-default">
						          <div data-am-collapse="{target: '#collapse-panel-2'}" class="am-panel-hd am-cf">浏览器统计<span class="am-icon-chevron-down am-fr"></span></div>
						          <div class="am-in" id="collapse-panel-2">
						            <table class="am-table am-table-bd am-table-bdrs am-table-striped am-table-hover">
						              <tbody>
						              <tr>
						                <th class="am-text-center">#</th>
						                <th>浏览器</th>
						                <th>访问量</th>
						              </tr>
						              <tr>
						                <td class="am-text-center"><img alt="" src="assets/i/examples/admin-chrome.png"></td>
						                <td>Google Chrome</td>
						                <td>3,005</td>
						              </tr>
						              <tr>
						                <td class="am-text-center"><img alt="" src="assets/i/examples/admin-firefox.png"></td>
						                <td>Mozilla Firefox</td>
						                <td>2,505</td>
						              </tr>
						              <tr>
						                <td class="am-text-center"><img alt="" src="assets/i/examples/admin-ie.png"></td>
						                <td>Internet Explorer</td>
						                <td>1,405</td>
						              </tr>
						              <tr>
						                <td class="am-text-center"><img alt="" src="assets/i/examples/admin-opera.png"></td>
						                <td>Opera</td>
						                <td>4,005</td>
						              </tr>
						              <tr>
						                <td class="am-text-center"><img alt="" src="assets/i/examples/admin-safari.png"></td>
						                <td>Safari</td>
						                <td>505</td>
						              </tr>
						              </tbody>
						            </table>
						          </div>
						        </div>
						      </div>
						
						      <div class="am-u-md-6">
						        <div class="am-panel am-panel-default">
						          <div data-am-collapse="{target: '#collapse-panel-4'}" class="am-panel-hd am-cf">任务 task<span class="am-icon-chevron-down am-fr"></span></div>
						          <div class="am-panel-bd am-collapse am-in" id="collapse-panel-4">
						            <ul class="am-list admin-content-task">
						              <li>
						                <div class="admin-task-meta"> Posted on 25/1/2120 by John Clark</div>
						                <div class="admin-task-bd">
						                  The starting place for exploring business management; helping new managers get started and experienced managers get better.
						                </div>
						                <div class="am-cf">
						                  <div class="am-btn-toolbar am-fl">
						                    <div class="am-btn-group am-btn-group-xs">
						                      <button class="am-btn am-btn-default" type="button"><span class="am-icon-check"></span></button>
						                      <button class="am-btn am-btn-default" type="button"><span class="am-icon-pencil"></span></button>
						                      <button class="am-btn am-btn-default" type="button"><span class="am-icon-times"></span></button>
						                    </div>
						                  </div>
						                  <div class="am-fr">
						                    <button class="am-btn am-btn-default am-btn-xs" type="button">删除</button>
						                  </div>
						                </div>
						              </li>
						              <li>
						                <div class="admin-task-meta"> Posted on 25/1/2120 by 呵呵呵</div>
						                <div class="admin-task-bd">
						                  基兰和狗熊出现在不同阵营时。基兰会获得BUFF，“装甲熊憎恨者”。狗熊会获得BUFF，“时光老人憎恨者”。
						                </div>
						                <div class="am-cf">
						                  <div class="am-btn-toolbar am-fl">
						                    <div class="am-btn-group am-btn-group-xs">
						                      <button class="am-btn am-btn-default" type="button"><span class="am-icon-check"></span></button>
						                      <button class="am-btn am-btn-default" type="button"><span class="am-icon-pencil"></span></button>
						                      <button class="am-btn am-btn-default" type="button"><span class="am-icon-times"></span></button>
						                    </div>
						                  </div>
						                  <div class="am-fr">
						                    <button class="am-btn am-btn-default am-btn-xs" type="button">删除</button>
						                  </div>
						                </div>
						              </li>
						            </ul>
						          </div>
						        </div>
						
						        <div class="am-panel am-panel-default">
						          <div data-am-collapse="{target: '#collapse-panel-3'}" class="am-panel-hd am-cf">最近留言<span class="am-icon-chevron-down am-fr"></span></div>
						          <div id="collapse-panel-3" class="am-panel-bd am-collapse am-in am-cf">
						            <ul class="am-comments-list admin-content-comment">
						              <li class="am-comment">
						                <a href="#"><img width="48" height="48" class="am-comment-avatar" alt="" src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/96/h/96"></a>
						                <div class="am-comment-main">
						                  <header class="am-comment-hd">
						                    <div class="am-comment-meta"><a class="am-comment-author" href="#">某人</a> 评论于 <time>2014-7-12 15:30</time></div>
						                  </header>
						                  <div class="am-comment-bd"><p>遵循 “移动优先（Mobile First）”、“渐进增强（Progressive enhancement）”的理念，可先从移动设备开始开发网站，逐步在扩展的更大屏幕的设备上，专注于最重要的内容和交互，很好。</p>
						                  </div>
						                </div>
						              </li>
						
						              <li class="am-comment">
						                <a href="#"><img width="48" height="48" class="am-comment-avatar" alt="" src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/96/h/96"></a>
						                <div class="am-comment-main">
						                  <header class="am-comment-hd">
						                    <div class="am-comment-meta"><a class="am-comment-author" href="#">某人</a> 评论于 <time>2014-7-12 15:30</time></div>
						                  </header>
						                  <div class="am-comment-bd"><p>有效减少为兼容旧浏览器的臃肿代码；基于 CSS3 的交互效果，平滑、高效。AMUI专注于现代浏览器（支持HTML5），不再为过时的浏览器耗费资源，为更有价值的用户提高更好的体验。</p>
						                  </div>
						                </div>
						              </li>
						
						            </ul>
						            <ul class="am-pagination am-fr admin-content-pagination">
						              <li class="am-disabled"><a href="#">«</a></li>
						              <li class="am-active"><a href="#">1</a></li>
						              <li><a href="#">2</a></li>
						              <li><a href="#">3</a></li>
						              <li><a href="#">4</a></li>
						              <li><a href="#">5</a></li>
						              <li><a href="#">»</a></li>
						            </ul>
						          </div>
						        </div>
						      </div>
						    </div>
						 </section>
						 <!-- 文件上传  结束 -->
						 
						 <section class="mt20">
						 	<div class="am-g">

						      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
						        <div class="am-panel am-panel-default">
						          <div class="am-panel-bd">
						            <div class="am-g">
						              <div class="am-u-md-4">
						                <img alt="" src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/200/h/200/q/80" class="am-img-circle am-img-thumbnail">
						              </div>
						              <div class="am-u-md-8">
						                <p>你可以使用<a href="#">gravatar.com</a>提供的头像或者使用本地上传头像。 </p>
						                <form class="am-form">
						                  <div class="am-form-group">
						                    <input type="file" id="user-pic">
						                    <p class="am-form-help">请选择要上传的文件...</p>
						                    <button class="am-btn am-btn-primary am-btn-xs" type="button">保存</button>
						                  </div>
						                </form>
						              </div>
						            </div>
						          </div>
						        </div>
						
						        <div class="am-panel am-panel-default">
						          <div class="am-panel-bd">
						            <div class="user-info">
						              <p>等级信息</p>
						              <div class="am-progress am-progress-sm">
						                <div style="width: 60%" class="am-progress-bar"></div>
						              </div>
						              <p class="user-info-order">当前等级：<strong>LV8</strong> 活跃天数：<strong>587</strong> 距离下一级别：<strong>160</strong></p>
						            </div>
						            <div class="user-info">
						              <p>信用信息</p>
						              <div class="am-progress am-progress-sm">
						                <div style="width: 80%" class="am-progress-bar am-progress-bar-success"></div>
						              </div>
						              <p class="user-info-order">信用等级：正常当前 信用积分：<strong>80</strong></p>
						            </div>
						          </div>
						        </div>
						
						      </div>
						
						      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
						        <form class="am-form am-form-horizontal">
						          <div class="am-form-group">
						            <label class="am-u-sm-3 am-form-label" for="user-name">姓名 / Name</label>
						            <div class="am-u-sm-9">
						              <input type="text" placeholder="姓名 / Name" id="user-name">
						              <small>输入你的名字，让我们记住你。</small>
						            </div>
						          </div>
						
						          <div class="am-form-group">
						            <label class="am-u-sm-3 am-form-label" for="user-email">电子邮件 / Email</label>
						            <div class="am-u-sm-9">
						              <input type="email" placeholder="输入你的电子邮件 / Email" id="user-email">
						              <small>邮箱你懂得...</small>
						            </div>
						          </div>
						
						          <div class="am-form-group">
						            <label class="am-u-sm-3 am-form-label" for="user-phone">电话 / Telephone</label>
						            <div class="am-u-sm-9">
						              <input type="email" placeholder="输入你的电话号码 / Telephone" id="user-phone">
						            </div>
						          </div>
						
						          <div class="am-form-group">
						            <label class="am-u-sm-3 am-form-label" for="user-QQ">QQ</label>
						            <div class="am-u-sm-9">
						              <input type="email" placeholder="输入你的QQ号码" id="user-QQ">
						            </div>
						          </div>
						
						          <div class="am-form-group">
						            <label class="am-u-sm-3 am-form-label" for="user-weibo">微博 / Twitter</label>
						            <div class="am-u-sm-9">
						              <input type="email" placeholder="输入你的微博 / Twitter" id="user-weibo">
						            </div>
						          </div>
						
						          <div class="am-form-group">
						            <label class="am-u-sm-3 am-form-label" for="user-intro">简介 / Intro</label>
						            <div class="am-u-sm-9">
						              <textarea placeholder="输入个人简介" id="user-intro" rows="5" class=""></textarea>
						              <small>250字以内写出你的一生...</small>
						            </div>
						          </div>
						
						          <div class="am-form-group">
						            <div class="am-u-sm-9 am-u-sm-push-3">
						              <button class="am-btn am-btn-primary" type="button">保存修改</button>
						            </div>
						          </div>
						        </form>
						      </div>
						    </div>
						 </section>
						  <!-- 个人资料  结束 -->
						 <section class="mt20">
						 <div class="am-g error-log">
						      <div class="am-u-sm-12 am-u-sm-centered">
						        <pre class="am-pre-scrollable"><span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						<span class="am-text-success">[Tue Jan 11 17:32:52 2013]</span> <span class="am-text-danger">[error]</span> [google 123.124.2.2] client denied by server: /export/home/macadmin/testdoc - no such file
						        </pre>
						        <p>这里是静态页面展示，使用时结合代码高亮插件</p>
						      </div>
						    </div>
						 </section>
						 
						 <section class="mt20">
						 	<div class="am-g">
						      <div class="am-u-sm-12">
						        <h2 class="am-text-center am-text-xxxl am-margin-top-lg">404. Not Found</h2>
						        <p class="am-text-center">没有找到你要的页面，<a href="" class="am-btn am-btn-secondary">返回首页</a> 或 <span class="am-btn am-btn-success">按F5刷新</span></p>
						        <pre class="page-404">          .----.
						       _.'__    `.
						   .--($)($$)---/#\
						 .' @          /###\
						 :         ,   #####
						  `-..__.-' _.-\###/
						        `;_:    `"'
						      .'"""""`.
						     /,  ya ,\\
						    //  404!  \\
						    `-._______.-'
						    ___`. | .'___
						   (______|______)
						        </pre>
						      </div>
						    </div>
						 </section>
						 <!-- 404  结束 -->
						 
				    </div>
				</div>
				<!-- 全局公共右内容区  开始 -->
			</div>
		</div>
	</div>
	<!-- 全局主体区  开始 -->
	
	<!-- 全局尾部区  开始 -->
	<footer class="NXB-footer">
		<div class="NXB-container">
	  		<hr>
	 	 </div>
	</footer>
	<!-- 全局尾部区  结束 -->
	
	<!--[if lt IE 9]>
	<script src="${ctximg}/static/common/admin/js/modernizr.js"></script>
	<script src="${ctximg}/static/common/admin/js/amazeui.ie8polyfill.min.js"></script>
	<![endif]-->
	<script src="${ctximg}/static/common/admin/js/amazeui.min.js"></script>
	<script>
		//开启全屏模式
		(function($) {
		  'use strict';
		  $(function() {
		    var $fullText = $('.admin-fullText');
		    $('#admin-fullscreen').on('click', function() {
		      $.AMUI.fullscreen.toggle();
		    });
		    $(document).on($.AMUI.fullscreen.raw.fullscreenchange, function() {
		      $fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
		    });
		  });
		})(jQuery);
	</script>
</body>
</html>