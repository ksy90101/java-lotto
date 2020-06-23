package lotto.console.domain.user;

import java.util.Arrays;

import lotto.console.domain.ball.WinningBalls;
import lotto.console.domain.ticket.LottoTicket;

public enum Rank {
	FIRST_RANK(2_000_000_000, 6),
	SECOND_RANK(30_000_000, 5),
	THIRD_RANK(1_500_000, 5),
	FOURTH_RANK(50_000, 4),
	FIFTH_RANK(5_000, 3),
	NO_RANK(0, 0);

	private final int winningMoney;
	private final int winningCount;

	Rank(final int winningMoney, final int winningCount) {
		this.winningMoney = winningMoney;
		this.winningCount = winningCount;
	}

	public static Rank of(final LottoTicket lottoTicket, final WinningBalls winningBalls) {
		long winningCount = winningBalls.getWinningBalls()
			.stream()
			.flatMap(n -> lottoTicket.getBalls()
				.stream()
				.filter(n::equals)).count();

		return Arrays.stream(Rank.values())
			.filter(rank -> rank.getWinningCount() == winningCount)
			.filter(rank -> isSecond(rank, lottoTicket, winningBalls))
			.findFirst()
			.orElseGet(() -> Rank.NO_RANK);
	}

	private static boolean isSecond(final Rank rank, final LottoTicket lottoTicket, final WinningBalls winningBalls) {
		if (rank != Rank.SECOND_RANK) {
			return true;
		}
		return lottoTicket.getBalls()
			.stream()
			.anyMatch(n -> n == winningBalls.getBonusBall());
	}

	public int getWinningCount() {
		return winningCount;
	}
}
