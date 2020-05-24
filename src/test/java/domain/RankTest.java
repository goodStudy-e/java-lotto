package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("당첨된 복권 등수를 조회하는 테스트한다")
    @Test
    void test_valueOfFunction() {
        //given
        int countOfMatchSix = 6;
        int countOfMatchFive = 5;
        int countOfMatchFour = 4;
        int countOfMatchThree = 3;
        int countOfMatchTwo = 2;

        //when
        Rank answer1 = Rank.valueOf(countOfMatchSix, false);
        Rank answer2 = Rank.valueOf(countOfMatchFive, true);
        Rank answer3 = Rank.valueOf(countOfMatchFive, false);
        Rank answer4 = Rank.valueOf(countOfMatchFour, false);
        Rank answer5 = Rank.valueOf(countOfMatchThree, false);
        Rank answer6 = Rank.valueOf(countOfMatchTwo, false);

        //then
        assertThat(answer1).isEqualTo(Rank.FIRST);
        assertThat(answer2).isEqualTo(Rank.SECOND);
        assertThat(answer3).isEqualTo(Rank.THIRD);
        assertThat(answer4).isEqualTo(Rank.FOURTH);
        assertThat(answer5).isEqualTo(Rank.FIFTH);
        assertThat(answer6).isEqualTo(Rank.MISS);
    }
}