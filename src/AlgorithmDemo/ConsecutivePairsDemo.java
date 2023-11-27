package AlgorithmDemo;

import java.util.List;

import static algorithm.getDistances.getConsecutivePairs;

public class ConsecutivePairsDemo {

    public static void main(String[] args) {
        List<String> inputList = List.of("one", "two", "three", "four", "five");

        List<List<String>> consecutivePairs = getConsecutivePairs(inputList);

        System.out.println("Input List: " + inputList);
        System.out.println("Consecutive Pairs: " + consecutivePairs);
    }


}
