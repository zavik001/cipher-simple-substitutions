package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            // Путь к файлу с ключом
            String keyPath = Paths.get("src/main/resources/key.json").toString();
            String key;

            // Проверяем, существует ли файл key.json
            if (Files.exists(Path.of(keyPath))) {
                // Если файл существует, читаем ключ из файла
                key = JsonUtil.readKeyFromFile(keyPath);
                System.out.println("Ключ из файла: " + key);
            } else {
                // Если файл не существует, генерируем новый ключ
                KeyGenerator keyGen = new KeyGenerator();
                key = keyGen.generateKey();
                System.out.println("Сгенерированный ключ: " + key);

                // Записываем новый ключ в файл key.json
                JsonUtil.writeKeyToFile(keyPath, key);
            }

            // Чтение текста из text.json
            String textPath = Paths.get("src/main/resources/text.json").toString();
            String text = JsonUtil.readTextFromFile(textPath);
            System.out.println("Исходный текст: " + text);

            // Шифрование текста с использованием ключа
            TextEncryptor encryptor = new TextEncryptor(key);
            String encryptedText = encryptor.encrypt(text);
            System.out.println("Зашифрованный текст: " + encryptedText);

            // Запись зашифрованного текста в файл encryptedText.json
            String encryptedTextPath = Paths.get("src/main/resources/encryptedText.json").toString();
            JsonUtil.writeEncryptedTextToFile(encryptedTextPath, encryptedText);

            // Запись данных (essay, key, encryptedEssay) в файл POST.json
            String postJsonPath = Paths.get("src/main/resources/POST.json").toString();
            JsonUtil.writePostDataToFile(postJsonPath, text, key, encryptedText);



            // // Чтение текста из файла 1mvTxt.txt
            // String textPath = Paths.get("src/main/resources/1mbText.txt").toString();
            // String text = Files.readString(Path.of(textPath));
            // System.out.println("Исходный текст (первые 100 символов): " + text.substring(0, 100));

            // // Шифрование текста с использованием ключа
            // TextEncryptor encryptor = new TextEncryptor(key);
            // long startTime = System.nanoTime();
            // String encryptedText = encryptor.encrypt(text);
            // long endTime = System.nanoTime();
            // double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;

            // // Вывод зашифрованного текста и времени шифрования
            // System.out.println("Время шифрования: " + durationInSeconds + " секунд");
            // System.out.println("Зашифрованный текст (первые 100 символов): " + encryptedText.substring(0, 100));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
