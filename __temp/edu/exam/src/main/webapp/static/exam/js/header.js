$(function() {
	///动态添加背景图
	thisURL = document.URL;
	// 练习于模考
	if (thisURL.indexOf("/quest/toQuestionitemList")>0) {
		$("#lianxiyumokao").addClass("current");
	}
	// 我的练习
	if (thisURL.indexOf("/paper/toExamPaperRecordList")>0 || 
			thisURL.indexOf("/quest/toErrorQuestionList")>0 ||
			thisURL.indexOf("/quest/toNoteQuestionList")>0 ||
			thisURL.indexOf("/quest/favoriteQuestion")>0
	) {
		$("#wodelianxi").addClass("current");
	}
	// 能力评估
	if (thisURL.indexOf("/paper/competentAssessment")>0) {
		$("#nenglipinggu").addClass("current");
	}

	var subjectid =getCookie("e.subject");
	if(!isNull(subjectid)){
		querypaperRecordByCusIdAndSubjectId();
		getsubjectNameBySubjectId();
	}
	
});
// 获得当前专业的名字
function getsubjectNameBySubjectId() {
	$.ajax({
		type : "POST",
		dataType : "json",
		url:baselocation+"/subj/querySubjectById",
		data : {},
		cache : true,
		async : false,
		success : function(result) {
			if (result.success == true && result.entity!=null) {
				$("#subjectName").html(result.entity.subjectName);
			}
		}
	});
}

function toSubject(subjectId) {
	$.ajax({
				type : "POST",
				dataType : "json",
				url:baselocation+"/subj/querySubjectByPid",
				data : {
					"querySubject.parentId" : subjectId
				},
				async : false,
				success : function(result) {
					if (result.success == true) {
						if (result.entity.length != 0) {
							return false;
						}
					} else {
						window.location.href = baselocation+"/subj/addSubjectCookies?subject.subjectId="
								+ subjectId + "";
					}
				}
			});
}