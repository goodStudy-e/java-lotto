package view;

import domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해주세요.";
    private static final String INPUT_NUMBERS_LENGTH_ERROR = "서로 다른 6개의 숫자만 입력해주세요. (, 로 구분)";
    private static final String COMMA = ",";

    private static Scanner scanner = new Scanner(System.in);

    public static PurchaseMoney inputPurchaseMoney() {
        OutputView.printInputPurchaseMoneyMessage();
        try {
            return new PurchaseMoney(scanner.nextInt());
        } catch (IllegalArgumentException e) {
            return inputPurchaseMoney();
        }
    }

    public static List<Lotto> getUserLottos(int purchaseMoney) { // todo: getUserLottos가 view영역에 필요한 것일까.. ? (인자들도 마찬가지)  Model로 LottoStore 라던지 LottoMachine 같은걸 만들까?
        return generateUserLotto(getLottoNumber(purchaseMoney));
    }

    private static List<Lotto> generateUserLotto(int lottoNumber) {
        List<Lotto> userLottos = new ArrayList<>();
        for (int i = 0; i < lottoNumber; i++) {
            Lotto userLotto = LottoStore.generateLotto();
            userLottos.add(userLotto);
            OutputView.printLotto(userLotto);
        }
        return new ArrayList<>(userLottos);
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
            winningLottoNumbers = getWinningLottoNumbers(); //todo: WinningLotto 초기화시에 예외처리!
            isRightLottoLength(winningLottoNumbers);
        } catch (IllegalArgumentException e) {
            return inputWinningLottoNumber();
        }
        return winningLottoNumbers;
    }

    private static void isRightLottoLength(List<Integer> winningLottoNumbers) {
        if (winningLottoNumbers.size() != Lotto.LOTTO_LENGTH) {
            throw new IllegalArgumentException(INPUT_NUMBERS_LENGTH_ERROR);
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
        } catch (IllegalArgumentException e) { //todo: 표준화 된 예외를 사용하자!
            return insertBonusBall(winningLotto);
        }
    }
}
