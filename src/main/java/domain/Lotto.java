package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_LENGTH = 6;
    public static final int LOTTO_UNIT_PRICE = 1000;
    private static final String INPUT_NUMBERS_LENGTH_ERROR = "6개의 숫자만 입력해주세요. (, 로 구분)";
    private static final String INPUT_NUMBERS_DUPLICATION_ERROR = "중복되지 않게 입력해주세요.";

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validateLotto(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validateLotto(List<Integer> numbers) {
        validateLottoNumbersLength(numbers);
        validateLottoNumbers(numbers);
        validateLottoNumberRange(numbers);
    }

    private void validateLottoNumbersLength(List<Integer> numbers) {
        if (numbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException(INPUT_NUMBERS_LENGTH_ERROR);
        }
    }

    private void validateLottoNumbers(List<Integer> numbers) {
        if (isDuplicated(numbers)) {
            throw new IllegalArgumentException(INPUT_NUMBERS_DUPLICATION_ERROR);
        }
    }

    private boolean isDuplicated(List<Integer> numbers) {
        int distinctNumberSize = (int) numbers.stream()
                .mapToInt(Integer::intValue)
                .distinct() //중복된 숫자 제거
                .count();
        return numbers.size() != distinctNumberSize;
    }

    private void validateLottoNumberRange(List<Integer> numbers) {
        if (isOverRangeOfLottoNumber(numbers)) {
            throw new IllegalArgumentException(INPUT_NUMBERS_DUPLICATION_ERROR);
        }
    }

    private boolean isOverRangeOfLottoNumber(List<Integer> numbers) {
        int distinctNumberSize = (int) numbers.stream()
                .mapToInt(Integer::intValue)
                .filter(number -> MIN_LOTTO_NUMBER <= number && number <= MAX_LOTTO_NUMBER) // 범위 외 숫자 제거
                .count();
        return numbers.size() != distinctNumberSize;
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
}