<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>DO BUY: Coupon Home</title>

    <style>
        table#table-1 {
            width: 450px;
            background-color: #FFD700;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 3px ridge Gray;
            height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
    <tr>
        <td><h3>DO BUY: Coupon Home</h3><h4>( MVC )</h4></td>
    </tr>
</table>

<p>This is the Home page for DO BUY Coupons</p>

<h3>��Ƭd��:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">�Эץ��H�U���~:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<ul>
    <li><a href='listAllCoupon.jsp'>List</a> all Coupons. <br><br></li>
</ul>

<h3>�u�f��޲z</h3>

<ul>
    <li><a href='addCoupon.jsp'>Add</a> a new Coupon.</li>
</ul>

<h3>�d���u�f��</h3>

<ul>
    <li>
        <form method="post" action="coupon.do">
            <b>��J�u�f��s��:</b>
            <input type="text" name="couponNo">
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="�e�X">
        </form>
    </li>

<jsp:useBean id="couponSvc" class="com.coupon.model.CouponService" scope="request" />


    <li>
        <form method="post" action="coupon.do">
            <b>����u�f��s��:</b>
            <select size="1" name="couponNo">
                <c:forEach var="couponVO" items="${couponSvc.all}">
                    <option value="${couponVO.couponNo}">${couponVO.couponNo}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="�e�X">
        </form>
    </li>

    <li>
        <form method="post" action="coupon.do">
            <b>����u�f��W��:</b>
            <select size="1" name="couponNo">
                <c:forEach var="couponVO" items="${couponSvc.all}">
                    <option value="${couponVO.couponNo}">${couponVO.couponTitle}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="�e�X">
        </form>
    </li>
</ul>

</body>
</html>
