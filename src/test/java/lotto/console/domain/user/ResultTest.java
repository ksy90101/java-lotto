package lotto.console.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

	@DisplayName("수익률을 구하는 테스트")
	@Test
	void calculateEarningRate() {
		Money money = new Money(5000);
		List<Rank> ranks = Arrays.asList(Rank.FIRST_RANK, Rank.SECOND_RANK, Rank.NO_RANK, Rank.NO_RANK, Rank.NO_RANK);
		Result result = new Result(money, ranks);
		assertThat(result.calculateEarningRate()).isEqualTo(40599900);
	}
}
