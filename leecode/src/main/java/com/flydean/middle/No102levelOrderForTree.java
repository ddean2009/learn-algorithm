package com.flydean.middle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wayne
 * @version No102levelOrderForTree,  2020/8/26
 *
 * 二叉树的层序遍历
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class No102levelOrderForTree {

    /**
     * 宽度优先搜索
     * 思路和算法
     *
     * 我们可以用宽度优先搜索解决这个问题。
     *
     * 我们可以想到最朴素的方法是用一个二元组 (node, level) 来表示状态，
     * 它表示某个节点和它所在的层数，每个新进队列的节点的 level 值都是父亲节点的 level 值加一。
     * 最后根据每个点的 level 对点进行分类，分类的时候我们可以利用哈希表，
     * 维护一个以 level 为键，对应节点值组成的数组为值，宽度优先搜索结束以后按键 level 从小到大取出所有值，组成答案返回即可。
     *
     * 考虑如何优化空间开销：如何不用哈希映射，并且只用一个变量 node 表示状态，实现这个功能呢？
     *
     * 我们可以用一种巧妙的方法修改 BFS：
     *
     * 首先根元素入队
     * 当队列不为空的时候
     * 求当前队列的长度 si
     * 依次从队列中取 si 个元素进行拓展，然后进入下一次迭代
     * 它和 BFS 的区别在于 BFS 每次只取一个元素拓展，而这里每次取 si个元素。
     * 在上述过程中的第 i 次迭代就得到了二叉树的第 i 层的 si个元素。

     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<>();
        if (root == null) {
            return lists;
        }
        //2.
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
            lists.add(list);
        }
        return lists;
    }

    //双队列
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        if (root==null)
            return res;
        LinkedList<TreeNode> queue1=new LinkedList<>(),queue2=new LinkedList<>();
        queue1.offer(root);
        while (!queue1.isEmpty()){
            List<Integer> item=new ArrayList<>();
            while (!queue1.isEmpty()){
                TreeNode node=queue1.remove();
                item.add(node.val);
                if (node.left!=null)
                    queue2.offer(node.left);
                if (node.right!=null)
                    queue2.offer(node.right);
            }
            res.add(item);
            LinkedList<TreeNode> tmp=queue1;
            queue1=queue2;
            queue2=tmp;
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
