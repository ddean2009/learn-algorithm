package com.flydean;

import java.util.HashSet;

/**
 * @author wayne
 * @version TernaryTree,  2020/11/6
 */
public class TernaryTree {

    enum NodeType
    {
        COMPLETED,
        UNCOMPLETED
    }

    static class Node
    {
        public char word;

        public Node leftChild, centerChild, rightChild;

        public NodeType type;

        public Node(char ch, NodeType type)
        {
            word = ch;
            this.type = type;
        }
    }

    private Node _root;

    private HashSet<String> _hashSet;


    /**
     * 向node插入 s 中的 index 位的字符
     * @param s 整个单词
     * @param index 单词中的制定字符位
     * @param node 要被插入到的树的节点
     */
    private void insert(String s, int index, Node node)
    {
        if (null == node)
        {
            node = new Node(s.charAt(index), NodeType.UNCOMPLETED);
        }

        if (s.charAt(index) < node.word)
        {
            this.insert(s, index,  node.leftChild);
        }
        else if (s.charAt(index) > node.word)
        {
            this.insert(s, index,  node.rightChild);
        }
        else
        {
            if (index + 1 == s.length())
            {
                node.type = NodeType.COMPLETED;
            }
            else
            {
                this.insert(s, index + 1,  node.centerChild);
            }
        }
    }

    /**
     * 将单词 s 插入到树中
     * @param s
     */
    public void insert(String s)
    {
        if (s == null || s.length() == 0 )
        {
            return ;
        }

        insert(s, 0,  _root);
    }

    /**
     * 查找特定的单词
     * @param s 待查找的单词
     * @return
     */
    public Node find(String s)
    {
        if (s == null || s.length() == 0 )
        {
            return null;
        }

        int pos = 0;
        Node node = _root;
        _hashSet = new HashSet();
        while (node != null)
        {
            if (s.charAt(pos) < node.word)
            {
                node = node.leftChild;
            }
            else if (s.charAt(pos) > node.word)
            {
                node = node.rightChild;
            }
            else
            {
                if (++pos == s.length())
                {
                    _hashSet.add(s);
                    return node.centerChild;
                }

                node = node.centerChild;
            }
        }

        return null;
    }

    /**
     * 前缀匹配
     * @param prefix
     * @param node
     */
    private void DFS(String prefix, Node node)
    {
        if (node != null)
        {
            if (NodeType.COMPLETED == node.type)
            {
                _hashSet.add(prefix + node.word);
            }

            DFS(prefix, node.leftChild);
            DFS(prefix + node.word, node.centerChild);
            DFS(prefix, node.rightChild);
        }
    }

    /**
     * 相识度查找
     * @param s 要查找的单词
     * @return
     */
    public HashSet<String> findSimilar(String s)
    {
        Node node = this.find(s);
        this.DFS(s, node);
        return _hashSet;
    }

}
