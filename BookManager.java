package com.book.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BookManager {
	
	public static void main(String[] args) throws IOException {
		meau();
	}

	// 菜单
	public static void meau() throws IOException {
		//ArrayList<Book> list =new ArrayList<Book>();
		String fileName="book.txt";
		Scanner input =new Scanner(System.in);
		while(true){
		System.out.println("**********************************");
		System.out
				.println("*        欢迎使用图书管理系统                                     *");
		System.out.println("**********************************");
		System.out
				.println("*    1、新增图书；                      2、查看图书；      *");
		System.out
				.println("*    3、删除图书；                      4、借出图书；      *");
		System.out
				.println("*    5、归还图书；                      6、修改图书；       *");
		System.out
				.println("*    7、退出；                                                                *");
		System.out.println("**********************************");
		System.out.print("请输入：");
		int chioce = input.nextInt();
		switch(chioce){
		case 1:
			addBook(fileName);
			break;
		case 2:
			lookBook(fileName);
			break;
		case 3:
			delBook(fileName);
			break;
		case 4:
			loanBook(fileName);
			break;
		case 5:
			backBook(fileName);
			break;
		case 6:
			updateBook(fileName);
			break;
		case 7:
			exitBook();
			break;
		default:
			System.out.println("您输入的不正确，请重新输入");
			break;
		}
		}
	}

	//读取文件
	public static void readDate(String filePath,ArrayList<Book> list) throws IOException{
		BufferedReader br=new BufferedReader(new FileReader("book.txt"));
		String line;
		while((line=br.readLine())!=null){
			if ("".equals(line)) {
				break;
			}
			String[] split = line.split(",");
			Book book =new Book(split[0],Integer.parseInt(split[1]),Integer.parseInt(split[2]),split[3],split[4]);
			list.add(book);
		}
		br.close();
	}
	
	//写入文件
	public static void writeDate(String filePath,ArrayList<Book> list) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("book.txt"));
		
		//Book book=new Book();
		for (int i = 0; i < list.size(); i++) {
			StringBuilder sb=new StringBuilder();
			Book book = list.get(i);
			StringBuilder append = sb.append(book.getBookName()).append(",").append(book.getCount()).append(",").
							append(book.getState()).append(",").append(book.getLoanDate()).
							append(",").append(book.getReturnDate());
			bw.write(append.toString());
			bw.newLine();
			bw.flush();
		}
		bw.close();
	}
	// 查找
	public static int findBook(ArrayList<Book> list,String name) {
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(i);
			if((book.getBookName()!=null)&&(book.getBookName().equals(name))){
				index = i;
			}
		}
		return index;
	}

	// 查看
	public static void lookBook(String filePath) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		if(list.size()==0){
			System.out.println("不好意思，没有查到当前书籍");
			return;
		}
		System.out.println("书的名称\t"+"借出次数\t"+"借出状态\t"+"借出日期\t\t"+"归还日期");
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(i);
			System.out.println(book.getBookName()+"\t"+book.getCount()+
					"\t"+book.getState()+"\t"+book.getLoanDate()+"\t"+book.getReturnDate());
		}
	}

	// 借出
	public static void loanBook(String filePath ) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		Scanner input =new Scanner(System.in);
		System.out.println("请输入借出书籍的名称");
		String name=input.next();
		int index = findBook(list, name);
		if(list.size()==0){
			System.out.println("不好意思，没有查到当前书籍,无法借出");
			return;
		}
		if(index == -1){
			System.out.println("没有发现"+name+"这本书,无法借出。");
		}else{
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(index);
			if(book.getState()!=0){
				System.out.println("已经借出成功，无法借出。");
				break;
			}else{
				book.setState(1);
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				book.setLoanDate(dateFormat.format(now));
				book.setCount((book.getCount()+1));
				writeDate(filePath, list);
				System.out.println("借出成功。");
				break;
			}
		}
		}
	}

	// 归还
	public static void backBook(String filePath) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		Scanner input =new Scanner(System.in);
		System.out.println("请输入归还书籍的名称");
		String name=input.next();
		int index = findBook(list, name);
		if(list.size()==0){
			System.out.println("不好意思，没有查到当前书籍,无法归还");
			return;
		}
		if(index == -1){
			System.out.println("没有发现"+name+"这本书,无法归还。");
		}else{
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(index);
			if(book.getState()==0){
				System.out.println("这本书没有借出，不能归还。");
				break;
			}else{
				book.setState(0);
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				book.setReturnDate(dateFormat.format(now));
				writeDate(filePath, list);
				System.out.println("归还成功。");
				break;
			}
		}
		}
	}

	// 添加
	public static void addBook(String filePath ) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		Scanner input =new Scanner(System.in);
		System.out.println("请输入书的名称");
		String name = input.next();
		int findBook = findBook(list,name);
		if(findBook != -1){
			System.out.println("这本书已经添加");
			return;
		}else{
		Book book=new Book();
		book.setBookName(name);
		list.add(book);
		writeDate(filePath, list);
		}
		System.out.println("添加成功。");
		
	}

	// 退出
	public static void exitBook() {
		System.out.println("退出程序");
		System.exit(0);
		
	}
	//修改
	public static void updateBook(String filePath ) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		Scanner input =new Scanner(System.in);
		Book book;
		System.out.println("请输入要修改的书籍的名称");
		String name=input.next();
		int index = findBook(list, name);
		if(list.size()==0){
			System.out.println("不好意思，没有查到当前书籍,无法修改");
			return;
		}
		if(index == -1){
			System.out.println("没有发现"+name+"这本书,无法修改。");
		}else{
		for (int i = 0; i < list.size(); i++) {
			 book = list.get(index);
			if(book.getState()!=0){
				System.out.println("已经借出成功，无法修改。");
				break;
			}else{
				System.out.println("请输入要更新的书籍的名称");
				String bookName=input.next();
				System.out.println("请输入借出的次数");
				int count = input.nextInt();
				System.out.println("请输入图书当前的状态 ");
				int state = input.nextInt();
				System.out.println("请输入借出的日期");
				String loanDate = input.next();
				System.out.println("请输入归还的日期");
				String returnDate = input.next();
				book =new Book(bookName,count,state,returnDate,loanDate);
				list.set(index, book);
				writeDate(filePath, list);
			}
		}
		}
	}
	
	// 删除
	public static void delBook(String filePath ) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		Scanner input =new Scanner(System.in);
		System.out.println("请输入要删除书籍的名称");
		String name=input.next();
		int index = findBook(list, name);
		if(list.size()==0){
			System.out.println("不好意思，没有查到当前书籍,无法删除");
			return;
		}
		if(index == -1){
			System.out.println("没有发现"+name+"这本书,无法删除。");
		}else{
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(index);
			if(book.getState()==1){
				System.out.println("已经借出成功，无法删除。");
				break;
			}else{
				list.remove(index);
				writeDate(filePath, list);
				System.out.println("删除成功");
			}
		}
		}
	}

}
