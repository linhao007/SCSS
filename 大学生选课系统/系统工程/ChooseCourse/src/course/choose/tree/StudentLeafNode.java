package course.choose.tree;

public class StudentLeafNode{
	 
	 private String sname;//ѧ������
	 private String sno;//ѧ��ѧ��
	 public StudentLeafNode(String sno, String sname) {
	  super();
	  // TODO Auto-generated constructor stub
	  this.sno = sno;
	  this.sname = sname;
	 }
	 public String getNo() {
	  return sno;
	 }
	 public void setNo(String sno) {
	  this.sno = sno;
	 }
	 public String getName() {
	  return sname;
	 }
	 public void setName(String sname) {
	  this.sname = sname;
	 }
	 public String toString() {
	 //�ص�:JTree����ʾ�����������������������.����������������
	  return this.sname;
	 }
	 
	}

