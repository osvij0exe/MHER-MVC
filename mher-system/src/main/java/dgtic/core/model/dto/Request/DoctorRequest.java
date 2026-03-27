package dgtic.core.model.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorRequest {
    private int doctorId;
    @NotBlank
    private String nombre;
    private String apellido;
    private char genero;
    private boolean active;
    private String cedulaProfesional;
    private int especialidadId;

}
