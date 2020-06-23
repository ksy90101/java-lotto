package lotto.console.domain.ball;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.console.domain.ticket.LottoTicket;

public class BallFactory {

	private static final List<Ball> balls = IntStream.range(1, 46)
		.mapToObj(Ball::new)
		.collect(toList());
	private static final int LOTTO_TICKET_SIZE = 6;

	private BallFactory() {

	}

	public static Ball of(int ballNumber) {
		return balls.stream()
			.filter(ball -> ball.getNumber() == ballNumber)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("로또 볼은 1 ~ 45까지만 있습니다. ballNumber = " + ballNumber));
	}

	public static LottoTicket createRandomSixBalls() {
		Random random = new Random();

		return random.ints(1, 46).
			distinct()
			.limit(6)
			.mapToObj(BallFactory::of)
			.collect(collectingAndThen(toList(), LottoTicket::new));

	}

	public static LottoTicket createManualSixBalls(List<Integer> ballNumbers) {
		validateBallNumbersSizeNotSix(ballNumbers);
		validateBallNumbersReduplication(ballNumbers);

		return ballNumbers.stream()
			.map(BallFactory::of)
			.collect(collectingAndThen(toList(), LottoTicket::new));
	}

	public static void validateBallNumbersSizeNotSix(List<Integer> ballNumbers){
		if(ballNumbers.size() != LOTTO_TICKET_SIZE) {
			throw new IllegalArgumentException("로또 번호가 6개가 아닙니다. ballNumbers = " + ballNumbers);
		}
	}

	private static void validateBallNumbersReduplication(List<Integer> ballNumbers) {
		long ballNumberSize = ballNumbers.stream()
			.distinct()
			.count();

		if (ballNumberSize != LOTTO_TICKET_SIZE) {
			throw new IllegalArgumentException("중복된 로또 번호를 선택 했습니다. ballNumbers = " + ballNumbers);
		}
	}
}
