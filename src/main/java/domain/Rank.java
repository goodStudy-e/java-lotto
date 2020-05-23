package domain;

public enum Rank {
    FIRST(6, 2_000_000_000), // 1등
    SECOND(5, 30_000_000), // 2등
    THIRD(5, 1_500_000), // 3등
    FOURTH(4, 50_000), // 4등
    FIFTH(3, 5_000), // 5등
    MISS(0, 0);

    private static final String COUNT_OF_MATCH_INVALID_MESSAGE = "는 유효하지 않은 값입니다.";
    private static final int WINNING_MIN_COUNT = 3;

    private int countOfMatch;
    private int winningMoney;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return MISS;
        }

        if (SECOND.matchCount(countOfMatch) && matchBonus) { //todo: 이부분이 적용이 안됌 ...ㅠㅠ 
            return SECOND;
        }

        if (THIRD.matchCount(countOfMatch) && !matchBonus) {
            return THIRD;
        }

        for (Rank rank : values()) {
            if (rank.matchCount(countOfMatch)) { //SECOND에 대한 예외처리 안돼있음
                return rank;
            }
        }
        throw new IllegalArgumentException(countOfMatch + COUNT_OF_MATCH_INVALID_MESSAGE);
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }
}