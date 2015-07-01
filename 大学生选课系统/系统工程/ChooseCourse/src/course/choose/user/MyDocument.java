package course.choose.user;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
//此类要具体了解
public class MyDocument extends PlainDocument {
	int maxLength;
	public MyDocument(int newMaxLength) {
		super();
		maxLength = newMaxLength;
	}
	public MyDocument() {
		this(10);
	}
	public void insertString(int offset,String str,AttributeSet a) throws BadLocationException {
		if(getLength()+str.length()>maxLength) {
			return;
		}else{
			super.insertString(offset, str, a);
		}
		
		
	}

}
