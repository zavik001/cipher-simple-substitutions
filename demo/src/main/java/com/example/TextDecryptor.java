package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextDecryptor {
    private String key;
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public TextDecryptor(String key) {
        this.key = key;
    }

    public String decrypt(String encryptedText) {
        Map<Character, Character> decryptionMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            decryptionMap.put(key.charAt(i), ALPHABET.charAt(i));
        }

        StringBuilder decryptedText = new StringBuilder();

        encryptedText = encryptedText.replaceAll("[^абвгдеёжзийклмнопрстуфхцчшщъыьэюя]", "");

        for (char ch : encryptedText.toCharArray()) {
            decryptedText.append(decryptionMap.get(ch));
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        // Заданный ключ для шифрования/расшифровки
        String key = "фывапролджэйцукенгшщзхъюбьтимсчя";

        String inputFile = "src/main/resources/encrypted.txt";
        String outputFile = "src/main/resources/decrypted.txt";

        try {
            StringBuilder encryptedMessage = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    encryptedMessage.append(line);
                }
            }

            // Расшифровка текста
            TextDecryptor decryptor = new TextDecryptor(key);
            String decryptedMessage = decryptor.decrypt(encryptedMessage.toString());

            // Запись расшифрованного текста в файл
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(decryptedMessage);
            }

            System.out.println("Сообщение расшифровано и записано в файл " + outputFile);
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }
}
