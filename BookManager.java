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

	// �˵�
	public static void meau() throws IOException {
		//ArrayList<Book> list =new ArrayList<Book>();
		String fileName="book.txt";
		Scanner input =new Scanner(System.in);
		while(true){
		System.out.println("**********************************");
		System.out
				.println("*        ��ӭʹ��ͼ�����ϵͳ                                     *");
		System.out.println("**********************************");
		System.out
				.println("*    1������ͼ�飻                      2���鿴ͼ�飻      *");
		System.out
				.println("*    3��ɾ��ͼ�飻                      4�����ͼ�飻      *");
		System.out
				.println("*    5���黹ͼ�飻                      6���޸�ͼ�飻       *");
		System.out
				.println("*    7���˳���                                                                *");
		System.out.println("**********************************");
		System.out.print("�����룺");
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
			System.out.println("������Ĳ���ȷ������������");
			break;
		}
		}
	}

	//��ȡ�ļ�
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
	
	//д���ļ�
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
	// ����
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

	// �鿴
	public static void lookBook(String filePath) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		if(list.size()==0){
			System.out.println("������˼��û�в鵽��ǰ�鼮");
			return;
		}
		System.out.println("�������\t"+"�������\t"+"���״̬\t"+"�������\t\t"+"�黹����");
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(i);
			System.out.println(book.getBookName()+"\t"+book.getCount()+
					"\t"+book.getState()+"\t"+book.getLoanDate()+"\t"+book.getReturnDate());
		}
	}

	// ���
	public static void loanBook(String filePath ) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		Scanner input =new Scanner(System.in);
		System.out.println("���������鼮������");
		String name=input.next();
		int index = findBook(list, name);
		if(list.size()==0){
			System.out.println("������˼��û�в鵽��ǰ�鼮,�޷����");
			return;
		}
		if(index == -1){
			System.out.println("û�з���"+name+"�Ȿ��,�޷������");
		}else{
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(index);
			if(book.getState()!=0){
				System.out.println("�Ѿ�����ɹ����޷������");
				break;
			}else{
				book.setState(1);
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				book.setLoanDate(dateFormat.format(now));
				book.setCount((book.getCount()+1));
				writeDate(filePath, list);
				System.out.println("����ɹ���");
				break;
			}
		}
		}
	}

	// �黹
	public static void backBook(String filePath) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		Scanner input =new Scanner(System.in);
		System.out.println("������黹�鼮������");
		String name=input.next();
		int index = findBook(list, name);
		if(list.size()==0){
			System.out.println("������˼��û�в鵽��ǰ�鼮,�޷��黹");
			return;
		}
		if(index == -1){
			System.out.println("û�з���"+name+"�Ȿ��,�޷��黹��");
		}else{
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(index);
			if(book.getState()==0){
				System.out.println("�Ȿ��û�н�������ܹ黹��");
				break;
			}else{
				book.setState(0);
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				book.setReturnDate(dateFormat.format(now));
				writeDate(filePath, list);
				System.out.println("�黹�ɹ���");
				break;
			}
		}
		}
	}

	// ���
	public static void addBook(String filePath ) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		Scanner input =new Scanner(System.in);
		System.out.println("�������������");
		String name = input.next();
		int findBook = findBook(list,name);
		if(findBook != -1){
			System.out.println("�Ȿ���Ѿ����");
			return;
		}else{
		Book book=new Book();
		book.setBookName(name);
		list.add(book);
		writeDate(filePath, list);
		}
		System.out.println("��ӳɹ���");
		
	}

	// �˳�
	public static void exitBook() {
		System.out.println("�˳�����");
		System.exit(0);
		
	}
	//�޸�
	public static void updateBook(String filePath ) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		Scanner input =new Scanner(System.in);
		Book book;
		System.out.println("������Ҫ�޸ĵ��鼮������");
		String name=input.next();
		int index = findBook(list, name);
		if(list.size()==0){
			System.out.println("������˼��û�в鵽��ǰ�鼮,�޷��޸�");
			return;
		}
		if(index == -1){
			System.out.println("û�з���"+name+"�Ȿ��,�޷��޸ġ�");
		}else{
		for (int i = 0; i < list.size(); i++) {
			 book = list.get(index);
			if(book.getState()!=0){
				System.out.println("�Ѿ�����ɹ����޷��޸ġ�");
				break;
			}else{
				System.out.println("������Ҫ���µ��鼮������");
				String bookName=input.next();
				System.out.println("���������Ĵ���");
				int count = input.nextInt();
				System.out.println("������ͼ�鵱ǰ��״̬ ");
				int state = input.nextInt();
				System.out.println("��������������");
				String loanDate = input.next();
				System.out.println("������黹������");
				String returnDate = input.next();
				book =new Book(bookName,count,state,returnDate,loanDate);
				list.set(index, book);
				writeDate(filePath, list);
			}
		}
		}
	}
	
	// ɾ��
	public static void delBook(String filePath ) throws IOException {
		ArrayList<Book> list =new ArrayList<Book>();
		readDate(filePath, list);
		Scanner input =new Scanner(System.in);
		System.out.println("������Ҫɾ���鼮������");
		String name=input.next();
		int index = findBook(list, name);
		if(list.size()==0){
			System.out.println("������˼��û�в鵽��ǰ�鼮,�޷�ɾ��");
			return;
		}
		if(index == -1){
			System.out.println("û�з���"+name+"�Ȿ��,�޷�ɾ����");
		}else{
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(index);
			if(book.getState()==1){
				System.out.println("�Ѿ�����ɹ����޷�ɾ����");
				break;
			}else{
				list.remove(index);
				writeDate(filePath, list);
				System.out.println("ɾ���ɹ�");
			}
		}
		}
	}

}
