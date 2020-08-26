package com.flydean.easy;

/**
 * @author wayne
 * @version No108sortedArrayToBST,  2020/8/26
 *
 * 将有序数组转换为二叉搜索树
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class No108sortedArrayToBST {

    /**
     * 我们可以选择中间数字作为二叉搜索树的根节点，这样分给左右子树的数字个数相同或只相差 1，
     * 可以使得树保持平衡。如果数组长度是奇数，则根节点的选择是唯一的，
     * 如果数组长度是偶数，则可以选择中间位置左边的数字作为根节点或者选择中间位置右边的数字作为根节点，
     * 选择不同的数字作为根节点则创建的平衡二叉搜索树也是不同的。
     * 确定平衡二叉搜索树的根节点之后，其余的数字分别位于平衡二叉搜索树的左子树和右子树中，
     * 左子树和右子树分别也是平衡二叉搜索树，因此可以通过递归的方式创建平衡二叉搜索树。
     *
     * 递归的基准情形是平衡二叉搜索树不包含任何数字，此时平衡二叉搜索树为空。
     * 方法一：中序遍历，总是选择中间位置左边的数字作为根节点
     * 选择中间位置左边的数字作为根节点，则根节点的下标为mid=(left+right)/2，此处的除法为整数除法。
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
