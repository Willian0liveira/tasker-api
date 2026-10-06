package br.com.wbytesistemas.tasker_api.controller;
import br.com.wbytesistemas.tasker_api.dto.TaskRequestDTO;
import br.com.wbytesistemas.tasker_api.dto.TaskResponseDTO;
import br.com.wbytesistemas.tasker_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO create(
            @Valid @RequestBody TaskRequestDTO dto) {

        return taskService.create(dto);
    }
}
