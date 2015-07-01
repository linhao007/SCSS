package course.choose.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import course.choose.dao.Dao;
import course.choose.tree.Stree;

/**
 * ���ѧ�������Ϣ���
 */
public class SDialog extends JDialog implements ActionListener {
	public JPanel jpanel;
	JLabel label_sNo;
	JLabel label_sName;
	JLabel label_sSex;
	JLabel label_sSpeClass;
	JLabel label_spassWord;
	public JTextField sNo;
	public JTextField sName;
	public JTextField passWord;
	public JRadioButton jRadioButton_Boy;
	public JRadioButton jRadioButton_Girl;
	public ButtonGroup buttonts;
	public String ts = "��";
	public JTextField speClass;
	public JButton submit;
	public JButton cancel;
	public String name;
	private Stree se;
	
	String str = null;
	
	public SDialog(Stree se,String str) {
		this.se = se;
		this.str = str;
		init();
	}

	public void init() {
		this.setSize(300, 250);
		this.setModal(true);
		this.setTitle("���ѧ��");
		this.setLocationRelativeTo(null);
		label_sNo = new JLabel("ѧ��ѧ��:");
		label_sNo.setBounds(40, 10, 80, 40);
		label_sName = new JLabel("ѧ������:");
		label_sName.setBounds(40, 40, 80, 40);
		label_sSex = new JLabel("ѧ���Ա�:");
		label_sSex.setBounds(40, 70, 80, 40);
		label_sSpeClass = new JLabel("רҵ�༶:");
		label_sSpeClass.setBounds(40, 100, 80, 40);
		label_spassWord = new JLabel("ѧ������:");
		label_spassWord.setBounds(40, 130, 80, 40);
		sNo = new JTextField();
		sNo.setBounds(100, 20, 140, 22);
		sName = new JTextField();
		sName.setBounds(100, 50, 140,22);
		passWord = new JTextField();
		passWord.setBounds(100, 140, 140,22);
		
		jRadioButton_Boy = new JRadioButton("��", true);// Ĭ��ѡ����
		jRadioButton_Boy.setBounds(100, 80, 50, 22);
		jRadioButton_Girl = new JRadioButton("Ů", false);
		jRadioButton_Girl.setBounds(160, 80, 50, 22);
		
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
		
		
		speClass = new JTextField();
		speClass.setBounds(100, 110, 140,22);
		speClass.setEditable(false);
		speClass.setText(str);
		submit = new JButton("�ύ");
		submit.setBounds(50, 175, 60, 25);
		cancel = new JButton("ȡ��");
		cancel.setBounds(170, 175, 60, 25);
		
	
		jpanel = new JPanel();
		jpanel.setLayout(null);
		jpanel.setBorder(BorderFactory.createTitledBorder("������������Ϣ"));
		jpanel.add(label_sNo);
		jpanel.add(label_sName);
		jpanel.add(label_sSex);
		jpanel.add(label_sSpeClass);
		jpanel.add(label_spassWord);
		jpanel.add(sNo);
		jpanel.add(sName);
		jpanel.add(jRadioButton_Boy);
		jpanel.add(jRadioButton_Girl);
		jpanel.add(speClass);
		jpanel.add(passWord);
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
			boolean flag1 = true; // �ж������ѧ���Ƿ�������
			boolean flag2 = false; // �ж������ѧ���м��Ƿ��пո�
			boolean flag4 = false; // �ж�����������Ƿ�������
			
			for (int i = 0; i < sNo.getText().length(); i++) {
				char[] ch = sNo.getText().toCharArray();
				if (Character.isDigit(ch[i])) {
					flag1 = false;
				}
			}
			for (int i = 0; i < sName.getText().length(); i++) {
				char[] ch = sName.getText().toCharArray();
				if (Character.isDigit(ch[i])) {
					flag4 = true;
				}
			}
			
			for (int i = 0; i < sNo.getText().length(); i++) {
				if (sNo.getText().charAt(i) == ' ')
					flag2 = true;
			}
			
			// �ж������ѧ���Ƿ��ظ�
			boolean flag3 = false;
			String str = "select sno from tb_student";
			ResultSet rs = Dao.executeQuery(str);
			try {
				while(rs.next()) {
					if(rs.getString("sno").equals(sNo.getText())){
						flag3 = true;
					}		
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if (sNo.getText().trim().length() < 1
					|| sName.getText().trim().length() < 1
					|| passWord.getText().trim().length() < 1) {
				JOptionPane.showMessageDialog(null, "����ȫ��Ϣ");
			}else if(flag1||flag2||sNo.getText().length() != 8){
				JOptionPane.showMessageDialog(null, "����д��ȷ��ѧ�ţ��磺20121521");
				sNo.setText("");
			}else if (flag3) {
				JOptionPane.showMessageDialog(null, "�Ѵ���"
						+ sNo.getText() + "ѧ�ţ�");
				sNo.setText("");
			}else if(flag4){
				JOptionPane.showMessageDialog(null, "��������ȷ��������������");
				sName.setText("");
			}else{			
			int No = Integer.parseInt(sNo.getText());
			int speclassno = Integer.parseInt(speClass.getText());
			name = sName.getText();
			int pwd = Integer.parseInt(passWord.getText());	
			String sql = "insert into tb_Student values(" + No + "," + "'"
					+ name + "'," + "'" + ts + "',"
					+ pwd + "," + speclassno + ")";
			Dao.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "���"+name+"�ɹ���");
			se.update(name);
			this.dispose();
			}
		}
	}
}
