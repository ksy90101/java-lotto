package lotto.console.view;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lotto.console.domain.ball.Ball;
import lotto.console.domain.ticket.LottoTicket;

public class OutputView {
	private static final String BALL_JOINING_DELIMITER = ",";

	public static void printLottoTicket(List<LottoTicket> lottoTickets) {
		for (LottoTicket lottoTicket : lottoTickets) {
			System.out.println(joinLottoTicketWithBracket(lottoTicket));
		}
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
