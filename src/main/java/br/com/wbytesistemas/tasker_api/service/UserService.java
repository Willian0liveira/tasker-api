package br.com.wbytesistemas.tasker_api.service;
import br.com.wbytesistemas.tasker_api.dto.UserRequestDTO;
import br.com.wbytesistemas.tasker_api.dto.UserResponseDTO;
import br.com.wbytesistemas.tasker_api.entity.UserEntity;
import br.com.wbytesistemas.tasker_api.exception.EmailAlreadyExistsException;
import br.com.wbytesistemas.tasker_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO create(UserRequestDTO dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException(
                    "E-mail já cadastrado"
            );
        }

        UserEntity user = new UserEntity();

        user.setNome(dto.nome());
        user.setEmail(dto.email());

        UserEntity saved = userRepository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getNome(),
                saved.getEmail(),
                saved.getDataCriacao()
        );
    }

    public List<UserResponseDTO> findAll() {

        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getNome(),
                        user.getEmail(),
                        user.getDataCriacao()
                ))
                .toList();
    }
}