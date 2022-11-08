package com.quiz.questionretrieve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.quiz.connection.ConnectionTest;

public class QuestionRetrieve {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public int score1;
	String name;
	String grade;
		public void registerStudent() {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter your name...");
			name = scanner.next();
			System.out.println("Hello " + name + " Let's start the quiz...");

		}

	

	public void getQuestion() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ConnectionTest connectionTest = new ConnectionTest();
		try {
			connection = connectionTest.getConnectionDetails();
			ps = connection.prepareStatement(
					"select question,option_1,option_2,option_3,option_4,correct_answer from quiz_data order by rand() limit 10");
			ResultSet rs = ps.executeQuery();
			int i = 1;
			int score = 0;
			Scanner scanner = new Scanner(System.in);
			while (rs.next()) {

				System.out.println("Que." + i + " " + rs.getString(1));
				System.out.println("A) " + rs.getString(2));
				System.out.println("B) " + rs.getString(3));
				System.out.println("C) " + rs.getString(4));
				System.out.println("D) " + rs.getString(5));
				i++;
				System.out.println("Submit Your Answer");
				String ans = scanner.next();
				if (ans.equalsIgnoreCase(rs.getString(6))) {
					System.out.println(ANSI_GREEN + "Answer is correct.." + ANSI_RESET);
					score++;

				} else {
					System.out.println(ANSI_RED + "Answer is incorrect.." + ANSI_RESET);
				}
				System.out.println();
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			}
			score1 = score;
			System.out.println("Your Score is " + score1 + "/10");
			if (score1 >= 8) {
				System.out.println(ANSI_GREEN + "Grade A" + ANSI_RESET);
			} else if (score1 >= 6 && score1 < 8) {
				System.out.println(ANSI_BLUE + "Grade B" + ANSI_RESET);
			} else if (score1 == 5) {
				System.out.println(ANSI_YELLOW + "Grade C" + ANSI_RESET);
			} else {
				System.out.println(ANSI_RED + "Grade D(Failed)" + ANSI_RESET);
			}
			
			rs.close();
			connection.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public String calculateGrade() {
		 grade=null;
		if(score1>=8 && score1<=10) {
			grade ="Grade A";
		}else if(score1 >5 && score1<8) {
			grade = "Grade B";
		}else if(score1 ==5) {
			grade ="Grade C";
		}else {
			grade ="Grade D";
		}
		
		return grade;
}
	public void insertStudentData() {
		ConnectionTest connectionTest = new ConnectionTest();

		

		try {
			Connection connection1 = connectionTest.getConnectionDetails();
			PreparedStatement ps1 = connection1.prepareStatement("insert into student(Student_name,marks,grade)values(?,?,?)");
			ps1.setString(1, name);
			ps1.setInt(2, score1);
			ps1.setString(3, grade);
			ps1.execute();

			System.out.println("Data inserted successfully..");
			connection1.close();
			ps1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
