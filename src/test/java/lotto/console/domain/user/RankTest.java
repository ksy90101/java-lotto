package lotto.console.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.console.converter.Converter;
import lotto.console.domain.ball.BallFactory;
import lotto.console.domain.ball.WinningBalls;
import lotto.console.domain.ticket.LottoTicket;

public class RankTest {

	@DisplayName("각 등수를 확인하는 테스트")
	@ParameterizedTest
	@CsvSource(value = {
		"1,2,3,4,5,6:FIRST_RANK",
		"1,2,3,4,5,7:SECOND_RANK",
		"1,2,3,4,5,10:THIRD_RANK",
		"1,2,3,4,8,9:FOURTH_RANK",
		"1,2,3,10,11,12:FIFTH_RANK",
		"1,2,10,11,12,13:NO_RANK",
		"1,10,11,12,13,14:NO_RANK",
		"10,11,12,13,14,15:NO_RANK"
	}, delimiter = ':')
	void findRank(String lottoTicketNumbers, Rank rank) {
		List<Integer> winningBallNumbers = IntStream.range(1, 7)
			.boxed()
			.collect(Collectors.toList());

		WinningBalls winningBalls = new WinningBalls(
			BallFactory.createManualSixBalls(winningBallNumbers),
			BallFactory.of(7));

		LottoTicket lottoTicket = new LottoTicket(BallFactory.createManualSixBalls(
			Converter.numberListConverterBy(lottoTicketNumbers))
		);

		assertThat(Rank.of(lottoTicket, winningBalls)).isEqualTo(rank);
	}
}
