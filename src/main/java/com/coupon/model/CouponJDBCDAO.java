package com.coupon.model;

import java.sql.*;
import java.util.*;

public class CouponJDBCDAO implements CouponDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/dobuy?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "e1012125";

	private static final String INSERT_STMT = "INSERT INTO coupon (counterNo, couponTitle, couponContext, couponStart, couponEnd, couponStatus, usageLimit) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT couponNo, counterNo, couponTitle, couponContext, couponStart, couponEnd, couponStatus, usageLimit FROM coupon ORDER BY couponNo";
	private static final String GET_ONE_STMT = "SELECT couponNo, counterNo, couponTitle, couponContext, couponStart, couponEnd, couponStatus, usageLimit FROM coupon WHERE couponNo = ?";
	private static final String DELETE = "DELETE FROM coupon WHERE couponNo =?";
	private static final String UPDATE = "UPDATE coupon SET counterNo=?, couponTitle=?, couponContext=?, couponStart=?, couponEnd=?, couponStatus=?, usageLimit=? WHERE couponNo = ?";

	@Override
	public void insert(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, couponVO.getCounterNo());
			pstmt.setString(2, couponVO.getCouponTitle());
			pstmt.setString(3, couponVO.getCouponContext());
			pstmt.setDate(4, couponVO.getCouponStart());
			pstmt.setDate(5, couponVO.getCouponEnd());
			pstmt.setInt(6, couponVO.getCouponStatus());
			pstmt.setInt(7, couponVO.getUsageLimit());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error: " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, couponVO.getCounterNo());
			pstmt.setString(2, couponVO.getCouponTitle());
			pstmt.setString(3, couponVO.getCouponContext());
			pstmt.setDate(4, couponVO.getCouponStart());
			pstmt.setDate(5, couponVO.getCouponEnd());
			pstmt.setInt(6, couponVO.getCouponStatus());
			pstmt.setInt(7, couponVO.getUsageLimit());
			pstmt.setInt(8, couponVO.getCouponNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error: " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer couponNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, couponNo);
			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error: " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	//單筆查詢
	@Override
	public CouponVO findByPrimaryKey(Integer couponNo) {

		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, couponNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCouponNo(rs.getInt("couponNo"));
				couponVO.setCounterNo(rs.getInt("counterNo"));
				couponVO.setCouponTitle(rs.getString("couponTitle"));
				couponVO.setCouponContext(rs.getString("couponContext"));
				couponVO.setCouponStart(rs.getDate("couponStart"));
				couponVO.setCouponEnd(rs.getDate("couponEnd"));
				couponVO.setCouponStatus(rs.getInt("couponStatus"));
				couponVO.setUsageLimit(rs.getInt("usageLimit"));
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error: " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return couponVO;
	}
	//多筆查詢
	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCouponNo(rs.getInt("couponNo"));
				couponVO.setCounterNo(rs.getInt("counterNo"));
				couponVO.setCouponTitle(rs.getString("couponTitle"));
				couponVO.setCouponContext(rs.getString("couponContext"));
				couponVO.setCouponStart(rs.getDate("couponStart"));
				couponVO.setCouponEnd(rs.getDate("couponEnd"));
				couponVO.setCouponStatus(rs.getInt("couponStatus"));
				couponVO.setUsageLimit(rs.getInt("usageLimit"));
				list.add(couponVO);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error: " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		
	    CouponJDBCDAO dao = new CouponJDBCDAO();

	    // 新增
//	    CouponVO couponVO1 = new CouponVO();
//	    couponVO1.setCounterNo(1);
//	    couponVO1.setCouponTitle("夏日優惠");
//	    couponVO1.setCouponContext("全館商品打8折");
//	    couponVO1.setCouponStart(java.sql.Date.valueOf("2024-01-01"));
//	    couponVO1.setCouponEnd(java.sql.Date.valueOf("2024-12-31"));
//	    couponVO1.setCouponStatus(1);  // 1: 使用中
//	    couponVO1.setUsageLimit(100);
//	    dao.insert(couponVO1);

//	    System.out.println("----- 新增成功 -----");

	    // 修改
//	    CouponVO couponVO2 = new CouponVO();
//	    couponVO2.setCouponNo(1);  // 假設此編號為 1 的優惠券已存在
//	    couponVO2.setCounterNo(2);
//	    couponVO2.setCouponTitle("冬季優惠");
//	    couponVO2.setCouponContext("滿500免運費");
//	    couponVO2.setCouponStart(java.sql.Date.valueOf("2024-01-01"));
//	    couponVO2.setCouponEnd(java.sql.Date.valueOf("2024-06-30"));
//	    couponVO2.setCouponStatus(1);  // 1: 使用中
//	    couponVO2.setUsageLimit(50);
//	    dao.update(couponVO2);
//
//	    System.out.println("----- 修改成功 -----");

	    // 刪除
//	    dao.delete(1);  // 假設要刪除編號為 1 的優惠券
//	    System.out.println("----- 刪除成功 -----");

	    // 查詢單筆
	    CouponVO couponVO3 = dao.findByPrimaryKey(2);  // 查詢編號為 2 的優惠券
	    if (couponVO3 != null) {
	        System.out.println("單筆查詢結果:");
	        System.out.println(couponVO3.getCouponNo() + ", " +
	                           couponVO3.getCounterNo() + ", " +
	                           couponVO3.getCouponTitle() + ", " +
	                           couponVO3.getCouponContext() + ", " +
	                           couponVO3.getCouponStart() + ", " +
	                           couponVO3.getCouponEnd() + ", " +
	                           couponVO3.getCouponStatus() + ", " +
	                           couponVO3.getUsageLimit());
	    } else {
	        System.out.println("查無此優惠券");
	    }
	    System.out.println("---------------------");

	    // 查詢所有
	    List<CouponVO> list = dao.getAll();
	    System.out.println("所有優惠券列表:");
	    for (CouponVO coupon : list) {
	        System.out.println(coupon.getCouponNo() + ", " +
	                           coupon.getCounterNo() + ", " +
	                           coupon.getCouponTitle() + ", " +
	                           coupon.getCouponContext() + ", " +
	                           coupon.getCouponStart() + ", " +
	                           coupon.getCouponEnd() + ", " +
	                           coupon.getCouponStatus() + ", " +
	                           coupon.getUsageLimit());
	    }
	    System.out.println("---------------------");
	}

}
