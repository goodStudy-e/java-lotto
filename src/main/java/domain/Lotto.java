package domain;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * 로또 한장을 의미하는 객체
 */
public class Lotto {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_LENGTH = 6;
    public static final int LOTTO_UNIT_PRICE = 1000;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "[" + numbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(joining(", ")) + "]";
    }
}