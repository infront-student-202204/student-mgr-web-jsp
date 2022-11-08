package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Dateユーティリティ
 * 
 * @author infront
 */
public class DateUtils {

	/**
	 * String型の日付文字列をjava.util.Date型に変換
	 * 
	 * @param strDate（任意の日付型文字列）
	 * @param type（strDateの区切り文字を記載（"yyyy-MM-dd"や"yyyy/MM/dd"等））
	 * @return java.util.Date strDate
	 */
	public static java.util.Date parseDate(String strDate, String type) {
		
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat(type);
			return sdFormat.parse(strDate);
			}
		catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * String型"yyyy-MM-dd"形式の日付文字列をjava.util.Date型に変換
	 * 
	 * @param strDate（"yyyy-MM-dd"形式）
	 * @return java.util.Date strDate
	 */
	public static java.util.Date parseDate(String strDate) {
		return parseDate(strDate, "yyyy-MM-dd");
	}
	
	/**
	 * String型"yyyy/MM/dd"形式の日付文字列をjava.util.Date型に変換
	 * 
	 * @param strDate（"yyyy/MM/dd"形式）
	 * @return java.util.Date strDate
	 */
	public static java.util.Date parseDate_S(String strDate) {
		return parseDate(strDate, "yyyy/MM/dd");
	}
	
	/**
	 * java.util.Dateをjava.sql.Dateに変換
	 * 
	 * @param java.util.Date date
	 * @return java.sql.Date date
	 */
	public static java.sql.Date convDate(java.util.Date date) {
		
		long timeInMilliSeconds = date.getTime();
		return new java.sql.Date(timeInMilliSeconds);	
	}
}
