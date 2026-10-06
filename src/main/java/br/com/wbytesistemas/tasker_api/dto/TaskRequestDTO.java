package br.com.wbytesistemas.tasker_api.dto;
import br.com.wbytesistemas.tasker_api.entity.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public record TaskRequestDTO(

        @NotBlank(message = "Título é obrigatório")
        @Size(max = 100, message = "Título deve ter no máximo 100 caracteres")
        String titulo,

        String descricao,

        TaskStatus status,

        @FutureOrPresent(message = "A data de vencimento não pode ser anterior à data atual")
        LocalDate dataVencimento,

        @NotNull(message = "Usuário é obrigatório")
        Long userId

) {
}
