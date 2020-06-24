package lotto.console.domain.ball;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningBallsTest {

	@DisplayName("당첨 번호가 제대로 생성되는지 확인하는 테스트")
	@Test
	void createCorrectWinningBalls() {
		LottoTicket expectedWinningBall = IntStream.range(1, 7)
			.mapToObj(BallFactory::of)
			.collect(collectingAndThen(toList(), LottoTicket::new));
		Ball expectedBonusBall = BallFactory.of(7);
		WinningBalls winningBalls = new WinningBalls(expectedWinningBall, expectedBonusBall);

		assertAll(
			() -> assertThat(winningBalls.getWinningBalls()).containsAll(expectedWinningBall.getBalls()),
			() -> assertThat(winningBalls.getBonusBall()).isEqualTo(expectedBonusBall)
		);
	}
}