<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>

<% // 從 com.coupon.controller.CouponServlet 存入 req 的 couponVO 物件 (用於資料庫取出的 couponVO 或輸入錯誤的 couponVO)
   CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
%>

--<%= couponVO == null %>--${couponVO.counterNo}--<!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>優惠券資料修改 - update_coupon_input.jsp</title>

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
            <h3>優惠券資料修改 - update_coupon_input.jsp</h3>
            <h4><a href="select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/images/logo noback.png" width="120" height="120" border="0">回首頁</a></h4>
        </td>
    </tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤訊息 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/Coupon/coupon.do" name="form1">
<table>
    <tr>
        <td>優惠券編號:<font color=red><b>*</b></font></td>
        <td><%=couponVO.getCouponNo()%></td>
    </tr>
    <tr>
        <td>櫃位編號:</td>
        <td><input type="TEXT" name="counterNo" value="<%=couponVO.getCounterNo()%>" size="45"/></td>
    </tr>
    <tr>
        <td>優惠券名稱:</td>
        <td><input type="TEXT" name="couponTitle" value="<%=couponVO.getCouponTitle()%>" size="45"/></td>
    </tr>
    <tr>
        <td>優惠券內容:</td>
        <td><input type="TEXT" name="couponContext" value="<%=couponVO.getCouponContext()%>" size="45"/></td>
    </tr>
    <tr>
        <td>起始日期:</td>
        <td><input name="couponStart" id="start_date" type="text"></td>
    </tr>
    <tr>
        <td>到期日期:</td>
        <td><input name="couponEnd" id="end_date" type="text"></td>
    </tr>
    <tr>
        <td>狀態:</td>
        <td>
            <select name="couponStatus">
                <option value="0" <%= couponVO.getCouponStatus() == 0 ? "selected" : "" %>>未開放</option>
                <option value="1" <%= couponVO.getCouponStatus() == 1 ? "selected" : "" %>>時效內</option>
                <option value="2" <%= couponVO.getCouponStatus() == 2 ? "selected" : "" %>>過期</option>
            </select>
        </td>
    </tr>
    <tr>
        <td>領取次數:</td>
        <td><input type="TEXT" name="usageLimit" value="<%=couponVO.getUsageLimit()%>" size="45"/></td>
    </tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="couponNo" value="<%=couponVO.getCouponNo()%>">
<input type="submit" value="送出修改">
</FORM>

</body>

<!-- 日期選擇器設定 -->
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
