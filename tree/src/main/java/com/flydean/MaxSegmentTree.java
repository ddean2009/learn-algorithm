package com.flydean;

/**
 * @author wayne
 * @version MaxSegmentTree,  2020/8/18
 * 最大线段树，非叶子节点存储的是范围内的最大值
 */
public class MaxSegmentTree {

    private int[] segmentTree; //新构建的segmentTree，对于满二叉树 最后一层的节点数乘以2 大致就是整棵树的节点数。
    //但是线段树并不一定是满二叉树，但是一定是平衡二叉树，所以需要多冗余一层。也就是 乘以4 就足以盛放所有的节点数
    //新构建的segmentTree 以index=1为起点
    private int[] originalArray; //原始数组
    private int n; //原始数组的长度
    private int left (int p) { return p << 1; } //左子结点为2*p
    private int right(int p) { return (p << 1) + 1; } //右子结点为2*p + 1

    /**
     * 构建segmentTree
     * @param treeIndex 当前需要添加节点的索引
     * @param arrayLeft 数组的左边界
     * @param arrayRight 数组的右边界
     */
    private void build(int treeIndex, int arrayLeft, int arrayRight) {
        if (arrayLeft == arrayRight)   //如果相等则随便选择一个赋值
            segmentTree[treeIndex] = originalArray[arrayLeft];
        else {       // 否则分别构建左侧子树和右侧子树，并根据我们需要构建的segmentTree类型来设置当前节点的值
            build(left(treeIndex) , arrayLeft , (arrayLeft + arrayRight) / 2);
            build(right(treeIndex), (arrayLeft + arrayRight) / 2 + 1, arrayRight);
            int p1 = segmentTree[left(treeIndex)], p2 = segmentTree[right(treeIndex)];
            segmentTree[treeIndex] = (p1 >= p2) ? p1 : p2;
        } }


    /**
     * 范围查询
     * @param treeIndex 当前要查找的节点index
     * @param arrayLeft 数组左边界
     * @param arrayRight 数组右边界
     * @param searchLeft 搜索左边界
     * @param searchRight 搜索右边界
     * @return
     */
    private int rangeQuery(int treeIndex, int arrayLeft, int arrayRight, int searchLeft, int searchRight) {
        if (searchLeft >  arrayRight || searchRight <  arrayLeft) return -1; // 搜索超出数组范围
        if (arrayLeft >= searchLeft && arrayRight <= searchRight) return segmentTree[treeIndex];  // 搜索的是整个数组范围，则直接返回根元素

        // 否则左右搜索
        int p1 = rangeQuery(left(treeIndex) , arrayLeft, (arrayLeft+arrayRight) / 2, searchLeft, searchRight);
        int p2 = rangeQuery(right(treeIndex), (arrayLeft+arrayRight) / 2 + 1, arrayRight, searchLeft, searchRight);

        if (p1 == -1) return p2;   // 如果超出范围，则返回另外一个
        if (p2 == -1) return p1;
        return (p1 >= p2) ? p1 : p2; }  //返回最小的那个


    /**
     * 更新数组中的某个节点
     * @param treeIndex 树的index
     * @param arrayLeft 数组左边界
     * @param arrayRight 数组右边界
     * @param arrayIndex 要更新的数组index
     * @param newValue 要更新的值
     * @return
     */
    private int updatePoint(int treeIndex, int arrayLeft, int arrayRight, int arrayIndex, int newValue) {
        // 设置i 和 j 等于要更新的数组index
        int i = arrayIndex, j = arrayIndex;

        // arrayIndex超出范围，则直接返回
        if (i > arrayRight || j < arrayLeft)
            return segmentTree[treeIndex];

        // 左右两个index相等
        if (arrayLeft == i && arrayRight == j) {
            originalArray[i] = newValue; // 找到要更新的index
            return segmentTree[treeIndex] = originalArray[i]; // 更新segmentTree
        }

        // 分别获得左右子树的最小值
        int p1, p2;
        p1 = updatePoint(left(treeIndex) , arrayLeft , (arrayLeft + arrayRight) / 2, arrayIndex, newValue);
        p2 = updatePoint(right(treeIndex), (arrayLeft + arrayRight) / 2 + 1, arrayRight , arrayIndex, newValue);

        // 更新treeIndex的值
        return segmentTree[treeIndex] = (p1 >= p2) ? p1 : p2;
    }

    public MaxSegmentTree(int[] array) {
        originalArray = array; n = originalArray.length; // 拷贝原始数组
        segmentTree = new int[4 * n];  //初始化新数组，长度是4*n
        for (int i = 0; i < 4 * n; i++) segmentTree[i] = 0;
        build(1, 0, n - 1);   // 递归构建segmentTree，以index=1为起点
    }

    public int rangeQuery(int i, int j) { return rangeQuery(1, 0, n - 1, i, j); } // overloading

    public int updatePoint(int idx, int newValue) {
        return updatePoint(1, 0, n - 1, idx, newValue);
    }

    public static void main(String[] args) {
        int[] A = new int[] { 18, 17, 13, 19, 15, 11, 20 }; // the original array
        MaxSegmentTree st = new MaxSegmentTree(A);

        System.out.printf("              idx    0, 1, 2, 3, 4,  5, 6\n");
        System.out.printf("              A is {18,17,13,19,15, 11,20}\n");
        System.out.printf("RMQ(1, 3) = %d\n", st.rangeQuery(1, 3)); // answer = 13

        System.out.printf("              idx    0, 1, 2, 3, 4,  5, 6\n");
        System.out.printf("Now, modify A into {18,17,13,19,15,100,20}\n");
        st.updatePoint(5, 100);                  // update A[5] from 11 to 100
        System.out.printf("These values do not change\n");
        System.out.printf("RMQ(1, 3) = %d\n", st.rangeQuery(1, 3));               // 13
    }
}
