package src.ex04;

/**
 * Интерфейс
 * консольной команды;
 * шаблон Command
 */

public interface ConsoleCommand extends Command {
    public char getKey();
}