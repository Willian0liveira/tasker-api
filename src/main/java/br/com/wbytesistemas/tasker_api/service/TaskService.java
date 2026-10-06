package br.com.wbytesistemas.tasker_api.service;
import br.com.wbytesistemas.tasker_api.dto.TaskRequestDTO;
import br.com.wbytesistemas.tasker_api.dto.TaskResponseDTO;
import br.com.wbytesistemas.tasker_api.entity.TaskEntity;
import br.com.wbytesistemas.tasker_api.entity.TaskStatus;
import br.com.wbytesistemas.tasker_api.entity.UserEntity;
import br.com.wbytesistemas.tasker_api.repository.TaskRepository;
import br.com.wbytesistemas.tasker_api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(
            TaskRepository taskRepository,
            UserRepository userRepository) {

        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskResponseDTO create(TaskRequestDTO dto) {

        UserEntity user = userRepository.findById(dto.userId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuário não encontrado"
                        )
                );

        TaskEntity task = new TaskEntity();

        task.setTitulo(dto.titulo());
        task.setDescricao(dto.descricao());
        task.setStatus(
                dto.status() != null
                        ? dto.status()
                        : TaskStatus.PENDENTE
        );
        task.setDataVencimento(dto.dataVencimento());
        task.setUser(user);

        TaskEntity saved = taskRepository.save(task);

        return toResponse(saved);
    }

    private TaskResponseDTO toResponse(TaskEntity task) {

        return new TaskResponseDTO(
                task.getId(),
                task.getTitulo(),
                task.getDescricao(),
                task.getStatus(),
                task.getDataVencimento(),
                task.getUser().getId()
        );
    }
}

