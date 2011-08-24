/**
 * RTStateSettingDialogFactory.java
 *
 * @author Yuki Suga (ysuga.net)
 * @date 2011/08/23
 * @copyright 2011, ysuga.net allrights reserved.
 *
 */
package net.ysuga.firosophy.ui;

import net.ysuga.firosophy.FIROSOPHY;
import net.ysuga.statemachine.state.State;
import net.ysuga.statemachine.ui.state.AbstractStateSettingDialog;
import net.ysuga.statemachine.ui.state.DefaultStateSettingDialogFactory;

/**
 * <div lang="ja">
 *
 * </div>
 * <div lang="en">
 *
 * </div>
 * @author ysuga
 *
 */
public class RTStateSettingDialogFactory extends
		DefaultStateSettingDialogFactory {

	/**
	 * <div lang="ja">
	 * コンストラクタ
	 * </div>
	 * <div lang="en">
	 * Constructor
	 * </div>
	 */
	public RTStateSettingDialogFactory() {
		super();
	}

	/**
	 * <div lang="ja">
	 * @param state
	 * @return
	 * </div>
	 * <div lang="en">
	 * @param state
	 * @return
	 * </div>
	 */
	@Override
	public AbstractStateSettingDialog createStateSettingDialog(State state) {
		return new RTStateSettingDialog(state);
	}

	/**
	 * <div lang="ja">
	 * @return
	 * </div>
	 * <div lang="en">
	 * @return
	 * </div>
	 */
	@Override
	public String getKind() {
		return FIROSOPHY.RTSTATE;
	}
	
	

}
