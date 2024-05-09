package com.leetcode.arrayString;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/*
* https://leetcode.com/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
*/
public class MergeSortedArrayTest {

    @Test
    void test1() {
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;

        int[] result = this.merge(nums1, m, nums2, n);
        assertArrayEquals(result, new int[] {1,2,2,3,5,6});
    }

    @Test
    void test2() {
        int[] nums1 = {1};
        int m = 1;
        int[] nums2 = {};
        int n = 0;

        int[] result = this.merge(nums1, m, nums2, n);
        assertArrayEquals(result, new int[]{1});
    }

    @Test
    void test3() {
        int[] nums1 = {0};
        int m = 0;
        int[] nums2 = {1};
        int n = 1;

        int[] result = this.merge(nums1, m, nums2, n);
        assertArrayEquals(result, new int[]{1});
    }

    private int[] merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i = 0; i < m + n; i++) {
            if (i < m) {
                continue;
            } else {
                nums1[i] = nums2[i-m];
            }
            System.out.println(nums1[i]);
        }
        Arrays.sort(nums1);

        return nums1;
    }

    @Test
    void solution() {
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;

        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // Handle remaining elements from nums2
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
