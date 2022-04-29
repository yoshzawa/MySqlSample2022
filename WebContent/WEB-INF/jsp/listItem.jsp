<%@page import="java.util.List"%>
<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Optional<List<String[]>> optResultList = Optional.ofNullable((List<String[]>) request.getAttribute("resultList"));
	if (!optResultList.isPresent()) {
	%>
	結果が取得できませんでした
	<%
		} else {
	%>
	<table border=1>
		<%
			for (String[] s : optResultList.get()) {
		%>
		<tr>
			<th><%=s[0]%></th>
			<th><%=s[1]%></th>
		</tr>

		<%
			}
		%>

	</table>
	<%
		}
	%>
</body>
</html>