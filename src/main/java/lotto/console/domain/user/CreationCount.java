package lotto.console.domain.user;

public class CreationCount {

	private final int totalCount;
	private final int automaticCount;
	private final int manualCount;

	public CreationCount(final Money money, final int manualCount) {
		this.totalCount = money.getMoney() / 1000;
		validateNegativeManualCount(manualCount);
		validateGreaterManualCountThanTotalCount(this.totalCount, manualCount);
		this.manualCount = manualCount;
		this.automaticCount = totalCount - manualCount;
	}

	private void validateGreaterManualCountThanTotalCount(int totalCount, int manualCount) {
		if(totalCount < manualCount){
			throw new IllegalArgumentException(
				String.format("총 횟수에 비해 수동 입력값이 큽니다. totalCount = %d, manualCount = %d",
					totalCount, manualCount));
		}
	}

	private void validateNegativeManualCount(int manualCount){
		if(manualCount < 0){
			throw new IllegalArgumentException("수동 구매 입력 횟수는 음수가 될 수 없습니다. manualCount = " + manualCount);
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getAutomaticCount() {
		return automaticCount;
	}

	public int getManualCount() {
		return manualCount;
	}
}