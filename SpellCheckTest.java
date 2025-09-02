package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.*;

public class SpellCheckTest {

    // Simple dictionary for demo
    private static final Set<String> dictionary = new HashSet<>(Arrays.asList(
            "script", "selenium", "automation", "test", "java", "web", "page", "example",
            "hello", "world", "welcome", "login", "user", "password"
    ));

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // Open target page
            driver.get("http://sheetal.net/packers-and-movers-bangalore.php"); // Replace with your webpage

            // Extract all text
            String pageText = driver.findElement(By.tagName("body")).getText();

            // Split words (remove punctuation & lowercase)
            String[] words = pageText.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

            List<String> misspelledWords = new ArrayList<>();

            // Check each word
            for (String word : words) {
                if (!word.isEmpty() && !dictionary.contains(word)) {
                    misspelledWords.add(word);
                }
            }

            // Print results
            if (misspelledWords.isEmpty()) {
                System.out.println("No spelling mistakes found!");
            } else {
                System.out.println("Spelling mistakes found: " + misspelledWords);
            }

        } finally {
            driver.quit();
        }
    }
}
