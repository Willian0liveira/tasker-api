package br.com.wbytesistemas.tasker_api.dto;
import br.com.wbytesistemas.tasker_api.entity.TaskStatus;
import java.time.LocalDate;


public record TaskResponseDTO(
        Long id,
        String titulo,
        String descricao,
        TaskStatus status,
        LocalDate dataVencimento,
        Long userId
) {
}