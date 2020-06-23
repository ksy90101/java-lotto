package lotto.console.view;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lotto.console.domain.ball.Ball;
import lotto.console.domain.ticket.LottoTicket;
import lotto.console.domain.user.Rank;
import lotto.console.domain.user.Result;

public class OutputView {
	private static final String BALL_JOINING_DELIMITER = ",";

	public static void printLottoTicket(List<LottoTicket> lottoTickets) {
		for (LottoTicket lottoTicket : lottoTickets) {
			System.out.println(joinLottoTicketWithBracket(lottoTicket));
		}
	}

	public static void printResult(Result result){
		for (Rank rank : Rank.values()){
			System.out.println(joinRankInfo(rank, result.sizeBy(rank)));
		}
		System.out.printf("총 수익률은 %f%%입니다.", result.calculateEarningRate());
	}

	private static String joinRankInfo(Rank rank, long rankPersonnel){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(rank.getWinningCount())
			.append("개 일치 (")
			.append(rank.getWinningMoney())
			.append("원) - ")
			.append(rankPersonnel)
			.append("개");

		return stringBuilder.toString();
	}

	private static String joinLottoTicketWithBracket(LottoTicket lottoTicket) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[")
			.append(joinLottoBalls(lottoTicket))
			.append("]");

		return stringBuilder.toString();
	}

	private static String joinLottoBalls(LottoTicket lottoTicket) {
		return lottoTicket.getBalls().stream()
			.map(Ball::getNumber)
			.map(Objects::toString)
			.collect(Collectors.joining(BALL_JOINING_DELIMITER));
	}
}
