package lotto.console.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static String inputMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return scanner.nextLine();
	}

	public static String inputManualCount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return scanner.nextLine();
	}
}
