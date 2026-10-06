package br.com.wbytesistemas.tasker_api.repository;
import br.com.wbytesistemas.tasker_api.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
