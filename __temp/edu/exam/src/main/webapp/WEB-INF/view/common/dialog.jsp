<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!-- /**
	 * 弹出框 
	 * @param dTitle 窗口标题
	 * @param conent 窗口内容
	 * @param idnex 窗口下标（要用哪一种窗口）
	 * 0为支付弹框1为错误弹框2为confirm弹框3登陆弹框4为正确弹框5为强制登陆6为确定跳转7为错误提示8视频试听地址
	 * @param url (确认窗口的确定按钮路径)
	 * @param loginType (登录窗口的登录类)
	 * 
	 * 如果有的点参不需要，可以传入空字符串
	 */ -->
<c:choose>
    <c:when test="${dialog.index==0}"><!--0支付完成弹出窗  -->
		<div class='d-tips-1'>
			<em class='icon30 pa d-t-icon-1'></em>
			<p class='fsize14 c-666'>你选择使用网上在线支付，在你支付成功课程会及时开通。祝你学习愉快！</p>
			<div class='tac mt20'>
				<a href='${ctx }/front/showcoulist' title='继续购买'
					class='blue-btn mr10'>继续购买</a><a href='javascript:void(0)'
					onclick='goCorder()' title='已成功完成支付' class='blue-btn ml10'>已成功完成支付</a><a
					href='javascript:void(0)' onclick='goOrder()' title='支付失败'
					class='blue-btn ml10'>支付失败</a>
			</div>
			<p class='tar mt20 c-666'>如有疑问请询问客服</p>
		</div>
	</c:when>
    <c:when test="${dialog.index==1}"><!-- 1错误提示框 -->
		<div class='d-tips-2'>
			<em class='pa d-t-icon-2 d-t-wrong icon30'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a id='tisbutt' href='javascript:void(0)' title=''
					class='dClose order-submit'>确定</a>
			</div>
		</div>
	</c:when>
    <c:when test="${dialog.index==2}"><!-- 2为confirm弹框 -->
		<div class='d-tips-3'>
			<em class='pa d-t-icon-3 d-t-right icon30'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a href='${dialog.url}' title='确定' class='order-submit'>确定</a><a
					href='javascript:void(0)' title='' class='goBack-btn ml10'
					id='qujiao'>取消</a>
			</div>
		</div>
	</c:when>
    <c:when test="${dialog.index==3}"><!-- 3登录弹出窗口-->
        <div class="d-tips-4">
            <input type="hidden" id="forceStatus" name="forceStatus" value="">
            <ul class="l-r-w-Inpt">
                <li><p id="requestErrorID"></p>
                </li>
                <li>
                    <label class="vam">用户名：</label>
				<span style="display:inline-block;position:relative;">
				<div id="mailListBox_0" class="justForJs out_box" style="position:absolute;left:-6000px;top:34px;z-index:1;"></div>
				<input type="text" onkeyup="enterSubmit(event,'pageLogin(1)')" class="lTxt" value="" id="userEmail">
				</span>
                    <p id="userNameError" class="disIb ml5 c-orange"></p>
                </li>
                <li class="mt30">
                    <label class="vam">密&nbsp;&nbsp;&nbsp;码：</label>
                    <input type="password" onkeyup="enterSubmit(event,'pageLogin(1)')" id="userPassword" class="lTxt">
                    <p id="passwordError" class="disIb ml5 c-orange"></p>
                </li>
                <li class="mt15">
                    <label class="vam">&nbsp;</label>
				<span class="inpCb">
					<input type="checkbox" name="" value="" checked="checked" class="c-icon" id="autoThirty">
					<tt for="forget" class="vam c-999 ml10">30天内自动登录</tt>
				</span>
				<span class="ml50">
					<a href="${ctx}/front/forget_passwd" title="忘记密码？" class="c-orange">
                        <u>忘记密码？</u></a>
				</span>
                </li>
                <li class="mt30">
                    <label class="vam">&nbsp;</label>
				<span class="login-btn">
				<input type="button" onclick="pageLogin(1)" value="登 录" style="margin-left: 0;">
				</span>
                </li>
            </ul>
        </div>
    </c:when>
    <c:when test="${dialog.index==4}"><!-- 4为正确弹框 -->
		<div class='d-tips-2'>
			<em class='pa d-t-icon-3 d-t-right icon30'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a id='tisbutt' href='javascript:void(0)' title=''
					class='order-submit'>确定</a>
			</div>
		</div>
	</c:when>
    <c:when test="${dialog.index==5}"><!-- 5为强制登陆 -->
		<div class='d-tips-3'>
			<em class='pa d-t-icon-3 d-t-right icon30'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a href='${dialog.url}' title='强制登陆' class='order-submit'>强制登陆</a><a
					href='javascript:void(0)' title='' class='goBack-btn ml10'
					id='qujiao'>取消</a>
			</div>
		</div>
	</c:when>
    <c:when test="${dialog.index==6}"><!-- 6为确定跳转 -->
		<div class='d-tips-3'>
			<em class='pa d-t-icon-3 d-t-right icon30'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a href='"${dialog.url}"' title='确定' class='order-submit'>确定</a>
			</div>
		</div>
	</c:when>
    <c:when test="${dialog.index==7}"><!-- 7为错误提示 -->
		<div class='d-tips-2'>
			<em class='pa d-t-icon-2 d-t-wrong icon30'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a id='tisbutt' href='javascript:void(0)' title=''
					class='order-submit'>确定</a>
			</div>
		</div>
	</c:when>
    <c:when test="${dialog.index==8}">
		<div class='d-tips-5'>
			<div style='width: 612px; height: 440px;'>${dialog.conent}</div>
		</div>
	</c:when>
    <c:when test="${dialog.index==9}"><!-- 个人中心错误提示 重复替换 0-->
		<div class='d-tips-2'>
			<em class='icon30 pa d-t-icon-1'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a id='tisbutt' href='javascript:void(0)' title=''
					class='dClose order-submit'>确定</a>
			</div>
		</div>
	</c:when>
    <c:when test="${dialog.index==10}"><!-- 个人中心课程卡1 -->
		<div class='d-tips-4'>
			<ul class='l-r-w-Inpt'>
				<li><label class='vam'>卡号：</label><input type='text'
					class='lTxt' value='' id='cardCourseCode'
					name='cardCourse.cardCourseCode'></li>
				<li class='mt20'><label class='vam'>密码：</label><input
					type='password' class='lTxt' value='' id='cardCoursePassword'
					name='cardCourse.cardCoursePassword'></li>
				<li class='mt20'><label class='vam'>&nbsp;</label><span
					class='login-btn'><input type='button' onclick='jihuo()'
						value='激 活' style='margin-left: 0;'></span></li>
			</ul>
		</div>
	</c:when>
    <c:when test="${dialog.index==11}"><!-- 个人中心订单 2-->
		<div class='d-tips-4'>
			<ul class='l-r-w-Inpt'>
				<li><label class='vam'>订单编号：</label><strong class='c-orange'
					id='contractId'>${dialog.conent}</strong></li>
				<li class='mt20'><label class='vam'>激&nbsp;活&nbsp;码&nbsp;：</label><input
					maxlength='4' class='activationOrdersWin' type='text'
					id='contractCDkey1' />-<input class='activationOrdersWin'
					maxlength='4' id='contractCDkey2' type='text' />-<input
					maxlength='4' class='activationOrdersWin' type='text'
					id='contractCDkey3' />-<input maxlength='4'
					class='activationOrdersWin' type='text' id='contractCDkey4' /></li>
				<li class='mt2 ntsyl c-666'>每框输入4位激活码，激活成功后可以学习其中的课程</li>
				<li class='mt20'><label class='vam'>&nbsp;</label><span
					class='login-btn'><input type='button' onclick='actCourse()'
						value='激 活' style='margin-left: 0;'></span></li>
			</ul>
		</div>
	</c:when>
	<c:when test="${dialog.index==12}"><!-- 3 -->
		<div class='d-tips-2'>
			<em class='icon30 pa d-t-icon-1'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a href='${dialog.url}' title='确定' class='d-cancel order-submit'>确定</a><a
					href='javascript:void(0)' title='' class='d-cancel goBack-btn ml10'
					id='qujiao'>取消</a>
			</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==13}"><!-- 4 -->
		<div class='d-tips-2'>
			<em class='icon30 pa d-t-icon-1'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a id='tisbutt' href='javascript:void(0)' title=''
					class='dClose order-submit'>确定</a>
			</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==14}"><!-- 5 -->
		<div class='d-tips-2'>
			<em class='icon30 pa d-t-icon-1'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a href='${dialog.url}' title='确定' class='d-cancel order-submit'>确定</a><a
					href='javascript:void(0)' title='' class='d-cancel goBack-btn ml10'
					id='qujiao'>取消</a>
			</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==15}"><!-- 6 -->
		<div class='d-tips-2'>
			<em class='icon30 pa d-t-icon-2'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a id='tisbutt' href='javascript:void(0)' title=''
					class='dClose order-submit'>确定</a>
			</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==16}"><!-- 7 -->
		<div class='d-tips-2'>
			<em class='icon30 pa d-t-icon-2'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a id='tisbutt' href='${dialog.url}' title='' class='dClose order-submit'>确定</a>
			</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==17}">
		<div class='d-tips-2'>
			<em class='icon30 pa d-t-icon-1'></em>
			<p class='fsize14 c-666'>${dialog.conent}</p>
			<div class='tac mt30'>
				<a id='tisbutt' href='${dialog.url}' title='' class='dClose order-submit'>确定</a>
			</div>
		</div>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
