package lotto.domain;

import lotto.Exception.DuplicationException;
import lotto.util.InputValidationUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class WinningBallsTest {
    @Test
    @DisplayName("자동 생성된 로또볼과 당첨 로또볼과 비교하는 테스트")
    void compare_lotto_ball_and_winning_ball() {
        String lottoBallInput = "1,2,3,4,5,6";
        Set<LottoBall> lottoBalls = LottoBalls.generateLottoBalls(lottoBallInput);
        WinningBalls winningBalls = new WinningBalls(lottoBalls, LottoBallFactory.findByLottoBall(7));
        LottoTicket lottoTicket = new LottoTicket(lottoBalls);

        Assertions.assertThat(winningBalls.hitLottoBalls(lottoTicket)).isEqualTo(6);
    }

    @Test
    @DisplayName("보너스번호가 있는경우 테스트")
    void present_bonus_balls() {
        int bonusBall = InputValidationUtil.returnNumberWithNumberCheck("10");
        String winningBallInputs = "3,4,5,6,7,8";
        String lottoTicketNumbers = "3,4,5,6,7,10";
        Set<LottoBall> winningBallValues = LottoBalls.generateLottoBalls(winningBallInputs);
        Set<LottoBall> lottoTicket = LottoBalls.generateLottoBalls(lottoTicketNumbers);
        WinningBalls winningBalls = new WinningBalls(winningBallValues, LottoBallFactory.findByLottoBall(bonusBall));

        Assertions.assertThat(winningBalls.hitBonusBall(new LottoTicket(lottoTicket))).isTrue();
    }

    @Test
    @DisplayName("당첨 로또 볼과 보너스 번호가 중복인 경우 테스트")
    void duplicate_winning_balls_and_bonus_ball() {
        int bonusBall = InputValidationUtil.returnNumberWithNumberCheck("6");
        String winningBallInputs = "1,2,3,4,5,6";
        Set<LottoBall> winningBallValues = LottoBalls.generateLottoBalls(winningBallInputs);

        Assertions.assertThatThrownBy(() -> new WinningBalls(winningBallValues, LottoBallFactory.findByLottoBall(bonusBall)))
                .isInstanceOf(DuplicationException.class)
                .hasMessage("보너스 볼이 중복입니다. 당첨 번호를 다시 입력해주세요.");
    }
}
