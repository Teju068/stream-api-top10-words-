
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MainClass {

    public static void main(String[] args) {

        String content = "\n" +
                "India\n" +
                "Emblem of India.svg\n" +
                "This article is part of a series on the\n" +
                "politics and government of\n" +
                "India\n" +
                "Constitution and law[show]\n" +
                "Government of India[show]\n" +
                "Elections[show]\n" +
                "Federalism[show]\n" +
                "Other countries Atlas\n" +
                "vte\n" +
                "The Prime Minister of India is the chief executive of the Government of India. In India's parliamentary system, the Constitution names the President as head of state de jure, but his or her de facto executive powers are vested in the prime minister and their Council of Ministers. Appointed and sworn-in by the President, the prime minister is usually the leader of the party or alliance that has a majority in the Lok Sabha, the lower house of Parliament of India";

        List<String> words = fetchWordsFromParagraph(content);

        Map<String, Long> maps = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(maps);

        Map<String, Long> maps2 = maps.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(maps2);

        Map<String, Long> maps3 = maps.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(maps3);

        Map<String, Long> maps4 = maps.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(maps4);
    }

    private static List<String> fetchWordsFromParagraph(String content) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }
}
