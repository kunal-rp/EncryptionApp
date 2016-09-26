package com.example.kpadmin.encryptionapp;


public class RotorsInPlace {

    private Rotor[] allRotors = new Rotor[5];
    private Rotor[] selectedRotors = new Rotor[3];
    private ObjectList reflector;
    char[] reflectorOrder;


    public int getRotor1Number() {
        int j;
        for (j = 0; selectedRotors[0] != allRotors[j]; j++) {
        }
        return j + 1;
    }

    public int getRotor2Number() {
        int j;
        for (j = 0; selectedRotors[1] != allRotors[j]; j++) {
        }
        return j + 1;
    }

    public int getRotor3Number() {
        int j;
        for (j = 0; selectedRotors[2] != allRotors[j]; j++) {
        }
        return j + 1;
    }

    public int getRotor1Position() {
        return selectedRotors[0].getStartingPositionNumericalStartingPoint();
    }

    public int getRotor2Position() {
        return selectedRotors[1].getStartingPositionNumericalStartingPoint();
    }

    public int getRotor3Position() {
        return selectedRotors[2].getStartingPositionNumericalStartingPoint();
    }

    public void setAllRotors(int number, String line) {

        Rotor temp = new Rotor(number);
        temp.setCorrectOrderedLetters(reflectorOrder);
        temp.importLetterOrder(line);
        allRotors[number - 1] = temp;

    }

    public void setRotorPosition(int number, int rotorNumber) {
        selectedRotors[number - 1] = allRotors[rotorNumber - 1];
    }

    public String printStartingPositions() {
        String line = "";
        line += selectedRotors[0].getStartingPosition();
        line += selectedRotors[1].getStartingPosition();
        line += selectedRotors[2].getStartingPosition();
        return line;
    }

    public Object passLetterThroughRotors(char letter) {

        letter = (char) selectedRotors[0].returnCorrespondingLetterToGoingIn(letter);
        if (selectedRotors[1].needToUpdate(selectedRotors[0])) {
            selectedRotors[1].updateRotor();
        }
        letter = (char) selectedRotors[1].returnCorrespondingLetterToGoingIn(letter);
        if (selectedRotors[2].needToUpdate(selectedRotors[1])) {
            selectedRotors[2].updateRotor();
        }
        letter = (char) selectedRotors[2].returnCorrespondingLetterToGoingIn(letter);
        letter = (char) reflector.switchInfo(letter);
        letter = (char) selectedRotors[2].returnCorrespondingLetterToGoingOut(letter);
        letter = (char) selectedRotors[1].returnCorrespondingLetterToGoingOut(letter);
        letter = (char) selectedRotors[0].returnCorrespondingLetterToGoingOut(letter);
        selectedRotors[0].updateRotor();
        return letter;
    }


    public void importReflector(String line) {
        char[] letters = line.toCharArray();
        reflectorOrder = letters;
        ObjectListNode p = new ObjectListNode(letters[0], letters[1]);
        reflector = new ObjectList(p);
        for (int i = 2; i < letters.length; i += 2) {
            ObjectListNode r = new ObjectListNode(letters[i], letters[i + 1]);
            reflector.insert(r);
        }
    }

    public void keyToSettings(String key) {
        char[] phrase = key.toCharArray();
        int sum = 0;
        int i;
        for (i = 0; i < phrase.length; i++) {
            sum += ((int) phrase[i] * allRotors[0].getRotorSize());

        }
        int k = 0;
        for (int j = 5; j >= 1; j -= 2) {
            for (i = sum; i >= 6; i -= j) {
            }
            if (i < 0) {
                i = i * -1;
            }
            setRotorPosition(k + 1, i);
            k++;
        }
        k = 0;
        for (int j = 5; j >= 1; j -= 2) {
            for (i = sum; i >= selectedRotors[0].getRotorSize(); i -= (int) phrase[0] * j) {
            }
            if (i < 0) {
                i = i * -1;
            }
            setRotorStartingPositions(k + 1, i);
            k++;
        }


    }

    public String getVariant(int i, String phrase) {
        StringBuilder newText = null;
        for (int l = 0; l < phrase.length(); l++) {
            int temp = ((int) phrase.charAt(l)) + i;
            newText.append((char) temp);
        }
        return newText.toString();
    }

    private void setRotorStartingPositions(int a, int b) {
        selectedRotors[a - 1].setStartingPosition(b);
    }
}
