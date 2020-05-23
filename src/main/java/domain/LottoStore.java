package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoStore {
    static private final int LOTTO_LIMIT_LENGTH = 6;

    private PurchaseMoney purchaseMoney;
    private final int lottoNumber;

    public LottoStore(PurchaseMoney purchaseMoney) {
        this.purchaseMoney = new PurchaseMoney(purchaseMoney.getAmount());
        lottoNumber = calculateLottoNumber();
    }

    private int calculateLottoNumber() {
        int lottoNumber = purchaseMoney.getAmount() / Lotto.LOTTO_UNIT_PRICE;
        return lottoNumber;
    }

    public List<Lotto> issueUserLottos() {
        List<Lotto> userLottos = new ArrayList<>();
        for (int i = 0; i < lottoNumber; i++) {
            userLottos.add(generateLotto());
        }
        return new ArrayList<>(userLottos);
    }

    private Lotto generateLotto() {
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

    public int getLottoNumber() {
        return lottoNumber;
    }
}
