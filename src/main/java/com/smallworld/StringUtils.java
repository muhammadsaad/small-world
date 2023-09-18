package com.smallworld;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class StringUtils {

    private static final String PUNCTUATION_CHARS_KEY = "com.small-word.string-utils.punctuation-marks";

    private static final Character[] PUNCTUATION_CHARACTERS = new Character[] { '.' };

    private static StringUtils instance;

    List<Character> punctuationCharacters;

    private StringUtils() {
        URL url = ClassLoader.getSystemResource("application.properties");
        Properties properties = new Properties();
        try (InputStream inputStream = url.openStream()) {
            properties.load(inputStream);

            if (properties.containsKey(PUNCTUATION_CHARS_KEY)) {
                punctuationCharacters = ((String) properties.get(PUNCTUATION_CHARS_KEY))
                        .chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toList());
            } else {
                punctuationCharacters = Arrays.asList(PUNCTUATION_CHARACTERS);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized StringUtils getInstance() {
        if (null == instance) {
            instance = new StringUtils();
        }

        return instance;
    }

    public String reverseString(String string) {
        String[] words = string.split("\\s");
        StringBuilder reverseStringBuilder = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            String word = words[i];
            if (word.length() > 0) {
                if (punctuationCharacters.contains(word.charAt(word.length() - 1))) {
                    reverseStringBuilder.append(word.charAt(word.length() - 1));
                    word = word.substring(0, word.length() - 1);
                }

                if (i == words.length - 1) {
                    reverseStringBuilder.append(word);
                } else {
                    reverseStringBuilder.append(" ").append(word);
                }
            }
        }

        return reverseStringBuilder.toString();
    }

}
