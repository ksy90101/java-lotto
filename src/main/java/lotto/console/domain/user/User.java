package lotto.console.domain.user;

import java.util.List;

import lotto.console.domain.ticket.LottoTicket;

public class User {
	private final Money money;
	private final CreationCount creationCount;
	private final List<LottoTicket> lottoTickets;

	public User(final Money money, final CreationCount creationCount,
		final List<LottoTicket> lottoTickets) {
		this.money = money;
		this.creationCount = creationCount;
		this.lottoTickets = lottoTickets;
	}

	public int getMoney() {
		return money.getMoney();
	}

	public List<LottoTicket> getLottoTickets() {
		return lottoTickets;
	}
}

