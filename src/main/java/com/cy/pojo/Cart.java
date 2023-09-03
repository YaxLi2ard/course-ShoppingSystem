package com.cy.pojo;

public class Cart {
    private Brand brand;
    private Integer count;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Cart() {

    }

    public Cart(Brand brand, Integer count) {
        this.brand = brand;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "brand=" + brand +
                ", count=" + count +
                '}';
    }
}
