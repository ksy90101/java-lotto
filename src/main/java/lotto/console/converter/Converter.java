package lotto.console.converter;

public class Converter {
	public static int numberConverterBy(String value){
		try{
			return Integer.parseInt(value);
		}catch (NumberFormatException e){
			throw new NumberFormatException("숫자만 입력 가능합니다. value = " + value);
		}
	}
}
