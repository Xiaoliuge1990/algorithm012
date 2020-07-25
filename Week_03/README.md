# 学习笔记
回顾一下经典题目（范性递归，树，分治，回溯）

### 3.1爬楼梯 （斐波那契数列变形）
> 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
```
	/**
	 * 斐波那契数列通向公式 
	 */
    public int climbStairs(int n) {
        double sqrt5 = Math.sqrt(5);
        return (int)((Math.pow((1 + sqrt5) / 2,n + 1) - Math.pow((1 - sqrt5) / 2,n + 1)) /sqrt5);
    }

    /**
     * 动态规划
     */
    public int climbStairs(int n) {
        int tmp0 = 0, tmp1 = 0, tmp2 = 1;
        for (int i = 0; i < n; i++) {
            tmp0 = tmp1;
            tmp1 = tmp2;
            tmp2 = tmp0 + tmp1;
        }
        return tmp2;
    }

```
### 3.2括号生成
> 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
```
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (0 == n) {
            return list;
        }
        recursion(list, 0, 0, n, "");
        return list;
    }
    private void recursion(List<String> list, int l, int r, int n, String s) {
        if (l == n && r == n) {
            list.add(s);
            return;
        }
        if (l < n) {
            recursion(list, l + 1, r, n, s + "(");
        }
        if (l > r) {
            recursion(list, l,r + 1, n, s + ")");
        }
    }
```
### 3.3翻转二叉树
> 翻转一棵二叉树。
```
	/**
	 * 递归
	 */
	public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
    /**
	 * 队列 + 迭代
	 */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        TreeNode tmp,rtmp;
        while (!q.isEmpty()) {
            tmp = q.pop();
            rtmp = tmp.left;
            tmp.left = tmp.right;
            tmp.right = rtmp;
            if (tmp.left != null) {
                q.offer(tmp.left);
            }
            if (tmp.right != null) {
                q.offer(tmp.right);
            }
        }
        return root;
    }
```
### 3.4 验证二叉搜索树
> 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
```
	/**
	 * 中序遍历 ，使用栈
	 */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.val <= pre.val ) {
                return false;
            }
            pre = root;
            root = root.right;
        }
        return true;
    }

    /**
     *  中序遍历 递归
     */
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isValidBST(root.left);
        if (pre != null && root.val <= pre.val) {
            return false;
        }
        pre = root;
        boolean right = isValidBST(root.right);
        return left && right;
    }
```
### 3.5二叉树的最大深度
>给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
```
	/**
	 * 递归
	 */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
```
### 3.6二叉树的最小深度
>给定一个二叉树，找出其最小深度。最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
```
	/**
	 * 递归
	 */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return left == 0 || right == 0 ? left + right + 1 : Math.min(left,right) + 1;
    }
```
### 3.7二叉树的序列化与反序列化
> 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
```
    public String serialize(TreeNode root) {
        StringBuilder s = new StringBuilder("");
        serializeStr(root,s);
        return s.toString();
    }
    private void serializeStr(TreeNode root ,StringBuilder s) {
        if (root == null) {
            s.append("null,");
            return;
        }
        s.append(root.val + ",");
        serializeStr(root.left ,s);
        serializeStr(root.right ,s);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodesArr = data.split(",");
        if (nodesArr.length == 0) {
            return null;
        }
        Deque<String> q = new LinkedList<>(Arrays.asList(nodesArr));
        return deserializeStr(q);

    }
    private TreeNode deserializeStr(Deque<String> q) {
        String val = q.pop();
        if (val.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserializeStr(q);
        root.right = deserializeStr(q);
        return root;
    }
```



