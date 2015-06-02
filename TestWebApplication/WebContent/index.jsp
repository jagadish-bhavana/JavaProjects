<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	System.out.println("I GOT REQUEST");
	if (request.getParameter("message") == null) {

	} else {
		System.out.println(request.getParameter("message"));
		out.println(request.getParameter("message"));
	}
	out.println("1,0,1,60,05,65050,10,180,10,0065645,1000,EOS");
%>
<%--"1,0,1,60,05,65050,10,180,10,0065645,1000,EOS" --%>


