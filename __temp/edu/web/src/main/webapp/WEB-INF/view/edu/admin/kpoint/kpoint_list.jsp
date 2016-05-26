<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>视频树</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.excheck-3.5.js?v=${v}"></script>

<script type="text/javascript">


	function changeType(){
		var val=$("#type").val()
		//$("#typeHidden").val(val);
		if(val==0){
			$(".videoTr").show();
			
		}else if(val==1){
			$(".videoTr").hide();
			$("#videourl").val('');//文件夹清空视频地址
		}
	}
	var load_subject = 0;
	//subject ztree start

	$().ready(function() {
		loadztree();
		$("#videotype").change(function() {
			if ($(this).val() == 'FIVESIX') {
				$(".playType").show();
			} else {
				$(".playType").hide();
			}
			if($(this).val() == 'LETV'){
				$(".playTypeLETV").show();
			}else{
				$(".playTypeLETV").hide();
			}
			if($(this).val()=="CC"){
				$("#videoUrlTitle").html("CC播放地址");
			}else{
				$("#videoUrlTitle").html("视频地址");
			}
		});
	});
	function subject_showZtree() {
		$("#subject_distree").show();
	}
	function subject_closetree() {
		$("#subject_distree").hide();
	}

	// subject ztree end

	//left point ztree start

	function load2left(subjectId) {

		var left_point_setting = {
			check : {
				enable : true,
				chkboxType : {
					"Y" : "s",
					"N" : "s"
				}
			},
			view : {
				showLine : true,
				showIcon : true,
				selectedMulti : false
			},
			data : {
				simpleData : {
					enable : true,
					idKey : 'id',
					pIdKey : 'parentId',
					rootPid : ''
				},
				key : {
					name : 'name',
					title : 'title'
				}
			},
			async : {
				enable : true,
				type : "post",
				contentType : "application/json",
				dataType : "json",
				url : "${ctx}/admin/kpoint/query/${courseId}",
				otherParam : {},
				dataFilter : ajaxDataFilter
			},
			callback : {
				onClick : left_point_treeOnclick
			}
		};

		$.fn.zTree.init($("#left_point_ztreedemo"), left_point_setting);
		var treeObj = $.fn.zTree.getZTreeObj("left_point_ztreedemo");
		treeObj.expandAll(true);
	}

	function ajaxDataFilter(treeId, parentNode, responseData) {
		var _responseData = responseData.entity;
		if (_responseData) {
			for (var i = 0; i < _responseData.length; i++) {
				_responseData[i].title = (_responseData[i].name + _responseData[i].id);
				_responseData[i].name = (_responseData[i].name);
			}
		}
		return _responseData;
	}
	function left_point_treeOnclick(e, treeId, treeNode) {
		$("#id").val(treeNode.id);
		$("#name").val(treeNode.name);
		$("#type").val(treeNode.type);
		$("#videourl").val(treeNode.videourl);
		$("#sort").val(treeNode.sort);
		$("#playcount").val(treeNode.playcount);
		$("#isfree").val(treeNode.isfree);
		$("#videotype").val(treeNode.videotype);
		$("#courseMinutes").val(treeNode.courseMinutes);
		$("#courseSeconds").val(treeNode.courseSeconds);		
		$("#tcId").val(treeNode.teacherId);
		$("#type").attr("disabled",true);
		querytecher();
		$("#type").change();
		$("#videotype").change();
		var zTree = $.fn.zTree.getZTreeObj("right_point_ztreedemo");
		var parentNode = zTree.getNodeByParam("id", treeNode.parentId, null);
		if (parentNode != null) {
			$("#pIdHidden").val(parentNode.id);
			$("#pIdName").val(parentNode.name);
		} else {//没有父级
			$("#pIdHidden").val(0);
			$("#pIdName").val("根目录");
		}
		closerighttree();
	}

	// left point ztree end

	//right point ztree start
	var right_point_setting = {
		view : {
			showLine : true,
			showIcon : true,
			selectedMulti : false
		},
		data : {
			simpleData : {
				enable : true,
				idKey : 'id',
				pIdKey : 'parentId',
				rootPid : ''
			},
			key : {
				name : 'name',
				title : 'title'
			}
		},
		callback : {
			onClick : right_point_treeOnclick
		}
	};
	//右侧选择时赋值
	function right_point_treeOnclick(e, treeId, treeNode) {
		$("#pIdHidden").val(treeNode.id);
		$("#pIdName").val(treeNode.name);
		closerighttree();
	}

	function selectrighttreeroot() {
		$("#pIdHidden").val(0);
		$("#pIdName").val("根目录");
		closerighttree();
		closerighttree();
	}
	function showrightZtree() {
		$("#disrighttree").show();
	}
	function closerighttree() {
		$("#disrighttree").hide();
	}
	// right point ztree end

	$().ready(function() {
		newPoint();
	});

	//切换专业时加载树
	function loadztree() {
		load2left("");
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${ctx}/admin/kpoint/querytype/${courseId}",
			data : {},
			async : false,
			success : function(result) {
				//$.fn.zTree.init($("#left_point_ztreedemo"), left_point_setting, result.entity);
				 $.fn.zTree.init($("#right_point_ztreedemo"), right_point_setting, result.entity);
			}
		});
		var treeObj = $.fn.zTree.getZTreeObj("left_point_ztreedemo");
		treeObj.expandAll(true);
	}

	function newPoint() {
		$("#name").val("");
		$("#type").val("0");
		$("#videourl").val("");
		$("#sort").val(0);
		$("#playcount").val(0);
		$("#isfree").val("1");
		$("#videotype").val("CC");
		$("#courseMinutes").val("");
		$("#courseSeconds").val("");
		clearTeacher();
		$("#name").val("");
		$("#id").val("0");//0代表添加。否则为更新
		$("#pIdName").val("");
		$("#type").removeAttr("disabled");
		changeType(0);
		$("#videojson").val("");
		$("#type").change();
		$("#videotype").change();
		$('#saveKpointForm')[0].reset();
		$("#pIdHidden").val(0);
	}

	function confirmMessage(str) {
		if (confirm(str)) {
			return true;
		} else {
			return false;
		}
	}
	function deletePoints() {
		if (confirm("确认删除选中的视频？")) {
			var zTree = $.fn.zTree.getZTreeObj("left_point_ztreedemo"), checkCount = zTree
					.getCheckedNodes(true).length;
			if (checkCount > 0) {
				var checkedNodes = zTree.getCheckedNodes(true);
				var arrayObj = new Array()
				$.each(checkedNodes, function(index, val) {
					arrayObj.push(val.id);
				});
				$("#pointIds").val(arrayObj.join(","));
				$.ajax({
					type : "POST",
					dataType : "json",
					url : "${ctx}/admin/kpoint/delBatch",
					data : $("#delPointForm").serialize(),
					async : false,
					success : function(result) {
						if (result.success == true) {
							loadztree();
							alert("提交成功");
						}
					}
				});
			} else {
				alert("未选中视频");
			}
		} else {
			return false;
		}

	}
	
	
	// 复制视频章节
	function copyPoints() {
		if (confirm("确认复制选中的视频？")) {
			var zTree = $.fn.zTree.getZTreeObj("left_point_ztreedemo"), checkCount = zTree
					.getCheckedNodes(true).length;
			if (checkCount > 0) {	
				var checkedNodes = zTree.getCheckedNodes(true);
				var arrayObj = new Array()
				$.each(checkedNodes, function(index, val) {
					arrayObj.push(val.id);
				});
				$("#pointIds").val(arrayObj.join(","));
				// 提示弹框
				kpointDialog($("#pointIds").val());
			} else {
				alert("未选中视频");
			}
		} else {
			return false;
		}
	}

	function copyKpoint(){
		var copyKpoints = $("#copyKpoints").val();
		var copyCourseId = $("#copyCourseId").val();
		$("#backdrop_dialog,#course_id_dialog").remove();
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${ctx}/admin/copy/kpoints",
			data : {"copyKpoints":copyKpoints,"copyCourseId":copyCourseId},
			async : false,
			success : function(result) {
				if (result.success == true) {
					alert("提交成功");
					window.location.reload();
				}else{
					alert(result.message);
				}
			}
		}) 
	}
	
	function savaPoint() {
		if($("#name").val()==null||$("#name").val()==''){
			alert("请输入名称");
			return;
		}
		if($("#type").val()==0){//视频专属验证
			if($("#videourl").val()==null||$("#videourl").val()==''){
				alert("请输入视频地址");
				return;
			}
			if($("#tcId").val()==null||$("#tcId").val()==0){
				alert("请添加教师");
				return;
			} 
			
		}
		
		var date = $("#saveKpointForm").serialize();
		if($("#type").attr("disabled")=='disabled'){
			date+='&courseKpoint.type='+$("#type").val();
		}
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${ctx}/admin/kpoint/add",
			data : date,
			async : false,
			success : function(result) {
				if (result.success == true) {
					alert("提交成功");
					loadztree();
					newPoint();
					$("#type").change();
				}
			}
		});
		
	}

	
	//选择教师
	function showNewwin() {
		window
				.open(
						'${ctx}/admin/teacher/selectlist?teacher.checkboxFalg=radio',
						'newwindow',
						'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
	}
	function addnewTeacherId(newTcIdArr) {
		$("#tcId").val(newTcIdArr);
		querytecher();
	}
	function querytecher() {
		var ids = $("#tcId").val();
		if (ids != null && ids != "") {
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${ctx}/admin/teacher/queryByIds",
				data : {
					"ids" : ids
				},
				async : false,
				success : function(result) {
					if (result.success == true) {
						var str = "";
						var teacherList = result.entity;
						for (var i = 0; i < teacherList.length; i++) {
							var tc = teacherList[i];
							str += '<p style="width:100%;margin: 0 0 0em">'
									+ tc.name + '&nbsp;&nbsp;</p>';
						}
						$("#tchtml").html(str);
					}
				}
			});
		} else {
			$("#tchtml").html("");
		}
	}
	function clearTeacher() {
		$("#tcId").val(0);
		$("#tchtml").html("");
	}
	 //获得56 上传视频的url
	function uploading(){
		$.ajax({
			url : "${ctx}/admin/56/uploadurl",
			data : {},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.success){
					var url = result.entity;
					$("#UploadIframe").attr("src",url);
					$("#UploadIframe").show();
				}
			}
		});
	}
	//上传的回调方法
	function callback(vid,subject,url,result,player,chk,cover,
		coop_public,forbid,coopid,sid,category,attach,tags,content,item_1,msg,iframePlayer){
		if(vid==0){//如果vid等于0则上传失败
			alert(msg);
		}else{
			$("#videourl").val(decodeURIComponent(iframePlayer));
			$("#UploadIframe").hide();
				var values = decodeURIComponent(vid)+","+
				decodeURIComponent(subject)+","+
				decodeURIComponent(url)+","+
				decodeURIComponent(result)+","+
				decodeURIComponent(player)+","+
				decodeURIComponent(chk+cover)+","+
				decodeURIComponent(coop_public)+","+
				decodeURIComponent(forbid)+","+
				decodeURIComponent(coopid)+","+
				decodeURIComponent(sid)+","+
				decodeURIComponent(category)+","+
				decodeURIComponent(attach)+","+
				decodeURIComponent(tags)+","+
				decodeURIComponent(content)+","+
				decodeURIComponent(item_1);
			$("#videojson").val(values);
		}
	}
	
	var video_unique;//视频唯一标示码
	var video_json;//乐视json
	function initLetv(){//乐视云上传
		if($("#name").val()==null||$("#name").val()==''){
			alert("请输入视频名称");
			return;
		}
		$.ajax({
			url:"${ctx}/admin/letv/uploadurl",
			type:"post",
			data:{"videoName":$("#name").val(),"courseId":$("#courseId").val()},
			dataType:"json",
			success:function(result){
				if(result.message){
					//var upload_url=result.entity.upload_url;
					//video_id=result.entity.video_id;
					video_unique=result.entity.letvmap.video_unique;
					video_json=result.entity.letvjson;
					//$("#uploadLetv").attr("action",upload_url);
					$("#precent").html(result.entity.letvmap.flash_upload);
				}else{
					alert(result.message);
				}
			}
		});	
	}
	function letv_callback(){
		$("#videourl").val(video_unique);
		$("#videojson").val(video_json);
		$("#precent").html("");
		}
