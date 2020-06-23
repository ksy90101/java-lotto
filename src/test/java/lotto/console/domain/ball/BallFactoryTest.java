package lotto.console.domain.ball;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BallFactoryTest {
	@DisplayName("1 ~ 45의 로또 볼이 생성되는지 확인하는 테스트")
	@ParameterizedTest
	@MethodSource("rangeProvider")
	void createCorrectBallBetweenOneAndFortyFive(int ballNumber) {
		assertThat(BallFactory.of(ballNumber).getNumber()).isEqualTo(ballNumber);
	}

	static IntStream rangeProvider() {
		return IntStream.range(1, 46);
	}

	@DisplayName("1 ~ 45가 아닌 다른 로또 볼 숫자가 들어왔을 경우 예외처리")
	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void createIncorrectWhereBallOverFortyFiveAndLessThanOne(int ballNumber){
		assertThatThrownBy(() -> BallFactory.of(ballNumber)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 볼은 1 ~ 45까지만 있습니다. ballNumber = " + ballNumber);
	}

	@DisplayName("6개의 로또볼을 생성하는 테스트")
	@Test
	void createRandomSixBalls(){
		assertThat(BallFactory.createRandomSixBalls()).hasSize(6);
	}
}
