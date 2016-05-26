<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>视频树</title>
<script type="text/javascript"
	src="${ctximg}/static/common/Sortable/Sortable.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/Sortable/Sortable.min.js?v=${v}"></script>
<script type="text/javascript">
function ckick(id){
	window.open("${ctx}/front/couinfo/"+id+"");
	
}
</script>
<style>	
.img{
height:100px;
width:150px;
}
.ull li{
list-style-type:none;
}
#divs{
float:left;
}
</style>
</head>
<body>
   
    <c:forEach items="${Package }" var="pack">
     <div id="divs" style="border:1px;display:inline;">
     <ul class="ull" style="width:150px">
      <li><img class="img" src="<%=staticImageServer %>${pack.logo}"></img></li>
      <li><span><a onclick="ckick(${pack.id})" href="">${pack.name}</a></span></li>
       <li><span>${pack.title}</span></li>
      <li><span> 课时：${pack.lessionnum}</span>
      <span>价格：${pack.sourceprice}￥</span></li>
      </ul>
      </div>
    </c:forEach>
    
</body>
</html>
