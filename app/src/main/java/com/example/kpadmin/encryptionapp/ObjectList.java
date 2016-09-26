package com.example.kpadmin.encryptionapp;

/**
 * ObjectList Class
 * @author Kunal Purohit
 * @version 1.0 - November 11,2015
 */
public class ObjectList {
    private ObjectListNode first;
    private ObjectListNode startingNode;


    // Constructs an empty list



    public ObjectList(ObjectListNode first) {
        this.first = first;
        this.startingNode = first;
        first.setNext(first);
    }



    public ObjectListNode getStartingNode(){
        return startingNode;
    }
    public void updateFirstNode(){
        ObjectListNode p = first;
        Object one = p.getInfo2();
        while(p.getNext() != first){
            p.setInfo2(p.getNext().getInfo2());
            p = p.getNext();
        }
        p.setInfo2(one);
    }

    public ObjectListNode getFirstNode(){
        return first;
    }

    public void setFirstNode(ObjectListNode p){
        first = p;
    }

    public void printList(){
        ObjectListNode r = first;
        while(r.getNext() != first){
            System.out.print((char)r.getInfo1());
            r = r.getNext();
        }
        System.out.println();
    }

    // Inserts a node into its correct location within an ordered list
    public void insert(ObjectListNode r) {
        ObjectListNode p = first.getNext();
        while(p.getNext() != first){
            p = p.getNext();
        }
        r.setNext(first);
        p.setNext(r);

    }

    public Object switchInfo(Object c){
        ObjectListNode r = first;
        while(r.getInfo1() != c &&r.getInfo2() != c ){
            r = r.getNext();
        }
        if(r.getInfo1() == c){
            return r.getInfo2();
        }
        else{
            return r.getInfo1();
        }
    }

}



