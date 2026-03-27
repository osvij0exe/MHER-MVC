package dgtic.core.model.dto.Request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspecialidadRequest {
    @NotNull(message = "Seleccione una especialidad")
    private Integer especialidadId;
    private String nombreEspecialidad;
}
