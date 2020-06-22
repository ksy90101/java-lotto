package lotto.console.domain.user;

public class User {
	private final Money money;
	private final CreationCount creationCount;

	public User(final Money money, final CreationCount creationCount) {
		this.money = money;
		this.creationCount = creationCount;
	}

	public int getMoney() {
		return money.getMoney();
	}

	public CreationCount getCreationCount() {
		return creationCount;
	}
}

