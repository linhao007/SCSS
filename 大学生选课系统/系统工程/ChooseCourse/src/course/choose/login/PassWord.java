package course.choose.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import course.choose.dao.Dao;
import course.choose.frame.Administrator;
import course.choose.frame.StudentFrame;
import course.choose.frame.TeacherFrame;
import course.choose.panel.ImagePanel;
import course.choose.user.Operator;
import course.choose.user.Person;
import course.choose.user.Student;
import course.choose.user.Teacher;

public class PassWord extends Dao implements ActionListener {
	JTextField user, checktflogin;
	JPasswordField passWd;

	JLabel userName, userPassword, checkno, randomno;
	public static String userNames;
	JButton signIn, reset;
	Container contentPane;
	SpringLayout layout1, layout2;
	random randomphoto;
	JFrame flogin;
	JPanel panel;
	Person person;

	public PassWord() {

		flogin = new JFrame();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 300));
		panel.setOpaque(false);// ����͸��
		flogin.setResizable(false);
		randomphoto = new random();
		ImageIcon titleIcon = new ImageIcon(getClass().getResource(
				"/image/1.jpg"));// ��ȡͼƬ
		randomno = new JLabel(new ImageIcon(randomphoto.creatImage()));
		ImagePanel jDialogImage = new ImagePanel("/image/2.jpg");

		flogin.setContentPane(jDialogImage);// ���ñ���ͼƬ
		jDialogImage.setBounds(0, 0, flogin.getWidth(), flogin.getHeight());
		flogin.setIconImage(titleIcon.getImage());// ����ͼ��

		initframe();

		contentPane = flogin.getContentPane();
		layout1 = new SpringLayout();
		layout2 = new SpringLayout();
		add();
		contentPane.setLayout(layout1);
		signIn.addActionListener(this);
		reset.addActionListener(this);
		flogin.setBounds(400, 120, 400, 300);
		flogin.getRootPane().setDefaultButton(signIn);// ������Ӧ�س���
		flogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		flogin.setVisible(true);
		contentPane.add(panel);
	}

	/*
	 * ��ʼ�����ڱ���
	 */
	public void initframe() {
		flogin.setTitle("��ӭ��������ũҵ��ѧ");
		userName = new JLabel("�û���:");
		userName.setForeground(Color.white);
		userName.setFont(new Font("����", Font.BOLD, 15));
		userPassword = new JLabel("��  ��:");
		userPassword.setForeground(Color.white);
		userPassword.setFont(new Font("����", Font.BOLD, 15));
		passWd = new JPasswordField(12);
		user = new JTextField(12);
		signIn = new JButton("��¼");
		signIn.setFont(new Font("����", Font.BOLD, 15));
		signIn.setBackground(new Color(10, 97, 190));

		signIn.setForeground(Color.white);
		reset = new JButton("����");
		reset.setBackground(Color.blue);
		reset.setForeground(Color.white);
		reset.setFont(new Font("����", Font.BOLD, 15));
		reset.setBackground(new Color(10, 97, 190));
		checkno = new JLabel("��֤��:");
		checkno.setForeground(Color.white);
		checkno.setFont(new Font("����", Font.BOLD, 15));
		checktflogin = new JTextField(12);
	}

	/*
	 * panel ���������Լ���������岼��
	 */
	public void add() {
		panel.add(userName);
		panel.add(userPassword);
		panel.add(user);
		panel.add(passWd);
		panel.add(signIn);
		panel.add(reset);
		panel.add(checkno);
		panel.add(checktflogin);
		panel.add(randomno);
		panel.setLayout(layout2);
		layout1.putConstraint(SpringLayout.NORTH, panel, 80,
				SpringLayout.NORTH, flogin);
		layout1.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST,
				flogin);
		layout2.putConstraint(SpringLayout.NORTH, userName, 5,
				SpringLayout.NORTH, panel);
		layout2.putConstraint(SpringLayout.WEST, userName, 70,
				SpringLayout.WEST, panel);
		layout2.putConstraint(SpringLayout.NORTH, userPassword, 50,
				SpringLayout.NORTH, panel);
		layout2.putConstraint(SpringLayout.WEST, userPassword, 70,
				SpringLayout.WEST, panel);
		layout2.putConstraint(SpringLayout.NORTH, user, 5, SpringLayout.NORTH,
				panel);
		layout2.putConstraint(SpringLayout.WEST, user, 140, SpringLayout.WEST,
				panel);
		layout2.putConstraint(SpringLayout.NORTH, passWd, 50,
				SpringLayout.NORTH, panel);
		layout2.putConstraint(SpringLayout.WEST, passWd, 140,
				SpringLayout.WEST, panel);

		layout2.putConstraint(SpringLayout.NORTH, checkno, 95,
				SpringLayout.NORTH, panel);
		layout2.putConstraint(SpringLayout.WEST, checkno, 70,
				SpringLayout.WEST, panel);
		layout2.putConstraint(SpringLayout.NORTH, checktflogin, 95,
				SpringLayout.NORTH, panel);
		layout2.putConstraint(SpringLayout.WEST, checktflogin, 140,
				SpringLayout.WEST, panel);
		layout2.putConstraint(SpringLayout.NORTH, randomno, 98,
				SpringLayout.NORTH, panel);
		layout2.putConstraint(SpringLayout.WEST, randomno, 290,
				SpringLayout.WEST, panel);

		layout2.putConstraint(SpringLayout.NORTH, signIn, 140,
				SpringLayout.NORTH, panel);
		layout2.putConstraint(SpringLayout.WEST, signIn, 70, SpringLayout.WEST,
				panel);
		layout2.putConstraint(SpringLayout.NORTH, reset, 140,
				SpringLayout.NORTH, panel);
		layout2.putConstraint(SpringLayout.WEST, reset, 160, SpringLayout.WEST,
				panel);

	}

	/*
	 * ����ı��������ݲ�����������֤��
	 */
	public void Tip() {
		user.setText("");
		passWd.setText("");
		checktflogin.setText("");
		randomno.setText("");
		randomno.setIcon(new ImageIcon(randomphoto.update()));
		flogin.setVisible(true);
	}
	/*
	 * û����ݣ���ʾ������Ϣ
	 */
	public void noAuthority() {
		JOptionPane.showConfirmDialog(null,"�ף�û��ݡ���ô����", "��¼ʧ��",JOptionPane.CLOSED_OPTION);//(null, "�ף�û��ݡ���ô����");
		Tip();
	}
	/*
	 * ��֤�����������ʾ������Ϣ
	 */
	public void identifyError() {
		JOptionPane.showMessageDialog(null, "�ף���֤���������!");
		Tip();
	}
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		String name = user.getText();
		String passWord = passWd.getText();

		if (cmd.equals("��¼")) {
			if(name.length()!=4&&name.length()!=5&&name.length()!=8) {
				JOptionPane.showMessageDialog(null, "�ף��û������ȳ���!");
				Tip();
			}else {
				person = Dao.check(name, passWord);
				// ͨ���жϸ��˵�������½���ͬ����
				// ����Ա
				if (person instanceof Operator) {
					Operator op = (Operator) person;
					// System.out.println(p.name);
				  if(op.getName() != null) {//�жϸ��û��Ƿ���Ч
					if (checktflogin.getText().equals(randomphoto.getSRand())) {
						flogin.dispose();
						Administrator ad = new Administrator(op);
						ad.setVisible(true);
						userNames = op.getName();
					} else {
						identifyError();
					}
				  }else {
					  noAuthority();				 
				  }
				} else if (person instanceof Student) {
					// ѧ��
					Student st = (Student) person;
					if(st.getName() != null) {//�жϸ��û��Ƿ���Ч
					if (checktflogin.getText().equals(randomphoto.getSRand())) {
						flogin.dispose();
						StudentFrame se = new StudentFrame(st);
						se.setVisible(true);
						userNames = st.getName();
					} else {
						identifyError();
					}
					}else {
						noAuthority();				
					}
				} else if (person instanceof Teacher) {
					// ��ʦ
					Teacher tea = (Teacher) person;
					if(tea.getName() != null) {//�жϸ��û��Ƿ���Ч
					if (checktflogin.getText().equals(randomphoto.getSRand())) {
						flogin.dispose();
						TeacherFrame te = new TeacherFrame(tea);
						te.setVisible(true);
						userNames = tea.getName();
					} else {
						identifyError();
					}
					}else {
						noAuthority();					
					}
				} 
		}
			}else if (cmd.equals("����")) {
				Tip();
			}

	}
}