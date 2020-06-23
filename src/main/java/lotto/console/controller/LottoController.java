package lotto.console.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lotto.console.converter.Converter;
import lotto.console.domain.ball.Ball;
import lotto.console.domain.ball.BallFactory;
import lotto.console.domain.ball.WinningBalls;
import lotto.console.domain.ticket.LottoTicket;
import lotto.console.domain.user.CreationCount;
import lotto.console.domain.user.Money;
import lotto.console.domain.user.Rank;
import lotto.console.domain.user.Result;
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
		WinningBalls winningBalls = createWinningBalls();
		List<Rank> ranks = user.calculateRanks(winningBalls);
		Result result = new Result(money, ranks);
		OutputView.printResult(result);
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

	private WinningBalls createWinningBalls(){
		String winningBallNumbers = InputView.inputWinningBalls();
		String bonusBall = InputView.inputBonusBall();

		return new WinningBalls(createWinningBallsBy(winningBallNumbers),
			BallFactory.of(Converter.numberConverterBy(bonusBall)));
	}

	private List<Ball> createWinningBallsBy(String winningBallNumbers){
		return BallFactory.createManualSixBalls(
			Converter.numberListConverterBy(winningBallNumbers)
		);
	}
}
