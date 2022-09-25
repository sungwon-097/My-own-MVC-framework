package com.example.test.customerOrder;

import java.util.Objects;

public class Cook {
    private final String name;
    private final int price;

    public Cook(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Cook(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.price = menuItem.getPrice();
    }

    // 다른 클래스의 객체 값을 비교 할 때는 control+Enter 후 equals() and hashCode() 재정의
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cook cook = (Cook) o;
        return price == cook.price && Objects.equals(name, cook.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
