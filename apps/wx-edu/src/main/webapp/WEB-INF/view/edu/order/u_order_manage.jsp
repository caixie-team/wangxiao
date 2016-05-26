<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>订单管理</title>
</head>
<body>
<article>
    <header class="uc-com-title">
        <span>订单管理</span>
    </header>
    <div class="u-title-box u-order-title">
        <ol class="js-tap clearfix">
            <li ><a href="${ctx}/uc/order" title="">全部</a></li>
            <li><a href="${ctx}/uc/order?trxStatus=SUCCESS" title="">已支付</a></li>
            <li><a href="${ctx}/uc/order?trxStatus=INIT" title="">未支付</a></li>
            <li><a href="${ctx}/uc/order?trxStatus=CANCEL" title="">已取消</a></li>
            <li><a href="${ctx}/uc/order?trxStatus=REFUND" title="">已退款</a></li>
        </ol>
    </div>
    <c:if test="${empty trxorderList}">
        <section class="mt30 mb30 tac">
            <em class="no-data-ico cTipsIco">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">还没有订单记录~</span>
        </section>

    </c:if>
    <c:if test="${not empty trxorderList}">
        <form action="${ctx}/uc/order" id="searchForm" method="post">
            <input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
            <input type="hidden" name="trxStatus" value="${payStatusRe}"/>
        </form>
        <!-- pc端订单列表 开始-->
        <section class="pc-order-list">
            <div class="i-box">
                <c:forEach var="trxorder"  items="${trxorderList}">
                    <div class="mt20">
                        <table class="u-order-table" cellspacing="0" cellpadding="0" border="0" >
                            <thead>
                                <tr>
                                    <th colspan="3">
                                        <div>
                                            <span class="c-999 f-fM"><fmt:formatDate value="${trxorder.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " /> </span>
                                            <span class="c-999 f-fM ml20">订单号：${trxorder.requestId} </span>
                                            <span class="c-999 f-fM ml20">优惠：<tt class="c-green">￥ ${trxorder.couponAmount}</tt> </span>
                                        </div>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${trxorder.trxorderDetailList}" var="detail" varStatus="index">
                                    <tr>
                                        <td width="70%">
                                            <div class="c-cou-item">
                                                <a class="u-ol-pic" title="" href="javascript:void(0)">
                                                    <c:if test="${not empty detail.courseImgUrl}">
                                                        <img src="<%=staticImageServer%>/${detail.courseImgUrl}" xsrc="" alt="" class="img-responsive">
                                                    </c:if>
                                                    <c:if test="${empty detail.courseImgUrl}">
                                                        <img src="<%=staticImageServer%>/${courseimagemap.courseimage.url}" xsrc="" alt="" class="img-responsive">
                                                    </c:if>
                                                </a>
                                                <h6 class="hLh30 of">
                                                    <a class="fsize14 c-666" title="" href="${ctx}/front/couinfo/${detail.courseId}">${detail.courseName}</a>
                                                </h6>
                                                <section class="hLh30 of">
                                                    <p class="c-999 f-fA">${detail.courseTitle}</p>
                                                </section>
                                            </div>
                                        </td>
                                        <td class="tac" width="15%"><span class="c-master fsize14">￥<font>${detail.currentPirce}</font></span> </td>
                                        <c:if test="${index.index==0}">
                                            <td class="tac" width="15%" rowspan="${trxorder.trxorderDetailList.size()}">
                                                <div class="tac">
                                                    <p class="hLh30"><span class="fsize16 c-666 f-fG">￥${trxorder.orderAmount}</span></p>
                                                    <c:if test="${trxorder.trxStatus=='INIT'}">
                                                        <p class="hLh20 c-999 f-fA"><a class="c-blue f-fM" href="javascript:void(0)" onclick="gotoPay(${trxorder.id})">继续支付</a></p>
                                                        <p><a class="c-999 f-fM" href="${ctx}/cancleoder/${trxorder.id}">取消</a></p>
                                                    </c:if>
                                                    <p><a href="${ctx}/uc/odetail/${trxorder.id}" class="c-999 f-fM">详情</a></p>
                                                </div>
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:forEach>
            </div>
        </section>
        <!-- pc端订单列表  结束-->
        <!-- 移动端订单列表 开始-->
        <section class="phone-order-list">
            <c:forEach var="trxorder"  items="${trxorderList}">
                <dl class="mt15">
                    <dt class="clearfix">
                        <span class="fl">订单号：${trxorder.requestId} </span>
                        <span class="fr c-master">
                            <c:choose>
                                <c:when test="${trxorder.trxStatus == 'INIT'}">未支付</c:when>
                                <c:when test="${trxorder.trxStatus == 'SUCCESS'}">已支付</c:when>
                                <c:when test="${trxorder.trxStatus == 'CACULE'}">已取消</c:when>
                                <c:otherwise>已退款</c:otherwise>
                            </c:choose>
                        </span>
                    </dt>
                    <c:forEach items="${trxorder.trxorderDetailList}" var="detail" varStatus="index">
                        <dd>
                            <div class="p-order-box">
                                <a class="phone-ol-pic" title="" href="javascript:void(0)">
                                    <c:if test="${not empty detail.courseImgUrl}">
                                        <img src="<%=staticImageServer%>${detail.courseImgUrl}" xsrc="" alt="" class="img-responsive">
                                    </c:if>
                                    <c:if test="${empty detail.courseImgUrl}">
                                        <img src="${ctx}/static/common/images/default_goods.png" xsrc="" alt="" class="img-responsive">
                                    </c:if>
                                </a>
                                <h6 class="hLh30 txtOf">
                                    <a class="fsize12 c-666" title="" href="javascript:void(0)">${detail.courseName }</a>
                                </h6>
                                <section class="hLh30">
                                    <p class="c-999 f-fA txtOf">${detail.courseTitle }</p>
                                </section>
                                <div class="o-ol-price"><span class="c-333 fsize12 f-fM">￥${detail.currentPirce }</span></div>
                            </div>
                            <c:if test="${index.index==trxorder.trxorderDetailList.size()-1}">
                                <div class="p-order-more hLh30">
                                    <div class="fr"><span class="fsize12 c-666 f-fM">实付款<small class="">￥</small><tt class="c-red fsize14 f-fG">${trxorder.orderAmount}</tt></span></div>
                                    <div class="fl"><span class="fsize12 c-666"><fmt:formatDate value="${trxorder.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " /></span></div>
                                    <div class="clear"></div>
                                </div>
                                <div class="p-order-oper">
                                    <div class="fr">
                                        <c:if test="${trxorder.trxStatus=='INIT'}">
                                            <a href="javascript:void(0)" onclick="gotoPay(${trxorder.id})">付款</a>
                                            <a href="${ctx}/cancleoder/${trxorder.id}" class="ml10">取消</a>
                                        </c:if>
                                        <a href="${ctx}/uc/odetail/${trxorder.id}" class="ml10">详情</a>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                            </c:if>
                        </dd>
                    </c:forEach>
                </dl>
            </c:forEach>
        </section>
        <!-- 移动端订单列表 结束-->
        <jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
    </c:if>

</article>
<script type="text/javascript">
    $(function() {
   		var payStatus = '${payStatusRe}';
        var status = 0;
        if (payStatus == '') {

        }else if(payStatus == 'SUCCESS'){
            status = 1;
        }else if(payStatus == 'INIT'){
            status = 2;
        }else if(payStatus == 'CANCEL'){
            status = 3;
        }else if(payStatus == 'REFUND'){
            status = 4;
        }
        $(".js-tap li").removeClass("current");
        $(".js-tap li").eq(status).addClass("current"); 
    });
    function gotoPay(id){
        $.ajax({
            url:"${ctx}/front/repaycheck/"+id,
            type:"post",
            dataType:"json",
            success:function(result){
                if(result.message=='true'){
                    window.location.href='${ctx}/front/repay/'+id;
                }else{
                    dialogFun('错误提示',result.message,0);
                }
            }
        })
    }
</script>
</body>
</html>
