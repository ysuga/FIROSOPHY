/**
 * RTStateSettingDialog.java
 *
 * @author Yuki Suga (ysuga.net)
 * @date 2011/08/23
 * @copyright 2011, ysuga.net allrights reserved.
 *
 */
package net.ysuga.firosophy.ui;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.ysuga.firosophy.state.RTState;
import net.ysuga.firosophy.state.action.AllRTCsActivateAction;
import net.ysuga.firosophy.state.action.AllRTCsConfigureAction;
import net.ysuga.firosophy.state.action.AllRTCsDeactivateAction;
import net.ysuga.firosophy.state.action.AllRTCsResetAction;
import net.ysuga.firosophy.state.action.BuildConnectionAction;
import net.ysuga.firosophy.state.action.ClearAllConnectionAction;
import net.ysuga.statemachine.state.State;
import net.ysuga.statemachine.state.action.StateActionList;
import net.ysuga.statemachine.ui.StateMachinePanel;
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
	
	private RTState rtState;
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
	public RTStateSettingDialog(StateMachinePanel panel, State state) {
		super(panel, state);
		systemFileField = new JTextField("");
		
		if(state != null && state instanceof RTState) {
			rtState = (RTState)state;
			systemFileField.setText(rtState.getFileName());
		} else {
			StateActionList onEntryActionList = getOnEntryStateActionList();
			onEntryActionList.add(new AllRTCsResetAction());
			onEntryActionList.add(new ClearAllConnectionAction());
			onEntryActionList.add(new AllRTCsConfigureAction());
			onEntryActionList.add(new BuildConnectionAction());
			onEntryActionList.add(new AllRTCsActivateAction());

			StateActionList onExitActionList = getOnExitStateActionList();
			onExitActionList.add(new AllRTCsDeactivateAction());
			onExitActionList.add(new AllRTCsResetAction());
			onExitActionList.add(new ClearAllConnectionAction());
		}
		findButton = new JButton(new AbstractAction("...") {
			public void actionPerformed(ActionEvent e) {
				onOpenFile();
			}
		});
		editButton = new JButton(new AbstractAction("Edit") {
			public void actionPerformed(ActionEvent e) {
				onEditSystem();
			}
		});
	}

	class XMLFileFilter extends javax.swing.filechooser.FileFilter {
		public boolean accept(java.io.File f) {
			if (f.isDirectory())
				return true;
			String name = f.getName().toLowerCase();
			if (name.endsWith("xml"))
				return true;
			return false;
		}

		public java.lang.String getDescription() {
			return "*." + "xml";
		}
	}
	
	public final File showSaveFileDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setDialogTitle("Select File");
		fileChooser.setFileFilter(new XMLFileFilter());
		if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		File file = fileChooser.getSelectedFile();
		if (!file.getName().endsWith("xml")) {
			file = new File(file.getAbsolutePath() + "." + "xml");
		}

		if (file.exists()) {
			return file;
		} else {
			return null;
		}
	}
	
	
	private void onOpenFile() {
		File file = this.showSaveFileDialog();
		if(file != null) {
			this.systemFileField.setText(file.getAbsolutePath());
			String[] tokens = file.getName().split(".xml");
			super.setStateNameField(tokens[0]);
		}
	}
	
	private void onEditSystem() {
		
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
		state.setFileName(systemFileField.getText());
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
