/* web app html5 commJs 2015.03.23 */
/*页面全部加载未完成之前运行且显示loading.gif图标效果*/
var _LoadingHtml = '<div id="loadingDiv" class="pageLoading">loading...</div>';
document.write(_LoadingHtml);
document.onreadystatechange = completeLoading;
function completeLoading() {
    if (document.readyState == "complete") {
        var loadingMask = document.getElementById('loadingDiv');
        loadingMask.parentNode.removeChild(loadingMask);
    }
}