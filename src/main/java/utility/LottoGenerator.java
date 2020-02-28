package utility;

import domain.Lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoGenerator {
    static private int LOTTO_LIMIT_LENGTH = 6;

    static Lotto generateLotto() {
        List<Integer> lottoNumbers = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < LOTTO_LIMIT_LENGTH; i++) { //while로 바꾸면 depth 1 가능할 듯
            int randomNumber = Lotto.MIN_LOTTO_NUMBER + random.nextInt(Lotto.MAX_LOTTO_NUMBER); //1 ~ 45

            if (lottoNumbers.contains(randomNumber)) { //todo: indent depth 1까지만 허용! (리팩토링)
                --i; // 이게 리팩토링의 문제점
                continue;
            }
            lottoNumbers.add(randomNumber);
        }
        return new Lotto(lottoNumbers);
    }
}
