package com.book.file;

public class Book {
	public String bookName;//书的名字
	public int count;//借出的次数
	public int state;//图书当前的状态        状态，0未借，1已借
	public String returnDate="0000-00-00";//归还的日期
	public String loanDate="0000-00-00";//归还的日期
	
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
			return 0;   //借出
		}
		else{
			return 1;  //未借
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
