package com.leetcode.intervals;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/summary-ranges/description/?envType=study-plan-v2&envId=top-interview-150
*/
public class SummaryRangesTest {
    @Test
    void test1() {
        int[] nums = {0,1,2,4,5,7};

        List<String> result = this.summaryRanges(nums);

        List<String> expect = List.of("0->2","4->5","7");
        assertEquals(result, expect);
    }

    @Test
    void test2() {
        int[] nums = {0,2,3,4,6,8,9};

        //List<String> result = this.summaryRanges(nums);
        List<String> result = this.solution(nums);

        List<String> expect = List.of("0","2->4","6","8->9");
        assertEquals(result, expect);
    }

    @Test
    void test3() {
        int[] nums = {};

        List<String> result = this.summaryRanges(nums);
        assertEquals(result, new ArrayList<>());
    }

    @Test
    void test4() {
        int[] nums = {-1};

        //List<String> result = this.summaryRanges(nums);
        List<String> result = this.solution(nums);

        List<String> expect = List.of("-1");
        assertEquals(result, expect);
    }

    private List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }

        if (nums.length == 1) {
            return List.of(String.valueOf(nums[0]));
        }

        List<String> result = new ArrayList<>();
        int start = nums[0];
        int end;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] - nums[i-1] != 1) {
                end = nums[i-1];
                result.add(start == end ? String.valueOf(end) : String.format("%s->%s", start, end));
                start = nums[i];
            }

            if (i == nums.length - 1) {
                result.add(start == nums[i] ? String.valueOf(nums[i]) : String.format("%s->%s", start, nums[i]));
            }
        }

        return result;
    }

    private List<String> solution(int[] nums) {
        StringBuilder range = new StringBuilder();
        List<String> list = new ArrayList<>();

        for(int i=0; i<nums.length; i++){
            if(i < nums.length-1 && nums[i] == nums[i+1]-1){
                range.append(nums[i]).append("->");
                while(i < nums.length-1 && nums[i] == nums[i+1]-1){
                    i++;
                }
                range.append(nums[i]);
                list.add(range.toString());
                range.setLength(0);
            } else {
                list.add(nums[i] + "");
            }
        }
        return list;
    }
}
