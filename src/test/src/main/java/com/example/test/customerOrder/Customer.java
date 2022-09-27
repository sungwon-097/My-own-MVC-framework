package com.example.test.customerOrder;

import java.net.CookieStore;

public class Customer {

    public void order(String menuName, Menu menu, Cooking cooking){
        MenuItem menuItem = menu.choose(menuName);
        Cook cook = cooking.makeCook(menuItem);
    }

}
