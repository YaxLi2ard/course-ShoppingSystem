package com.cy.pojo;

public class OrderBean {
    private Brand brand;
    private Order order;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "brand=" + brand +
                ", order=" + order +
                '}';
    }

    public OrderBean(){

    }

    public OrderBean(Brand brand, Order order) {
        this.brand = brand;
        this.order = order;
    }
}
