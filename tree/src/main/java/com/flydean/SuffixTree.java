package com.flydean;

import java.util.List;

/**
 * @author wayne
 * @version SuffixTree,  2020/11/7
 */
public class SuffixTree {

    SuffixTrieNode root = new SuffixTrieNode();

    // Constructor (Builds a trie of suffies of the
    // given text)
    SuffixTree(String txt) {

        // Consider all suffixes of given string and
        // insert them into the Suffix Trie using
        // recursive function insertSuffix() in
        // SuffixTrieNode class
        for (int i = 0; i < txt.length(); i++)
            root.insertSuffix(txt.substring(i), i);
    }

    /* Prints all occurrences of pat in the Suffix Trie S
    (built for text) */
    void searchTree(String pat) {

        // Let us call recursive search function for
        // root of Trie.
        // We get a list of all indexes (where pat is
        // present in text) in variable 'result'
        List<Integer> result = root.search(pat);

        // Check if the list of indexes is empty or not
        if (result == null)
            System.out.println("Pattern not found");
        else {

            int patLen = pat.length();

            for (Integer i : result)
                System.out.println("Pattern found at position " +
                        (i - patLen));
        }
    }

    // driver program to test above functions
    public static void main(String args[]) {

        // Let us build a suffix trie for text
        String txt = "www.flydean.com";
        SuffixTree S = new SuffixTree(txt);

        System.out.println("Search for 'ww'");
        S.searchTree("ww");

        System.out.println("\nSearch for 'flydean'");
        S.searchTree("flydean");

        System.out.println("\nSearch for 'ea'");
        S.searchTree("ea");

        System.out.println("\nSearch for 'com'");
        S.searchTree("com");
    }
}
