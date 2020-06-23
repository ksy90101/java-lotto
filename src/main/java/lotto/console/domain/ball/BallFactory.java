package lotto.console.domain.ball;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import lotto.console.domain.ticket.LottoTicket;

public class BallFactory {
	private static final List<Ball> balls = IntStream.range(1, 46)
		.mapToObj(Ball::new)
		.collect(toList());

	private BallFactory() {

	}

	public static Ball of(int ballNumber){
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

	public static LottoTicket createManualSixBalls(List<Integer> ballNumbers){
		return ballNumbers.stream()
			.map(BallFactory::of)
			.collect(collectingAndThen(toList(), LottoTicket::new));
	}
}
