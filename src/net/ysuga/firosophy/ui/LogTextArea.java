package net.ysuga.firosophy.ui;

import java.awt.BorderLayout;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class LogTextArea extends JPanel {
	JScrollPane scrollPane;
	OutputStream os;
	JTextArea textArea;
	Logger logger;
	
	public LogTextArea(String loggerName) {
		super();
		logger = Logger.getLogger(loggerName);
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		os = new JTextAreaOutputStream(textArea, "Shift_JIS");
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, scrollPane);
		
		logger.addHandler(new AutoFlushStreamHandler(new PrintStream(os, true), new SimpleFormatter()));
	}

	
	public class AutoFlushStreamHandler extends StreamHandler {

		public AutoFlushStreamHandler() {
			super();
		}

		public AutoFlushStreamHandler(OutputStream arg0, Formatter arg1) {
			super(arg0, arg1);
		}

		/* (non-Javadoc)
		 * @see java.util.logging.StreamHandler#publish(java.util.logging.LogRecord)
		 */
		@Override
		public synchronized void publish(LogRecord arg0) {
			super.publish(arg0);
			super.flush();
		}
	}
	
	
	public class JTextAreaOutputStream extends OutputStream {
		private ByteArrayOutputStream os;
		private JTextArea textArea;
		private String encode;

		public JTextAreaOutputStream(JTextArea textArea, String encode) {
			super();
			this.os = new ByteArrayOutputStream();
			this.textArea = textArea;
			this.encode = encode;
		}
		
		public void write(int arg) throws IOException {
			this.os.write(arg);
		}
	
		
		@Override
		public void flush() throws IOException {
			// 文字列のエンコード
			final String str = new String(this.os.toByteArray(), this.encode);
			
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					JTextAreaOutputStream.this.textArea.append(str);
				}
			});
			// 書き出した内容はクリアする
			this.os.reset();
		}
	}

}
