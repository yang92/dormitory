<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isErrorPage="true"%>
    
<%
	String msg = exception.getMessage();
	if(msg != null && msg.equals("auth")) {
		out.print("로그인되지 않았습니다.<br />");
		out.print("<a href='/login.do'>로그인으로 이동</a>");
	} else {
		out.print(msg);
	}
%>