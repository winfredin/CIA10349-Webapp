<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.coupon.model.*"%>

<%
  // CouponServlet.java (Controller) 存入 req 的 couponVO 物件
  CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
%>

<html>
<head>
<title>優惠券資料 - listOneCoupon.jsp</title>

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
    width: 600px;
    background-color: white;
    margin-top: 5px;
    margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習 Script 的寫法取值:</h4>
<table id="table-1">
    <tr>
        <td>
            <h3>優惠券資料 - listOneCoupon.jsp</h3>
            <h4><a href="select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/images/logo noback.png" width="30" height="32" border="0">回首頁</a></h4>
        </td>
    </tr>
</table>

<table>
    <tr>
        <th>優惠券編號</th>
        <th>櫃位編號</th>
        <th>優惠券名稱</th>
        <th>優惠券內容</th>
        <th>起始日期</th>
        <th>到期日期</th>
        <th>狀態</th>
        <th>領取次數</th>
    </tr>
    <tr>
        <td><%=couponVO.getCouponNo()%></td>
        <td><%=couponVO.getCounterNo()%></td>
        <td><%=couponVO.getCouponTitle()%></td>
        <td><%=couponVO.getCouponContext()%></td>
        <td><%=couponVO.getCouponStart()%></td>
        <td><%=couponVO.getCouponEnd()%></td>
        <td>
            <% 
                int status = couponVO.getCouponStatus();
                String statusText = "";
                switch (status) {
                    case 0: statusText = "未開放"; break;
                    case 1: statusText = "時效內"; break;
                    case 2: statusText = "過期"; break;
                    default: statusText = "未知狀態"; break;
                }
            %>
            <%=statusText%>
        </td>
        <td><%=couponVO.getUsageLimit()%></td>
    </tr>
</table>

</body>
</html>
