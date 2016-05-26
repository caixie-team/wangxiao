
//联合登录,重新打开窗口
function oauthLogin(appType){
	window.location.href=baselocation+"/app/authlogin?appType="+appType;
}