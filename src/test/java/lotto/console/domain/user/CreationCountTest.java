package lotto.console.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreationCountTest {

	@DisplayName("총 카운트가 생성되는지 확인하는 테스트")
	@Test
	void createCorrectTotalCountCreationCount() {
		CreationCount creationCount = new CreationCount(new Money(5000), 3);
		assertThat(creationCount.getTotalCount()).isEqualTo(5);
	}

	@DisplayName("자동 생성 카운트가 생성되는지 확인하는 테스트")
	@Test
	void createCorrectAutomaticCountCreationCount() {
		CreationCount creationCount = new CreationCount(new Money(5000), 3);
		assertThat(creationCount.getAutomaticCount()).isEqualTo(2);
	}

	@DisplayName("수동 생성 카운트가 생성되는지 확인하는 테스트")
	@Test
	void createCorrectManualCountCreationCount() {
		CreationCount creationCount = new CreationCount(new Money(5000), 3);
		assertThat(creationCount.getManualCount()).isEqualTo(3);
	}
}