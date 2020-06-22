package lotto.console.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

	@DisplayName("돈이 제대로 생성되는지 확인하는 테스트")
	@Test
	void createCorrectMoney() {
		Money money = new Money("1000");
		assertThat(money.getMoney()).isEqualTo(1000);
	}
}
