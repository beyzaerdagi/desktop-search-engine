/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures_proje3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author beyza
 */
public class BinarySearchTree {

    String[] punctuations = {".", ",", "!", "'", "?", ":", "{}", "()", "-", "*", ";", "..."};

    Node root;

    void insertBinaryTree(File file) throws IOException {

        try {
            Scanner scanner = new Scanner(file);
            //read word by word from file
            while (scanner.hasNext()) {
                String word = scanner.next();
                //if is not title check if is HTML tag
                if (!word.contains("<")) {
                    //check if word equals to ignoreList's element od punctuations' element
                    boolean isFound = hasIgnoreList(word);
                    //if word not equals to ignoreList's element od punctuations' element add to the tree
                    if (!isFound) {
                        if (root == null) {
                            //if root is empty then insert word as root
                            root = new Node(word);
                            //increase frequency
                            root.frequency++;
                        } else {
                            Node curr = root;
                            while (curr != null) {
                                //check if word less than curr
                                if (curr.data.compareTo(word) > 0) {
                                    if (curr.left == null) {
                                        curr.left = new Node(word);
                                        curr.left.frequency++;
                                        break;
                                    } else {
                                        curr = curr.left;
                                    }
                                    //check if word greater than curr
                                } else if (curr.data.compareTo(word) < 0) {
                                    if (curr.right == null) {
                                        curr.right = new Node(word);
                                        curr.right.frequency++;
                                        break;
                                    } else {
                                        curr = curr.right;
                                    }
                                    //if word equals to the curr not insert tree, just increase frequency
                                } else {
                                    curr.frequency++;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    boolean hasIgnoreList(String str) throws FileNotFoundException {

        File file = new File("docs\\ignoreList.txt");
        Scanner scanner = new Scanner(file);
        //read word by word from ignore list
        while (scanner.hasNext()) {
            String word = scanner.next();
            //equals to ignoreList's element return true
            if (word.compareTo(str) == 0) {
                return true;
            }
        }
        //send to method
        return hasPunctuations(str);
    }

    boolean hasPunctuations(String str) {

        for (int i = 0; i < punctuations.length; i++) {
            //equals to punctuations' element return true
            if (str.compareTo(punctuations[i]) == 0) {
                return true;
            }
        }
        return false;
    }

    int search(Node root, String key) {
        // Traverse until root reaches to dead end
        while (root != null) {
            // pass right subtree as new tree
            if (root.data.compareTo(key) < 0) {
                root = root.right;
            } // pass left subtree as new tree
            else if (root.data.compareTo(key) > 0) {
                root = root.left;
            } else {
                return root.frequency; // if the key is found return frequency
            }
        }
        return 0;
    }
}
