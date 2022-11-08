package common;

import java.util.Arrays;
import java.util.Map;

/**
 * JSPユーティリティ
 * 
 * @author infront
 */
public class JSPUtils {
	
	/**
	 * 保持した値が空文字（""）やnullではないかを判別し対応する値を返す
	 * 
	 * @param hValue
	 * @return nullまたは空文字（"")ではない場合は保持した値。それ以外は空文字（""）。
	 */
	public static String valueCheck(String hValue) {
		
		if(!(hValue == null || "".equals(hValue))) {
			
			return hValue;
		}
		
		return "";
	}
	
	/**
	 * 保持した値が空文字（""）やnullではないか、mapの値と一致するかを判別し対応する値を返す
	 * 
	 *@param hValue, mValue
	 *@return nullまたは空文字（"")ではないかつmapの値と一致した場合はchecked。それ以外は空文字（""）。
	 */
	public static String checkedCheck(String hValue, String mValue) {
		
		if(!(hValue == null || "".equals(hValue)) && hValue.equals(mValue)) {
			
			return "checked";
		}
		
		return "";
	}
	
	/**
	 * 保持した配列がnullではないか、mapの値と一致するかを判別し対応する値を返す
	 * 
	 * @param hValue, mValue
	 * @return nullまたは空文字（"")ではないかつmapの値と一致した場合はchecked。それ以外は空文字（""）。
	 */
	public static String checkedCheck(String[] hList, String mKey) {
		
		if(hList != null && Arrays.asList(hList).contains(mKey)) {
			
			return "checked";
		}
		
		return "";
	}
	
	/**
	 * 保持した値が空文字（""）やnullではないか、mapの値と一致するかを判別し対応する値を返す
	 * 
	 * @param hValue, mValue
	 * @return nullまたは空文字（"")ではないかつmapの値と一致した場合はselected。それ以外は空文字（""）。
	 */
	public static String selectedCheck(String hValue, String mValue) {
		
		if(!(hValue == null || "".equals(hValue)) && hValue.equals(mValue)) {
			
			return "selected";
		}
		
		return "";
	}
	
	/**
	 * テキストボックスの生成
	 * 
	 * @param name name属性
	 * @param value value属性（初期値となる値）
	 * @return 初期値が設定されている場合は初期値を挿入済み
	 * 			未設定の場合は空のテキストボックス
	 */
	public static String renderText(String name, String value) {
		
		if (value == null) {
			value = "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<input type=\"text\" name=" + name + " value=\"" + value + "\">");
		return sb.toString();
	}
	
	/**
	 * セレクトボックスの生成
	 * 
	 * @param name name属性
	 * @param selected（初期値となる値）
	 * @return 初期値が設定されている場合は初期値の項目を選択済み
	 * 			未設定の場合は未選択状態のセレクトボックス
	 */
	public static String renderSelect(String name, Map<String,String> map, String selected) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<select name=" + name + ">");
		sb.append("<option value=\"\">（選択してください）</option>");
		
		for (String key : map.keySet()) {
			
			sb.append("<option value=\"" + key + "\"");
			
			if(selected != null && selected.equals(String.valueOf(key))) { 
				sb.append("selected");
			}
			sb.append(">" + map.get(key) + "</option>");
		}
		sb.append("</select>");
		
		return sb.toString();
	}
	
	/**
	 * ラジオボタンの生成
	 * 
	 * @param name name属性
	 * @param checked（初期値となる値）
	 * @return 初期値が設定されている場合は初期値の項目を選択済み
	 * 			未設定の場合は未選択状態のラジオボタン
	 */
	public static String renderRadio(String name, Map<String,String> map, String checked) {
		
		StringBuilder sb = new StringBuilder();
		
		for(String key : map.keySet()) {
			
			sb.append("<input id=\"" +  key + "\" type=\"radio\" name=" + name + " value=\"" + key + "\"");
			
			if(checked != null && checked.equals(key)) {
				sb.append("checked");
			}
			sb.append("><label for=\"" + key + "\">" + map.get(key) + "</label>");
		}
		return sb.toString();
	}
	
	/**
	 * 日付型テキストボックスの生成
	 * 
	 * @param name name属性
	 * @param value value属性（初期値となる値）
	 * @return 初期値が設定されている場合は初期値を表示
	 * 			未設定の場合は未選択状態の日付型テキストボックス
	 */
	public static String renderDate(String name, String value) {
		
		if (value == null) {
			value = "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<input type=\"date\" name=" + name + " value=\"" + value + "\">");
		
		return sb.toString();
	}
	
	/**
	 * チェックボックスの生成
	 * 
	 * @param name name属性
	 * @param checked（初期値となる値）
	 * @return 初期値が設定されている場合は初期値の項目を選択済み
	 * 			未設定の場合は未選択状態のチェックボックス
	 */
	public static String renderCheckbox(String name, Map<String,String> map, String[] checked) {
		
		StringBuilder sb = new StringBuilder();
		
		for(String key : map.keySet()) {
			
			sb.append("<input id=\"" +  key + "\" type=\"checkbox\" name=" + name + " value=\"" + key + "\"");
			
			if(checked != null && Arrays.asList(checked).contains(key)) {
				sb.append("checked");
			}
			sb.append("><label for=\"" +  key + "\">" + map.get(key) + "</label>");
		}
		return sb.toString();
	}
	
	/**
	 * Hiddenの生成
	 * 
	 * @param name name属性
	 * @param value value属性（初期値となる値）
	 * @return 初期値が設定されている場合は初期値を挿入済み
	 * 			未設定の場合は空文字指定
	 */
	public static String renderHidden(String name, String value) {
		
		if (value == null) {
			value = "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<input type=\"hidden\" name=" + name + " value=\"" + value + "\">");
		return sb.toString();
	}
	
	/**
	 * Hiddenの生成（配列用）
	 * 
	 * @param name name属性
	 * @param values 配列指定
	 * @return 初期値が設定されている場合は初期値を挿入済み
	 * 			未設定の場合は空文字指定
	 */
	public static String renderHidden(String name, String[] values) {
		
		if (values == null) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			sb.append(renderHidden(name, value));
		}
		return sb.toString();
	}	
}
