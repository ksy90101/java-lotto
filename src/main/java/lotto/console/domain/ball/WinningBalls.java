package lotto.console.domain.ball;

import java.util.List;

public class WinningBalls {
	private final List<Ball> winningBalls;
	private final Ball bonusBall;

	public WinningBalls(final List<Ball> winningBalls, final Ball bonusBall) {
		this.winningBalls = winningBalls;
		this.bonusBall = bonusBall;
	}

	public List<Ball> getWinningBalls() {
		return winningBalls;
	}

	public Ball getBonusBall() {
		return bonusBall;
	}
}
