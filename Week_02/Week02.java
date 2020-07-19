package org.chang.test.week02;

import java.util.*;

public class Week02 {

    /**
     * 1.有效的字母异位词
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
           return false;
        }
        Map map = new HashMap();
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int k : counter) {
            if (k != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 2.1N叉树的前序遍历 递归
     * @param root
     * @return
     */
    List<Integer> preorderList = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if (root == null) {
            return preorderList;
        }
        preorderList.add(root.val);
        for (Node node : root.children) {
            preorder(node);
        }
        return preorderList;
    }

    /**
     * 2.2N叉树的前序遍历 迭代
     * @param root
     * @return
     */

    public List<Integer> preorder2(Node root) {
        List<Integer> preorderList2 = new ArrayList<>();
        if (root == null) {
            return preorderList2;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            preorderList2.add(root.val);
            for (int i = root.children.size() - 1; i >= 0; i-- ) {
                stack.push(root.children.get(i));
            }
        }
        return preorderList2;
    }

    /**
     * 3.1字母异位词分组
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String,List<String>> map = new HashMap<>(strs.length);
        for (String str : strs) {
            char[] sArr = str.toCharArray();
            Arrays.sort(sArr);
            String key = String.valueOf(sArr);
            if (!map.containsKey(key)) {
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
    /**
     * 3.2字母异位词分组
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String,List<String>> map = new HashMap<>(strs.length);
        for (String str : strs) {
            int[] count = new int[26];
            for (int i = 0; i < str.length() ; i++) {
                count[str.charAt(i) - 'a']++;
            }
            String key = Arrays.toString(count);
            if (!map.containsKey(key)) {
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 3.1二叉树的中序遍历 递归
     * @param root
     * @return
     */
    List<Integer> inorderTraversalList = new ArrayList<>();
    public List<Integer> inorderTraversal1(TreeNode root) {
        if (root == null) {
            return inorderTraversalList;
        }
        inorderTraversal1(root.left);
        inorderTraversalList.add(root.val);
        inorderTraversal1(root.right);
        return inorderTraversalList;
    }

    /**
     * 3.2二叉树的中序遍历 栈
     * @param root
     * @return
     */

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> inorderTraversalList2 = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            inorderTraversalList2.add(root.val);
            root = root.right;
        }
        return inorderTraversalList2;
    }

    /**
     * 4.1二叉树的前序遍历 递归
     */
    List<Integer> preorderTraversalList = new ArrayList<>();
    public List<Integer> preorderTraversal1(TreeNode root) {
        if (root == null) {
            return preorderTraversalList;
        }
        preorderTraversalList.add(root.val);
        preorderTraversal1(root.left);
        preorderTraversal1(root.right);
        return preorderTraversalList;
    }

    /**
     * 4.2二叉树的前序遍历 栈
     * @param root
     * @return
     */

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> preorderTraversalList2 = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (root != null) {
                preorderTraversalList2.add(root.val);
                stack.push(root.right);
                stack.push(root.left);
            }
        }
        return preorderTraversalList2;
    }

    /**
     * 4.1 给定一个 N 叉树，返回其节点值的后序遍历
     * @param root
     * @return
     */

    public List<Integer> postorder1(Node root) {
        List<Integer> postorderList = new LinkedList<>();
        if (root == null) {
            return postorderList;
        }
        for (Node node : root.children) {
            postorderList.addAll(postorder1(node));
        }
        postorderList.add(root.val);
        return postorderList;
    }

    /**
     * 4.2 给定一个 N 叉树，返回其节点值的后序遍历
     * @param root
     * @return
     */

    public List<Integer> postorder2(Node root) {
        List<Integer> postorderList = new LinkedList<>();
        if (root == null) {
            return postorderList;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            postorderList.add(0,root.val);
            for (Node node : root.children) {
                stack.push(node);
            }
        }
        return postorderList;
    }

    /**
     * 5.N叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<List<Integer>> list = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> levelNodes = new LinkedList<>();
            for (int i = 0; i < queue.size(); i++) {
                root = queue.poll();
                levelNodes.add(root.val);
                for (Node node : root.children) {
                    queue.add(node);
                }
            }
            list.add(levelNodes);
        }
        return list;
    }

    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
