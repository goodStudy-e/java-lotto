package utility;

import domain.Lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoGenerator {
    static private final int LOTTO_LIMIT_LENGTH = 6;

    static Lotto generateLotto() {
        List<Integer> lottoNumbers = new ArrayList<>();

        for (int i = 0; i < LOTTO_LIMIT_LENGTH; i++) {
            isDuplicated(lottoNumbers);
        }
        return new Lotto(lottoNumbers);
    }

    private static void isDuplicated(List<Integer> lottoNumbers) {
        Random random = new Random();
        int randomNumber = Lotto.MIN_LOTTO_NUMBER + random.nextInt(Lotto.MAX_LOTTO_NUMBER); //1 ~ 45
        if (lottoNumbers.contains(randomNumber)) {
            isDuplicated(lottoNumbers);
            return;
        }
        lottoNumbers.add(randomNumber);
    }
}
