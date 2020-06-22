package lotto.console.ball;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BallTest {

	@Test
	void createCorrectBall() {
		Ball ballTest = new Ball(1);
		assertThat(ballTest.getNumber()).isEqualTo(1);
	}
}
