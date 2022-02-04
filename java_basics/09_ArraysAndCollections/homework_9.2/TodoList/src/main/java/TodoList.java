import java.util.ArrayList;
import java.util.StringJoiner;

public class TodoList {
    private ArrayList<String> tasks = new ArrayList<>();

    public void add(String todo) {
        // TODO: добавьте переданное дело в конец списка
        add(-1, todo);
    }

    private boolean isIndexInRange(int index) {
        return (index >= 0 && index < tasks.size());
    }

    public void add(int index, String todo) {
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
        if (isIndexInRange(index)) {
            tasks.add(index, todo);
        } else {
            tasks.add(todo);
        }
        System.out.println("Добавлено дело " + todo);
    }

    public void edit(String todo, int index) {
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
        if (isIndexInRange(index)) {
            System.out.printf("Дело \"%s\" заменено на \"%s\"%n", tasks.get(index), todo);
            tasks.set(index, todo);
        } else {
            System.out.println("Дело с таким номером не существует");
        }
    }

    public void delete(int index) {
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела
        if (isIndexInRange(index)) {
            System.out.printf("Дело \"%s\" удалено%n", tasks.get(index));
            tasks.remove(index);
        } else {
            System.out.println("Дело с таким номером не существует");
        }
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return tasks;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < tasks.size(); i++) {
            joiner.add(i + " - " + tasks.get(i));
        }
        return joiner.toString();
    }
}