package view;

import domain.BonusBall;
import domain.Lotto;
import domain.PurchaseMoney;
import domain.WinningLotto;
import utility.InputException;
import utility.LottoGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해주세요.";
    private static final String INPUT_NUMBERS_LENGTH_ERROR = "서로 다른 6개의 숫자만 입력해주세요. (, 로 구분)";
    public static final String COMMA = ",";

    private static Scanner scanner = new Scanner(System.in);

    public static PurchaseMoney inputPurchaseMoney() {
        OutputView.printInputPurchaseMoneyMessage();
        try {
            int purchaseMoney = scanner.nextInt();
            return new PurchaseMoney(purchaseMoney);
        } catch (IllegalArgumentException e) { //todo: 표준화 된 예외를 사용하자!
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
            OutputView.printLotto(userLotto);
        }
        return userLottoList;
    }

    private static int getLottoNumber(int purchaseMoney) {
        int lottoNumber = purchaseMoney / Lotto.LOTTO_UNIT_PRICE;
        System.out.println(lottoNumber + "개를 구매했습니다.");
        return lottoNumber;
    }

    public static WinningLotto inputWinningLotto() {
        OutputView.printInputWinningLottoMessage();
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
        String[] numbers = inputNumbers.split(COMMA);
        return Arrays.stream(numbers)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .filter(number -> Lotto.MIN_LOTTO_NUMBER <= number && number <= Lotto.MAX_LOTTO_NUMBER) // 범위 외 숫자 제거
                .distinct() //중복된 숫자 제거
                .boxed()
                .collect(Collectors.toList());
    }

    private static BonusBall insertBonusBall(Lotto winningLotto) {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        try {
            return new BonusBall(scanner.nextInt(), winningLotto);
        } catch (Exception e) { //todo: 표준화 된 예외를 사용하자!
            return insertBonusBall(winningLotto);
        }
    }
}
