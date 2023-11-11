package com.justin.springbootjpa.aop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrafficFortuneServiceImplTest {

    @Autowired
    TrafficFortuneService trafficFortuneService;

    @Test
    void getFortune() {
        trafficFortuneService.getFortune();
    }
    /*
    This is to test aop @Around
    executing @Around on method: TrafficFortuneServiceImpl.getFortune()
    Duration: 5.005 seconds
     */
}