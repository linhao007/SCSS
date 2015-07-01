package course.choose.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import course.choose.dao.Dao;
import course.choose.user.Operator;
import course.choose.user.Person;
import course.choose.user.Student;
import course.choose.user.Teacher;


public class ModifyPanel extends JPanel implements ActionListener {
	public JLabel LPersonPwd1;
	public JLabel LPersonPwd2;
	private Person person;
	JButton jButton_Ok;
	JButton jButton_Cancel;
	JTextField Personpwd1;
	JTextField Personpwd2;
	public ModifyPanel(Person person) {
		this.person = person;
		LPersonPwd1 = new JLabel("  ������������:");
		Personpwd1 = new JPasswordField();
		LPersonPwd2 = new JLabel("���ٴ���������:");
		Personpwd2 = new JPasswordField();
	    jButton_Ok = new JButton("ȷ��");
		jButton_Cancel = new JButton("ȡ��");
		setLayout(null);
		LPersonPwd1.setBounds(100, 10, 90, 40);
		Personpwd1.setBounds(190, 20, 140, 22);
		LPersonPwd2.setBounds(100, 40, 90, 40);
		Personpwd2.setBounds(190, 50, 140, 22);
		jButton_Ok.setBounds(120, 90, 60, 22);
		jButton_Cancel.setBounds(220, 90, 60, 22);
		add(LPersonPwd1);
		add(Personpwd1);
		add(LPersonPwd2);
		add(Personpwd2);
		add(jButton_Ok);
		add(jButton_Cancel);
		jButton_Ok.addActionListener(this);
		jButton_Cancel.addActionListener(this);
		this.setBorder(BorderFactory.createTitledBorder("�޸�����"));
		this.setSize(490, 380);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jButton_Ok) {
			CheckPassword();
		}else if(e.getSource() == jButton_Cancel) {
			this.setVisible(false);
		}
	}
	
	
	/*
	 * ��ʾ������Ϣ
	 */
	public void Tip() {
		JOptionPane.showMessageDialog(null, "�����������벻һ��");
		Personpwd1.setText("");
		Personpwd2.setText("");		
	}
	/*����������룬�Ƿ���Ч�����޸�����
	 * 
	 */
	public void CheckPassword() {
		
		String SPersonPwd1 = Personpwd1.getText();
		String SPersonPwd2 = Personpwd2.getText();
		//ѧ���޸�ģ��
		if(person instanceof Student) {
			Student st = (Student) person;			
			if(SPersonPwd1.equals(SPersonPwd2)) {
				String SPersonNo =st.getNo();
				String sql = "update tb_Student set password = '"+SPersonPwd1+"' where sno = '"+SPersonNo+"'";
				Dao.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, SPersonNo+"�޸�����ɹ�");
				this.setVisible(false);
			}else {
				Tip();
			}
		}else if(person instanceof Teacher) {
			//��ʦ�޸�ģ��
			Teacher tea = (Teacher) person;			
			if(SPersonPwd1.equals(SPersonPwd2)) {
				String SPersonNo =tea.getNo();
				String sql = "update tb_Teacher set password = '"+SPersonPwd1+"' where tno = '"+SPersonNo+"'";
				Dao.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, SPersonNo+"�޸�����ɹ�");
				this.setVisible(false);
			}else {
				Tip();
			}
		}else if(person instanceof Operator) {
			//����Ա�޸�ģ��
			Operator ope = (Operator) person;			
			if(SPersonPwd1.equals(SPersonPwd2)) {
				String SPersonNo =ope.getNo();
				String sql = "update tb_Manager set password = '"+SPersonPwd1+"' where managerno = '"+SPersonNo+"'";
				Dao.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, SPersonNo+"�޸�����ɹ�");
				this.setVisible(false);
			}else {
				Tip();
			}
		}
	}


}
