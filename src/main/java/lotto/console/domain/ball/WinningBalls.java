package lotto.console.domain.ball;

import java.util.Collections;
import java.util.List;

import lotto.console.domain.ticket.LottoTicket;

public class WinningBalls {
	private final LottoTicket winningBalls;
	private final Ball bonusBall;

	public WinningBalls(final LottoTicket winningBalls, final Ball bonusBall) {
		this.winningBalls = winningBalls;
		this.bonusBall = bonusBall;
	}

	public List<Ball> getWinningBalls() {
		return Collections.unmodifiableList(winningBalls.getBalls());
	}

	public Ball getBonusBall() {
		return bonusBall;
	}
}
