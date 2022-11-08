package com.quiz.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.quiz.connection.ConnectionTest;

public class StudentRecord implements StudentRec {
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_WHITE = "\u001B[37m";
public void fetchRecord(int id) {
	Connection connection4 = null;
	PreparedStatement ps4 = null;

	ConnectionTest connectionTest = new ConnectionTest();
	
	try {
		connection4 = connectionTest.getConnectionDetails();
		ps4 = connection4.prepareStatement("select * from student where student_id=?");
		ps4.setInt(1, id);
		ResultSet rs3 = ps4.executeQuery();
		
		
		while (rs3.next()) {
				int student_id= rs3.getInt(1);
				if (id == student_id) {
				
					System.out.println("---------------------------------------------------------------------------------------------");
					System.out.printf(ANSI_RED+"%-12s %-15s %-15s %-15s","Student_Id","Student_Name", "Marks","Grade"+ANSI_RESET);
					System.out.println();
					System.out.println("---------------------------------------------------------------------------------------------");
					System.out.printf( "%-12d %-15s %-15d %-15s",rs3.getInt(1),rs3.getString(2),rs3.getInt(3),rs3.getString(4));
					System.out.println();
					System.out.println("---------------------------------------------------------------------------------------------");
			}else {
				System.out.println(ANSI_BLUE_BACKGROUND+ANSI_WHITE+"Invalid Student Id"+ANSI_RESET);
			}
		}
			
			
			
	connection4.close();
	ps4.close();
	rs3.close();
	
		
	} catch (SQLException e) {
		e.printStackTrace();
	}

}

}
