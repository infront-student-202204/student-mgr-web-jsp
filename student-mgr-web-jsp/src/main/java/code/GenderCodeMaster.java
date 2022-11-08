package code;

/**
 * 性別コードマスタクラス.
 *
 * @author infront
 */
public class GenderCodeMaster extends CodeMaster {

	/**
	 * コンストラクタ
	 */
	public GenderCodeMaster() {

		codeMap.put("M","男性");
		codeMap.put("F","女性");
	}
}