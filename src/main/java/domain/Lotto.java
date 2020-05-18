package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_LENGTH = 6;
    public static final int LOTTO_UNIT_PRICE = 1000;

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
}