package ie.tudublin;

public class Main {
    public void gant() {
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Gantt());
    }
    
    public static void main(String[] arg) {
        Main main = new Main();
		main.gant();        
    }
}