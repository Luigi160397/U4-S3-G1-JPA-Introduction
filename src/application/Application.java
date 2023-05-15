package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Application {

	static Connection conn = null;

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/epicode_db?PostgreSQL?useSSL=false";
		String username = "postgres";
		String password = "Luigi123";
		try {
			System.out.println("Connecting...");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected ✅");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Student s1 = new Student("Luca", "Pezzolo", "M", Date.valueOf("2000-04-23"), 7.0, 10.0);
		Student s2 = new Student("Maria", "Impiccetta", "F", Date.valueOf("2000-03-16"), 9.0, 10.0);
		Student s3 = new Student("Marco", "Colubro", "M", Date.valueOf("1986-04-16"), 4.0, 10.0);

		insertStudent(s3);
	}

	public static void insertStudent(Student s) {
		String sqlInsert = "INSERT INTO school_students (name, lastname, gender, birthdate, min_vote, max_vote)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getLastName());
			stmt.setString(3, s.getGender());
			stmt.setDate(4, s.getBirthdate());
			stmt.setDouble(5, s.getMin_vote());
			stmt.setDouble(6, s.getMax_vote());
			stmt.execute();
			System.out.println("Studente inserito!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
