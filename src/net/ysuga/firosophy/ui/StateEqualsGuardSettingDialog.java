/**
 * DelayGuardSettingDialog.java
 *
 * @author Yuki Suga (ysuga.net)
 * @date 2011/08/20
 * @copyright 2011, ysuga.net allrights reserved.
 *
 */
package net.ysuga.firosophy.ui;

import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.ysuga.firosophy.FIROSOPHY;
import net.ysuga.firosophy.guard.StateEqualsGuard;
import net.ysuga.firosophy.state.RTState;
import net.ysuga.rtsbuilder.RTCCondition;
import net.ysuga.rtsystem.profile.RTComponent;
import net.ysuga.rtsystem.profile.RTSProperties;
import net.ysuga.rtsystem.profile.RTSystemProfile;
import net.ysuga.statemachine.StateMachineTagNames;
import net.ysuga.statemachine.exception.InvalidGuardException;
import net.ysuga.statemachine.guard.Guard;
import net.ysuga.statemachine.state.State;
import net.ysuga.statemachine.ui.guard.AbstractGuardSettingDialog;
import net.ysuga.statemachine.ui.shape.GridLayoutPanel;
import net.ysuga.statemachine.ui.shape.TransitionSettingDialog;

/**
 * @author ysuga
 *
 */
public class StateEqualsGuardSettingDialog extends AbstractGuardSettingDialog {

	private JComboBox componentComboBox;
	private JComboBox conditionComboBox;
	
	private RTState rtState;
	/**
	 * <div lang="ja">
	 * �R���X�g���N�^
	 * @param transitionSettingDialog
	 * @param guard
	 * </div>
	 * <div lang="en">
	 * Constructor
	 * @param transitionSettingDialog
	 * @param guard
	 * </div>
	 */
	public StateEqualsGuardSettingDialog(
			TransitionSettingDialog transitionSettingDialog, Guard guard) {
		super(transitionSettingDialog, guard);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		componentComboBox = new JComboBox();
		componentComboBox.addItem("any");
		
		State state = transitionSettingDialog.getSourceState();
		if(state != null && state instanceof RTState) {
			rtState = (RTState)state;
		
		}
		try {
			RTSystemProfile profile = new RTSystemProfile(new File(rtState.getFileName()));
			for(RTSProperties component : profile.componentSet) {
				componentComboBox.addItem(component.get(RTComponent.PATH_URI));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Failed to opening RT System File");
			e.printStackTrace();
		}
		
		conditionComboBox = new JComboBox();
		conditionComboBox.addItem(RTCCondition.ACTIVE.toString());
		conditionComboBox.addItem(RTCCondition.INACTIVE.toString());
		conditionComboBox.addItem(RTCCondition.ERROR.toString());
		conditionComboBox.addItem(RTCCondition.CREATED.toString());
		conditionComboBox.addItem(RTCCondition.ANY.toString());
		conditionComboBox.addItem(RTCCondition.NONE.toString());
	}

	/**
	 * <div lang="ja">
	 * @param panel
	 * </div>
	 * <div lang="en">
	 * @param panel
	 * </div>
	 */
	@Override
	protected void initPanel(GridLayoutPanel panel) {
		panel.addComponent(0, 0, 0, 0, 10, 1, new JLabel("Select Component and State."));
		panel.addComponent(0, 1, 0, 0, 1, 1, new JLabel("Component"));
		panel.addComponent(1, 1, 10, 0, 9, 1, componentComboBox);
		panel.addComponent(0, 2, 0, 0, 1, 1, new JLabel("State"));
		panel.addComponent(1, 2, 10, 0, 9, 1, conditionComboBox);
	}

	/**
	 * <div lang="ja">
	 * @return
	 * @throws InvalidGuardException
	 * </div>
	 * <div lang="en">
	 * @return
	 * @throws InvalidGuardException
	 * </div>
	 */
	@Override
	public Guard createGuard() throws InvalidGuardException {
		RTCCondition condition = RTCCondition.parseString((String)conditionComboBox.getSelectedItem());
		
		StateEqualsGuard  guard = new StateEqualsGuard(getGuardName(), (String)componentComboBox.getSelectedItem(), condition);
		return guard;
	}

	/**
	 * <div lang="ja">
	 * @param guard
	 * </div>
	 * <div lang="en">
	 * @param guard
	 * </div>
	 */
	@Override
	public void setDefaultSetting(Guard guard) {
		StateEqualsGuard stateEqualsGuard = (StateEqualsGuard)guard;
		
		//delayField.setText(Long.toString(((DelayGuard)guard).getInterval()));
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
		return FIROSOPHY.STATE_EQUALS;
	}
}
