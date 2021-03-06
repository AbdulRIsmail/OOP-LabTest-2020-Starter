package ie.tudublin;

import processing.data.TableRow;

public class Task {
    // private variables
    private String task;
    private int start;
    private int end;

    // constructor
    public Task(String task, int start, int end) {
        this.task = task;
        this.start = start;
        this.end = end;
    }

    public Task(TableRow tr) {
        this(tr.getString("Task"), tr.getInt("Start"), tr.getInt("End"));
    }

    // accessor methods
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    // methods
    public String toString() {
        return "[" + this.task + ", " + this.start + ", " + this.end + "]";
    }
}