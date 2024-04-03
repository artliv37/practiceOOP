package src.ex02;

/**
 * Creator
 * (шаблон проектирования
 * Factory Method)<br>
 * Объявляет метод,
 * "фабрикующий" объекты
 * 
 * @see Viewable#getView()
 */

public interface Viewable {
    public View getView();
}