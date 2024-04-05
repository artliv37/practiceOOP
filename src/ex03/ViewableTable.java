package src.ex03;

import src.ex02.ViewableResult;
import src.ex02.View;

/**
 * ConcreteCreator
 * (шаблон проектирования
 * Factory Method)<br>
 * Объявляет метод,
 * "фабрикующий" объекты
 * 
 * @see ViewableResult
 * @see ViewableTable#getView()
 */

public class ViewableTable extends ViewableResult {
    @Override
    public View getView() {
        return new ViewTable();
    }
}