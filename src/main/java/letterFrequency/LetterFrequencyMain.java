package letterFrequency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class LetterFrequencyMain {

    public static void main(String[] args) throws IOException {


        String text = createStringFromFile();

        Map<Character, Long> frequency = frequencyCounter(text);

        System.out.println(frequency);
    }

    public static Map<Character, Long> frequencyCounter(String text) {
        return text.chars()
                .mapToObj(a -> (char) a)
                .collect(Collectors.groupingBy(b -> b, Collectors.counting()));
    }


    private static String createStringFromFile() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/main/java/letterFrequency/text.txt"));
        StringBuilder text = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            text.append(line);
        }
        in.close();
        return text.toString().toUpperCase().replaceAll("[^A-Z]", "");
    }
}
