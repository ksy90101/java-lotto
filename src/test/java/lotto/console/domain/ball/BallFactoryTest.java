package lotto.console.domain.ball;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.console.converter.Converter;

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
	void createIncorrectWhereBallOverFortyFiveAndLessThanOne(int ballNumber) {
		assertThatThrownBy(() -> BallFactory.of(ballNumber)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 볼은 1 ~ 45까지만 있습니다. ballNumber = " + ballNumber);
	}

	@DisplayName("6개의 자동 로또볼을 생성하는 테스트")
	@Test
	void createRandomSixBalls() {
		assertThat(BallFactory.createRandomSixBalls().getBalls()).hasSize(6);
	}

	@DisplayName("6개의 수동 로또 볼을 생성하는 테스트")
	@Test
	void createManualSixBalls() {
		assertThat(
			BallFactory.createManualSixBalls(Converter.numberListConverterBy("1,2,3,4,5,6")).getBalls()).containsAll(
			Arrays.asList(BallFactory.of(1),
				BallFactory.of(2),
				BallFactory.of(3),
				BallFactory.of(4),
				BallFactory.of(5),
				BallFactory.of(6))
		);
	}

	@DisplayName("수동 로또 번호 입력 값 중에 중복이 있을 경우 예외처리")
	@Test
	void validateBallNumbersReduplication() {
		assertThatThrownBy(() -> BallFactory.createManualSixBalls(
			Converter.numberListConverterBy("1,1,2,3,4,5"))).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("중복된 로또 번호를 선택 했습니다. ballNumbers = [1, 1, 2, 3, 4, 5]");
	}

	@DisplayName("수동 로또 볼 입력 값이 6개가 아닌 경우 예외처리")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
	void validateSizeNotSix(String ballNumbers) {
		assertThatThrownBy(() -> BallFactory.createManualSixBalls(
			Converter.numberListConverterBy((ballNumbers)
			))).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 번호가 6개가 아닙니다. ballNumbers = " + Converter.numberListConverterBy(ballNumbers));
	}
}