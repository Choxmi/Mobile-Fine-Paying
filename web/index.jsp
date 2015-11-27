<%@page import="com.HackDev.App.PaymentHash"%>
<%@page import="com.HackDev.App.SLPDdatabase"%>
<%@page import="com.HackDev.App.FeedbackService"%>
<%@page import="java.util.Map"%>
<%@page import="com.HackDev.App.FeedbackService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedback View</title>
<style>
table,th,td {
	border: 1px solid black;
}
</style>
</head>
<body>


	<table>
		<tr>
			<th>Mobile Number</th>
			<th>Reference</th>
                        <th>License</th>
                        <th>Amount</th>
		</tr>
		<%
			Map<String, SLPDdatabase> payments = PaymentHash.getMap();

			for (String count : payments.keySet()) {

				out.println("<tr><td>" + PaymentHash.mobile + "</td><td>" + payments.get(count).getreferenceNo() + "</td>"+ "</td><td>" + payments.get(count).getcustomerIDNo() + "</td>"+ "</td><td>" + payments.get(count).getamount() + "</td></tr>");
			}
		%>


	</table>


</body>
</html>