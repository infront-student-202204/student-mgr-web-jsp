package common;

/**
 * 数値型関連ユーティリティ
 * 
 * @author infront
 */
public class NumberUtils {
	
	/**
	 * 文字列からIntegerに変換する。
	 * nullまたは空文字の場合はnullを返却する。
	 * 
	 * @param value 対象文字
	 * @return
	 */
	public static Integer intValue(String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		return Integer.parseInt(value);
	}

}
