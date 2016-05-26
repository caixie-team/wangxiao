<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>广告图</title>
    <script type="text/javascript">
        $(function() {
            var title='${websiteImages.title}'==''?'':'${websiteImages.title}';
            $("#title").val(title);
            var keyWord='${websiteImages.keyWord}'==''?'':'${websiteImages.keyWord}';
            $("#keyWord").val(keyWord);
            var imagesUrl='${websiteImages.imagesUrl}'==''?'':'${websiteImages.imagesUrl}';
            $("#imagesUrl").val(imagesUrl);
            var linkAddress='${websiteImages.linkAddress}'==''?'':'${websiteImages.linkAddress}';
            $("#linkAddress").val(linkAddress);
        });

        function deleteImg() {
            var imgIds = document.getElementsByName("ids");
            var num = 0;
            var ids = '';
            for (var i = 0; i < imgIds.length; i++) {
                if (imgIds[i].checked == true) {
                    num++;
                    ids += imgIds[i].value;
                    if (i != imgIds.length - 1) {
                        ids += ",";
                    }
                }
            }
            if (num == 0) {
                dialogFun("提示","请选择要删除的广告图",0);
                return;
            }
            dialogFun("提示","确认删除这些数据吗？",2,"javascript:delsAction('"+ids+"')");
        }

        function allCheck(cb) {
            $("input[name=ids]").prop('checked', cb.checked);
        }


        function cancel() {
            $("#title").val("");
            $("#imagesUrl").val("");
            $("#linkAddress").val("");
            $("#keyWord").val("");
            $("#keyWord").find('option').eq(0).attr('selected', true);
        }
        function delImg(id){
        	dialogFun("提示","确定删除吗？",2,"javascript:delAction("+id+")");
        }        
        function delAction(id){
        	$.ajax({
        		url:"${ctx}/admin/website/delImages/"+id,
        		type:"post",
        		dataType:"json",
        		success:function(result){
        			if(result.message=='true'){
        				window.location.reload();
        			}
        		}
        	});
        }
        function delsAction(ids){
        	$.ajax({
                url: "${ctx}/admin/website/delImages/" + ids,
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.message == 'true') {
                        alert("删除成功");
                        window.location.reload();
                    }
                }
            });
        }
    </script>

