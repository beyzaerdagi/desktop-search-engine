/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures_proje3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author beyza
 */
public class Heap {

    BinarySearchTree treeArr[];
    ArrayList<String> minHeap;

    public Heap() {
        minHeap = new ArrayList<>();
        treeArr = new BinarySearchTree[10];
    }

    int parent(int index) {

        return (index - 1) / 2;
    }

    void heapify(int n, int i) {
        int smallest = i; // Initialize smalles as root
        int left = 2 * i + 1; 
        int right = 2 * i + 2; 

        // If left child is smaller than root
        if (left < n) {
            int frLeft = findFrequency(minHeap.get(left));
            int frSmallest = findFrequency(minHeap.get(smallest));
            if (frLeft < frSmallest) {
                smallest = left;
            }
        }
        // If right child is smaller than smallest so far
        if (right < n) {
            int frRight = findFrequency(minHeap.get(right));
            int frSmallest = findFrequency(minHeap.get(smallest));
            if (frRight < frSmallest) {
                smallest = right;
            }
        }
        // If smallest is not root
        if (smallest != i) {
            Collections.swap(minHeap, i, smallest);
            // Recursively heapify
            heapify(n, smallest);
        }
    }

    void heapSort() {
        // Build heap (rearrange array)
        for (int i = minHeap.size() / 2 - 1; i >= 0; i--) {
            heapify(minHeap.size(), i);
        }

        // One by one extract an element from heap
        for (int i = minHeap.size() - 1; i >= 0; i--) {
            // Move current root to end
            Collections.swap(minHeap, i, 0);
            // call min heapify on the reduced heap
            heapify(i, 0);
        }
    }

    public void insert(String data) {

        minHeap.add(data);
        // Traverse up and fix violated property
        int current = minHeap.size() - 1;
        int fr1 = findFrequency(minHeap.get(current));
        int fr2 = findFrequency(minHeap.get(parent(current)));

        while (fr1 < fr2) {
            Collections.swap(minHeap, current, parent(current));
            current = parent(current);
            fr1 = findFrequency(minHeap.get(current));
            fr2 = findFrequency(minHeap.get(parent(current)));
        }
    }
    
    //find element's frequency ex: Doc1(4) return 4
    int findFrequency(String str) {
        String word = "";
        for (int i = 4; i < str.length(); i++) {
            if (i == 5 && str.charAt(i) == '0') {
                continue;
            }
            if (str.charAt(i) != '(' && str.charAt(i) != ')') {
                word += str.charAt(i);
            }
        }
        return Integer.parseInt(word);
    }

    void print() {
        //If there is no result then print no result
        if (minHeap.isEmpty()) {
            System.out.println("There were no results");
            return;
        }
        
        for (int i = 0; i < minHeap.size(); i++) {
            System.out.print(minHeap.get(i) + " ");
        }
        System.out.println();
    }
    
    //Create BST for every html folder then add to the array  
    void createArray() throws IOException {

        for (int i = 1; i <= 10; i++) {
            BinarySearchTree root = new BinarySearchTree();
            if (i != 10) {
                File file = new File("docs\\cse2250" + i + ".html");
                root.insertBinaryTree(file);
                treeArr[i - 1] = root;
            } else {
                File file = new File("docs\\cse225" + i + ".html");
                root.insertBinaryTree(file);
                treeArr[i - 1] = root;
            }
        }
    }
    
    void searchFromDocs(String input) throws IOException {
        //Create BST array
        createArray();
        // Add user entered words to list
        ArrayList<String> words = new ArrayList<>();
        String word = "";
        for (int i = 0; i < input.length(); i++) {
            // Ignore character except letter
            if (i == input.length() - 1
                    && (input.charAt(i) >= 65 && input.charAt(i) <= 90 || input.charAt(i) >= 97 && input.charAt(i) <= 122)) {
                word += input.charAt(i);
                words.add(word);
            } else if (input.charAt(i) >= 65 && input.charAt(i) <= 90 || input.charAt(i) >= 97 && input.charAt(i) <= 122) {
                word += input.charAt(i);
            } else {
                if (!word.equals("")) {
                    words.add(word);
                }
                word = "";
            }
        }
        //Send list to method 
        searchTree(words);
        heapSort();
        print();
        minHeap.clear();
    }
    
    //It searches the trees in the array one by one according to the sent list
    void searchTree(ArrayList words) {

        for (int i = 0; i < treeArr.length; i++) {
            int tmp = i + 1;
            int frequency = 0;
            for (int j = 0; j < words.size(); j++) {
                //Send word to method and add to frequency
                frequency += treeArr[i].search(treeArr[i].root, words.get(j).toString());
            }
            //If word is found then insert to the heap
            if (frequency != 0) {
                insert("Doc" + tmp + "(" + frequency + ")");
            }
        }
    }

}
