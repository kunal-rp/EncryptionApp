package com.example.kpadmin.encryptionapp;


public class Rotor {

    private int numberOfCircles = 0;
    private int placementNumber;
    private ObjectList letter_order ;
    char[] correctOrderedLetters ;
    public int rotorSize;



    public Rotor(int placementNumber){
        this.placementNumber = placementNumber;
    }


    public void setCorrectOrderedLetters(char[] correctOrderedLetters){
        this.correctOrderedLetters = correctOrderedLetters;
    }

    public int getRotorSize(){
        return rotorSize;
    }

    public void updateRotor(){
        letter_order.updateFirstNode();
        if(letter_order.getFirstNode().getInfo2().equals('A')){
            numberOfCircles++;
        }
    }

    public int getNumberOfCircles(){
        return numberOfCircles;
    }

    public void setNumberOfCircles(int numberOfCircles){
        this.numberOfCircles = numberOfCircles;
    }


    public boolean needToUpdate(Rotor r){
        if(r.getNumberOfCircles() >= 1){
            r.setNumberOfCircles(0);
            return true;
        }
        return false;
    }

    public Object getStartingPosition(){
        return letter_order.getFirstNode().getInfo2();
    }

    public int getStartingPositionNumericalStartingPoint(){
        int j=0;
        for(ObjectListNode r = letter_order.getStartingNode();(Object)r.getInfo2() != letter_order.getFirstNode().getInfo2();r = r.getNext()){
            j++;
        }
        return j+1;
    }

    public void importLetterOrder(String phrase){
        char[] letters = phrase.toCharArray();
        rotorSize = letters.length;

        ObjectListNode p = new ObjectListNode(letters[0],correctOrderedLetters[0]);
        letter_order = new ObjectList(p);
        for(int i = 1; i < letters.length; i ++){
            p = new ObjectListNode(letters[i],correctOrderedLetters[i]);
            letter_order.insert(p);
        }
    }

    public void setStartingPosition(int c){
        for(int i = 0; i <= c; i++){
            updateRotor();
        }
        setNumberOfCircles(0);
    }

    public Object returnCorrespondingLetterToGoingIn(char letter){
        ObjectListNode p;
        for(p = letter_order.getFirstNode();(char) p.getInfo2()!= letter; p = p.getNext()){
        }
        return p.getInfo1();
    }



    public Object returnCorrespondingLetterToGoingOut(char letter){
        ObjectListNode p;
        for(p = letter_order.getFirstNode();(char) p.getInfo1()!= letter; p = p.getNext()){
        }
        return p.getInfo2();
    }


}
