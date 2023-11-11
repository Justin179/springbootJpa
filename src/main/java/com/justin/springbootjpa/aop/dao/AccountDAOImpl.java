package com.justin.springbootjpa.aop.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{
    @Override
    public void addAccount() {
        System.out.println(getClass()+": doing my db work: adding an account");
    }
}
