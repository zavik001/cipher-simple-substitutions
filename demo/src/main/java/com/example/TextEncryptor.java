package com.example;

import java.util.HashMap;
import java.util.Map;

public class TextEncryptor {
    private String key;
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public TextEncryptor(String key) {
        this.key = key;
    }

    public String encrypt(String plainText) {
        Map<Character, Character> encryptionMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            encryptionMap.put(ALPHABET.charAt(i), key.charAt(i));
        }

        StringBuilder encryptedText = new StringBuilder();

        // 1. Приводим текст к нижнему регистру
        plainText = plainText.toLowerCase();

        // 2. Удаляем все символы кроме русских букв
        plainText = plainText.replaceAll("[^абвгдеёжзийклмнопрстуфхцчшщъыьэюя]", "");

        // 3. Шифруем текст
        for (char ch : plainText.toCharArray()) {
            encryptedText.append(encryptionMap.get(ch)); // Заменяем символ по ключу
        }

        return encryptedText.toString();
    }
}
