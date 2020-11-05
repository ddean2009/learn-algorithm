package com.flydean.easy;

import com.flydean.middle.No102levelOrderForTree;

/**
 * @author wayne
 * @version NO110isBalanced,  2020/8/26
 *
 * 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 */
public class NO110isBalanced {

    /**
     * 方法一：自顶向下的递归
     * 二叉树的每个节点的左右子树的高度差的绝对值不超过 11，则二叉树是平衡二叉树。
     * 根据定义，一棵二叉树是平衡二叉树，当且仅当其所有子树也都是平衡二叉树，因此可以使用递归的方式判断二叉树是不是平衡二叉树，
     * 递归的顺序可以是自顶向下或者自底向上。
     * 时间复杂度：O(n ^ 2)，其中 n 是二叉树中的节点个数。
     *  空间复杂度：O(n)，其中 n 是二叉树中的节点个数。空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过 n。
     */

    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height1(root.left) - height1(root.right)) <= 1 && isBalanced1(root.left) && isBalanced1(root.right);
        }
    }

    public int height1(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height1(root.left), height1(root.right)) + 1;
        }
    }

    /**
     * 方法二：自底向上的递归
     * 方法一由于是自顶向下递归，因此对于同一个节点，函数 height 会被重复调用，导致时间复杂度较高。
     * 如果使用自底向上的做法，则对于每个节点，函数 height 只会被调用一次。
     *
     * 自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。
     * 如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 −1。如果存在一棵子树不平衡，则整个二叉树一定不平衡。
     *
     * 时间复杂度：O(n)，其中 n 是二叉树中的节点个数。使用自底向上的递归，
     * 每个节点的计算高度和判断是否平衡都只需要处理一次，最坏情况下需要遍历二叉树中的所有节点，因此时间复杂度是 O(n)。
     *
     * 空间复杂度：O(n)，其中 n 是二叉树中的节点个数。空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过 n。
     *
     */

    public boolean isBalanced2(TreeNode root) {
        return height2(root) >= 0;
    }

    public int height2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height2(root.left);
        int rightHeight = height2(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


}
