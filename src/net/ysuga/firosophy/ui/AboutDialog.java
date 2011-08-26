package net.ysuga.firosophy.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class OKAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public OKAction(String arg0) {
			super(arg0);
		}

		public void actionPerformed(ActionEvent arg0) {
			okOperation();
		}
		
	}
	
	void okOperation() {
		setVisible(false);
	}
	
	public AboutDialog(JFrame arg0) {
		super(arg0, true);
		setSize(340, 340);
		
		setTitle("About This Software");
		int x = arg0.getX()+arg0.getWidth()/2-160;
		int y = arg0.getY()+arg0.getHeight()/2-120;
		setLocation(x, y);
		
		setLayout(new FlowLayout());
		this.getContentPane().add(new JLabel(new ImageIcon("ysuga.jpg")));
		JButton okButton = new JButton(new OKAction("OK"));
		getContentPane().add(okButton);
	}
	
	public void doModal() {
		setModal(true);
		setVisible(true);
	}
}
