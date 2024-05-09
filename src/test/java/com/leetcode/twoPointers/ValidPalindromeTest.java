package com.leetcode.twoPointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/*
 * https://leetcode.com/problems/valid-palindrome/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class ValidPalindromeTest {

    @Test
    void test1() {
        String s = "A man, a plan, a canal: Panama";
        //boolean result = this.isPalindrome(s);
        boolean result = this.solution(s);
        assertTrue(result);
    }

    @Test
    void test2() {
        String s = "race a car";
        //boolean result = this.isPalindrome(s);
        boolean result = this.solution(s);
        assertFalse(result);
    }

    @Test
    void test3() {
        String s = " ";
        boolean result = this.isPalindrome(s);
        assertTrue(result);
    }

    private boolean isPalindrome(String s) {
        String removedSpecialCharacter = s.replaceAll("[^a-zA-Z0-9]", "");
        String target = removedSpecialCharacter.toLowerCase();
        boolean result = true;

        if (target.length() < 2) {
            return true;
        }

        for(int i = 0; i < (target.length() / 2) + 1; i++) {
            if (target.charAt(i) != target.charAt(target.length() - 1 - i)) {
                result = false;
                break;
            }
        }

        return result;
    }

    boolean solution(String s) {
        if (s.isEmpty()) {
            return true;
        }

        int start = 0;
        int last = s.length() - 1;

        while(start <= last) {
            char currFirst = s.charAt(start);
            char currLast = s.charAt(last);
            if (!Character.isLetterOrDigit(currFirst )) {
                start++;
            } else if(!Character.isLetterOrDigit(currLast)) {
                last--;
            } else {
                if (Character.toLowerCase(currFirst) != Character.toLowerCase(currLast)) {
                    return false;
                }
                start++;
                last--;
            }
        }
        return true;
    }
}
