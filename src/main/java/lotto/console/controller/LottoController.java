package lotto.console.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.console.converter.Converter;
import lotto.console.domain.ticket.LottoTicket;
import lotto.console.domain.user.CreationCount;
import lotto.console.domain.user.Money;
import lotto.console.domain.user.User;
import lotto.console.view.InputView;

public class LottoController {
	public void run(){
		Money money = createMoney();
		CreationCount creationCount = createCreationCount(money);
		List<String> manualLottoBallNumbers = IntStream.range(0, creationCount.getManualCount())
			.mapToObj(n -> InputView.inputManualNumber())
			.collect(Collectors.toList());
		User user = new User(money, creationCount, new ArrayList<>());
	}

	private Money createMoney(){
		String inputMoney = InputView.inputMoney();

		return new Money(Converter.numberConverterBy(inputMoney));
	}

	private CreationCount createCreationCount(Money money) {
		String inputManualCount = InputView.inputManualCount();
		return new CreationCount(money, Converter.numberConverterBy(inputManualCount));
	}
}
