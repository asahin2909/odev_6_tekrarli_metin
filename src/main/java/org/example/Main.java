package org.example;

import java.util.*;

public class Main {

    public static Map<Character, Set<String>> findRepeatedCharacters(String[] words) {
        // Tekrar eden karakterleri ve hangi kelimelerde tekrar ettiğini saklamak için bir harita oluştur
        Map<Character, Set<String>> repeatedCharactersMap = new HashMap<>();

        // Her kelime için tekrar eden karakterleri bul ve hangi kelimede tekrar ettiğini haritaya ekleyin
        for (String word : words) {
            Set<Character> uniqueCharacters = new HashSet<>();
            // Kelime içindeki tekrar eden karakterleri bulun
            for (char c : word.toCharArray()) {
                if (!uniqueCharacters.add(c)) {
                    if (!repeatedCharactersMap.containsKey(c)) {
                        repeatedCharactersMap.put(c, new HashSet<>());
                    }
                    repeatedCharactersMap.get(c).add(word);
                }
            }
        }

        return repeatedCharactersMap;
    }

    public static String createWordFromAllRepeatedChars(Set<Character> repeatedChars) {
        StringBuilder sb = new StringBuilder();
        // Tekrar eden karakterleri birleştirerek yeni bir kelime oluştur
        for (char c : repeatedChars) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Kullanıcıdan metni al
        System.out.print("Lütfen bir metin girin: ");
        String text = scanner.nextLine();

        // Metni boşluklara göre ayır
        String[] words = text.split("\\s+");

        // Tekrar eden karakterleri ve hangi kelimelerde tekrar ettiğini bul
        Map<Character, Set<String>> repeatedCharactersMap = findRepeatedCharacters(words);

        // Eğer tekrar eden karakter yoksa kullanıcıya uyarı ver
        if (repeatedCharactersMap.isEmpty()) {
            System.out.println("Metinde tekrar eden karakter bulunamadı!");
        } else {
            // Tekrar eden karakterleri yan yana virgülle ayrılmış olarak yazdır
            StringBuilder repeatedCharsString = new StringBuilder();
            for (char c : repeatedCharactersMap.keySet()) {
                repeatedCharsString.append(c).append(",");
            }
            // En sondaki virgülü kaldır
            if (repeatedCharsString.length() > 0) {
                repeatedCharsString.deleteCharAt(repeatedCharsString.length() - 1);
            }
            System.out.println("Tekrar eden karakterler: " + repeatedCharsString);

            // Her bir tekrar eden karakterin hangi kelimede tekrar ettiğini yazdır
            for (Map.Entry<Character, Set<String>> entry : repeatedCharactersMap.entrySet()) {
                System.out.println("'" + entry.getKey() + "' karakteri " + entry.getValue() + " kelimelerinde tekrar ediyor.");
            }

            // Tekrar eden tüm karakterleri ve yeni oluşturulan kelimeyi yazdır
            Set<Character> allRepeatedChars = repeatedCharactersMap.keySet();
            String newWordFromAllRepeatedChars = createWordFromAllRepeatedChars(allRepeatedChars);
            System.out.println("Tekrar eden tüm karakterlerden oluşan yeni kelime: " + newWordFromAllRepeatedChars);
        }
    }
}
