import domain.Lotto;
import domain.LottoResult;
import domain.WinningLotto;
import utility.InputManager;

import java.util.List;

public class LottoGame {
    public static void main(String[] args) {
        int purchaseMoney = InputManager.inputPurchaseMoney(); //구입금액
        List<Lotto> userLottos = InputManager.getUserLottos(purchaseMoney); //유저의 로또들
        WinningLotto winningLotto = InputManager.inputWinningLotto(); // 당첨번호, 보너스볼 입력
        LottoResult.printWinningStatistics(userLottos, winningLotto);
    }
}
