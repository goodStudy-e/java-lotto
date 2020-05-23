package view;

import domain.Lotto;
import domain.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class OutputView {
    private static final String INPUT_PURCHASE_MONEY_MESSAGE = "구입 금액을 입력해주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBERS_MESSAGE = "지난주 당첨 번호를 입력해주세요. (, 로 구분)";
    private static final int ZERO = 0;
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해주세요.";

    public static void printInputPurchaseMoneyMessage() {
        System.out.println(INPUT_PURCHASE_MONEY_MESSAGE);
    }

    public static void printResult(Map<Rank, Integer> numberOfWinningLottosByRank, double earningRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        printWinningLottoInfoByRank(numberOfWinningLottosByRank);
        System.out.println("총 수익률은 " + earningRate + "% 입니다.");
    }

    private static void printWinningLottoInfoByRank(Map<Rank, Integer> rankMap) {
        Arrays.stream(Rank.values())
                .forEach(rank -> printRankInfo(rankMap, rank));
    }

    private static void printRankInfo(Map<Rank, Integer> rankMap, Rank rank) {
        System.out.print(rank.getCountOfMatch() + "개일치");
        printSecondRankInfo(rank);
        System.out.print("(" + rank.getWinningMoney() + "원)- ");
        System.out.println(rankMap.getOrDefault(rank, ZERO) + "개");
    }

    private static void printSecondRankInfo(Rank rank) {
        if (rank.equals(Rank.SECOND)) {
            System.out.print(", 보너스 볼 일치");
        }
    }

    public static void printInputWinningLottoMessage() {
        System.out.println(INPUT_WINNING_LOTTO_NUMBERS_MESSAGE);
    }

    public static void printInputBonusBallMessage() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
    }

    public static void printBuyLottoNumber(int lottoNumber) {
        System.out.println(lottoNumber + "개를 구매했습니다.");
    }

    public static void printLottos(List<Lotto> userLottos) {
        for (Lotto lotto : userLottos) {
            printLotto(lotto);
        }
    }

    private static void printLotto(Lotto lotto) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        String collectLottoNumbers = lottoNumbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(joining(", "));
        System.out.println("[" + collectLottoNumbers + "]");
    }
}
