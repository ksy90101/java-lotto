package lotto.domain;

import lotto.Exception.NumberOutOfRangeException;

public class LottoBall implements Comparable<LottoBall> {
    private static final int MIN_BALL_NUMBER = 0;
    private static final int MAX_BALL_NUMBER = 45;
    public static final String NUMBER_OUT_OF_RANGE_ERROR_MESSAGE = "로또 볼 범위를 벗어났습니다.";

    private int lottoNumber;

    public LottoBall(int lottoNumber) {
        if (lottoNumber < MIN_BALL_NUMBER || lottoNumber > MAX_BALL_NUMBER) {
            throw new NumberOutOfRangeException(NUMBER_OUT_OF_RANGE_ERROR_MESSAGE);
        }
        this.lottoNumber = lottoNumber;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public int compareTo(LottoBall lottoBall) {
        return this.lottoNumber - lottoBall.lottoNumber;
    }
}