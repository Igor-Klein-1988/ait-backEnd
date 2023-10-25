package test.additionally;

import additionally.Additionally;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AdditionallyTest {
    String fileName = "students.txt";

    @Test
    void checkStudentsInfo() {
        Map<String, ArrayList<Integer>> students = Additionally.getStudentInfo(fileName);
        assertEquals(3, students.size());

        ArrayList<Integer> expectedNickScores = new ArrayList<>(Arrays.asList(1));
        assertArrayEquals(expectedNickScores.toArray(), students.get("nick").toArray());

        ArrayList<Integer> expectedJohnScores = new ArrayList<>(Arrays.asList(5));
        assertArrayEquals(expectedJohnScores.toArray(), students.get("john").toArray());

        ArrayList<Integer> expectedJackScores = new ArrayList<>(Arrays.asList(3, 4));
        assertArrayEquals(expectedJackScores.toArray(), students.get("jack").toArray());
    }

    @Test
    void checkStudentByAvgValue() {
        Map<String, Double> students = Additionally.getStudentByAvgValue(fileName, 3.1);
        assertEquals(2, students.size());
        List<String> setNames = students.keySet().stream().sorted().collect(Collectors.toList());

        String[] sortedNames = students.keySet()
                .stream()
                .sorted()
                .toArray(String[]::new);

        assertArrayEquals((new String[]{"jack", "john"}), sortedNames);
    }

    @Test
    void checkValidStr() {
        String str = "(3(dasff+fg)dsdfg_sdfg (dfg+) fdg(()))";
        assertTrue(Additionally.checkString(str));
    }

    @Test
    void checkInvalidStr() {
        String str = "(3(dasff+fg)dsdfg_sdfg (dfg+) fdg(())))";
        assertFalse(Additionally.checkString(str));
    }
}