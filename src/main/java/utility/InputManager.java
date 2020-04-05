package utility;

import domain.Lotto;
import domain.PurchaseMoney;
import domain.WinningLotto;

import java.util.*;
import java.util.stream.Collectors;

public class InputManager {
    private static final String INPUT_PURCHASE_MONEY_MESSAGE = "구입 금액을 입력해주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBERS_MESSAGE = "지난주 당첨 번호를 입력해주세요. (, 로 구분)";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해주세요.";
    private static final String INPUT_NUMBERS_RANGE_ERROR = "1 ~ 45 중에 입력하세요!";
    private static final String INPUT_NUMBERS_LENGTH_ERROR = "서로 다른 6개의 숫자만 입력해주세요. (, 로 구분)";
    private static final String INPUT_BONUS_BALL_OVERLAPPING_ERROR = "당첨 번호와 다른 숫자를 입력하세요!";

    private static Scanner scanner = new Scanner(System.in);

    public static PurchaseMoney inputPurchaseMoney() {
        System.out.println(INPUT_PURCHASE_MONEY_MESSAGE);
        try {
            int purchaseMoney = scanner.nextInt();
            return new PurchaseMoney(purchaseMoney);
        } catch (InputException e) {
            return inputPurchaseMoney();
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

        return userLottoList;
    }

    private static int getLottoNumber(int purchaseMoney) {
        int lottoNumber = purchaseMoney / Lotto.LOTTO_UNIT_PRICE;
        System.out.println(lottoNumber + "개를 구매했습니다.");
        return lottoNumber;
    }

    public static WinningLotto inputWinningLotto() {
        System.out.println(INPUT_WINNING_LOTTO_NUMBERS_MESSAGE);
        Lotto winningLotto = new Lotto(inputWinningLottoNumber());
        return new WinningLotto(winningLotto, insertBonusBall(winningLotto));
    }

    private static List<Integer> inputWinningLottoNumber() {
        List<Integer> winningLottoNumbers;
        try {
            winningLottoNumbers = getWinningLottoNumbers();
            isRightLottoLength(winningLottoNumbers);
        } catch (Exception e) {
            return inputWinningLottoNumber();
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
                .filter(number -> Lotto.MIN_LOTTO_NUMBER <= number && number <= Lotto.MAX_LOTTO_NUMBER) // 범위 외 숫자 제거
                .distinct() //중복된 숫자 제거
                .boxed()
                .collect(Collectors.toList());
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
