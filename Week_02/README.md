# 学习笔记

整理一下这部分笔记经典题目（哈希表，映射，集合，树，二叉树，堆，二叉堆）

### 2.1有效的字母异位词
> 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。使用本节哈希表解法
```
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
           return false;
        }
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
```
### 2.2字母异位词分组 (给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。)
> 使用map解法
```
    public List<List<String>> groupAnagrams(String[] strs) {
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
                map.put(key,new LinkedList<>());
            }
            map.get(key).add(str);
        }
        return new LinkedList<>(map.values());
    }
```
### 2.3两数之和(给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。)
> map解法
```
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; map.put(nums[i],i++)) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]),i};
            }
        }
        return null;
    }
```
### 2.4二叉树的中序遍历（左根右）( 给定一个二叉树，返回它的中序 遍历。)
> 1)递归
```
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
 ```
> 2)维护栈的解法
```
    public List<Integer> inorderTraversal(TreeNode root) {
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
```
### 2.5二叉树的前序遍历（根左右）(给定一个二叉树，返回它的 前序 遍历。)
> 1)递归
```
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
```
> 2)维护栈的解法
```
    public List<Integer> preorderTraversal(TreeNode root) {
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
```
### 2.6 N叉树的后序遍历（左右根）(给定一个 N 叉树，返回其节点值的后序遍历。)
> 1)递归
```
    public List<Integer> postorder(Node root) {
        List<Integer> postorderList = new LinkedList<>();
        if (root == null) {
            return postorderList;
        }
        for (Node node : root.children) {
            postorderList.addAll(postorder(node));
        }
        postorderList.add(root.val);
        return postorderList;
    }
```
> 2)维护栈
```
    public List<Integer> postorder(Node root) {
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
```
### 2.7N叉树的层序遍历(给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。)
> 暂时理解了一种解法
```
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<List<Integer>> list = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> levelNodes = new LinkedList<>();
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
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
```
### 2.8 需要稍微在学习一下丑树的数学概念在更新

### 2.9 前 K 个高频元素 


