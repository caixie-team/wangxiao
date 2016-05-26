<%@page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>${course.title}-直播</title>
<script type="text/javascript">  
    function reinitIframe(){  
        var iframe = document.getElementById("frame_content");  
        try{  
            iframe.height =window.screen.height;
        }catch (ex){}
    }
  
    window.setTimeout("reinitIframe()", 200);
</script>
</head>

<iframe  src="${course.freeurl}?nickName=${_user.nickname}" id="frame_content" width="100%" height="100%" scrolling="no" frameborder="0" ></iframe>
</html>
