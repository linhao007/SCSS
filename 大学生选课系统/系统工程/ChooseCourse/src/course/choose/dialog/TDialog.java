package course.choose.dialog;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import course.choose.dao.Dao;
import course.choose.tree.Ttree;


public class TDialog extends JDialog implements ActionListener {
	public JPanel jpanel;
	JLabel label_tNo;
	JLabel label_tName;
	JLabel label_tSex;
	JLabel label_tpassWord;
	public JTextField tNo;
	public JTextField tName;
	public JTextField tpassWord;
	public JRadioButton jRadioButton_Boy;
	public JRadioButton jRadioButton_Girl;
	public ButtonGroup buttonts;
	public String ts = "��";
	public JButton submit;
	public JButton cancel;
	
	String name;
	int flag = 0;
	JDialog jg;

	private Ttree se;
	

	public TDialog(Ttree se) {
		this.se = se;
		init();
	}
	public void init(){
		this.setSize(300, 250);
		this.setModal(true);
		this.setTitle("��ӽ�ʦ");
		this.setLocationRelativeTo(null);
		label_tNo = new JLabel("��ʦ����:");
		label_tNo.setBounds(40, 20, 80, 40);
		label_tName = new JLabel("��ʦ����:");
		label_tName.setBounds(40, 50, 80, 40);
		label_tSex = new JLabel("��ʦ�Ա�:");
		label_tSex.setBounds(40, 80, 80, 40);
		label_tpassWord = new JLabel("��ʦ����:");
		label_tpassWord.setBounds(40, 110, 80, 40);
		tNo = new JTextField();
		tNo.setBounds(100, 30, 140, 22);
		tName = new JTextField();
		tName.setBounds(100, 60, 140,22);
		tpassWord = new JTextField();
		tpassWord.setBounds(100, 120, 140,22);
		
		jRadioButton_Boy = new JRadioButton("��", true);// Ĭ��ѡ����
		jRadioButton_Boy.setBounds(100, 90, 50, 22);
		jRadioButton_Girl = new JRadioButton("Ů", false);
		jRadioButton_Girl.setBounds(160, 90, 50, 22);
		
		jRadioButton_Boy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ts = "��";
			}
		});
		jRadioButton_Girl.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ts = "Ů";
			}
		});	
		gettsButtonGroup();// ���밴ť��
		
		submit = new JButton("�ύ");
		submit.setBounds(50, 165, 60, 25);
		cancel = new JButton("ȡ��");
		cancel.setBounds(170, 165, 60, 25);
		
	
		jpanel = new JPanel();
		jpanel.setLayout(null);
		jpanel.setBorder(BorderFactory.createTitledBorder("������������Ϣ"));
		jpanel.add(label_tNo);
		jpanel.add(label_tName);
		jpanel.add(label_tSex);
		jpanel.add(label_tpassWord);
		jpanel.add(tNo);
		jpanel.add(tName);
		jpanel.add(jRadioButton_Boy);
		jpanel.add(jRadioButton_Girl);
		jpanel.add(tpassWord);
		jpanel.add(submit);
		jpanel.add(cancel);
		submit.addActionListener(this);
		cancel.addActionListener(this);
		this.setContentPane(jpanel);
	}

	
	private void gettsButtonGroup() {// ��ť��
		if (buttonts == null) {
			buttonts = new ButtonGroup();
			buttonts.add(jRadioButton_Boy);
			buttonts.add(jRadioButton_Girl);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			dispose();
		}else if (e.getSource() == submit) {
			boolean flag1 = true; // �ж�����Ĺ����Ƿ�������
			boolean flag2 = false; // �ж�����Ĺ����м��Ƿ��пո�
			boolean flag4 = false; // �ж�����������Ƿ�������
			
			for (int i = 0; i < tNo.getText().length(); i++) {
				char[] ch = tNo.getText().toCharArray();
				if (Character.isDigit(ch[i])) {
					flag1 = false;
				}
			}
			for (int i = 0; i < tName.getText().length(); i++) {
				char[] ch = tName.getText().toCharArray();
				if (Character.isDigit(ch[i])) {
					flag4 = true;
				}
			}
			
			for (int i = 0; i < tNo.getText().length(); i++) {
				if (tNo.getText().charAt(i) == ' ')
					flag2 = true;
			}
			
			// �ж�����Ĺ����Ƿ��ظ�
			boolean flag3 = false;
			String str = "select tno from tb_Teacher";
			ResultSet rs = Dao.executeQuery(str);
			try {
				while(rs.next()) {
					if(rs.getString("tno").equals(tNo.getText())){
						flag3 = true;
					}		
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if (tNo.getText().trim().length() < 1
					|| tName.getText().trim().length() < 1
					|| tpassWord.getText().trim().length() < 1) {
				JOptionPane.showMessageDialog(null, "����ȫ��Ϣ");
			}else if(flag1||flag2||tNo.getText().length() != 4){
				JOptionPane.showMessageDialog(null, "����д��ȷ�Ĺ��ţ��磺1111");
				tNo.setText("");
			}else if (flag3) {
				JOptionPane.showMessageDialog(null, "�Ѵ���"
						+ tNo.getText() + "���ţ�");
				tNo.setText("");
			}else if(flag4){
				JOptionPane.showMessageDialog(null, "��������ȷ��������������");
				tName.setText("");
			}else{			
			int No = Integer.parseInt(tNo.getText());
			name = tName.getText();
			int pwd = Integer.parseInt(tpassWord.getText());	
			String sql = "insert into tb_Teacher values(" + No + "," + "'"
					+ name + "'," + "'" + ts + "',"
					+ pwd + ")";
			Dao.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "���"+name+"�ɹ���");
			se.update(name);
			this.dispose();
			}
		}
	}
}
