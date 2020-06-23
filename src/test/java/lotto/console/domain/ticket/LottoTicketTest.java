package lotto.console.domain.ticket;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.console.domain.ball.BallFactory;

public class LottoTicketTest {

	@DisplayName("6개의 로또 볼이 들어있는 로또 티켓이 생성되는지 확인하는 테스트")
	@Test
	void createSixBall() {
		LottoTicket lottoTicket = BallFactory.createRandomSixBalls();
		assertThat(lottoTicket.getBalls()).hasSize(6);
	}
}