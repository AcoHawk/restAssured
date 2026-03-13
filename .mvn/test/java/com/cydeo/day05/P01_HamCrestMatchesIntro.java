package com.cydeo.day05;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class P01_HamCrestMatchesIntro {




    @Test
    public void test1() {
        Assertions.assertEquals(9, 6 + 3);

        assertThat(6 + 3, is(9));
        assertThat(6 + 3, is(equalTo(9)));
        assertThat(6 + 3, equalTo(9));


        assertThat(5+5,not(9));
        assertThat(5+5,not(equalTo(9)));
        assertThat(5+5,is(not(equalTo(9))));


        assertThat(5+6,is(greaterThan(10)));
        assertThat(5+6,greaterThan(10));
        assertThat(5+6,lessThan(12));
    }

    @Test
    public void testString() {
        String msg = "API is fun!";

        assertThat(msg,is("API is fun!"));
        assertThat(msg,equalTo("API is fun!"));
        assertThat(msg,equalToIgnoringCase("api is fun!"));

        assertThat(msg,startsWith("API"));
        assertThat(msg,endsWith("fun!"));
        assertThat(msg,containsString("is"));

        assertThat(msg,not("Hello World!"));
        assertThat(msg,is(not(equalTo("Hello World"))));
    }

    @Test
    public void test2() {
        List<Integer> numberList = Arrays.asList(3,5,1,77,44,76);//6 elements

        //collection size
        assertThat(numberList,hasSize(6));

        //how to check 77 exist
        assertThat(numberList,hasItem(77));

        //how to check 44 and 76 exist in collection
        assertThat(numberList,hasItems(44,76));
        assertThat(numberList,hasItems(44,76,4));//false 4 is not on the list

        //looping through all the elements and make sure they are matching with the Matcher inside everyItem
        assertThat(numberList,everyItem(greaterThanOrEqualTo(1)));

        //check if it has all the values in the same order
        assertThat(numberList,containsInRelativeOrder(3,5,44));

        assertThat(numberList,containsInAnyOrder(76, 3,5,1,77,44));
    }
}
