package lotto.console.domain.ball;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lotto.console.domain.ticket.LottoTicket;

public class LottoTicketTest {
	@Test
	void createSixBall() {
		LottoTicket lottoTicket = new LottoTicket(BallFactory.createRandomSixBalls());
		assertThat(lottoTicket.getBalls()).hasSize(6);
	}
}
