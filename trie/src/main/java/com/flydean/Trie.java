package com.flydean;

/**
 * @author wayne
 * @version Trie,  2020/11/5
 */
public class Trie {
    // 假如字典中的单词只有26个英文字母
    static final int ALPHABET_SIZE = 26;

    // Trie的node节点
    static class TrieNode
    {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // 这个节点是否是结束节点
        boolean isEndOfWord;

        //初始化TireNode节点
        TrieNode(){
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    }

    static TrieNode root= new TrieNode();

    // 如果当前level中不存在，那么就会在特定的位置插入新的节点
    // 如果当前level中存在该字符，则从其子节点继续插入
    // 最后，将该叶子节点标记为isEndOfWord。
    static void insert(String key)
    {
        int level;
        int length = key.length();
        int index;

        TrieNode currentNode = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';
            if (currentNode.children[index] == null)
                currentNode.children[index] = new TrieNode();

            currentNode = currentNode.children[index];
        }

        // 将叶子节点标记为 isEndOfWord
        currentNode.isEndOfWord = true;
    }

    // 执行搜索，也是按level来进行查询
    static boolean search(String key)
    {
        int level;
        int length = key.length();
        int index;
        TrieNode currentNode = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';

            if (currentNode.children[index] == null)
                return false;

            currentNode = currentNode.children[index];
        }

        return (currentNode != null && currentNode.isEndOfWord);
    }

    //判断该节点是否有子节点
    static boolean hasChild(TrieNode currentNode)
    {
        for (int i = 0; i < ALPHABET_SIZE; i++)
            if (currentNode.children[i] != null)
                return true;
        return false;
    }

    static TrieNode remove(TrieNode currentNode, String key, int level ){
        if(currentNode ==null){
            return null;
        }

        int length = key.length();

        //正在处理最后一个字符
        if(level == length){
            //将当前节点的标志位删除
            if(currentNode.isEndOfWord){
                currentNode.isEndOfWord= false;
            }
            //如果没有子节点，则只接受删除该节点
            if (!hasChild(currentNode)) {
                currentNode = null;
            }

            return currentNode;
        }

        // 如果不是最后一个节点，则递归调用其子节点
        int index =  key.charAt(level) - 'a';
        currentNode.children[index] =
                remove(currentNode.children[index], key, level + 1);

        // 如果当前节点既没有子节点，也不是其他单词的结束节点，那么直接将这个节点删除即可。
        if (!hasChild(currentNode) && currentNode.isEndOfWord == false) {
            currentNode = null;
        }
        return currentNode;
    }

    public static void main(String args[])
    {
        String keys[] = {"www", "flydean", "com", "is", "a",
                "good", "website"};

        // 构造Trie tree
        int i;
        for (i = 0; i < keys.length ; i++)
            insert(keys[i]);
    }
}
