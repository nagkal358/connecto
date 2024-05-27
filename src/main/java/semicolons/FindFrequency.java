package semicolons;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class FindFrequency {

    public static void main(String[] args) {
       System.out.println(findFrquency("Nageswara reddy", 2));
    }
    private static Character findFrquency(String str, int n){
        return str.toLowerCase()
                .chars().mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(e -> e.getKey())
                .skip(n-1).findFirst().get();
//                .forEach(System.out::print);
//                .forEach((character, frequency) -> System.out.println(character+"::"+frequency));
    }
}
