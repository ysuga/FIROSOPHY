/**
 * MainFrame.java
 *
 * @author Yuki Suga (ysuga.net)
 * @date 2011/08/05
 * @copyright 2011, ysuga.net allrights reserved.
 *
 */
package net.ysuga.firosophy.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.xml.parsers.ParserConfigurationException;

import net.ysuga.firosophy.FIROSOPHY;
import net.ysuga.statemachine.StateMachineTagNames;
import net.ysuga.statemachine.exception.InvalidStateNameException;
import net.ysuga.statemachine.state.ExitState;
import net.ysuga.statemachine.state.StartState;
import net.ysuga.statemachine.state.State;
import net.ysuga.statemachine.ui.StateMachinePanel;
import net.ysuga.statemachine.ui.state.AbstractStateSettingDialog;
import net.ysuga.statemachine.ui.state.StateSettingDialogFactory;
import net.ysuga.statemachine.ui.state.StateSettingDialogFactoryManager;

/**
 * @author ysuga
 * 
 */
public class FirosophyFrame extends JFrame {

	private StateMachinePanel firosophyPanel;

	private JSplitPane mainPane;

	private JScrollPane firosophyScrollPane;
	private LogTextArea debugPanel;

	private JButton newButton;

	private JButton openButton;

	private JButton saveButton;

	private JButton addStartButton;

	private JButton addExitButton;

	private JButton addStateButton;

	private JButton addTransitionButton;

	private JButton startButton;

	private JButton suspendButton;

	private JButton stopButton;

	static {
		StateSettingDialogFactoryManager.add(new RTStateSettingDialogFactory());
	}

