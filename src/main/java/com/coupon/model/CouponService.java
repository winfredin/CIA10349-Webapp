package com.coupon.model;

import java.sql.Date;
import java.util.List;

public class CouponService {

    private CouponDAO_interface dao;

    // Constructor
    public CouponService() {
        dao = new CouponJDBCDAO();
    }

    // 新增優惠券
    public CouponVO addCoupon(Integer counterNo, String couponTitle, String couponContext, 
                              Date couponStart, Date couponEnd, Integer couponStatus, 
                              Integer usageLimit) {

        CouponVO couponVO = new CouponVO();

        couponVO.setCounterNo(counterNo);
        couponVO.setCouponTitle(couponTitle);
        couponVO.setCouponContext(couponContext);
        couponVO.setCouponStart(couponStart);
        couponVO.setCouponEnd(couponEnd);
        couponVO.setCouponStatus(couponStatus);
        couponVO.setUsageLimit(usageLimit);

        dao.insert(couponVO);

        return couponVO;
    }

    // 更新優惠券
    public CouponVO updateCoupon(Integer couponNo, Integer counterNo, String couponTitle, 
                                 String couponContext, Date couponStart, Date couponEnd, 
                                 Integer couponStatus, Integer usageLimit) {

        CouponVO couponVO = new CouponVO();

        couponVO.setCouponNo(couponNo);
        couponVO.setCounterNo(counterNo);
        couponVO.setCouponTitle(couponTitle);
        couponVO.setCouponContext(couponContext);
        couponVO.setCouponStart(couponStart);
        couponVO.setCouponEnd(couponEnd);
        couponVO.setCouponStatus(couponStatus);
        couponVO.setUsageLimit(usageLimit);

        dao.update(couponVO);

        return couponVO;
    }

    // 刪除優惠券
    public void deleteCoupon(Integer couponNo) {
        dao.delete(couponNo);
    }

    // 查詢單一優惠券
    public CouponVO getOneCoupon(Integer couponNo) {
        return dao.findByPrimaryKey(couponNo);
    }

    // 查詢所有優惠券
    public List<CouponVO> getAll() {
        return dao.getAll();
    }
}
