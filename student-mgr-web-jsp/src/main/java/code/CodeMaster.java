package code;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * コードマスタ基底クラス
 * 
 * @author infront
 */
public class CodeMaster {

	protected Map<String,String> codeMap = new LinkedHashMap<>();

	/**
	 * コードマップを取得する.
	 *
	 * @return コードマップ
	 */
	public Map<String, String> getMap() {
		return codeMap;
	}

	/**
	 * 値を取得する.
	 *
	 * @param key キー
	 * @return 値
	 */
	public String getValue(String key) {
		return codeMap.get(key);
	}	
	
	/**
	 * 値を取得する.
	 *
	 * @param key キー
	 * @return 値
	 */
	public String getValue(int key) {
		return codeMap.get(String.valueOf(key));
	}
	
}
