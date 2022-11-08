package com.quiz;

import java.sql.SQLException;
import java.util.Scanner;

import com.quiz.questionretrieve.QuestionRetrieve;
import com.quiz.student.StudentRecord;
import com.quiz.student.StudentSort;

public class TestMain {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public void callMethod() throws SQLException {
		String ans;
		do{
			Scanner scanner = new Scanner(System.in);
		
			QuestionRetrieve qr = new QuestionRetrieve();
			qr.registerStudent();
			qr.getQuestion();
			qr.calculateGrade();
			qr.insertStudentData();
		    System.out.println("Please press 'Yes' if next student wants to attempt quiz else press 'No'");
		    ans = scanner.next();
			}while(ans.equalsIgnoreCase("yes"));	
	}
	public static void main(String[] args) throws SQLException{
		Scanner scanner = new Scanner(System.in);
		System.out.println(ANSI_WHITE + ANSI_RED_BACKGROUND + "*...Welcome to the Group J quiz app...*" + ANSI_RESET);
		System.out.println();
		
		TestMain tm =new TestMain();
		StudentSort stdsort = new StudentSort();
		StudentRecord stdrec = new StudentRecord();
		System.out.println("Please choose operation to perform...");
	    System.out.println(ANSI_BLUE_BACKGROUND+ANSI_WHITE+"1. Play Quiz"+ANSI_RESET+ "     "+ANSI_BLUE_BACKGROUND+ANSI_WHITE+"2.Display List of Total Students"+ANSI_RESET+"     "+ANSI_BLUE_BACKGROUND+ANSI_WHITE+"3.Display Individual Students"+ANSI_RESET);
		String input = scanner.next();
		if(input.equalsIgnoreCase("1")) {
			tm.callMethod();
		}
		if(input.equalsIgnoreCase("2")) {
			stdsort.sortStudent();
		}
	if(input.equalsIgnoreCase("3")) {
			System.out.println("Please enter student id to get record...");
		    int id =scanner.nextInt();
			stdrec.fetchRecord(id);
	}
		System.out.println(ANSI_WHITE + ANSI_RED_BACKGROUND + "*...Thank You...*" + ANSI_RESET);
	}

}
