<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.coupon.model.*"%>

<%
  // CouponServlet.java (Controller) �s�J req �� couponVO ����
  CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
%>

<html>
<head>
<title>�u�f���� - listOneCoupon.jsp</title>

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

<h4>�����m�� Script ���g�k����:</h4>
<table id="table-1">
    <tr>
        <td>
            <h3>�u�f���� - listOneCoupon.jsp</h3>
            <h4><a href="select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/images/logo noback.png" width="30" height="32" border="0">�^����</a></h4>
        </td>
    </tr>
</table>

<table>
    <tr>
        <th>�u�f��s��</th>
        <th>�d��s��</th>
        <th>�u�f��W��</th>
        <th>�u�f�餺�e</th>
        <th>�_�l���</th>
        <th>������</th>
        <th>���A</th>
        <th>�������</th>
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
                    case 0: statusText = "���}��"; break;
                    case 1: statusText = "�ɮĤ�"; break;
                    case 2: statusText = "�L��"; break;
                    default: statusText = "�������A"; break;
                }
            %>
            <%=statusText%>
        </td>
        <td><%=couponVO.getUsageLimit()%></td>
    </tr>
</table>

</body>
</html>
