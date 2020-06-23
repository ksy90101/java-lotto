package lotto.console.domain.user;

public class CreationCount {

	private final int totalCount;
	private final int automaticCount;
	private final int manualCount;

	public CreationCount(final Money money, final int manualCount) {
		this.totalCount = money.getMoney() / 1000;
		this.manualCount = manualCount;
		this.automaticCount = totalCount - manualCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getAutomaticCount() {
		return automaticCount;
	}

	public int getManualCount() {
		return manualCount;
	}bㅎ
}