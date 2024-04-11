package src.ex04;

/**
 * Вычисление и отображение
 * результатов; cодержит реализацию
 * статического метода main()
 * 
 * @see Main#main
 */

public class Main {
    public static void main(String[] args) {
        Application app = Application.getInstance();
        app.run();
    }
}