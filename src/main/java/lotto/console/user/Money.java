package lotto.console.user;

public class Money {
	private final int money;

	public Money(final String money) {
		this.money = Integer.parseInt(money);
	}

	public int getMoney() {
		return money;
	}
}
