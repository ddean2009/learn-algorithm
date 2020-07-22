package com.flydean;

/**
 * @author wayne
 * @version BinarySearchTree,  2020/7/20
 */
public class BinarySearchTree {

    //根节点
    Node root;

    class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    //搜索方法，默认从根节点搜索
    public Node search(int data){
        return search(root,data);
    }

    //递归搜索节点
    private Node search(Node node, int data)
    {
        // 如果节点匹配，则返回节点
        if (node==null || node.data==data)
            return node;

        // 节点数据大于要搜索的数据，则继续搜索左边节点
        if (node.data > data)
            return search(node.left, data);

        // 如果节点数据小于要搜素的数据，则继续搜索右边节点
        return search(node.right, data);
    }

    // 插入新节点，从根节点开始插入
    public void insert(int data) {
        root = insert(root, data);
    }

    //递归插入新节点
    private Node insert(Node node, int data) {

        //如果节点为空，则创建新的节点
        if (node == null) {
            node = new Node(data);
            return node;
        }

        //节点不为空，则进行比较，从而递归进行左侧插入或者右侧插入
        if (data < node.data)
            node.left = insert(node.left, data);
        else if (data > node.data)
            node.right = insert(node.right, data);

        //返回插入后的节点
        return node;
    }

    // 删除新节点，从根节点开始删除
    void delete(int data)
    {
        root = delete(root, data);
    }

    //递归删除节点
    Node delete(Node node, int data)
    {
        //如果节点为空，直接返回
        if (node == null)  return node;

        //遍历左右两边的节点
        if (data < node.data)
            node.left = delete(node.left, data);
        else if (data > root.data)
            node.right = delete(node.right, data);

        //如果节点匹配
        else
        {
            //如果是单边节点，直接返回其下面的节点
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

            //如果是双边节点，则先找出右边最小的值，作为根节点，然后将删除最小值过后的右边的节点，作为根节点的右节点
            node.data = minValue(node.right);

            // 从右边删除最小的节点
            node.right = delete(node.right, node.data);
        }

        return node;
    }

    //查找节点的最小值
    int minValue(Node node)
    {
        int minv = node.data;
        while (node.left != null)
        {
            minv = node.left.data;
            node = node.left;
        }
        return minv;
    }

    //中序遍历BST
    public void inOrder(){
        inOrder(root);
    }

    //递归中序遍历
    private void inOrder(Node node){
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.data);
            inOrder(node.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree= new BinarySearchTree();
        binarySearchTree.insert(34);
        binarySearchTree.insert(21);
        binarySearchTree.insert(15);
        binarySearchTree.insert(44);
        binarySearchTree.insert(43);
        binarySearchTree.insert(37);
        binarySearchTree.insert(12);
        binarySearchTree.inOrder();
        binarySearchTree.delete(34);
        binarySearchTree.inOrder();
    }




}
