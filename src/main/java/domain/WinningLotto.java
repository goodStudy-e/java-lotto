package domain;

public class WinningLotto {
    private final Lotto lotto;
    private final BonusBall bonusBall;

    public WinningLotto(Lotto lotto, BonusBall bonusBall) {
        this.lotto = new Lotto(lotto.getNumbers());
        this.bonusBall = new BonusBall(bonusBall.getNumber(), lotto);
    }

    public Rank match(Lotto userLotto) {
        int countOfMatch = (int) lotto.getNumbers().stream()
                .filter(number -> userLotto.getNumbers().contains(number))
                .count();
        boolean matchBonus = userLotto.getNumbers().contains(bonusBall.getNumber());
        return Rank.valueOf(countOfMatch, matchBonus);
    }

    public Lotto getLotto() {
        return new Lotto(lotto.getNumbers());
    }

    public BonusBall getBonusBall() {
        return bonusBall;
    }
}