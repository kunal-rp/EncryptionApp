package com.example.kpadmin.encryptionapp;


/**
 * ObjectListNode Class
 * @author Kunal Purohit
 * @version 1.0 - November 11,2015
 */

public class ObjectListNode {
    private Object info1;
    private Object info2;
    private ObjectListNode next;

    // Default ctor
    public ObjectListNode() {
        info1 = null;
        info2 = null;
        next = null;
    }

    // One-arg ctor
    public ObjectListNode(Object o, Object r) {
        info1 = o;
        info2 = r;
        next = null;
    }

    public ObjectListNode(Object o) {
        info1 = o;
        info2 = null;
        next = null;
    }

    // Two-arg ctor
    public ObjectListNode(Object o, Object r, ObjectListNode p) {
        info1 = o;
        info2 = r;
        next = p;
    }

    // Sets info field
    public void setInfo1(Object o) {
        info1 = o;
    }

    // Returns object in info field
    public Object getInfo1() {
        return info1;
    }

    // Sets info field
    public void setInfo2(Object o) {
        info2 = o;
    }

    // Returns object in info field
    public Object getInfo2() {
        return info2;
    }

    // Sets next field
    public void setNext(ObjectListNode p) {
        next = p;
    }

    // Returns object in info field
    public ObjectListNode getNext() {
        return next;
    }
}

