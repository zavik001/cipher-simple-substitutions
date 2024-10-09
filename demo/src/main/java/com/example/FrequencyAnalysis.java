package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FrequencyAnalysis {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    // Подсчитываем частоту букв в зашифрованном тексте
    public static Map<Character, Integer> countFrequencies(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : text.toCharArray()) {
            if (ALPHABET.indexOf(c) != -1) {  // Проверяем, что это русская буква
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }

        return frequencyMap;
    }

    // Метод для чтения файла
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    // Метод для сортировки частот по убыванию
    public static List<Map.Entry<Character, Integer>> sortFrequencies(Map<Character, Integer> frequencies) {
        List<Map.Entry<Character, Integer>> sortedList = new ArrayList<>(frequencies.entrySet());
        sortedList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));  // Сортировка по убыванию
        return sortedList;
    }

    public static void main(String[] args) {
        try {
            // Читаем зашифрованный текст из файла
            String encryptedMessage = readFileAsString("src/main/resources/encrypted.txt");

            // Подсчитываем частоты букв
            Map<Character, Integer> frequencies = countFrequencies(encryptedMessage);

            // Получаем общее количество символов
            int totalCharacters = encryptedMessage.replaceAll("[^" + ALPHABET + "]", "").length();

            // Сортируем частоты по убыванию
            List<Map.Entry<Character, Integer>> sortedFrequencies = sortFrequencies(frequencies);

            // Выводим частоты и проценты на экран
            System.out.printf("%-10s%-10s%-10s%n", "Буква", "Частота", "Процент");
            for (Map.Entry<Character, Integer> entry : sortedFrequencies) {
                double percentage = (entry.getValue() / (double) totalCharacters) * 100;
                System.out.printf("%-10s%-10d%-10.2f%%%n", entry.getKey(), entry.getValue(), percentage);
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
