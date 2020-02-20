package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoBallFactory {
    private static List<LottoBall> lottoBalls;

    private LottoBallFactory() {
    }

    static {
        lottoBalls = IntStream.rangeClosed(1, 45)
                .mapToObj(LottoBall::new)
                .collect(Collectors
                        .toList());
    }

    public static List<LottoBall> getInstance() {
        return lottoBalls;
    }

    public static LottoBall findByLottoBall(int lottoBallNumber) {
        return lottoBalls.stream()
                .filter(x -> x.getLottoNumber() == lottoBallNumber)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
