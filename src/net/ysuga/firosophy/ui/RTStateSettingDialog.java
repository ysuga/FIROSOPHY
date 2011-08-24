/**
 * RTStateSettingDialog.java
 *
 * @author Yuki Suga (ysuga.net)
 * @date 2011/08/23
 * @copyright 2011, ysuga.net allrights reserved.
 *
 */
package net.ysuga.firosophy.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.ysuga.firosophy.state.RTState;
import net.ysuga.statemachine.state.State;
import net.ysuga.statemachine.ui.shape.GridLayoutPanel;
import net.ysuga.statemachine.ui.state.DefaultStateSettingDialog;

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
public class RTStateSettingDialog extends DefaultStateSettingDialog {

	private JTextField systemFileField;
	private JButton findButton;
	private JButton editButton;
	
	public RTStateSettingDialog() throws Exception {
		super(null);
	}
	
	/**
	 * <div lang="ja">
	 * コンストラクタ
	 * @param state
	 * </div>
	 * <div lang="en">
	 * Constructor
	 * @param state
	 * </div>
	 */
	public RTStateSettingDialog(State state) {
		super(state);
		systemFileField = new JTextField("");
		findButton = new JButton("...");
		editButton = new JButton("Edit");
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
	public State createState() {
		RTState state =  new RTState(getStateName());
		return state;
	}

	/**
	 * <div lang="ja">
	 * </div>
	 * <div lang="en">
	 * </div>
	 */
	@Override
	protected void initParameterPanel(GridLayoutPanel parameterPanel) {
		// TODO 自動生成されたメソッド・スタブ
		//super.initParameterPanel();
		///parameterPanel.setPreferredSize(new Dimension(400, 40));
		parameterPanel.addComponent(0, 0, 1, 1, new JLabel("RTSystem"));
		parameterPanel.addComponent(GridBagConstraints.RELATIVE, 0, 10, 0, 7, 1, systemFileField);
		parameterPanel.addComponent(GridBagConstraints.RELATIVE, 0, 0, 0, 1, 1, findButton);
		parameterPanel.addComponent(GridBagConstraints.RELATIVE, 0, 0, 0, 1, 1, editButton);
		//parameterPanel.pack();
		
	}
	
	

}
