package com.flydean.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wayne
 * @version No144preorderTraversal,  2020/8/26
 *
 * 二叉树的前序遍历
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class No144preorderTraversal {


    /**
     * 递归解法
     * @param head
     */
    public static void preOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 迭代算法
     */

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resultList= new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            resultList.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return resultList;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
