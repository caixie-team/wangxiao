<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>发送短信消息</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-form.js"></script>
<script type="text/javascript">
	
	function sendmessage(){
		var sendLinkss = $("#pepole").val();
		  if(sendLinkss.trim()==''){
		 	 
		 	dialogFun("提示","短信接收人不能为空",0);
		 	 return false;
		 }
		 var valL = sendLinkss.split(",").length - 1;
		 var sendInfo=$("#message").val();
		 if(sendInfo.trim()==''){
		 	 
		 	dialogFun("提示","短信内容不能为空！",0); 
		 	 return false;
		 }
		 if(sendInfo.length>70){
		 	
		 	dialogFun("提示","短信内容过长，请按要求输入！",0);
		 	return false;
		 }
		 var sendType=$("#type").val();
		 var sendTime=$("#sendDate").val();
		 if(sendType==2){
			 var nowTime=new Date().getTime();
			 var sendDate=Date.parse(sendTime.replace(/-/gi,"/"));
			 if(sendTime==""||sendTime==null){
				 
				 dialogFun("提示","发送时间不能为空",0);
				 return false;
			 }else if(sendDate<nowTime){
				 
				 dialogFun("提示","定时发送时间必须大于当前系统时间",0);
				 return false;
			 }
		 }
		 
		 dialogFun("提示","你确定发送？",2,"javascript:sendmessages()");
			
		}
	
	
	
	function sendmessages(){
		var sendLinkss = $("#pepole").val();
		var valL = sendLinkss.split(",").length - 1;
		 var sendInfo=$("#message").val();
		 var sendType=$("#type").val();
		 var sendTime=$("#sendDate").val();
		$.ajax({  
			url : "${ctx}/admin/user/sendMsg",  
			data : {
					"linksman" : sendLinkss,
					"content" : sendInfo,
					"sendType" : sendType,
					"sendTime" : sendTime
					 },  // 参数  
			type : "post",  
			async : false,
			dataType : "json",  //返回json数据 
			success:function (result){
                if(result.message=='发送成功'&&sendType==1){
					window.location.href = "/admin/user/progressbar?type=2";
                }else{
					
					 dialogFun("提示",result.message,0);
					window.location.href = "/admin/user/sendMsglist";
				}

			}
		});
	}
	
		function changeType(){
			if($("#type").val()==1){
				$("#sendTr").hide();
			}else{
				$("#sendTr").show();
			}
		}
		
		//选择用户手机号
		function showNewwin(){
			window.open('${ctx}/admin/user/select_userlist/1','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
		} 
		//显示 去重
		function addnewUserId(newUserPhoneArr){
		    var phoneIds=new Array();
			if($("#pepole").val().trim()!=""){
				phoneIds=$("#pepole").val().split(",");
			}
			phoneIds = phoneIds.concat(newUserPhoneArr);  
			phoneIds.sort();
			phoneIds = uniqueArray(phoneIds);
			$("#pepole").val(phoneIds);
		}
		function uniqueArray(a){
		    temp = new Array();
		    for(var i = 0; i < a.length; i ++){
		        if(!contains(temp, a[i])){
		            temp.length+=1;
		            temp[temp.length-1] = a[i];
		        }
		    }
		    return temp;
		}
		function contains(a, e){
		    for(j=0;j<a.length;j++)if(a[j]==e)return true;
		    return false;
		}
		
		function importExcel(){
			var myFile = $("#myFile").val();
			if(myFile.length <= 0){
			
			 dialogFun("提示","请选择导入内容",0);
			return false;
			}
			$("#importP").submit();
		}
		
		//form 以ajax提交
		$(function() {  
	        $("#importP").submit(function(){  
	            $(this).ajaxSubmit({  
	                type:"post",  //提交方式  
	                dataType:"json", //数据类型  
	                url:"${ctx}/admin/user/importMsgExcel/1", //请求url  
	                success:function(result){ //提交成功的回调函数  
	                	if(result.success==true){
	                		
	                		 dialogFun("提示","导入成功",1);
	                		$("#pepole").val(result.entity);
	                	}else{
	                		
	                		 dialogFun("提示",result.message,0);
	                	}
	                }  
	            });  
	            return false; //不刷新页面  
	        });  
	    });  
</script>

</head>
<body  >
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">短信管理</strong> / <small>发送短信消息</small></div>
</div>
<hr/>
<div class="mt20">
 <div class="am-tabs">
    <ul class="am-tabs-nav am-nav am-nav-tabs">
      <li class="am-active"><a href="#tab1">基本信息</a></li>
    </ul>
    <div class="am-tabs-bd">
		<form class="am-form" action="/admin/user/importMsgExcel/1" method="post" id="importP" enctype="multipart/form-data">
				<div class="am-g am-margin-top am-form-group">
					<div class="am-u-sm-3 am-text-right">
						<font color="red">*</font>&nbsp;添加联系人
					</div>
					<div class="am-u-sm-6 am-u-end">
						<textarea name="numerStr" style="width: 80%; height: 200px;" id="pepole"></textarea><br />
						
						<font color="red">
						批量导入&nbsp;&nbsp;&nbsp;&nbsp;<a class="am-btn am-btn-danger"  href="/static/common/admin/masterplate/mobile.xls">示例模版</a>
						<br/>
						1、必须是excel格式,详情请参照模版sheet1<br/>
						2、格式不能有误<br/>
						3、记录要挨着输入，不能有空行<br />
						</font>
						<div class="am-form-group am-form-file">
						  <button type="button" class="am-btn am-btn-danger am-btn-sm">
						    <i class="am-icon-cloud-upload"></i> 选择要导入的excel</button>
						  <input id="myFile" type="file" value="" name="myFile" multiple />
						</div>
						<div id="file-list"></div>
						<script>
						  $(function() {
						    $('#myFile').on('change', function() {
						      var fileNames = '';
						      $.each(this.files, function() {
						        fileNames += '<span class="am-badge">' + this.name + '</span> ';
						      });
						      $('#file-list').html(fileNames);
						    });
						  });
						</script>
						<input type="button" value="提交" class="am-btn am-btn-danger"  onclick="importExcel()"/>
						<br/>
						<font color="red">
									规则<br/>
									1、手机号格式:132********  <br/>
									   或者132********,138********,159********<br/>
									2、短信内容,不能超过70汉字或者英文字母 <br/>
									3、发送流程：添加手机号-&gt;设置短信内容&gt;提交发送<br/>
									4、添加手机号时，查询后可以选择添加所选学员及添加所有学员，请慎重选择。<br/>
									5、群发短信最多不能超过1000条
							        6、定时短信会有几分钟的延迟，如有延迟请耐心等待。
						</font>
					</div>
					<div class="am-u-sm-2 am-u-end am-margin-vertical">
						<a class="am-btn am-btn-danger" onclick="showNewwin()" title="添 加学员手机" href="javascript:void(0)">添加学员手机</a>
					</div>
				</div>
				<div class="am-g am-margin-top am-form-group am-form-select">
					<div class="am-u-sm-3 am-text-right">
						<font color="red">*</font>发送方式
					</div>
					<div class="am-u-sm-6 am-u-end"  >
						<select  name="type" onchange="changeType()" data-am-selected="{btnWidth: '20%', btnSize: 'sm', btnStyle: 'secondary'}"  id="type">
                                  <option value="1">正常</option>
                                  <option value="2">定时</option>
                         </select>
					</div>
				</div>
				<div class="am-g am-margin-top am-form-group" id="sendTr" style="display:none" >
					<div class="am-u-sm-3 am-text-right">
						<font color="red">*</font>设置发送时间
					</div>
				<!-- 	<div class="am-u-sm-6 am-u-end">
						<input type="text" readonly="readonly"  name="sendDate" id="sendDate" class="form-datetime-lang am-form-field"/>
					</div> -->
					<div class="am-u-sm-6 am-u-end">
		              <div class="am-form-group am-form-icon">
		                <i class="am-icon-calendar"></i>
		                <input type="text" name="sendDate" id="sendDate" readonly placeholder="发送时间" class="form-datetime-lang am-form-field"/>
		              </div>
		          </div>
				</div> 
				
				<div class="am-g am-margin-top am-form-group">
					<div class="am-u-sm-3 am-text-right">
						<font color="red">*</font>&nbsp;发送的内容
					</div>
					<div class="am-u-sm-6 am-u-end">
						<textarea name="message" id="message"></textarea>
					</div>
				</div>
				
				<div class="am-g am-margin-top am-form-group">
                     <div class="am-u-sm-3 am-u-md-2 am-text-right">&nbsp;</div>
                     <div class="am-u-sm-6 am-u-md-4">
                         <a href="javascript:void(0)" title="发 送" onclick="sendmessage()" class="am-btn am-btn-danger">发 送</a>
                     </div>
                     <div class="am-u-sm-12 am-u-md-6"></div>
                 </div>
			</form>
			</div>
	</div>
</div>
</body>
</html>