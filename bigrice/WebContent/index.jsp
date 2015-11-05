<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大米工作室</title>
</head>
<body>
	<!-- 链接跳转，处理二级域名 -->
	<%
		String req = request.getServerName();
		out.println(req);
		String[] serverNames = req.split(".");
		if(serverNames != null && serverNames.length>2){
			//xxx.bigrice.xyz
			String subDomain = req.split(".")[0];
			if("app".equals(subDomain)){
				out.println(subDomain);
			}
		}
	%>
	
	<%--
 		String  realPath1  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);  
       	out.println("web  URL  路径:"+realPath1);
	--%>
</body>
</html>