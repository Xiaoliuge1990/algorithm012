package org.chang.test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Work01 {

    public static void main(String[] args) {
        Deque deque = new ArrayDeque();
    }

    /**
     * 1.使用新deque新的api
     */
    private void useNewApi(){

        Deque<String> foo = new LinkedList<>();
        foo.addFirst("1");
        foo.add("3");
        foo.addLast("2");
        System.out.println(foo);

        //检索但不删除双端队列的第一个元素 ==peek()
        String str = foo.peekFirst();
        System.out.println("head：" + str);
        System.out.println(foo);

        while (foo.size() > 0) {
            // ==pop()
            System.out.println(foo.removeFirst());
        }
        System.out.println(foo);

        Queue<String> queue = new PriorityQueue<>();
    }

    /**
     * 2.删除排序数组中的重复项
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int j = 0;
        for (int i = 1; i < nums.length;i++) {
            if (nums[j] != nums[i]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }

    /**
     * 3.1旋转数组
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        int temp,prev;
        for (int i = 0; i < k; i++) {
            prev = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = prev;
                prev = temp;
            }
        }
    }

    /**
     * 3.2旋转数组,尽管看的是题解，但是这个方法还是比较容易理解的，环状替换没理解
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    private void reverse (int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    /**
     * 4.合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null){
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

    /**
     * 5.合并两个有序数组
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //暴力
//        System.arraycopy(nums2,0,nums1,m,n);
//        Arrays.sort(nums1);
        //双指针
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] < nums2[j]) {
                nums1[k--] = nums2[j--];
            } else {
                nums1[k--] = nums1[i--];
            }
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    /**
     * 两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        //暴力
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if ((nums[i] + nums[j]) == target) {
//                    return new int[]{i,j};
//                }
//            }
//        }
        //hashmap
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; map.put(nums[i], i++)) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]),i};
            }
        }
        return null;
    }

    /**
     * 移动零
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int i = 0;
        for (int j = 0 ;j < nums.length ; j++) {
            if (nums[j] != 0) {
                int tmp = nums[i];
                nums[i++] = nums[j];
                nums[j] = tmp;
            }
        }
    }

    /**
     * 加一
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
