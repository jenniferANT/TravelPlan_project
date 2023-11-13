package com.app.travelplan.utils;

public class Test {
    public static void main(String[] args) {


        if(SecurityUtils.getUsernameOfPrincipal() == null) {
            System.out.println("asdf");
        }
    }
}
