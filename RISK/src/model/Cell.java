package model;

import java.util.ArrayList;

public class Cell {
	private int value;
	private ArrayList<Node> members;
	private int id;
	
	public Cell(int id, int value) {
		members = new ArrayList<>();
		this.id = id;
		this.value = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void addMember(Node Member) {
		members.add(Member);
	}
	
	public ArrayList<Node> getMembers() {
		return members;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
