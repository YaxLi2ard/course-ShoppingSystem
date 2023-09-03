package com.cy.pojo;

public class Order {
    private Integer userId;
    private Integer brandId;
    private Integer count;
    private Integer total;
    private String date;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", brandId=" + brandId +
                ", count=" + count +
                ", total=" + total +
                ", date='" + date + '\'' +
                '}';
    }
}
