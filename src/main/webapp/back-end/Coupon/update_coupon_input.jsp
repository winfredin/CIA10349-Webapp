<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>

<% // �q com.coupon.controller.CouponServlet �s�J req �� couponVO ���� (�Ω��Ʈw���X�� couponVO �ο�J���~�� couponVO)
   CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
%>

--<%= couponVO == null %>--${couponVO.counterNo}--<!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�u�f���ƭק� - update_coupon_input.jsp</title>

<style>
  table#table-1 {
    background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
    width: 450px;
    background-color: white;
    margin-top: 1px;
    margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
    <tr>
        <td>
            <h3>�u�f���ƭק� - update_coupon_input.jsp</h3>
            <h4><a href="select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/images/logo noback.png" width="120" height="120" border="0">�^����</a></h4>
        </td>
    </tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~�T�� --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">�Эץ��H�U���~:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/Coupon/coupon.do" name="form1">
<table>
    <tr>
        <td>�u�f��s��:<font color=red><b>*</b></font></td>
        <td><%=couponVO.getCouponNo()%></td>
    </tr>
    <tr>
        <td>�d��s��:</td>
        <td><input type="TEXT" name="counterNo" value="<%=couponVO.getCounterNo()%>" size="45"/></td>
    </tr>
    <tr>
        <td>�u�f��W��:</td>
        <td><input type="TEXT" name="couponTitle" value="<%=couponVO.getCouponTitle()%>" size="45"/></td>
    </tr>
    <tr>
        <td>�u�f�餺�e:</td>
        <td><input type="TEXT" name="couponContext" value="<%=couponVO.getCouponContext()%>" size="45"/></td>
    </tr>
    <tr>
        <td>�_�l���:</td>
        <td><input name="couponStart" id="start_date" type="text"></td>
    </tr>
    <tr>
        <td>������:</td>
        <td><input name="couponEnd" id="end_date" type="text"></td>
    </tr>
    <tr>
        <td>���A:</td>
        <td>
            <select name="couponStatus">
                <option value="0" <%= couponVO.getCouponStatus() == 0 ? "selected" : "" %>>���}��</option>
                <option value="1" <%= couponVO.getCouponStatus() == 1 ? "selected" : "" %>>�ɮĤ�</option>
                <option value="2" <%= couponVO.getCouponStatus() == 2 ? "selected" : "" %>>�L��</option>
            </select>
        </td>
    </tr>
    <tr>
        <td>�������:</td>
        <td><input type="TEXT" name="usageLimit" value="<%=couponVO.getUsageLimit()%>" size="45"/></td>
    </tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="couponNo" value="<%=couponVO.getCouponNo()%>">
<input type="submit" value="�e�X�ק�">
</FORM>

</body>

<!-- �����ܾ��]�w -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width: 300px;
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;
  }
</style>

<script>
    $.datetimepicker.setLocale('zh');
    $('#start_date').datetimepicker({
       theme: '',
       timepicker: false,
       step: 1,
       format: 'Y-m-d',
       value: '<%=couponVO.getCouponStart()%>',
    });

    $('#end_date').datetimepicker({
       theme: '',
       timepicker: false,
       step: 1,
       format: 'Y-m-d',
       value: '<%=couponVO.getCouponEnd()%>',
    });
</script>
</html>
