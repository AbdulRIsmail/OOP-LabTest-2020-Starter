package ie.tudublin;

import processing.data.TableRow;

public class Task {
    private String task;
    private int start;
    private int end;

    public Task(String task, int start, int end) {
        this.task = task;
        this.start = start;
        this.end = end;
    }

    public Task(TableRow tr) {
        this(tr.getString("Task"), tr.getInt("Start"), tr.getInt("End"));
    }
}