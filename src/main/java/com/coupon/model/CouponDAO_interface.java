package com.coupon.model;

import java.util.*;

public interface CouponDAO_interface {
	    public void insert(CouponVO couponVO);
	    public void update(CouponVO couponVO);
	    public void delete(Integer couponNo);
	    public CouponVO findByPrimaryKey(Integer couponNo);
	    public List<CouponVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<CouponVO> getAll(Map<String, String[]> map); 
}

