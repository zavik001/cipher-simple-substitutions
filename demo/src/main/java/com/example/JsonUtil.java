package com.example;

import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonUtil {

    // Метод для записи ключа в JSON файл
    public static void writeKeyToFile(String filePath, String key) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", key);
        Files.write(Paths.get(filePath), jsonObject.toString().getBytes());
    }

    // Метод для чтения ключа из JSON файла
    public static String readKeyFromFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        String content = Files.readString(path);
        JSONObject jsonObject = new JSONObject(content);
        return jsonObject.getString("key");
    }

    // Метод для чтения текста из JSON файла
    public static String readTextFromFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        String content = Files.readString(path);
        JSONObject jsonObject = new JSONObject(content);
        return jsonObject.getString("text");
    }

    // Метод для записи зашифрованного текста в JSON файл
    public static void writeEncryptedTextToFile(String filePath, String encryptedText) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("encryptedText", encryptedText);
        Files.write(Paths.get(filePath), jsonObject.toString().getBytes());
    }

    // Метод для записи essay, encryptedEssay, key в POST.json в нужном порядке
    public static void writePostDataToFile(String filePath, String essay, String key, String encryptedEssay)
            throws Exception {
        String jsonContent = String.format(
                "{\n    \"essay\": \"%s\",\n    \"key\": \"%s\",\n    \"encryptedEssay\": \"%s\"\n}",
                escapeJson(essay),
                escapeJson(key),
                escapeJson(encryptedEssay));

        Files.write(Paths.get(filePath), jsonContent.getBytes());
    }

    private static String escapeJson(String input) {
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
