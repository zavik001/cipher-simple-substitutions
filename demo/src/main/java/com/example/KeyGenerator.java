package com.example;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class KeyGenerator {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public String generateKey() {
        List<Character> alphabetList = new ArrayList<>();
        for (char ch : ALPHABET.toCharArray()) {
            alphabetList.add(ch);
        }
        Collections.shuffle(alphabetList); // Тасование Фишера-Йетса
        StringBuilder shuffledAlphabet = new StringBuilder();
        for (char ch : alphabetList) {
            shuffledAlphabet.append(ch);
        }
        return shuffledAlphabet.toString();
    }
}
