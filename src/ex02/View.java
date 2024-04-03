package src.ex02;

import java.io.IOException;

/**
 * Product
 * (шаблон проектирования
 * Factory Method)<br>
 * Интерфейс "фабрикуемых"
 * объектов<br>
 * Объявляет методы
 * отображения объектов
 */

public interface View {
    public void viewHeader();

    public void viewBody();

    public void viewFooter();

    public void viewShow();

    public int viewCountHexDigits(String number);

    public int viewCountOctDigits(String number);

    public void viewSave() throws IOException;

    public void viewRestore() throws Exception;
}