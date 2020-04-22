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
		float rectHeight = 35;
		float radius = 5;
		
		stroke(255);
		fill(255);
		textAlign(LEFT);

		for(int i = 1; i <= numOfDays; i++) {
			float x = map(i, 1, numOfDays, leftMargin, width - margin);
			line(x, margin, x, height - margin);
			text(i, x, margin * textHeight);
		}

		// noStroke() to not display a white stroke around the rectangle
		noStroke();

		for(int i = 0; i < tasks.size(); i++) {
			// we need to set fill back to white so all texts can appear as white
			fill(255);

			float y = map(i, 0, tasks.size(), 2 * margin, height - margin);
			text(tasks.get(i).getTask(), margin, y);

			float color = map(i, 0, tasks.size(), 0, 255);
			fill(color, 255, 255);
			
			float rectStart = map(tasks.get(i).getStart(), 1, numOfDays, leftMargin, width - margin);
			float rectEnd = map(tasks.get(i).getEnd(), 1, numOfDays, leftMargin, width - margin);
			float rectWidth = rectEnd - rectStart;

			rect(rectStart, y - rectHeight / 2, rectWidth, rectHeight, radius); 
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
		colorMode(HSB);
		displayTasks();
	}
}
