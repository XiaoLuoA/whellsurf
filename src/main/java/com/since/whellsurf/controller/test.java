package com.since.whellsurf.controller;

public class test {
    public static void main(String[] args) {
        double min = 0.01;
        double max = 100;
        int maxDigit = 2;
        int pow = (int) Math.pow(10,maxDigit);
        double one = Math.floor((Math.random() * (max - min) + min) * pow) / pow;
        System.out.println(one);
    }
}
