package br.com.wbytesistemas.tasker_api.repository;
import br.com.wbytesistemas.tasker_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);
}
