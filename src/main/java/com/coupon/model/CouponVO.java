package com.coupon.model;

import java.io.Serializable;
import java.sql.Date;

public class CouponVO implements Serializable {
    private Integer couponNo;     // 優惠券編號
    private Integer counterNo;    // 櫃位編號
    private String couponTitle;   // 優惠券名稱
    private String couponContext; // 優惠券內容
    private Date couponStart;     // 優惠券起始日
    private Date couponEnd;       // 優惠券到期日
    private Integer couponStatus; // 優惠券狀態 (0: 未開放, 1: 時效內, 2: 過期)
    private Integer usageLimit;   // 領取次數

    // Getter 和 Setter 方法
    public Integer getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(Integer couponNo) {
        this.couponNo = couponNo;
    }

    public Integer getCounterNo() {
        return counterNo;
    }

    public void setCounterNo(Integer counterNo) {
        this.counterNo = counterNo;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public String getCouponContext() {
        return couponContext;
    }

    public void setCouponContext(String couponContext) {
        this.couponContext = couponContext;
    }

    public Date getCouponStart() {
        return couponStart;
    }

    public void setCouponStart(Date couponStart) {
        this.couponStart = couponStart;
    }

    public Date getCouponEnd() {
        return couponEnd;
    }

    public void setCouponEnd(Date couponEnd) {
        this.couponEnd = couponEnd;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Integer getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }

    // Override toString() 方法，用於除錯和輸出內容
    @Override
    public String toString() {
        return "CouponVO [couponNo=" + couponNo + 
               ", counterNo=" + counterNo + 
               ", couponTitle=" + couponTitle + 
               ", couponContext=" + couponContext + 
               ", couponStart=" + couponStart + 
               ", couponEnd=" + couponEnd + 
               ", couponStatus=" + couponStatus + 
               ", usageLimit=" + usageLimit + "]";
    }
}
