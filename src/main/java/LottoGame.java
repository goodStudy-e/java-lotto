import domain.*;
import utility.InputManager;
import utility.OutputManager;

import java.util.List;
import java.util.Map;

public class LottoGame {
    public static void main(String[] args) {
        PurchaseMoney purchaseMoney = InputManager.inputPurchaseMoney(); //구입금액
        List<Lotto> userLottos = InputManager.getUserLottos(purchaseMoney.getAmount()); // 유저의 로또
        WinningLotto winningLotto = InputManager.inputWinningLotto(); // 당첨번호, 보너스볼 입력
        List<Rank> userWinningLottos = LottoResult.getUserWinningLottos(userLottos, winningLotto); // 유저의 당첨된 로또들
        Map<Rank, Integer> lottosByRank = LottoResult.getLottosByRank(userWinningLottos);
        double earningRate = LottoResult.getEarningRate(lottosByRank, purchaseMoney);
        OutputManager.printResult(lottosByRank, earningRate);
    }
}
