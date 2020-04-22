package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet {	

	// array lists
	private ArrayList<Task> tasks = new ArrayList<Task>();

	// floats
	private float leftMargin;
	private float margin;
	private float rectHeight;

	// integers
	private int leftRectSelected;
	private int rightRectSelected;
	private int numOfDays;

	public void settings() {
		// size of canvas
		size(800, 600);

		// floats
		leftMargin = width / 6;
		margin = width / 20;
		rectHeight = 35;

		// integers
		leftRectSelected = -1;
		rightRectSelected = -1;
		numOfDays = 30;
	}

	public void loadTasks() {
		// load the csv and header
		Table table = loadTable("tasks.csv", "header");

		// iterate through the rows and add it to the arraylist tasks
		for (TableRow row : table.rows()) {
			Task t = new Task(row);
			tasks.add(t);
		}
	}

	public void printTasks() {
		// iterate through the arraylist tasks and print it out
		for (Task t : tasks) {
			System.out.println(t);
		}
	}

	public void displayTasks() {
		displayChart(); // display the lines and dates
		displayText(); // display the task text
	}

	public void displayChart() {
		float textHeight = 0.8f;

		fill(255);
		textAlign(LEFT);

		for(int i = 1; i <= numOfDays; i++) {
			// to display different line colours *like how it is on the youtube video example*
			if (i % 2 == 0) {
				stroke(80);
			} else {
				stroke(160);
			}

			float x = map(i, 1, numOfDays, leftMargin, width - margin);
			line(x, margin, x, height - margin); // display the lines
			text(i, x, margin * textHeight); // display the dates above the lines
		}
	}

	public void displayText() {
		float radius = 5;
		
		// noStroke() to not display a white stroke around the rectangle
		noStroke();

		for(int i = 0; i < tasks.size(); i++) {
			// set fill back to white so all texts can appear as white
			fill(255);

			float y = map(i, 0, tasks.size(), 2 * margin, height - margin);
			text(tasks.get(i).getTask(), margin, y); // display the task name

			float color = map(i, 0, tasks.size(), 0, 255);
			fill(color, 255, 255);
			
			float rectStart = map(tasks.get(i).getStart(), 1, numOfDays, leftMargin, width - margin);
			float rectEnd = map(tasks.get(i).getEnd(), 1, numOfDays, leftMargin, width - margin);
			float rectWidth = rectEnd - rectStart;

			// display the rectangle
			rect(rectStart, y - (rectHeight / 2), rectWidth, rectHeight, radius); 
		}
	}
	
	public void mousePressed() {
		float pixels = 20;

		for(int i = 0; i < tasks.size(); i++) {
			Task task = tasks.get(i);

			float rectStart = map(task.getStart(), 1, numOfDays, leftMargin, width - margin);
			float rectEnd = map(task.getEnd(), 1, numOfDays, leftMargin, width - margin);
			float rectTop = map(i, 0, tasks.size(), 2 * margin, height - margin) - rectHeight / 2;
			float rectBottom = rectTop + rectHeight;

			if(mouseY >= rectTop && mouseY <= rectBottom) {
				if(mouseX > rectStart - pixels && mouseX < rectStart + pixels) {
					leftRectSelected = i;
					rightRectSelected = -1;
				}

				if(mouseX > rectEnd - pixels && mouseX < rectEnd + pixels) {
					leftRectSelected = -1;
					rightRectSelected = i;
				}
			}
		}
	}

	public void mouseDragged() {
		if(leftRectSelected > -1) {
			Task task = tasks.get(leftRectSelected);
			int date = (int) map(mouseX, 0, width, 0, numOfDays);

			if(date > 0 && date < task.getEnd() && task.getEnd() - date >= 1)  {
				task.setStart(date);
			}
		}

		if(rightRectSelected > -1) {
			Task task = tasks.get(rightRectSelected);
			int date = (int) map(mouseX, 0, width, 0, numOfDays);

			if(date <= numOfDays && date > task.getStart() && date - task.getStart() >= 1) {
				task.setEnd(date);
			}
		}
	}

	public void mouseReleased() {
		leftRectSelected = -1;
		rightRectSelected = -1;
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
