package com.book.file;

public class Book {
	public String bookName;//�������
	public int count;//����Ĵ���
	public int state;//ͼ�鵱ǰ��״̬        ״̬��0δ�裬1�ѽ�
	public String returnDate="0000-00-00";//�黹������
	public String loanDate="0000-00-00";//�黹������
	
	public Book(){
		
	}
	
	public Book(String bookName,int state){
		this.bookName = bookName;
		this.state = state;
	}
	public Book(String bookName, int count, int state, String returnDate, String loanDate) {
		this.bookName = bookName;
		this.count = count;
		this.state = state;
		this.returnDate = returnDate;
		this.loanDate = loanDate;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getState() {
		if(state == 0){
			return 0;   //���
		}
		else{
			return 1;  //δ��
		}
		
	}
	public void setState(int state) {
		this.state=state;
		
	}
	public String getReturnDate() {
		return returnDate;
		
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	
	
	
}
