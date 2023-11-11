package com.justin.springbootjpa.aop.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountDAOImplTest {

    @Autowired
    AccountDAO accountDAO;

    @Test
    void addAccount() {
        accountDAO.addAccount();
    }
    /*
    this is to test aop @Before
    ====>>> Executing @Before advice on addAccount()
    class com.justin.springbootjpa.aop.dao.AccountDAOImpl: doing my db work: adding an account
     */
}