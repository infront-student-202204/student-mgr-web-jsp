package code;

/**
 * 教科コードマスタクラス.
 *
 * @author infront
 */
public class SubjectCodeMaster extends CodeMaster {

	/**
	 * コンストラクタ.
	 */
	public SubjectCodeMaster() {

		codeMap.put("MA", "数学");
		codeMap.put("JA", "国語");
		codeMap.put("EN", "英語");
	}
}
