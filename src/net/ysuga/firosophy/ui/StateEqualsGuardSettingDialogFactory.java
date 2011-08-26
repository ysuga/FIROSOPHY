/**
 * DelayGuardSettingDialogFactory.java
 *
 * @author Yuki Suga (ysuga.net)
 * @date 2011/08/20
 * @copyright 2011, ysuga.net allrights reserved.
 *
 */
package net.ysuga.firosophy.ui;

import net.ysuga.firosophy.FIROSOPHY;
import net.ysuga.statemachine.StateMachineTagNames;
import net.ysuga.statemachine.ui.guard.AbstractGuardSettingDialog;
import net.ysuga.statemachine.ui.guard.GuardSettingDialogFactory;
import net.ysuga.statemachine.ui.shape.TransitionSettingDialog;

/**
 * @author ysuga
 *
 */
public class StateEqualsGuardSettingDialogFactory implements
		GuardSettingDialogFactory {

	/**
	 * <div lang="ja">
	 * コンストラクタ
	 * </div>
	 * <div lang="en">
	 * Constructor
	 * </div>
	 */
	public StateEqualsGuardSettingDialogFactory() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * <div lang="ja">
	 * @return
	 * </div>
	 * <div lang="en">
	 * @return
	 * </div>
	 */
	public String getKind() {
		return FIROSOPHY.STATE_EQUALS;
	}

	/**
	 * <div lang="ja">
	 * @param transitionSettingDialog
	 * @return
	 * </div>
	 * <div lang="en">
	 * @param transitionSettingDialog
	 * @return
	 * </div>
	 */
	public AbstractGuardSettingDialog createGuardSettingDialog(
			TransitionSettingDialog transitionSettingDialog) {
		return new StateEqualsGuardSettingDialog(transitionSettingDialog, null);
	}

}
