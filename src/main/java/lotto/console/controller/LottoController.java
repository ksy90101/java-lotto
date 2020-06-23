package lotto.console.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lotto.console.converter.Converter;
import lotto.console.domain.ball.BallFactory;
import lotto.console.domain.ticket.LottoTicket;
import lotto.console.domain.user.CreationCount;
import lotto.console.domain.user.Money;
import lotto.console.domain.user.User;
import lotto.console.view.InputView;
import lotto.console.view.OutputView;

public class LottoController {
	public void run(){
		Money money = createMoney();
		CreationCount creationCount = createCreationCount(money);
		List<String> manualLottoBallNumbers = IntStream.range(0, creationCount.getManualCount())
			.mapToObj(n -> InputView.inputManualNumber())
			.collect(Collectors.toList());
		User user = new User(money, creationCount, Stream.concat(
			createAutomaticLottoTickets(creationCount.getAutomaticCount()).stream(),
			createManualLottoTickets(manualLottoBallNumbers).stream()
		).collect(Collectors.toList()));
		OutputView.printLottoTicket(user.getLottoTickets());
	}

	private Money createMoney(){
		String inputMoney = InputView.inputMoney();

		return new Money(Converter.numberConverterBy(inputMoney));
	}

	private CreationCount createCreationCount(Money money) {
		String inputManualCount = InputView.inputManualCount();
		return new CreationCount(money, Converter.numberConverterBy(inputManualCount));
	}

	private List<LottoTicket> createAutomaticLottoTickets(int automaticCount){
		return IntStream.range(0, automaticCount)
			.mapToObj(i -> BallFactory.createRandomSixBalls())
			.map(LottoTicket::new)
			.collect(Collectors.toList());
	}

	private List<LottoTicket> createManualLottoTickets(List<String> manualLottoBallNumbers){
		return manualLottoBallNumbers.stream()
			.map(Converter::numberListConverterBy)
			.map(BallFactory::createManualSixBalls)
			.map(LottoTicket::new)
			.collect(Collectors.toList());
	}
}
