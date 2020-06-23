package lotto.console.domain.ticket;

import java.util.List;

import lotto.console.domain.ball.Ball;

public class LottoTicket {
	private final List<Ball> balls;

	public LottoTicket(final List<Ball> balls) {
		validateOverSix(balls);
		this.balls = balls;
	}

	private void validateOverSix(List<Ball> balls) {
		if (balls.size() != 6) {
			throw new IllegalArgumentException("로또 한장 당 6개씩 가질 수 습니다. ballsSize = " + balls.size());
		}
	}

	public List<Ball> getBalls() {
		return balls;
	}
}