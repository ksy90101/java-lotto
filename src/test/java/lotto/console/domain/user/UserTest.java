package lotto.console.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
	@DisplayName("올바르게 유저가 생성되는지 확인하는 테스트")
	@Test
	void createCorrectUser() {
		Money money = new Money(10000);
		CreationCount creationCount = new CreationCount(money, 5);
		User user = new User(money, creationCount, new ArrayList<>());

		assertThat(user.getMoney()).isEqualTo(10000);
	}
}