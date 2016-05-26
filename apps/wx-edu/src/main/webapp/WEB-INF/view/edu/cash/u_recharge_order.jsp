<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>充值订单管理</title>
</head>
<body>
<article>
    <header class="uc-com-title">
        <span>充值订单</span>
    </header>
    <div class="u-title-box u-order-title">
        <ol class="js-tap clearfix">
            <li class="current"><a href="${ctx}/uc/cash/order" title="">全部</a></li>
            <li><a href="${ctx}/uc/cash/order?trxStatus=SUCCESS" title="">已支付</a></li>
            <li><a href="${ctx}/uc/cash/order?trxStatus=INIT" title="">未支付</a></li>
            <li><a href="${ctx}/uc/cash/order?trxStatus=CANCEL" title="" style="border: none;">已取消</a></li>
        </ol>
    </div>
    <div class="i-box">
        <div class="u-m-sub-head">
            <ol class="clearfix tac c-333 fsize14">
                <li><span>充值金额</span></li>
                <li><span>实付款</span></li>
                <li><span>状态</span></li>
                <li><span style="border: none;">交易操作</span></li>
            </ol>
        </div>
        <div>
            <c:if test="${empty cashOrderDTOs}">
                <section class="mt30 mb30 tac">
                    <em class="no-data-ico cTipsIco">&nbsp;</em>
                    <span class="c-666 fsize14 ml10 vam">您还没有充值记录~</span>
                </section>
            </c:if>
            <c:if test="${not empty cashOrderDTOs}">
                <c:forEach items="${cashOrderDTOs}" var="cashOrder" >
                    <div class="mt10">
                        <section class="recharge-list">
                            <table cellspacing="0" cellpadding="0" border="0" class="u-order-table">
                                <thead>
                                <tr>
                                    <th colspan="4">
                                        <div>
                                            <span class="c-999 f-fM">订单： ${cashOrder.requestId}</span>
                                            <span class="c-999 f-fM ml20 c-order-time">下单时间： <fmt:formatDate value="${cashOrder.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " /></span>
                                            <span class="c-999 f-fM ml20">状态:
                                                <c:if test="${cashOrder.trxStatus=='INIT'}"> 未支付 </c:if>
                                                <c:if test="${cashOrder.trxStatus=='SUCCESS'}"> 已支付 </c:if>
                                                <c:if test="${cashOrder.trxStatus=='CANCEL'}"> 已取消 </c:if>
                                            </span>
                                        </div>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td width="25%" class="tac">
                                        <span class="c-666 fsize14">￥${cashOrder.orderAmount}</span>
                                    </td>
                                    <td width="25%" class="tac">
                                        <span class="c-master fsize14">￥${cashOrder.amount}</span>
                                    </td>
                                    <td width="25%" class="tac">
                                        <span class="c-666 fsize14">
                                            <c:if test="${cashOrder.trxStatus=='INIT'}"> 未支付 </c:if>
                                            <c:if test="${cashOrder.trxStatus=='SUCCESS'}"> 已支付 </c:if>
                                            <c:if test="${cashOrder.trxStatus=='CANCEL'}"> 已取消 </c:if>
                                        </span>
                                    </td>
                                    <td width="25%" class="tac">
                                        <div class="tac">
                                            <c:if test="${cashOrder.trxStatus=='INIT'}">
                                                <p class="hLh20 c-999 f-fA"><a href="${ctx}/cash/order/repay/${cashOrder.id}" class="c-blue f-fM">付款</a></p>
                                                <p><a href="javascript:void(0)" onclick="orderCancel(${cashOrder.id})" class="c-999 f-fM">取消</a></p>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </section>
                    </div>
                </c:forEach>
                <form action="${ctx}/uc/cash/order" id="searchForm" method="post">
                    <input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
                    <input type="hidden" name="cashOrderDTO.trxStatus" value="${cashOrderDTO.trxStatus}" id="trxStatus"/>
                </form>
                <jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
            </c:if>
        </div>
    </div>
</article>
<script type="text/javascript">
    $(function(){
        var trxStatus = '${cashOrderDTO.trxStatus}';
        var status = 0;
        if(trxStatus==''){

        }else if(trxStatus=='SUCCESS'){
            status = 1;
        }else if(trxStatus=='INIT'){
            status = 2;
        }else if(trxStatus=='CANCEL'){
            status = 3;
        }
        $(".js-tap li").removeClass("current");
        $(".js-tap li").eq(status).addClass("current");
    })

    function orderCancel(id){
        $.ajax({
            url:'/cash/cancel/oder/'+id,
            type:'post',
            dataTypa:'json',
            success:function(result){
                if(result.success){
                    window.location.reload();
                }
            }
        });

    }
</script>
</body>
</html>
