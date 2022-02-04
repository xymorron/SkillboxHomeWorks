package Main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;


@Controller
public class DefaultController {

    @Value("${param}")
    private String param;

    private final ToDoRepository repository;

    public DefaultController(ToDoRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<ToDo> bookIterable = repository.findAll();
        ArrayList<ToDo> toDos = new ArrayList<>();
        for (ToDo toDo : bookIterable) toDos.add(toDo);
        model.addAttribute("todos", toDos);
        model.addAttribute("todoCount", toDos.size());
        model.addAttribute("paramtext", param);
        return "index";
    }
}