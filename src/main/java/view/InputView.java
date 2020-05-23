package view;

import domain.BonusBall;
import domain.Lotto;
import domain.PurchaseMoney;
import domain.WinningLotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
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

    public static WinningLotto inputWinningLotto() {
        OutputView.printInputWinningLottoMessage();
        try {
            Lotto winningLotto = new Lotto(inputWinningLottoNumbers());
            return new WinningLotto(winningLotto, inputBonusBall(winningLotto));
        } catch (IllegalArgumentException e) {
            return inputWinningLotto();
        }
    }

    private static List<Integer> inputWinningLottoNumbers() {
        String inputNumbers = scanner.next();
        String[] numbers = inputNumbers.split(COMMA);
        return Arrays.stream(numbers)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    private static BonusBall inputBonusBall(Lotto winningLotto) {
        OutputView.printInputBonusBallMessage();
        try {
            return new BonusBall(scanner.nextInt(), winningLotto);
        } catch (IllegalArgumentException e) {
            return inputBonusBall(winningLotto);
        }
    }
}
