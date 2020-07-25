package org.chang.test.week03;

import java.util.*;

public class Week03 {

    /**
     * 1二叉树的最近公共祖先(需要巩固)暂时只理解了这种解法
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    /**
     * 2.从前序与中序遍历序列构造二叉树 (目前理解了这一种方式)
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) {
            return null;
        }
        Map<Integer,Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return buildNode(preorder, inorder, 0, preorder.length, 0, inorder.length, map);
    }
    public TreeNode buildNode(int[] preorder, int[] inorder, int pStart, int pEnd, int iStart, int iEnd, Map<Integer, Integer> map)  {
        if (pStart == pEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        int iIndex = map.get(preorder[pStart]);
        int leftLength = iIndex - iStart;
        root.left = buildNode(preorder, inorder, pStart + 1, pStart + 1 + leftLength, iStart, iIndex, map);
        root.right = buildNode(preorder, inorder, pStart + leftLength - 1, pEnd , iIndex + 1, iEnd, map);
        return root;
    }

    /**
     * 3.组合 (还是放弃不了人肉递归的习惯，还需要多练)
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new LinkedList<>();
        if (n == 0 || k == 0 || n < k) {
            return new ArrayList<>();
        }
        combine(n,k,1,list,new ArrayList<>());
        return list;
    }

    private void combine(int n, int k, int start, List<List<Integer>> list,List<Integer> temp) {
        if (k == 0) {
            list.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = start; i <= n - k + 1; i ++) {
            temp.add(i);
            combine(n, k - 1, i + 1, list, temp);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * 4.全排列 (时间复杂度不会算了，回溯有点难)
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length == 0) {
            return list;
        }
        numsBacktrack(list, new LinkedList<>(), new HashSet<>(), nums);
        return list;
    }
    private void numsBacktrack(List<List<Integer>> list, List<Integer> tmpList, Set<Integer> tmpSet, int[] nums) {
        if (tmpSet.size() == nums.length) {
            list.add(new LinkedList<>(tmpList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tmpSet.contains(nums[i])) {
                continue;
            }
            tmpList.add(nums[i]);
            tmpSet.add(nums[i]);
            numsBacktrack(list, tmpList, tmpSet, nums);
            tmpSet.remove(tmpList.get(tmpList.size() - 1));
            tmpList.remove(tmpList.size() - 1);
        }
    }

    /**
     * 5.全排列 II （回溯的剪枝过程比回溯本身要难的多）
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length == 0) {
            return list;
        }
        Arrays.sort(nums);
        numsBacktrackAndCut(list, new ArrayList<>(), new boolean[nums.length], nums);
        return list;
    }

    private void numsBacktrackAndCut(List<List<Integer>> list, List<Integer> tmpList, boolean[] used, int[] nums) {
        if (tmpList.size() == nums.length) {
            list.add(new ArrayList<>(tmpList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                continue;
            }
            tmpList.add(nums[i]);
            used[i] = true;
            numsBacktrackAndCut(list, tmpList, used, nums);
            tmpList.remove(tmpList.size() - 1);
            used[i] = false;

        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }
}
