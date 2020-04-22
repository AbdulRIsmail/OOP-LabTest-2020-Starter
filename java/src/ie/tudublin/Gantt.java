package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet {	

	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public void settings() {
		size(800, 600);
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
	
	public void mousePressed() {
		println("Mouse pressed");	
	}

	public void mouseDragged() {
		println("Mouse dragged");
	}

	
	
	public void setup() {
	}
	
	public void draw() {			
		background(0);
	}
}
