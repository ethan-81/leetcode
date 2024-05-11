package com.leetcode.stack;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/valid-parentheses/description/?envType=study-plan-v2&envId=top-interview-150
*/
public class ValidParenthesesTest {

    @Test
    void test1() {
        String s = "()";

        boolean result = this.isValid(s);

        assertTrue(result);
    }

    @Test
    void test2() {
        String s = "()[]{}";

        boolean result = this.isValid(s);

        assertTrue(result);
    }

    @Test
    void test3() {
        String s = "(]";

        boolean result = this.isValid(s);

        assertFalse(result);
    }

    @Test
    void test4() {
        String s = "{[]}";

        //boolean result = this.isValid(s);
        boolean result = this.solution(s);

        assertTrue(result);
    }

    @Test
    void test5() {
        String s = "((";

        boolean result = this.isValid(s);

        assertFalse(result);
    }

    @Test
    void test6() {
        String s = "(){}}{";

        //boolean result = this.isValid(s);
        boolean result = this.solution(s);

        assertFalse(result);
    }

    private boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }

        List<Character> openBrackets = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char currentBracket = s.charAt(i);

            if (currentBracket == '(' || currentBracket == '{' || currentBracket == '[') {
                openBrackets.add(currentBracket);
            } else {
                if (openBrackets.isEmpty()) {
                    return false;
                }

                char lastOpenBracket = openBrackets.get(openBrackets.size() - 1);
                if (lastOpenBracket == '(' && currentBracket == ')') {
                    openBrackets.remove(openBrackets.size() - 1);
                } else if (lastOpenBracket == '{' && currentBracket == '}') {
                    openBrackets.remove(openBrackets.size() - 1);
                } else if (lastOpenBracket == '[' && currentBracket == ']') {
                    openBrackets.remove(openBrackets.size() - 1);
                } else {
                    return false;
                }
            }
        }

        return openBrackets.size() == 0;
    }

    private boolean solution(String s) {
        int[] openBrackets = new int[s.length()];
        int openBracketIndex = -1;

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                openBracketIndex++;
                openBrackets[openBracketIndex] = c;
            } else {
                if (openBracketIndex == -1) {
                    return false;
                }

                if ((openBrackets[openBracketIndex] == '(' && c == ')') || (openBrackets[openBracketIndex] == '[' && c == ']') || (openBrackets[openBracketIndex] == '{' && c == '}')) {
                    openBracketIndex--;
                } else {
                    return false;
                }
            }
        }

        return openBracketIndex == -1;
    }
}
