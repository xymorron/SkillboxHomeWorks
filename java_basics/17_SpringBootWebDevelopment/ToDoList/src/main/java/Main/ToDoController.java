package Main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {
    private final ToDoRepository repository;
    ToDoController(ToDoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todolist/")
    public List<ToDo> list() {
        return repository.findAll();
    }

    @PostMapping("/todolist/")
    public int add(ToDo toDo) {
        return repository.save(toDo).getId();
    }

    @GetMapping("/todolist/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional <ToDo> optionalToDo = repository.findById(id);
        if (!optionalToDo.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return new ResponseEntity(optionalToDo.get(), HttpStatus.OK);
    }

    @DeleteMapping("/todolist/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional <ToDo> optionalToDo = repository.findById(id);
        if (!optionalToDo.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        repository.delete(optionalToDo.get());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