	public FirosophyFrame() {
		super("FIROSOPHY");
		initMenu();
		initToolBar();
		mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				onExit();
			}
		});

		try {
			firosophyPanel = new FirosophyPanel();
			firosophyPanel.setSize(800, 800);
			firosophyPanel.setPreferredSize(new Dimension(800, 800));
			firosophyScrollPane = new JScrollPane(firosophyPanel);
			firosophyScrollPane.setPreferredSize(new Dimension(400, 400));
		} catch (ParserConfigurationException e) {
			// TODO é©ìÆê∂ê¨Ç≥ÇÍÇΩ catch ÉuÉçÉbÉN
			e.printStackTrace();
		}
		debugPanel = new LogTextArea("net.ysuga");
		// firosophyScrollPane.add(firosophyPanel);
		getContentPane().add(mainPane, BorderLayout.CENTER);
		mainPane.add(firosophyScrollPane);
		mainPane.add(debugPanel);
		this.setExtendedState(MAXIMIZED_BOTH);
		setSize(600, 600);
		setVisible(true);
	}

	/**
	 * 
	 * <div lang="ja">
	 * 
	 * </div> <div lang="en">
	 * 
	 * </div>
	 */
	public void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		fileMenu.add(new JMenuItem(new AbstractAction("New") {
			public void actionPerformed(ActionEvent arg0) {
				onNew();
			}
		}));

		fileMenu.add(new JMenuItem(new AbstractAction("Open") {
			public void actionPerformed(ActionEvent arg0) {
				onOpen();
			}
		}));

		fileMenu.add(new JMenuItem(new AbstractAction("Save") {
			public void actionPerformed(ActionEvent arg0) {
				onSave();
			}
		}));

		fileMenu.add(new JMenuItem(new AbstractAction("Save As") {
			public void actionPerformed(ActionEvent arg0) {
				onSaveAs();
			}
		}));

		fileMenu.add(new JSeparator());

		fileMenu.add(new JMenuItem(new AbstractAction("Exit") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				onExit();
			}
		}));

		JMenu viewMenu = new JMenu("View");
		menuBar.add(viewMenu);

		viewMenu.add(new JMenuItem(new AbstractAction("Repaint") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				repaint();
				System.gc();
			}
		}));

		// JSeparator separator = new JSeparator();

		JMenu windowMenu = new JMenu("Window");
		menuBar.add(windowMenu);

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem aboutThisSoftMenuItem = new JMenuItem(new AbstractAction(
				"About this soft") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				onAbout();
			}
		});
		helpMenu.add(aboutThisSoftMenuItem);
		setJMenuBar(menuBar);
	}

	private void onNew() {
		// firosophyPanel.
	}

	public void onExit() {
		if (JOptionPane.showConfirmDialog(this,
				"Do you want to quit FIROSOPHY?") == JOptionPane.YES_OPTION) {

			System.exit(0);
		}
	}

	private void onOpen() {
		firosophyPanel.showOpenFileDialog();
	}

	private void onSave() {
		firosophyPanel.save();
	}

	private void onSaveAs() {
		firosophyPanel.showOpenFileDialog();
	}

	private void onAbout() {
		AboutDialog dialog = new AboutDialog(this);
		dialog.doModal();
	}

	private void onAddStart() {
		if (firosophyPanel.getStateMachine().getState(
				StateMachineTagNames.START) != null) {
			JOptionPane.showMessageDialog(this,
					"State Machine can not have two start point");
			return;
		}

		State state = new StartState();
		state.setLocation(new Point(100, 100));
		try {
			firosophyPanel.getStateMachine().add(state);
		} catch (InvalidStateNameException e) {
			JOptionPane.showMessageDialog(this, "Failed to add State");
			e.printStackTrace();
		}
		repaint();
	}

	private void onAddExit() {
		if (firosophyPanel.getStateMachine()
				.getState(StateMachineTagNames.EXIT) != null) {
			JOptionPane.showMessageDialog(this,
					"State Machine can not have two exit point");
			return;
		}

		State state = new ExitState();
		state.setLocation(new Point(100, 100));
		try {
			firosophyPanel.getStateMachine().add(state);
		} catch (InvalidStateNameException e) {
			JOptionPane.showMessageDialog(this, "Failed to add State");
			e.printStackTrace();
		}
		repaint();
	}

	private void onAddState() {
		StateSettingDialogFactory factory = StateSettingDialogFactoryManager
				.getInstance().get(FIROSOPHY.RTSTATE);
		AbstractStateSettingDialog dialog = factory.createStateSettingDialog(
				firosophyPanel, null);
		if (dialog.doModal() == AbstractStateSettingDialog.OK_OPTION) {
			State state = dialog.buildState();
			try {
				state.setLocation(new Point(100, 100));
				firosophyPanel.getStateMachine().add(state);
				firosophyPanel.repaint();
			} catch (InvalidStateNameException e) {
				JOptionPane.showMessageDialog(null,
						(Object) "Invalid State Name", "Exception",
						JOptionPane.OK_OPTION);
			}
		}
		repaint();
	}

	private void onAddTransition() {
		if (firosophyPanel.getSelectedState() == null) {
			JOptionPane.showMessageDialog(this,
					"To add transition, Select one state");
			return;
		}

		firosophyPanel.setEditMode(StateMachinePanel.EDIT_TRANSITION);
	}

	private void onStart() {
		firosophyPanel.start();
	}

	private void onStop() {
		firosophyPanel.stop();
	}

	private void onSuspendResume() {
		if (firosophyPanel.isSuspend()) {
			firosophyPanel.resume();
		} else {
			firosophyPanel.suspend();
		}
	}

	public void initToolBar() {
		try {
			JToolBar toolBar = new JToolBar();
			toolBar.setRollover(true);
			getContentPane().add(BorderLayout.NORTH, toolBar);
			BufferedImage image = ImageIO.read(new File("logos.gif"));
			BufferedImage sub = image.getSubimage(0, 0, 16, 16);
			setIconImage(sub);
			sub = image.getSubimage(16, 0, 16, 16);
			newButton = new JButton(new AbstractAction("") {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					onNew();
				}
			});
			newButton.setIcon(new ImageIcon(sub));
			newButton.setFocusable(false);
			newButton.setToolTipText("New File");
			toolBar.add(newButton);
			sub = image.getSubimage(32, 0, 16, 16);
			openButton = new JButton(new AbstractAction("") {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					onOpen();
				}
			});
			openButton.setIcon(new ImageIcon(sub));
			openButton.setFocusable(false);
			openButton.setToolTipText("Open File");
			toolBar.add(openButton);
			sub = image.getSubimage(48, 0, 16, 16);
			saveButton = new JButton(new AbstractAction("") {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					onSave();
				}
			});
			saveButton.setIcon(new ImageIcon(sub));
			saveButton.setFocusable(false);
			saveButton.setToolTipText("Save File");
			toolBar.add(saveButton);

			toolBar.addSeparator();

			sub = image.getSubimage(64, 0, 16, 16);
			addStartButton = new JButton(new AbstractAction("") {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					onAddStart();
				}
			});
			addStartButton.setIcon(new ImageIcon(sub));
			addStartButton.setFocusable(false);
			addStartButton.setToolTipText("Add Start");
			toolBar.add(addStartButton);

			sub = image.getSubimage(80, 0, 16, 16);
			addExitButton = new JButton(new AbstractAction("") {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					onAddExit();
				}
			});
			addExitButton.setIcon(new ImageIcon(sub));
			addExitButton.setFocusable(false);
			addExitButton.setToolTipText("Add Exit");
			toolBar.add(addExitButton);

			sub = image.getSubimage(96, 0, 16, 16);
			addStateButton = new JButton(new AbstractAction("") {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					onAddState();
				}
			});
			addStateButton.setIcon(new ImageIcon(sub));
			addStateButton.setFocusable(false);
			addStateButton.setToolTipText("Add State");
			toolBar.add(addStateButton);

			sub = image.getSubimage(112, 0, 16, 16);
			addTransitionButton = new JButton(new AbstractAction("") {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					onAddTransition();
				}
			});
			addTransitionButton.setIcon(new ImageIcon(sub));
			addTransitionButton.setFocusable(false);
			addTransitionButton.setToolTipText("Add Transition");
			toolBar.add(addTransitionButton);

			toolBar.addSeparator();

			sub = image.getSubimage(128, 0, 16, 16);
			startButton = new JButton(new AbstractAction("") {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					onStart();
				}
			});
			startButton.setIcon(new ImageIcon(sub));
			startButton.setFocusable(false);
			startButton.setToolTipText("Start State Machine");
			toolBar.add(startButton);

			sub = image.getSubimage(144, 0, 16, 16);
			suspendButton = new JButton(new AbstractAction("") {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					onSuspendResume();
				}
			});
			suspendButton.setIcon(new ImageIcon(sub));
			suspendButton.setFocusable(false);
			suspendButton.setToolTipText("Suspend State Machine");
			toolBar.add(suspendButton);

			sub = image.getSubimage(160, 0, 16, 16);
			stopButton = new JButton(new AbstractAction("") {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					onStop();
				}
			});
			stopButton.setIcon(new ImageIcon(sub));
			stopButton.setFocusable(false);
			stopButton.setToolTipText("Stop State Machine");
			toolBar.add(stopButton);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,
					"Icon file \"logos.gif\" can not be found.");
			e.printStackTrace();
		}
	}
}
