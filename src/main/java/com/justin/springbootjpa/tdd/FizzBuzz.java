package com.justin.springbootjpa.tdd;

public class FizzBuzz {

    public static String compute(int i) {

        StringBuilder sb = new StringBuilder();

        if(i%3==0)
            sb.append("Fizz");
        if(i%5==0)
            sb.append("Buzz");
        if(sb.isEmpty())
            sb.append(i);

        return sb.toString();

//        if (i%3==0 && i%5==0)
//            return "FizzBuzz";
//        else if(i%3==0)
//            return "Fizz";
//        else if(i%5==0)
//            return "Buzz";
//        else
//            return String.valueOf(i);

    }
}
