/**
 * Description: 课程、套餐、直播列表js
 * Author:
 */
$(function(){
    scrollLoad(); //响应滚动加载课程图片
    cSortFun();
})
// 点击搜索(按班型、按讲师、按会员)
function clickSearch(type, id) {
    // 点击搜索时要把当前页码设置为1
    $("#pageCurrentPage").val(1);

    if (type == 'subject') {
        $("#hiddenSubjectId").val(id);
    } else if (type == 'teacher') {
        if (id == 0) {
            $("#hiddenTeacherIds").val('');
        } else {
            $("#hiddenTeacherIds").val(id);
        }
    } else if (type == 'memberType') {
        if (id == 0) {
            $("#hiddenMemberId").val('');
        } else {
            $("#hiddenMemberId").val(id);
        }
    } else if (type == 'clear') {// 清空
        $("#hiddenTeacherIds").val('');
        $("#hiddenSubjectId").val('');
        $("#hiddenMemberId").val('');
    }
    $("#searchForm").submit();
}
// 点击搜索(排序)
function orderByQuery(em, type) {
    if ($(em).hasClass('current')) {
        return false;
    }
    $("#hiddenOrder").val(type);
    $("#searchForm").submit();
}