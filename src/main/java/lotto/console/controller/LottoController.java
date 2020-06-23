package lotto.console.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lotto.console.converter.Converter;
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
	private final static Logger logger = Logger.getLogger("logger");

	public void run() {
		try {
			Money money = createMoney();
			CreationCount creationCount = createCreationCount(money);
			User user = new User(money, creationCount, Stream.concat(
				createAutomaticLottoTickets(creationCount.getAutomaticCount()).stream(),
				createManualLottoTickets(creationCount.getManualCount()).stream()
			).collect(Collectors.toList()));
			OutputView.printLottoTicket(user.getLottoTickets());
			WinningBalls winningBalls = createWinningBalls();
			List<Rank> ranks = user.calculateRanks(winningBalls);
			Result result = new Result(money, ranks);
			OutputView.printResult(result);
		} catch (IllegalArgumentException e) {
			logger.warning(e.getMessage());
		}
	}

	private Money createMoney() {
		String inputMoney = InputView.inputMoney();

		return new Money(Converter.numberConverterBy(inputMoney));
	}

	private CreationCount createCreationCount(Money money) {
		String inputManualCount = InputView.inputManualCount();
		return new CreationCount(money, Converter.numberConverterBy(inputManualCount));
	}

	private List<LottoTicket> createAutomaticLottoTickets(int automaticCount) {
		return IntStream.range(0, automaticCount)
			.mapToObj(i -> BallFactory.createRandomSixBalls())
			.collect(Collectors.toList());
	}

	private List<LottoTicket> createManualLottoTickets(int manualCount) {
		List<String> manualLottoBallNumbers = IntStream.range(0, manualCount)
			.mapToObj(n -> InputView.inputManualNumber())
			.collect(Collectors.toList());

		return manualLottoBallNumbers.stream()
			.map(Converter::numberListConverterBy)
			.map(BallFactory::createManualSixBalls)
			.collect(Collectors.toList());
	}

	private WinningBalls createWinningBalls() {
		String winningBallNumbers = InputView.inputWinningBalls();
		String bonusBall = InputView.inputBonusBall();

		return new WinningBalls(createWinningBallsBy(winningBallNumbers),
			BallFactory.of(Converter.numberConverterBy(bonusBall)));
	}

	private LottoTicket createWinningBallsBy(String winningBallNumbers) {
		return BallFactory.createManualSixBalls(
			Converter.numberListConverterBy(winningBallNumbers)
		);
	}
}
