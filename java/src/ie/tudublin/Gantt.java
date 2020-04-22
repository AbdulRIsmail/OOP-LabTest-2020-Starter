package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet {	

	private ArrayList<Task> tasks = new ArrayList<Task>();
	private float leftMargin;
	private float margin;
	
	public void settings() {
		size(800, 600);
		leftMargin = width / 6;
		margin = width / 20;
	}

	public void loadTasks() {
		Table table = loadTable("tasks.csv", "header");

		for (TableRow row : table.rows()) {
			Task t = new Task(row);
			tasks.add(t);
		}
	}

	public void printTasks() {
		for (Task t : tasks) {
			System.out.println(t);
		}
	}

	public void displayTasks() {
		int numOfDays = 30;
		float textHeight = 0.8f;
		
		stroke(255);
		fill(255);
		textAlign(CENTER);

		for(int i = 1; i <= numOfDays; i++) {
			float x = map(i, 1, 30, leftMargin, width - margin);
			line(x, margin, x, height - margin);
			text(i, x, margin * textHeight);
		}
	}
	
	public void mousePressed() {
		println("Mouse pressed");	
	}

	public void mouseDragged() {
		println("Mouse dragged");
	}
	
	public void setup() {
		loadTasks();
		printTasks();
	}
	
	public void draw() {
		background(0);
		displayTasks();
	}
}
