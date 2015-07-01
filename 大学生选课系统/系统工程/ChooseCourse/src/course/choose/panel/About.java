package course.choose.panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About extends JDialog implements ActionListener{

	private JPanel jContentPane = null;
	private JButton jButton_ok = null;
	private JLabel jLabel_text1 = null;
	private JLabel jLabel_text2 = null;

	public About() {
		super();
		init();
	}
	private void init() {
		this.setSize(420, 120);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setTitle("关于");
			jLabel_text2 = new JLabel();
			jLabel_text2.setBounds(10, 50, 280, 22);
			jLabel_text2.setText("感谢使用系统，有问题请联系“璇虎宝”开发小组！");
			jLabel_text1 = new JLabel();
			jLabel_text1.setBounds(15, 20, 280, 22);
			jLabel_text1.setText("学生选课系统！");
			
			
			jButton_ok = new JButton("确定");
			jButton_ok.setBounds(320, 45, 70, 22);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			//jContentPane.setBackground(new Color(255, 254, 254));
			jContentPane.add(jLabel_text1, null);
			jContentPane.add(jButton_ok, null);
			jContentPane.add(jLabel_text2, null);
			
			jButton_ok.addActionListener(this);			
			this.setContentPane(jContentPane);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jButton_ok){
			this.dispose();
		}
	}

}

