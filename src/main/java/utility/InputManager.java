package utility;

import domain.Lotto;
import domain.WinningLotto;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class InputManager {
    private static final String INPUT_PURCHASE_MONEY_MESSAGE = "구입 금액을 입력해주세요.";
    private static final String INPUT_PURCHASE_MONEY_ERROR = "1,000원 단위로 입력해주세요.";
    private static final String INPUT_PURCHASE_MONEY_NONE_ZERO_ERROR = "0원 이상의 금액을 입력해주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBERS_MESSAGE = "지난주 당첨 번호를 입력해주세요. (, 로 구분)";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해주세요.";
    private static final String INPUT_NUMBERS_RANGE_ERROR = "1 ~ 45 중에 입력하세요!";
    private static final String INPUT_NUMBERS_LENGTH_ERROR = "서로 다른 6개의 숫자만 입력해주세요. (, 로 구분)";
    private static final String INPUT_BONUS_BALL_OVERLAPPING_ERROR = "당첨 번호와 다른 숫자를 입력하세요!";

    private static Scanner scanner = new Scanner(System.in);

    public static int inputPurchaseMoney() {
        System.out.println(INPUT_PURCHASE_MONEY_MESSAGE);
        int purchaseMoney = 0;
        try {
            purchaseMoney = scanner.nextInt();
            isRightPurchaseMoney(purchaseMoney);
        } catch (InputException e) {
            inputPurchaseMoney();
        }
        return purchaseMoney;
    }

    private static void isRightPurchaseMoney(int purchaseMoney) {
        System.out.println();
        if (purchaseMoney == 0) { //todo: 구입금액 0을 입력한 후에 다시 금액 입력하면 로또 구매 개수가 0개로 고정됌
            throw new InputException(INPUT_PURCHASE_MONEY_NONE_ZERO_ERROR);
        }
        if (purchaseMoney % Lotto.LOTTO_UNIT_PRICE != 0) {
            throw new InputException(INPUT_PURCHASE_MONEY_ERROR);
        }
    }

    public static List<Lotto> getUserLottos(int purchaseMoney) {
        return generateUserLotto(new ArrayList<>(), getLottoNumber(purchaseMoney));
    }

    private static List<Lotto> generateUserLotto(List<Lotto> userLottoList, int lottoNumber) {
        for (int i = 0; i < lottoNumber; i++) {
            Lotto userLotto = LottoGenerator.generateLotto();
            userLottoList.add(userLotto);
            System.out.println(userLotto);
        }
        System.out.println();
        return userLottoList;
    }

    private static int getLottoNumber(int purchaseMoney) {
        int lottoNumber = purchaseMoney / Lotto.LOTTO_UNIT_PRICE;
        System.out.println(lottoNumber + "개를 구매했습니다.");
        return lottoNumber;
    }

    public static WinningLotto inputWinningLotto() {
        System.out.println(INPUT_WINNING_LOTTO_NUMBERS_MESSAGE);
        Lotto winningLotto = new Lotto(inputWinningLottoNumbers());
        return new WinningLotto(winningLotto, insertBonusBall(winningLotto));
    }

    private static List<Integer> inputWinningLottoNumbers() {
        List<Integer> winningLottoNumbers;
        try {
            winningLottoNumbers = getWinningLottoNumbers();
            isRightLottoLength(winningLottoNumbers);
        } catch (Exception e) {
            return inputWinningLottoNumbers();
        }
        return winningLottoNumbers;
    }

    private static void isRightLottoLength(List<Integer> winningLottoNumbers) {
        if (winningLottoNumbers.size() != Lotto.LOTTO_LENGTH) {
            throw new InputException(INPUT_NUMBERS_LENGTH_ERROR);
        }
    }

    private static List<Integer> getWinningLottoNumbers() {
        String inputNumbers = scanner.next();
        String[] numbers = inputNumbers.split(",");
        return Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .filter(number -> number <= Lotto.MAX_LOTTO_NUMBER && number >= Lotto.MIN_LOTTO_NUMBER)
                .distinct()
                .boxed()
                .collect(toList());
    }

    private static int insertBonusBall(Lotto winningLotto) {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        int bonusBall = scanner.nextInt();
        System.out.println();
        try {
            isRightBonusBall(bonusBall, winningLotto);
        } catch (Exception e) {
            return insertBonusBall(winningLotto);
        }
        return bonusBall;
    }

    private static void isRightBonusBall(int bonusBall, Lotto winningLotto) {
        if (bonusBall > Lotto.MAX_LOTTO_NUMBER || bonusBall < Lotto.MIN_LOTTO_NUMBER) {
            throw new InputException(INPUT_NUMBERS_RANGE_ERROR);
        }

        if (winningLotto.getNumbers().contains(bonusBall)) {
            throw new InputException(INPUT_BONUS_BALL_OVERLAPPING_ERROR);
        }
    }
}
