package com.flydean;

import java.util.Stack;

/**
 * 二叉树的遍历---递归算法和迭代算法
 * @author wayne
 * @version RecurTree,  2020/8/26
 */
public class RecurTree {

    /**
     * 递归算法
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void preOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void middleOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        middleOrderRecur(head.left);
        System.out.print(head.val + " ");
        middleOrderRecur(head.right);
    }

    public static void postOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.print(head.val + " ");
    }

    /**
     * 迭代算法
     * 本质上是在模拟递归，因为在递归的过程中使用了系统栈，所以在迭代的解法中常用Stack来模拟系统栈。
     *
     * 前序遍历
     * 首先我们应该创建一个Stack用来存放节点，首先我们想要打印根节点的数据，
     * 此时Stack里面的内容为空，所以我们优先将头结点加入Stack，然后打印。
     *
     * 之后我们应该先打印左子树，然后右子树。所以先加入Stack的就是右子树，然后左子树。
     * 此时你能得到的流程如下:
     *
     */

    public static void preOrderIteration(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
    /**
     * 中序遍历
     * 同理创建一个Stack，然后按 左 中 右的顺序输出节点。
     * 尽可能的将这个节点的左子树压入Stack，此时栈顶的元素是最左侧的元素，
     * 其目的是找到一个最小单位的子树(也就是最左侧的一个节点)，并且在寻找的过程中记录了来源，才能返回上层,
     * 同时在返回上层的时候已经处理完毕左子树了。
     * 当处理完最小单位的子树时，返回到上层处理了中间节点。
     * （如果把整个左中右的遍历都理解成子树的话，就是处理完 左子树->中间(就是一个节点)->右子树）
     * 如果有右节点，其也要进行中序遍历。
     *
     */

    public static void inOrderIteration(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                cur = node.right;
            }
        }
    }

    /**
     * 后序遍历
     * 代码片段1
     * 前序遍历的过程 是 中左右。
     * 将其转化成 中右左。也就是压栈的过程中优先压入左子树，在压入右子树。
     * 然后将这个结果返回来，这里是利用栈的先进后出倒序打印。
     *
     *
     */
    public static void postOrderIteration(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }

    /**
     * 后序遍历
     *
     * 代码片段2
     * 用一个指针cur标记当前退出的节点是什么。
     * 后序遍历的过程中在遍历完左子树跟右子树cur都会回到根结点。
     * 所以当前不管是从左子树还是右子树回到根结点都不应该再操作了，应该退回上层。
     * 如果是从右边再返回根结点，应该回到上层。
     *
     */

    public static void postOrderIteration2(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (peek.left != null && peek.left != cur && peek.right != cur) {
                stack.push(peek.left);
            } else if (peek.right != null && peek.right != cur) {
                stack.push(peek.right);
            } else {
                System.out.print(stack.pop().val + " ");
                cur = peek;
            }
        }
    }

    /**
     * Morris的整体思路就是将以某个根结点开始，找到它左子树的最右侧节点之后与这个根结点进行连接
     * 如果这么连接之后，cur 这个指针是可以完整的从一个节点顺着下一个节点遍历，将整棵树遍历完毕，直到这个节点右侧没有指向。
     *
     */

    public static void preOrderMorris(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;//当前开始遍历的节点
        TreeNode cur2 = null;//记录当前结点的左子树
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {//找到当前左子树的最右侧节点，且这个节点应该在指向根结点之前，否则整个节点又回到了根结点。
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {//这个时候如果最右侧这个节点的右指针没有指向根结点，创建连接然后往下一个左子树的根结点进行连接操作。
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {//当左子树的最右侧节点有指向根结点，此时说明我们已经回到了根结点并重复了之前的操作，同时在回到根结点的时候我们应该已经处理完 左子树的最右侧节点 了，把路断开。
                    cur2.right = null;
                }
            }
            cur1 = cur1.right;//一直往右边走，参考图
        }
    }

}
