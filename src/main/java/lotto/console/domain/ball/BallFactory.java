package lotto.console.domain.ball;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BallFactory {
	private static final List<Ball> balls = IntStream.range(1, 46)
		.mapToObj(Ball::new)
		.collect(Collectors.toList());

	private BallFactory() {

	}

	public static Ball of(int ballNumber){
		return balls.stream()
			.filter(ball -> ball.getNumber() == ballNumber)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("로또 볼은 1 ~ 45까지만 있습니다. ballNumber = " + ballNumber));
	}

	public static List<Ball> createRandomSixBalls() {
		Collections.shuffle(balls);
		return Collections.unmodifiableList(balls.subList(0, 6));
	}
}
