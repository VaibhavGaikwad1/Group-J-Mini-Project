package com.quiz.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.quiz.connection.ConnectionTest;

public class StudentSort {
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public void sortStudent() throws SQLException {
		Connection connection3 = null;
		PreparedStatement ps3 = null;

		ConnectionTest connectionTest = new ConnectionTest();
		try {
			connection3 = connectionTest.getConnectionDetails();
			ps3 = connection3.prepareStatement(
					"select * from student order by marks desc");
			ResultSet rs2 = ps3.executeQuery();
			System.out.println("---------------------------------------------------------------------------------------------");
			System.out.printf(ANSI_RED+"%-12s %-15s %-15s %-15s","Student_Id","Student_Name", "Marks","Grade"+ANSI_RESET);
			System.out.println();
			System.out.println("---------------------------------------------------------------------------------------------");
			while (rs2.next()) {
				System.out.printf( "%-12d %-15s %-7d %15s",rs2.getInt(1),rs2.getString(2),rs2.getInt(3),rs2.getString(4));
				System.out.println();
				
				
			}
			System.out.println("---------------------------------------------------------------------------------------------");
			
			rs2.close();
			connection3.close();
			ps3.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
