package com.leetcode.slidingWindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * https://leetcode.com/problems/minimum-size-subarray-sum/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class MinimumSizeSubarraySumTest {

    @Test
    void test1() {
        int target = 7;
        int[] nums = {2,3,1,2,4,3};

        //int result = this.finalMethod(target, nums);
        int result = this.solution(target, nums);

        assertEquals(2, result);
    }

    @Test
    void test2() {
        int target = 4;
        int[] nums = {1,4,4};

        int result = this.finalMethod(target, nums);

        assertEquals(1, result);
    }

    @Test
    void test3() {
        int target = 11;
        int[] nums = {1,1,1,1,1,1,1,1};

        //int result = this.finalMethod(target, nums);
        int result = this.solution(target, nums);

        assertEquals(0, result);
    }

    @Test
    void test4() {
        int target = 213;
        int[] nums = {12,28,83,4,25,26,25,2,25,25,25,12};

        // int result = this.finalMethod(target, nums);
        int result = this.solution(target, nums);

        assertEquals(8, result);
    }

    @Test
    void test5() {
        int target = 11;
        int[] nums = {1,2,3,4,5};

        //int result = this.finalMethod(target, nums);
        int result = this.solution(target, nums);

        assertEquals(3, result);
    }

    // 모든 테스트 케이스를 통과하지만 런타임 실행 속도가 느림...
    private int finalMethod(int target, int[] nums) {
        int minSize = nums.length;

        for (int i = 0; i < nums.length; i++) {
            int arraySize = 0;
            int sum = 0;
            boolean isSatisfy = false;

            for (int index = i; index < nums.length; index++) {
                sum = sum + nums[index];
                if (sum >= target) {
                    arraySize = index - i + 1;
                    isSatisfy = true;
                    break;
                }
                arraySize = minSize;
            }

            if (isSatisfy) {
                minSize = Math.min(minSize, arraySize);
            } else {
                return i == 0 ? 0 : minSize;
            }
        }

        return minSize;
    }

    // 대부분의 테스트 케이스는 통과하나 시간 복잡도로 인해 타임아웃이 발생
    private int firstTryMethod(int target, int[] nums) {
        int minCount = 0;

        for (int subArraySize = nums.length; subArraySize > 0; subArraySize--) {
            for (int startIndex = 0; startIndex <= nums.length - subArraySize; startIndex++) {
                int arraySum = 0;

                if (subArraySize == 1) {
                    arraySum = nums[startIndex];
                } else {
                    for (int i = 0; i < subArraySize; i++) {
                        arraySum = arraySum + nums[startIndex + i];
                    }
                }

                if (arraySum >= target) {
                    minCount = subArraySize;
                    break;
                }
            }
            if (minCount < subArraySize) {
                break;
            }
        }

        return minCount;
    }

    public int solution(int target, int[] nums) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        while( j < nums.length) {
            sum += nums[j];
            if (sum >= target) {
                while (sum >= target) {
                    min = Math.min(min, (j - i + 1));
                    sum -= nums[i];
                    i++;
                }
            }
            j++;
        }

        if(i == 0) {
            return 0;
        }
        return min;
    }
}
