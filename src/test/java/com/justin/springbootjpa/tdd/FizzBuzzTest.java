package com.justin.springbootjpa.tdd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FizzBuzzTest {

    // if number is divisible by 3, print Fizz
    // if number is divisible by 5, print Buzz
    // if  number is divisible by 3 and 5, print FizzBuzz
    // if number is Not divisible by 3 or 5, print the number

    @Test
    void testForDivisibleByThree(){
        String expected = "Fizz";
        assertEquals(expected, FizzBuzz.compute(3),"should return Fizz");
    }

    @Test
    void testForDivisibleByFive(){
        String expected = "Buzz";
        assertEquals(expected, FizzBuzz.compute(5),"should return Buzz");
    }

    @Test
    void testForDivisibleByThreeAndFive(){
        String expected = "FizzBuzz";
        assertEquals(expected, FizzBuzz.compute(15),"should return FizzBuzz");
    }

    @Test
    void testForNotDivisibleByThreeOrFive(){
        String number = "17";
        assertEquals(number, FizzBuzz.compute(Integer.parseInt(number)),"should return "+number);
    }

    @Test
    void testLoopOverArray(){
        String[][] data = {{"1","1"},
                            {"2","2"},
                            {"3","Fizz"},
                            {"4","4"},
                            {"5","Buzz"},
                            {"6","Fizz"},
                            {"7","7"}};
        for (int i = 0; i < data.length; i++) {
            String value = data[i][0];
            String expected = data[i][1];
            assertEquals(expected,FizzBuzz.compute(Integer.parseInt( value)));
        }
    }

    @ParameterizedTest
    @CsvSource({"1,1","2,2","3,Fizz","4,4","5,Buzz","6,Fizz","7,7"})
    void testCsvData(int value, String expected){
        assertEquals(expected,FizzBuzz.compute(value));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/small-test-data.csv")
    void testSmallDataFile(int value, String expected){
        assertEquals(expected,FizzBuzz.compute(value));
    }
}





















