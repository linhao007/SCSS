package course.choose.tree;

public class StudentLeafNode{
	 
	 private String sname;//学生姓名
	 private String sno;//学生学号
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
	 //重点:JTree上显示的内容是由这个函数决定的.所以重载它就行了
	  return this.sname;
	 }
	 
	}

