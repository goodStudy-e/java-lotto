import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoGame {
    public static void main(String[] args) {
        PurchaseMoney purchaseMoney = InputView.inputPurchaseMoney(); //구입금액
        LottoStore lottoStore = new LottoStore(purchaseMoney);
        OutputView.printBuyLottoNumber(lottoStore.getLottoNumber());
        List<Lotto> userLottos = lottoStore.issueUserLottos(); // 유저의 로또
        OutputView.printLottos(userLottos);
        WinningLotto winningLotto = InputView.inputWinningLotto(); // 당첨번호, 보너스볼 입력

        LottoResult lottoResult = new LottoResult(userLottos, winningLotto);
        Map<Rank, Integer> lottoByRank = lottoResult.getLottosByRank();

        double earningsRate = Calculator.calculateEarningRate(lottoByRank, purchaseMoney);
        OutputView.printResult(lottoByRank, earningsRate);
    }
}
