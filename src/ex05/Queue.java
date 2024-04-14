package src.ex05;

import src.ex04.Command;

/**
 * Представляет
 * методы для помещения
 * и извлечения задач
 * обработчиком потока;
 * шаблон Worker Thread
 */

public interface Queue {
    void put(Command cmd);

    Command take();
}