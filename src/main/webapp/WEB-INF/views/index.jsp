<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>      
<html>
<head>
</head>
<body>
<form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
<input type="hidden" name="cmd" value="_s-xclick">
<input type="hidden" name="hosted_button_id" value="GA9J26KTUU9MU">
<table>
<tr><td><input type="hidden" name="on0" value="pay account">pay account</td></tr><tr><td><select name="os0">
	<option value="eur1">eur1 €1.00 EUR</option>
	<option value="eur5">eur5 €5.00 EUR</option>
	<option value="eur20">eur20 €20.00 EUR</option>
	<option value="eur50">eur50 €50.00 EUR</option>
</select> </td></tr>
</table>
<input type="hidden" name="currency_code" value="EUR">
<input type="image" src="https://www.paypalobjects.com/en_US/C2/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
<img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
</form>

</body>
</html>