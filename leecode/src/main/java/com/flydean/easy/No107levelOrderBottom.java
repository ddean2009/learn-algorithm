package com.flydean.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wayne
 * @version No107levelOrderBottom,  2020/8/26
 *
 * 二叉树的层次遍历 II
 *
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class No107levelOrderBottom {

    /**
     * 和 No102，很类似， 这里我们使用 lists.add(0,list);添加到list中的第一个中去，从而保证了反向顺序。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<>();
        if (root == null) {
            return lists;
        }
        List<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
               TreeNode remove = nodes.remove(0);
                list.add(remove.val);
                if (remove.left != null) {
                    nodes.add(remove.left);
                }
                if (remove.right != null) {
                    nodes.add(remove.right);
                }
            }
            lists.add(0,list);
        }
        return lists;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
