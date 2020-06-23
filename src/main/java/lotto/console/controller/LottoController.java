package lotto.console.controller;

import lotto.console.converter.Converter;
import lotto.console.domain.user.CreationCount;
import lotto.console.domain.user.Money;
import lotto.console.domain.user.User;
import lotto.console.view.InputView;

public class LottoController {
	public void run(){
		Money money = createMoney();
		CreationCount creationCount = createCreationCount(money);
		User user = new User(money, creationCount);
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
