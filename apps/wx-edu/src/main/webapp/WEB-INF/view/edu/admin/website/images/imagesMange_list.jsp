<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>图片类型管理</title>
    <script type="text/javascript">
        function deleteImagesMange(id) {
        	dialogFun("提示","确定删除吗？",2,"${ctx}/admin/website/deleteImagesMange/" + id);
        }
        function checkAll(box){
        	$("input[name='ids']").prop("checked",box.checked);
        }
        function delImageMangebatch(){
        	var ids="";
        	var check=false;
        	var imageMangeEle=$("input[name='ids']:checked");
        	for(var i=0;i<imageMangeEle.length;i++){
        		ids+=imageMangeEle[i].value;
        		if(i!=imageMangeEle.length-1){
        			ids+=",";
        		}
        		check=true;
        	}
        	if(!check){
        		dialogFun("提示","请选择要删除图片类型",0);
        		return;
        	}
        	dialogFun("提示","确定删除吗？",2,"javascript:delAction('"+ids+"')");
        	
        }
        function delAction(ids){
        	$.ajax({
        		url : baselocation+"/admin/website/delImageMangebatch",
        		data : {"ids":ids},
        		type : "post",
        		dataType : "json",
        		success : function(result){
        			if(result.success){
        				window.location.reload();
        			}else{
        				dialogFun("提示","系统繁忙",0);
        			}
        		}
        	})
        }
    </script>
</head>
<body>
<!-- 公共右侧样式 -->
	<div class="am-cf">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">图片类型管理</strong> / <small>图片类型列表</small></div>
    </div>
    <hr/>
<!-- 公共右侧标题样式 结束-->
<div class="mt20">
   	<div class="am-g">
      <div class="am-u-md-6">
        <div class="am-btn-toolbar">
          <div class="am-btn-group am-btn-group-xs">
            <button class="am-btn am-btn-success" type="button" onclick="window.location.href='${ctx}/admin/website/toAddImagesMange'" title="新建图片类型"><span class="am-icon-plus"></span> 新增</button>
            <button class="am-btn am-btn-success" type="button" onclick="delImageMangebatch()" title="删除"><span class="am-icon-trash-o"></span> 删除</button>
          </div>
        </div>
      </div>
    </div>
</div>
<div class="am-g">
      <div class="mt20">
          <table class="am-table am-table-bordered am-table-striped am-text-nowrap">
            <thead>
            <tr>
                <th width="10%">
                	<label class="am-checkbox">
	                	<input type="checkbox" data-am-ucheck onclick="checkAll(this)"/>ID
	                </label>
                </th>
                <th width="25%">类型</th>
                <th width="25%">key值</th>
                <th width="20%">类型</th>
                <th width="20%">操作</th>
            </tr>
            </thead>
            <tbody id="tabS_02" align="center">
            <c:if test="${websiteImageMangeList.size()>0}">
                <c:forEach items="${websiteImageMangeList}" var="tc">
                    <tr id="rem${tc.id }">
                        <td>
                        <label class="am-checkbox">
                        <input data-am-ucheck type="checkbox" name="ids" value="${tc.id }"/>${tc.id }
	                </label>
                        </td>
                        <td>${tc.type }</td>
                        <td>${tc.image_key }</td>
                        <td><fmt:formatDate type="both" value="${tc.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                         <td>
                            <div class="am-btn-toolbar">
			                  <div class="am-btn-group am-btn-group-xs">
			                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${ctx}/admin/website/toUpdateImagesMange/${tc.image_key}'"><span class="am-icon-pencil-square-o"></span> 编辑</button>
			                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="deleteImagesMange(${tc.id})"><span class="am-icon-trash-o"></span> 删除</button>
			                  </div>
			                </div>
                         </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${websiteImageMangeList.size()==0||websiteImageMangeList==null}">
                <tr>
               		<td colspan="16">
            					<div data-am-alert=""
							class="am-alert am-alert-secondary mt20 mb50">
							<center>
								<big> <i class="am-icon-frown-o vam"
									style="font-size: 34px;"></i> <span class="vam ml10"> 还没有图片类型信息！</span></big>
							</center>
						</div>
					</td>
		        </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
