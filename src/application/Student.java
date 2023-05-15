package application;

import java.sql.Date;

public class Student {
	private int id;
	private String name;
	private String lastName;
	private String gender;
	private Date birthdate;
	private double avg;
	private double min_vote;
	private double max_vote;

	public Student(String name, String lastName, String gender, Date birthdate, double minVote, double maxVote) {
		setName(name);
		setLastName(lastName);
		setGender(gender);
		setBirthdate(birthdate);
		setMin_vote(minVote);
		setMax_vote(maxVote);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getMin_vote() {
		return min_vote;
	}

	public void setMin_vote(double min_vote) {
		this.min_vote = min_vote;
	}

	public double getMax_vote() {
		return max_vote;
	}

	public void setMax_vote(double max_vote) {
		this.max_vote = max_vote;
	}

}
