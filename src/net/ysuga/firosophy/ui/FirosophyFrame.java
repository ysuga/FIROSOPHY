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
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.xml.parsers.ParserConfigurationException;

import net.ysuga.statemachine.StateMachine;
import net.ysuga.statemachine.ui.state.StateSettingDialogFactoryManager;

/**
 * @author ysuga
 *
 */
public class FirosophyFrame extends JFrame {

	private FirosophyPanel firosophyPanel;
	
	static {
		StateSettingDialogFactoryManager.add(new RTStateSettingDialogFactory());
	}
	public FirosophyFrame() {
		super("FIROSOPHY");
		initMenu();
		initToolBar();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				onExit();
			}
		});
		
		try {
			firosophyPanel = new FirosophyPanel();
		} catch (ParserConfigurationException e) {
			// TODO é©ìÆê∂ê¨Ç≥ÇÍÇΩ catch ÉuÉçÉbÉN
			e.printStackTrace();
		}
		getContentPane().add(firosophyPanel, BorderLayout.CENTER);
		this.setExtendedState(MAXIMIZED_BOTH);
		setSize(600,600);
		setVisible(true);
	}
	
	/**
	 * 
	 * <div lang="ja">
	 *
	 * </div>
	 * <div lang="en">
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
		
		//JSeparator separator = new JSeparator();

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
		
	}
	
	public void onExit() {
		System.exit(0);
	}
	
	private void onOpen() {
		
	}
	
	private void onSave() {
		
	}
	
	private void onSaveAs() {
		
	}
	
	private void onAbout() {
		
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
			JButton newButton = new JButton(new AbstractAction("") {
		
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
			JButton openButton = new JButton(new AbstractAction("") {
		
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
			JButton saveButton = new JButton(new AbstractAction("") {
		
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
					onSaveAs();
				}
			});
			saveButton.setIcon(new ImageIcon(sub));
			saveButton.setFocusable(false);
			saveButton.setToolTipText("Save File");
			toolBar.add(saveButton);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,
					"Icon file \"logos.gif\" can not be found.");
			e.printStackTrace();
		}
	}
}
