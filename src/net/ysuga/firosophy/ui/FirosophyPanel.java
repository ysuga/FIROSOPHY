package net.ysuga.firosophy.ui;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import net.ysuga.firosophy.FIROSOPHY;
import net.ysuga.statemachine.StateMachine;
import net.ysuga.statemachine.exception.InvalidFSMFileException;
import net.ysuga.statemachine.ui.StateMachinePanel;
import net.ysuga.statemachine.ui.guard.GuardSettingDialogFactoryManager;

/**
 * FirosophyPanel.java
 *
 * @author Yuki Suga (ysuga.net)
 * @date 2011/08/22
 * @copyright 2011, ysuga.net allrights reserved.
 *
 */

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
public class FirosophyPanel extends StateMachinePanel {

	/**
	 * <div lang="ja">
	 * コンストラクタ
	 * </div>
	 * <div lang="en">
	 * Constructor
	 * </div>
	 * @throws ParserConfigurationException 
	 */
	public FirosophyPanel() throws ParserConfigurationException {
		super();
		
		GuardSettingDialogFactoryManager.add(new StateEqualsGuardSettingDialogFactory());
	}
	
	@Override
	public StateMachine createStateMachine(String string) throws ParserConfigurationException {
		return new FIROSOPHY(string);
	}

	@Override 
	public StateMachine createStateMachine(File file) throws InvalidFSMFileException, ParserConfigurationException, SAXException, IOException {
		return new FIROSOPHY(file);
	}
}
