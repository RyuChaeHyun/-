package entity;

import java.io.Serializable;
import java.util.Scanner;

public class ELecture {
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeacher() {
		return Teacher;
	}
	public void setTeacher(String teacher) {
		Teacher = teacher;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private int number;
	private String name;
	private String Teacher;
	private String score;
	private String time;
	
	public void read(Scanner scanner) {
		number=scanner.nextInt();
		name= scanner.next();
		Teacher = scanner.next();
		score = scanner.next();
		time = scanner.next();
	}
	

}
