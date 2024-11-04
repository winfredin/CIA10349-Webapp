package com.coupon.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.coupon.model.*;

@WebServlet("/back-end/Coupon/coupon.do")
public class CouponServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOne_For_Display".equals(action)) { // 來自 select_page.jsp 的請求
        	
            List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/ 
            String str = req.getParameter("couponNo");
            
                if (str == null || (str.trim()).length() == 0) {
                    errorMsgs.add("請輸入優惠券編號");
                }
				// Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Coupon/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                Integer couponNo = null;
                try {
                    couponNo = Integer.valueOf(str);
                } catch (Exception e) {
                    errorMsgs.add("優惠券編號格式不正確");
                }
				// Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Coupon/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }
				/***************************2.開始查詢資料*****************************************/
                CouponService couponSvc = new CouponService();
                CouponVO couponVO = couponSvc.getOneCoupon(couponNo);
                if (couponVO == null) {
                    errorMsgs.add("查無資料");
                }
				// Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Coupon/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
                req.setAttribute("couponVO", couponVO);// 資料庫取出的empVO物件,存入req
                String url = "/back-end/Coupon/listOneCoupon.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneEmp.jsp
                successView.forward(req, res);
                
            } 
        

        
        
        if ("getOne_For_Update".equals(action)) { // 來自 listAllCoupon.jsp 的請求
            List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            
			/***************************1.接收請求參數****************************************/
            Integer couponNo = Integer.valueOf(req.getParameter("couponNo"));

			/***************************2.開始查詢資料****************************************/
            CouponService couponSvc = new CouponService();
            CouponVO couponVO = couponSvc.getOneCoupon(couponNo);

			/***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("couponVO", couponVO); // 資料庫取出的empVO物件,存入req
            String url = "/back-end/Coupon/update_coupon_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }

        
        
        
        if ("update".equals(action)) { // 來自 update_coupon_input.jsp 的請求
            List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            
            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
Integer couponNo = Integer.valueOf(req.getParameter("couponNo").trim());

Integer counterNo = Integer.valueOf(req.getParameter("counterNo").trim());



String couponTitle = req.getParameter("couponTitle");
            String titleReg = "^[\\u4e00-\\u9fa5a-zA-Z0-9_\\s]{2,20}$";
            if (couponTitle == null || couponTitle.trim().length() == 0) {
                errorMsgs.add("優惠券名稱: 請勿空白");
            } else if (!couponTitle.trim().matches(titleReg)) {
                errorMsgs.add("優惠券名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
            }

String couponContext = req.getParameter("couponContext");
            if (couponContext == null || couponContext.trim().length() == 0) {
                errorMsgs.add("優惠券內容請勿空白");
            }

java.sql.Date couponStart = null;
            try {
                couponStart = java.sql.Date.valueOf(req.getParameter("couponStart").trim());
            } catch (IllegalArgumentException e) {
                couponStart = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入起始日期!");
            }

java.sql.Date couponEnd = null;
            try {
                couponEnd = java.sql.Date.valueOf(req.getParameter("couponEnd").trim());
            } catch (IllegalArgumentException e) {
                couponEnd = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入到期日期!");
            }

Integer couponStatus = null;
            try {
                couponStatus = Integer.valueOf(req.getParameter("couponStatus").trim());
            } catch (NumberFormatException e) {
                couponStatus = 0;
                errorMsgs.add("請輸入正確的優惠券狀態 (0: 未開放, 1: 時效內, 2: 過期).");
            }

Integer usageLimit = null;
            try {
                usageLimit = Integer.valueOf(req.getParameter("usageLimit").trim());
            } catch (NumberFormatException e) {
                usageLimit = 0;
                errorMsgs.add("請填入正確的領取次數.");
            }

//            Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

            // 建立 CouponVO 物件
            CouponVO couponVO = new CouponVO();
			couponVO.setCouponNo(couponNo); // 設定 couponNo
            couponVO.setCounterNo(counterNo);
            couponVO.setCouponTitle(couponTitle);
            couponVO.setCouponContext(couponContext);
            couponVO.setCouponStart(couponStart);
            couponVO.setCouponEnd(couponEnd);
            couponVO.setCouponStatus(couponStatus);
            couponVO.setUsageLimit(usageLimit);

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("couponVO", couponVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Coupon/update_coupon_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /***************************2.開始新增資料*****************************************/
            CouponService couponSvc = new CouponService();
            couponVO = couponSvc.updateCoupon(couponNo, counterNo, couponTitle, couponContext, couponStart, couponEnd, couponStatus, usageLimit);

            /***************************3.新增完成,準備轉交(Send the Success view)*************/
            req.setAttribute("couponVO", couponVO);// 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/back-end/Coupon/listOneCoupon.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }

        
        
        
        
        if ("insert".equals(action)) { // 來自 addCoupon.jsp 的請求  

            List<String> errorMsgs = new LinkedList<String>();
            // 將錯誤訊息設置在請求範圍內，以便在出錯時顯示
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            Integer counterNo = null;
            try {
                counterNo = Integer.valueOf(req.getParameter("counterNo").trim());
            } catch (NumberFormatException e) {
                errorMsgs.add("櫃位編號請填數字.");
            }

            String couponTitle = req.getParameter("couponTitle");
            String titleReg = "^[\\u4e00-\\u9fa5a-zA-Z0-9_\\s]{2,20}$";
            if (couponTitle == null || couponTitle.trim().length() == 0) {
                errorMsgs.add("優惠券名稱: 請勿空白");
            } else if (!couponTitle.trim().matches(titleReg)) {  //以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("優惠券名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
            }

            String couponContext = req.getParameter("couponContext");
            if (couponContext == null || couponContext.trim().length() == 0) {
                errorMsgs.add("優惠券內容請勿空白");
            }

            java.sql.Date couponStart = null;
            try {
                couponStart = java.sql.Date.valueOf(req.getParameter("couponStart").trim());
            } catch (IllegalArgumentException e) {
                couponStart = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入起始日期!");
            }

            java.sql.Date couponEnd = null;
            try {
                couponEnd = java.sql.Date.valueOf(req.getParameter("couponEnd").trim());
            } catch (IllegalArgumentException e) {
                couponEnd = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入到期日期!");
            }

            Integer couponStatus = null;
            try {
                couponStatus = Integer.valueOf(req.getParameter("couponStatus").trim());
            } catch (NumberFormatException e) {
                couponStatus = 0;
                errorMsgs.add("請輸入正確的優惠券狀態 (0: 未開放, 1: 時效內, 2: 過期).");
            }

            Integer usageLimit = null;
            try {
                usageLimit = Integer.valueOf(req.getParameter("usageLimit").trim());
            } catch (NumberFormatException e) {
                usageLimit = 0;
                errorMsgs.add("請填入正確的領取次數.");
            }

            
//            Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

            // 建立 CouponVO 物件
            CouponVO couponVO = new CouponVO();
            couponVO.setCounterNo(counterNo);
            couponVO.setCouponTitle(couponTitle);
            couponVO.setCouponContext(couponContext);
            couponVO.setCouponStart(couponStart);
            couponVO.setCouponEnd(couponEnd);
            couponVO.setCouponStatus(couponStatus);
            couponVO.setUsageLimit(usageLimit);

            // 如果有錯誤訊息，將 couponVO 和錯誤訊息轉交回表單頁面
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("couponVO", couponVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Coupon/addCoupon.jsp");
                failureView.forward(req, res);
                return;
            }

            /***************************2.開始新增資料***************************************/
            CouponService couponSvc = new CouponService();
            couponVO = couponSvc.addCoupon(counterNo, couponTitle, couponContext, couponStart, couponEnd, couponStatus, usageLimit);

            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            String url = "/back-end/Coupon/listAllCoupon.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交 listAllCoupon.jsp
            successView.forward(req, res);				
        }


        
        
        
        
        if ("delete".equals(action)) { // 來自 listAllCoupon.jsp 的請求
        	
            List<String> errorMsgs = new LinkedList<>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數***************************************/
                Integer couponNo = Integer.valueOf(req.getParameter("couponNo"));

				/***************************2.開始刪除資料***************************************/
                CouponService couponSvc = new CouponService();
                couponSvc.deleteCoupon(couponNo);

				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
                String url = "/back-end/Coupon/listAllCoupon.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);
        }
    }
}
