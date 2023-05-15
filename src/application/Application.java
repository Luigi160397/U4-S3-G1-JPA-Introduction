package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {

	static Connection conn = null;

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/epicode_db?PostgreSQL?useSSL=false";
		String username = "postgres";
		String password = "1234";
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

//		insertStudent(s3);
//		deleteStudent(1);

		getBest();

		getVoteRange(4, 10);
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

	public static void deleteStudent(int id) {
		String sqlDeleteOne = "DELETE FROM school_students WHERE id = ?";
		try {
			PreparedStatement stmtDeleteOne = conn.prepareStatement(sqlDeleteOne);
			stmtDeleteOne.setInt(1, id);
			stmtDeleteOne.execute();
			System.out.println("Studente eliminato!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void getBest() {
		String sqlBest = "SELECT id, name, lastname, gender, birthdate, min_vote, max_vote, avg "
				+ "FROM public.school_students WHERE avg = (SELECT MAX(avg) FROM public.school_students)";

		try {
			Statement stmt = conn.createStatement();
			ResultSet best = stmt.executeQuery(sqlBest);
			System.out.println(
					"=========================================================== Best =========================================================== ");
			while (best.next()) {
				System.out.println("ID: " + best.getInt("id") + ", Name:" + best.getString("name") + ", Last Name: "
						+ best.getString("lastname") + ", Gender: " + best.getString("gender") + ", Birthdate: "
						+ best.getDate("birthdate") + ", Min Vote: " + best.getDouble("min_vote") + ", Max vote: "
						+ best.getDouble("max_vote") + ", Avg: " + best.getDouble("avg"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getVoteRange(int min, int max) {
		String sqlVoteRange = "SELECT id, name, lastname, gender, birthdate, min_vote, max_vote, (min_vote + max_vote)/2 AS avg "
				+ "FROM public.school_students WHERE min_vote >= ? AND max_vote <= ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sqlVoteRange);
			stmt.setInt(1, min);
			stmt.setInt(2, max);
			ResultSet voteRange = stmt.executeQuery();

			System.out.println(
					"=========================================================== Range: =========================================================== ");
			while (voteRange.next()) {
				System.out.println("ID: " + voteRange.getInt("id") + ", Name: " + voteRange.getString("name")
						+ ", Last Name: " + voteRange.getString("lastname") + ", Gender: "
						+ voteRange.getString("gender") + ", Birthdate: " + voteRange.getDate("birthdate")
						+ ", Min Vote: " + voteRange.getDouble("min_vote") + ", Max vote: "
						+ voteRange.getDouble("max_vote") + ", Avg: " + voteRange.getDouble("avg"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