</head>
<body>
<!-- 全局公共右内容区  开始 -->
		<!-- 公共右侧样式 -->
		<div class="am-cf">
	      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">广告图管理</strong> / <small>广告图列表</small></div>
	    </div>
	    <hr/>
	    <!-- 公共右侧标题样式 结束-->
		<form class="am-form" action="${ctx}/admin/website/imagesPage" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	    <div class="mt20 am-padding admin-content-list">
	    	<div class="am-tab-panel am-fade am-active am-in">
		        <div class="am-g am-margin-top am-u-sm-5">
		            <div class="am-u-sm-4 am-text-right">
		            	 标题
		            </div>
		            <div class="am-u-sm-8">
		              <input type="text" class="am-input-sm" name="websiteImages.title" id="title" value="${websiteImages.title}"/>
		            </div>
		        </div>
				<div class="am-g am-margin-top am-u-sm-5">
		            <div class="am-u-sm-4 am-text-right">
		              	图片URL
		            </div>
		            <div class="am-u-sm-8">
		              <input type="text" class="am-input-sm" name="websiteImages.imagesUrl" id="imagesUrl" value="${websiteImages.imagesUrl}"/>
		            </div>
		        </div>
		        <div class="mt20 clear"></div>
		 		<div class="am-g am-margin-top am-u-sm-5">
		            <div class="am-u-sm-4 am-text-right">
		             	 类型
		            </div>
		            <div class="am-u-sm-8 ">
                        <select name="websiteImages.keyWord" id="keyWord" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						  <option value="">--请选择关键字--</option>
                            <c:forEach items="${websiteImageMangeList}" var="imageType">
                                <option value="${imageType.image_key} ">${imageType.type}</option>
                            </c:forEach>
						</select>
					</div>
		        </div>
				<div class="am-g am-margin-top am-u-sm-5">
		            <div class="am-u-sm-4 am-text-right">
		              	链接URL
		            </div>
		            <div class="am-u-sm-8">
		              <input type="text" class="am-input-sm" name="websiteImages.linkAddress" id="linkAddress" value="${websiteImages.linkAddress}"/>
		            </div>
		        </div>
		        <div class="mt20 clear"></div>
		     </div>
	    </div>
	    <div class="mt20">
	    	<div class="am-g">
		      <div class="am-u-md-6">
		        <div class="am-btn-toolbar">
		          <div class="am-btn-group am-btn-group-xs">
		            <button class="am-btn am-btn-success" type="button" onclick="window.location.href='${cxt}/admin/website/doAddImages'" title="新增"><span class="am-icon-plus"></span> 新增</button>
		            <button class="am-btn am-btn-success" type="button" onclick="deleteImg()" title="删除"><span class="am-icon-trash-o"></span> 删除</button>
		          </div>
		        </div>
		      </div>
		      <div class="am-u-sm-12 am-u-md-3">
		        <div class="am-input-group am-input-group-sm">
		          <span class="am-input-group-btn">
		            <button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
		            	<span class="am-icon-search"></span> 搜索
		            </button>
		            <button type="button" class="am-btn am-btn-danger" onclick="cancel()">
		            	清空
		            </button>
		          </span>
		        </div>
		      </div>
		    </div>
	    </div>
	    </form>
	    
	    <!-- 表格样式 二   开始-->
	    	<div class="am-g">
		      <div class="mt20">
		          <table class="am-table am-table-striped am-table-hover table-main">
		            <thead>
		              <tr>
		                <th>
		                 <label class="am-checkbox">
		                <input type="checkbox" data-am-ucheck onclick="allCheck(this)"/>
		                </label>
		                </th>
		                <th width="10%" class="table-id">ID</th>
		                <th width="10%">图片标题</th>
		                <th width="20%">图片URL</th>
		                <th width="15%">链接URL</th>
		                <th width="10%">背景颜色</th>
		                <th width="10%">类型</th>
		                <th width="5%">序号</th>
		                <th width="20%">操作</th>
		              </tr>
		          </thead>
		          <tbody>
		          	<c:if test="${websiteImagesList.size()>0}">
	                    <c:forEach items="${websiteImagesList}" var="images">
	                        <tr>
	                            <td>
	                            <label class="am-checkbox">
	                            	<input type="checkbox" data-am-ucheck name="ids" value="${images.id}"/>
								 </label>
	                            </td>
	                            <td>${images.id }</td>
	                            <td>${images.title }</td>
	                            <td>${images.imagesUrl }</td>
	                            <td>${images.linkAddress }</td>
	                            <td>${images.color }</td>
	                            <td>${images.typeName }</td>
	                            <td>${images.seriesNumber}</td>
	                            <td>
		                            <div class="am-btn-toolbar">
					                  <div class="am-btn-group am-btn-group-xs">
					                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${ctx}/admin/website/doUpdateImages/${images.id}'"><span class="am-icon-pencil-square-o"></span> 编辑</button>
					                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only" onclick="window.open('${staticUrl}${images.imagesUrl}')"><span class="am-icon-search-plus"></span> 预览</button>
					                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="delImg(${images.id})"><span class="am-icon-trash-o"></span> 删除</button>
					                  </div>
					                </div>
	                            </td>
	                        </tr>
	                    </c:forEach>
	                </c:if>
	                <c:if test="${websiteImagesList.size()==0||websiteImagesList==null}">
	                    <tr>
	                		<td colspan="9">
              					<div data-am-alert=""
									class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam"
											style="font-size: 34px;"></i> <span class="vam ml10">还没有广告图！</span></big>
									</center>
								</div>
							</td>
				        </tr>
	                </c:if>
		          </tbody>
		        </table>
		           <!-- /pageBar begin -->
			        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
			       <!-- /pageBar end -->
		      </div>
		    </div>
	    <!-- 表格样式 二   结束-->
<!-- 全局公共右内容区  开始 -->
</body>
</html>