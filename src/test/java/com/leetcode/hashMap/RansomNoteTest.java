package com.leetcode.hashMap;

import java.util.HashMap;
import java.util.Map.Entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/ransom-note/?envType=study-plan-v2&envId=top-interview-150
 */
public class RansomNoteTest {
    @Test
    void test1() {
        String ransomNote = "a";
        String magazine = "b";

        boolean result = this.canConstruct(ransomNote, magazine);

        assertFalse(result);
    }

    @Test
    void test2() {
        String ransomNote = "aa";
        String magazine = "ab";

        //boolean result = this.canConstruct(ransomNote, magazine);
        boolean result = this.solution(ransomNote, magazine);

        assertFalse(result);
    }

    @Test
    void test3() {
        String ransomNote = "aa";
        String magazine = "aab";

        boolean result = this.canConstruct(ransomNote, magazine);

        assertTrue(result);
    }

    private boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> noteMap = this.createHashMap(ransomNote);
        HashMap<Character, Integer> magazineMap = this.createHashMap(magazine);

        for(Entry<Character, Integer> entry : noteMap.entrySet()) {
            int magazineCount = magazineMap.getOrDefault(entry.getKey(), 0);
            if (entry.getValue() > magazineCount) {
                return false;
            }
        }

        return true;
    }

    private HashMap<Character, Integer> createHashMap(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char key = text.charAt(i);
            int count = map.getOrDefault(key, 0) + 1;

            map.put(key, count);
        }

        return map;
    }

    private boolean solution(String ransomNote, String magazine) {
        int[] check = new int[26];
        for (int i = magazine.length() - 1; i >= 0; i--) {
            check[magazine.charAt(i) - 'a']++;
        }
        int idx;
        for (int i = ransomNote.length() - 1; i >= 0; i--) {
            idx = ransomNote.charAt(i) - 'a';
            if (check[idx] < 1) {
                return false;
            }
            check[idx]--;
        }
        return true;
    }
}
