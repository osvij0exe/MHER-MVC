package dgtic.core.model.dto.Response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorNameResponse {
    @NotNull(message = "Seleccione un doctor")
    private Integer doctorId;
    private String nombreCompleto;
}