</script>
<style>
.tdLabel {
	align:center;
}

.label {
	color: red
}
</style>
</head>
<body>
		<div class="page_head">
			<h4>
				<em class="icon14 i_01"></em>&nbsp;<span>视频管理</span> &gt; <span>视频树</span>
			</h4>
		</div>
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th colspan="2" align="left"><span>视频树<font color="red">(*树级结构最多支持二级)</font></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="35%">
						<form action="${ctx}/admin/point/delPointByPointId" method="post" id="delPointForm">
							<input type="hidden" id="pointIds" name="pointIds" value="" />
							<div id="left_point_ztreedemo" class="ztree">请添加视频！</div>
						</form>
						<br/>
						<div style="bottom:0px;margin-top: 10px;">
							<p  style="margin-left: 10px; color: red;">
								1.点击已有的视频节点可以显示节点信息并进行修改<br/>
								2.选中节点前的复选框，点击删除，可删除视频节点<br/>
								3.点击新建视频，建立新的视频节点<br/>
								4.点击提交可以保存或修改视频节点信息<br/>
								5.复制章节只能选择一级节点，不能单选二级节点<br/>
							</p>
							<br/>
						<input class="btn ml10 btn-danger" type="button" onclick="deletePoints()" value="删 除" /> 
						<input class="btn ml10" type="button" onclick="newPoint()" value="新建视频" />
						<input class="btn ml10" type="button" onclick="copyPoints()" value="复制章节" />
						</div>
					</td>
					<td width="80%">
						<form action="${ctx}/admin/point/savePoint" method="post" id="saveKpointForm">
							<input type="hidden" name="courseKpoint.videojson" value="" id="videojson" /> 
							<input type="hidden" name="courseKpoint.id" value="0" id="id" /> 
							<input type="hidden" name="courseKpoint.courseId" value="${courseId}" id="courseId" /> 
							<input type="hidden" name="courseKpoint.teacherId" value="0" id="tcId" />
							<input type="hidden" name="courseKpoint.playcount" value="0" id="playcount" />
							<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
								<thead>
									<tr>
										<th colspan="2" align="left"><span>视频 <tt class="c_666 ml20 fsize12">
													（<font color="red">*</font>&nbsp;为必填项）
												</tt></span></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td align="center"><font color="red">*</font>&nbsp;类型</td>
										<td>
											<select id="type" onchange="changeType()" name="courseKpoint.type">
												<option value="0">视频</option>
												<option value="1">文件夹</option>
											</select>
											<!-- <input type="hidden" name="courseKpoint.type" id="typeHidden" value=""/> -->
										</td>
									</tr>
									<tr class="parentHidden">
										<td align="center"><font color="red">*</font>&nbsp;父级</td>
										<td><input type="text" id="pIdName" readonly="readonly" value="根目录" onclick="showrightZtree()" /> <input type="hidden" name="courseKpoint.parentId" value="" id="pIdHidden" class="{required:true}" />
											<div id="disrighttree" style="display: none">
												<a class="btn smallbtn btn-y" onclick="selectrighttreeroot()">根目录</a> <a class="btn smallbtn btn-y" onclick="closerighttree()">关闭</a>
												<div id="right_point_ztreedemo" class="ztree"></div>
											</div>
										</td>
									</tr>
									<tr>
										<td align="center"><font color="red">*</font>&nbsp;名称</td>
										<td><input type="text" name="courseKpoint.name" value="" id="name" class="{required:true}" /></td>
									</tr>
                                    <tr class="videoTr">
                                        <td align="center"><font color="red">*</font>&nbsp;视频类型</td>
                                        <td><select name="courseKpoint.videotype" id="videotype">
                                            <option value="CC">CC</option>
                                            <option value="FIVESIX">56</option>
                                            <option value="SWF">SWF</option>
                                            <option value="LETV">乐视</option>
                                            <option value="BAOLI">保利威视</option>
                                            <option value="mp4flv">本地视频</option>
                                            
                                        </select></td>
                                    </tr>
									
									<tr class="videoTr">
										<td align="center"><font color="red">*</font>&nbsp;<span id="videoUrlTitle">视频地址</span></td>
										<td><input type="text" name="courseKpoint.videourl" value="" id="videourl" class="{required:true}" style="width: 385px"/></td>
									</tr>
									<tr>
										<td align="center"><font color="red">*</font>&nbsp;排序</td>
										<td><input type="text" name="courseKpoint.sort" value="0" id="sort" class="{required:true,number:true}" /><font color="red">（从小到大排序）</font></td>
									</tr>
									<!-- <tr class="videoTr">
										<td align="center"><font color="red">*</font>&nbsp;播放次数</td>
										<td><input type="text" name="courseKpoint.playcount" value="0" id="playcount" class="{required:true,number:true}" /></td>
									</tr> -->
									<tr class="videoTr">
										<td align="center"><font color="red">*</font>&nbsp;是否试听</td>
										<td><select name="courseKpoint.isfree" id="isfree">
												<option value="1">是</option>
												<option value="2">否</option>
										</select></td>
									</tr>									
									<tr class="videoTr">
										<td align="center"><font color="red">*</font> &nbsp;添加教师</td>
										<td>
											<div id="tchtml"></div> <a href="javascript:showNewwin()" title="添加教师" class="ml10 btn smallbtn btn-y">添加教师</a> <a href="javascript:clearTeacher()" title="清空" class="ml10 btn smallbtn btn-y">清空</a>
										</td>
									</tr>
									<tr class="videoTr">
										<td align="center"><font color="red">*</font>&nbsp;时长</td>
										<td><input type="text" name="courseKpoint.courseMinutes" value="0" style="width: 50px" id="courseMinutes" class="{required:true,number:true}" /> 分 <input type="text" name="courseKpoint.courseSeconds" value="0" style="width: 50px" id="courseSeconds" class="{required:true,number:true}" /> 秒</td>
									</tr>
									<tr class="playType " style="display:none;">
							    		<td align="right"><font color="red">*</font>视频上传：</td>
							    		<td class="lists_tleft"> 
							    			<input type="button" value="视频上传" onclick="uploading()">
							    		</td>
							    	</tr>
							    	<tr class="playType " style="display:none;">
							    		<td align="right"></td>
							    		<td class="lists_tleft"> 
							    			<iframe style="display: none;width:462px;height:180px" id="UploadIframe" src="">
											</iframe>
							    		</td>
							    	</tr>
							    	<tr class="playTypeLETV " style="display:none;">
							    		<td align="right"><font color="red">*</font>视频上传：</td>
							    		<td class="lists_tleft"> 
							    			<input type="button" value="视频上传" onclick="initLetv()">
							    		</td>
							    	</tr>
							    	<tr class="playTypeLETV " style="display:none;">
							    		<td align="right"></td>
							    		<td class="lists_tleft" id="precent"> 
							    			
							    		</td>
							    	</tr>
									<tr>
										<td colspan="2" align="center"><input class="btn btn-danger" type="button" onclick="savaPoint()" value="提 交" /></td>
									</tr>
								</tbody>
							</table>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
</body>
</html>
