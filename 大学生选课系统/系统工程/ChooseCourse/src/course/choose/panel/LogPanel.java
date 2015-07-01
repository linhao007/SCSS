package course.choose.panel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import course.choose.login.LogRecord;



public class LogPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTextArea jTextArea = null;
	private JButton jButton_del = null;
	private JButton jButton_save = null;
	/**
	 * @param owner
	 * Download:http://down.liehuo.net
	 */
	public LogPanel() {
		initialize();
	}
	private void initialize() {
		this.setBounds(5, 20,480,315);
		jTextArea = new JTextArea();
		jTextArea.setText(setText());//把从txt文本读出来的数据显示到jTextArea里边
		 jTextArea.setEditable(false);
		jTextArea.setLineWrap(true);
		this.setBorder(BorderFactory.createTitledBorder("操作日志"));
		setLayout(null);

		jButton_save = new JButton();
		jButton_save.setBounds(new Rectangle(75, 280, 120, 22));
		jButton_save.setText("保存日志");
		jButton_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				JFileChooser fc= new JFileChooser();
			    FileNameExtensionFilter ft= new FileNameExtensionFilter("*.txt", "txt");
			    fc.addChoosableFileFilter(ft);//文件过滤默认保存为TXT文件，选择所有文件时需加后缀   
			    fc.showSaveDialog(null);//显示保存文件对话框
			    String fileName=fc.getSelectedFile().getAbsolutePath().trim();//获取保存文件的路径及输入的文件名
			    if(fileName!=null)
			    try{			    	
			    	String temp = jTextArea.getText();
			    	byte[] byteBuf = temp.getBytes();
				    FileOutputStream out=new FileOutputStream(fileName+ ".txt"); 
				    out.write(byteBuf); 
				    out.close(); 	    	
			    }
			    catch (IOException a)
			    {
			     System.out.println("保存文件出错!");
			     a.printStackTrace();
			    }				
				JOptionPane.showMessageDialog(null, "日志保存成功！");
			}
		});
		
		jButton_del = new JButton();
		jButton_del.setBounds(new Rectangle(275, 280, 120, 22));
		jButton_del.setText("清空日志");
		jButton_del.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {		
				LogRecord lg = new LogRecord();
				lg.deleteLog();
				JOptionPane.showMessageDialog(null, "日志已被清空！");
				jTextArea.setText(setText());
				jTextArea.repaint();
			}
		});
		
		jScrollPane = new JScrollPane();
		jScrollPane.setBounds(new Rectangle(10, 25, 465, 250));
		jScrollPane.setViewportView(jTextArea);
		add(jScrollPane);
		add(jButton_del);
		add(jButton_save);
	}

	private String setText(){
		LogRecord lg = new LogRecord();
		return lg.readLog();
	}



}  
