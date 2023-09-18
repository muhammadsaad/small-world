package com.smallworld;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    private static StringUtils stringUtils;

    @BeforeAll
    public static void beforeAll() {
        stringUtils = StringUtils.getInstance();
    }

    @Test
    void test1() {
        // given
        String sentence = "Good afternoon";

        // when
        String reversedString = stringUtils.reverseString(sentence);

        // then
        Assertions.assertEquals("afternoon Good", reversedString);
    }

    @Test
    void test2() {
        // given
        String sentence = "Hello, how are you?";

        // when
        String reversedString = stringUtils.reverseString(sentence);

        // then
        Assertions.assertEquals("?you are how, Hello", reversedString);
    }

    @Test
    void test3() {
        // given
        String sentence = "Are you twenty-one years old?";

        // when
        String reversedString = stringUtils.reverseString(sentence);

        // then
        Assertions.assertEquals("?old years twenty-one you Are", reversedString);
    }

}