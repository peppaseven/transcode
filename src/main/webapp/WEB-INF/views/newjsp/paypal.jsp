<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>PAY TEST</title>
</head>
<body>
付款测试--->
<!-- <form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top"> -->
<!-- 	<input type="hidden" name="cmd" value="_s-xclick"> -->
<!-- 	<input type="hidden" name="hosted_button_id" value="EJZ97XGXPUUPN"> -->
<!-- 	<input type="image" src="https://www.paypalobjects.com/en_US/C2/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!"> -->
<!-- 	<img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1"> -->
<!-- </form> -->

<form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" target="_top">
<input type="hidden" name="cmd" value="_s-xclick">
<input type="hidden" name="hosted_button_id" value="N8QTR5U4HM882">
<input type="image" src="https://www.sandbox.paypal.com/en_US/C2/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
<img alt="" border="0" src="https://www.sandbox.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">
</form>

</body>
</html>