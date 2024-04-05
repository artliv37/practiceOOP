package src.ex03;

import java.io.IOException;

import src.ex02.View;

/**
 * Вычисление и отображение результатов<br>
 * Содержит реализацию статического метода main()
 * 
 * @see Main#main
 */

public class Main extends src.ex02.Main {
    public Main(View view) {
        super(view);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main(new ViewableTable().getView());
        main.menu();
    }
}