package additionally;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Additionally {
    public static void main(String[] args) {
//        №1 Дан файл со списком студентов:
//          jack,3
//          john,5
//          nick,1
//          jack,4
//          и т.д. т.е. *имя,оценка"
//          необходитмо:
//
//        a) получить мэп, где ключ это имя студента, значение сколько у студента оценок
//          (в рамках этой задачи, строки с одинаковым именем относятся к одному студенту
//          т.е. у jack 2 оцеки)
//        b) получить список студентов с средним балом больше заданного.
//          Ркализоавть тесты

        String fileName = "students.txt";
        Map<String, ArrayList<Integer>> students = getStudentInfo(fileName);

//        Map<String, Double> studentsByAvgValue = getStudentByAvgValue(fileName, 3.5);
        printStudentInfo(students);


//        № 2 Ну и чуть сложнее .... дана строка вида "2(3+a)*b( (q-1): 3+4)" т.е. какое то
//          выражение, написать метод, который проверяет, правильно ли расставлены скобки в этой
//          строке т.е., например, так "2(3+a(" не правильно
        System.out.println();
        System.out.println(" ===== Task 2 =====");
        String exampleString1 = "2(3+a)*b( (q-1): 3+4)";
        boolean isCorrectString1 = checkString(exampleString1);
        System.out.println("isCorrectString1 = " + isCorrectString1);
        String exampleString2 = "2(3+a)*b(( (q-1): 3+4)";
        boolean isCorrectString2 = checkString(exampleString2);
        System.out.println("isCorrectString2 = " + isCorrectString2);
    }

    public static Map<String, Double> getStudentByAvgValue(String fileName, double threshold) {
        Map<String, ArrayList<Integer>> students = getStudentInfo(fileName);

        return students.entrySet()
                .stream()
                .filter(entry -> calculateAverage(entry.getValue()) >= threshold)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> calculateAverage(entry.getValue())));
    }

    private static double calculateAverage(List<Integer> scores) {
        return scores.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }


    public static Map<String, ArrayList<Integer>> getStudentInfo(String fileName) {
        Map<String, ArrayList<Integer>> students;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            students = reader.lines()
                    .map(l -> l.split(","))
                    .collect(Collectors.groupingBy(
                            parts -> parts[0],
                            Collectors.mapping(
                                    part -> Integer.parseInt(part[1]),
                                    Collectors.toCollection(ArrayList::new)
                            )
                    ));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    private static void printStudentInfo(Map<String, ArrayList<Integer>> students) {
        System.out.println(" ===== Students Info =====");

        for (Map.Entry<String, ArrayList<Integer>> entry : students.entrySet()) {
            System.out.println("name = " + entry.getKey());
            String values = entry.getValue().stream().map(Object::toString)
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse("");
            double avg = calculateAverage(entry.getValue());
            System.out.println("values = " + values + ", average = " + String.format("%.2f", avg));
            System.out.println(" === === === === ===");
        }
    }

    public static boolean checkString(String str) {
        char openBracket = '(';
        char closeBracket = ')';
        LinkedList<Character> brackets = new LinkedList<>();

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            if (currentChar == openBracket) {
                brackets.add(currentChar);
            } else if (currentChar == closeBracket) {
                if (brackets.isEmpty() || brackets.getLast() == closeBracket) {
                    return false;
                }

                brackets.removeLast();
            }
        }

        return brackets.isEmpty();
    }
}
