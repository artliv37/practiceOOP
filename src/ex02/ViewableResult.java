package src.ex02;

/**
 * ConcreteCreator
 * (шаблон проектирования
 * Factory Method)<br>
 * Объявляет метод,
 * "фабрикующий" объекты
 * 
 * @see Viewable
 * @see ViewableResult#getView()
 */

public class ViewableResult implements Viewable {
    @Override
    public View getView() {
        return new ViewResult();
    }
}