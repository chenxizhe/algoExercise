package com.algo.linledlist;

import java.util.Scanner;

/**
 * 基于单链表的LRU算法
 * @param <T>
 */
public class LRUBaseLinkedList<T> {
    private final static  Integer DEFAULT_CAPACITY=10;

    private Integer length;
    private Integer capacity;
    private Node<T> headNode;

    public LRUBaseLinkedList() {
        this.length = 0;
        this.capacity = DEFAULT_CAPACITY;
        this.headNode = new Node();
    }

    public LRUBaseLinkedList(Integer length) {
        this.length = length;
        this.capacity = DEFAULT_CAPACITY;
        this.headNode = new Node();
    }

    public void add(T data){
        Node node=findPreNode(data);
        if(node!=null){
             delete(node);
             insertAtBegin(data);
        }else{
             if(length>=capacity){
                 deleteAtEnd();
             }
            insertAtBegin(data);

        }

    }

    public Node findPreNode(T data){
        Node node=headNode;
        while(node.getNext()!=null){
            if(data.equals(node.getNext().getElement())){
                return node;
            }
            node=node.getNext();
        }
        return  null;
    }

    public void delete(Node preNode){
        Node tmp=preNode.getNext();
        preNode.setNext(tmp.getNext());
        tmp=null;
        length--;
    }


    public void deleteAtEnd(){
        Node cur=headNode;
        if(cur.getNext()==null){
           return;
        }

        while(cur.getNext().getNext()!=null){
            cur=cur.getNext();
        }
        Node tmp=cur.getNext();
        cur.setNext(null);
        length--;
        tmp=null;


    }



    public void insertAtBegin(T data){
      Node next=headNode.getNext();
      headNode.setNext(new Node(data,next));
      length++;

    }
    private void printAll() {
        Node node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    public class Node<T>{
       private T element;
       private Node next;

        public Node() {
            this.next=null;
        }

        public Node(T element) {
            this.element = element;
        }

        public Node(T element, Node nextNode) {
            this.element = element;
            this.next = nextNode;
        }

        public T getElement() {
            return this.element;
        }

        public void setElement(T element) {
            this.element=element;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node nextNode) {
            this.next=nextNode;
        }
    }


    public static void main(String[] args) {
        LRUBaseLinkedList list=new LRUBaseLinkedList();
        Scanner sc=new Scanner(System.in);
        while(true){
            list.add(sc.next());
            list.printAll();
        }


    }
}
