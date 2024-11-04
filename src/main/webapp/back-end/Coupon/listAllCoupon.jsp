<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>

<%
CouponService couponSvc = new CouponService();
List<CouponVO> list = couponSvc.getAll();
pageContext.setAttribute("list", list);



    int rowsPerPage = 8;
    int rowNumber = list.size();
    int pageNumber = (int) Math.ceil((double) rowNumber / rowsPerPage);
    int whichPage = 1;

    try {
        whichPage = Integer.parseInt(request.getParameter("whichPage"));
    } catch (Exception e) {
        whichPage = 1;
    }

    whichPage = Math.max(1, Math.min(whichPage, pageNumber));
    int pageIndex = (whichPage - 1) * rowsPerPage;
    
    
%>

<html>
<head>
<title>所有優惠券資料 - listAllCoupon.jsp</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/Coupon/Coupon.css">

<style>

body {
	background:
		url('<%=request.getContextPath()%>/back-end/images/bg3.webp')
		no-repeat center center fixed;
	background-size: cover;
	color: #333;
	font-family: Arial, sans-serif;
}
</style>
</head>

<body>

	<div class="header-section">
		<a href="select_page.jsp" class="back-home"> <img
			src="<%=request.getContextPath()%>/back-end/images/logo noback.png"
			width="120" height="120" alt="回首頁">
		</a>
		<h3>所有優惠券資料</h3>
	</div>



	<div class="table-wrapper">
		<table class="styled-table">
			<thead>
				<tr>
					<th>優惠券編號</th>
					<th>櫃位編號</th>
					<th>優惠券名稱</th>
					<th>優惠券內容</th>
					<th>起始日期</th>
					<th>到期日期</th>
					<th>狀態</th>
					<th>領取次數</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
			</thead>
			
<%-- 			<%@ include file="page1.file"%> --%>
		
		
			<tbody>
				<c:forEach var="couponVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=Math.min(pageIndex + rowsPerPage - 1, rowNumber - 1)%>">
					<tr>
						<td>${couponVO.couponNo}</td>
						<td>${couponVO.counterNo}</td>
						<td>${couponVO.couponTitle}</td>
						<td>${couponVO.couponContext}</td>
						<td>${couponVO.couponStart}</td>
						<td>${couponVO.couponEnd}</td>
						<td><c:choose>
								<c:when test="${couponVO.couponStatus == 0}">未開放</c:when>
								<c:when test="${couponVO.couponStatus == 1}">時效內</c:when>
								<c:otherwise>已過期</c:otherwise>
							</c:choose></td>
						<td>${couponVO.usageLimit}</td>
						<td>
						 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/Coupon/coupon.do" style="margin-bottom: 0px;">
					     <input type="submit" value="修改">
					     <input type="hidden" name="couponNo"  value="${couponVO.couponNo}">
					     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
						
						</td>

						
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/Coupon/coupon.do" style="margin-bottom: 0px;">
						     <input type="submit" value="刪除">
						     <input type="hidden" name="couponNo"  value="${couponVO.couponNo}">
						     <input type="hidden" name="action" value="delete"></FORM>
						
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


		<div class="pagination">
		    第 <%=whichPage%> / <%=pageNumber%> 頁, 共 <%=rowNumber%> 筆資料
		</div>




	<div class="pagination">
	    <a href="<%=request.getRequestURI()%>?whichPage=1" class="page-link">至第一頁</a>
	    <% if (whichPage > 1) { %>
	        <a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>" class="page-link">上一頁</a>
	    <% } %>
	    <% if (whichPage < pageNumber) { %>
	        <a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>" class="page-link">下一頁</a>
	        <a href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>" class="page-link">至最後一頁</a>
	    <% } %>
	</div>

	<form method="get" action="<%=request.getRequestURI()%>" class="page-select-form">
	    <select size="1" name="whichPage">
	        <% for (int i = 1; i <= pageNumber; i++) { %>
	            <option value="<%=i%>" <%= i == whichPage ? "selected" : "" %>>跳至第<%=i%>頁</option>
	        <% } %>
	    </select> 
	    <input type="submit" value="確定" class="btn-submit">
	</form>

<%-- 			<%@ include file="page2.file"%> --%>
		


</body>
</html>
