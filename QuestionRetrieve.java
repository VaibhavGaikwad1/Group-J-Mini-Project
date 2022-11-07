package com.min.project.java.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class QuestionRetrieve {

	public void getQuestion(int id) throws Exception {
		Scanner sc=new Scanner(System.in);//Scanner Class to get ans from user
		String a;
		int score=0;
		ResultMethod rm=new ResultMethod();
		ConnectionTest connectionTest = new ConnectionTest();
		try {
			Connection connection = connectionTest.getConnectionDetails();
			PreparedStatement ps = connection.prepareStatement("select question,option_1,option_2,option_3,option_4,correct_answer from Quiz_Data Order by Rand() Limit 10");
			ResultSet rs = ps.executeQuery();
			int i=1;
			while (rs.next()) {
				System.out.println("Que."+i+" "+ rs.getString(1));
				System.out.println("A) " + rs.getString(2));
				System.out.println("B) " + rs.getString(3));
				System.out.println("C) " + rs.getString(4));
				System.out.println("D) " + rs.getString(5));
				System.out.println("Enter your ans");
				a=sc.next();
				if (a.equalsIgnoreCase(rs.getString(6))) {
					System.out.println("Answer is correct..");
					score++;
				} else {
					System.out.println("Answer is incorrect..");
				}
				System.out.println();
				//run(a);	
				i++;
			}
			rm.storeResult(id,score);
			System.out.println("Your Score is " + score + "/10");
			rs.close();
			connection.close();
			ps.close();
			System.out.println("Thank you...\nYour test is finished... \ncheck your result by using your id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

/*	public void run(int a) {
		if(a==1||a==2||a==3||a==4){
			ResultMethod rm=new ResultMethod();
			rm.storeResult(a);
		}else{
			System.out.println("Invalid Option....");
			System.out.println("Please enter valid Option..");
			Scanner sc =new Scanner(System.in);
			a=sc.nextInt();
			run(a);
		}
	}
*/	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Home Screen-Select any 1 option");
		System.out.println("1)New user\n2)Already have account and want to attend test\n3)Result of all Students");
		
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		StudentRegistration sr=new StudentRegistration();
		if(a==1) {
			try {
				sr.StudRegistation();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}else if(a==3){
			ResultMethod rm=new ResultMethod();
			rm.overAllResult();
			
		}else {
			System.out.println("Enter your Id to Start the test");
			int id=sc.nextInt();
			System.out.println("Start your test");
			QuestionRetrieve qr = new QuestionRetrieve();
			qr.getQuestion(id);
		}
		}
		
		
	
}
