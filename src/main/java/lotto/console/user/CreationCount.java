package lotto.console.user;

public class CreationCount {
	private final int totalCount;
	private final int automaticCount;
	private final int manualCount;

	public CreationCount(final int totalCount, final int manualCount) {
		this.totalCount = totalCount;
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
	}
}
