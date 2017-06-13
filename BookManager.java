package com.book.arraylist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BookManager {
	
	public static void main(String[] args) {
		meau();
	}

	// �˵�
	public static void meau() {
		ArrayList<Book> list =new ArrayList<Book>();
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
				.println("*    7���˳���              				   *");
		System.out.println("**********************************");
		System.out.print("�����룺");
		int chioce = input.nextInt();
		switch(chioce){
		case 1:
			addBook(list);
			break;
		case 2:
			lookBook(list);
			break;
		case 3:
			delBook(list);
			break;
		case 4:
			loanBook(list);
			break;
		case 5:
			backBook(list);
			break;
		case 6:
			updateBook(list);
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
	public static void lookBook(ArrayList<Book> list) {
		
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
	public static void loanBook(ArrayList<Book> list) {
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
			if(book.getState().equals("���")){
				System.out.println("�Ѿ�����ɹ����޷������");
				break;
			}else{
				book.setState(1);
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				book.setLoanDate(dateFormat.format(now));
				book.setCount((book.getCount()+1));
				System.out.println("����ɹ���");
			}
		}
		}
	}

	// �黹
	public static void backBook(ArrayList<Book> list) {
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
			if(book.getState().equals("δ��")){
				System.out.println("�Ȿ��û�н�������ܹ黹��");
				break;
			}else{
				book.setState(0);
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				book.setReturnDate(dateFormat.format(now));
				System.out.println("�黹�ɹ���");
			}
		}
		}
	}

	// ���
	public static void addBook(ArrayList<Book> list) {
		
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
		}
		System.out.println("��ӳɹ���");
	}

	// �˳�
	public static void exitBook() {
		System.out.println("�˳�����");
		System.exit(0);
		
	}
	//�޸�
	public static void updateBook(ArrayList<Book> list) {
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
			if(book.getState().equals("���")){
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
			}
		}
		}
	}
	
	// ɾ��
	public static void delBook(ArrayList<Book> list) {
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
			if(book.getState().equals("���")){
				System.out.println("�Ѿ�����ɹ����޷�ɾ����");
				break;
			}else{
				list.remove(index);
				System.out.println("ɾ���ɹ�");
			}
		}
		}
	}

}
