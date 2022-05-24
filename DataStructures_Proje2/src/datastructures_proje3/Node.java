/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures_proje3;

/**
 *
 * @author beyza
 */
public class Node {
    
    String data;
    int frequency;
    Node left;
    Node right;
    Node next;

    public Node(String data) {
        this.data = data;
        this.frequency = 0;
        this.next = null;
    }
}
