package com.since.whellsurf.controller;

import com.since.whellsurf.common.SessionKey;
import com.since.whellsurf.entity.Account;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

public class test {
    public static void main(String[] args) {
        HttpServletRequest request = null;
        Account account2 = (Account) request.getSession().getAttribute(SessionKey.LOGIN_USER);
        System.out.println("account2"+account2);
    }
}
