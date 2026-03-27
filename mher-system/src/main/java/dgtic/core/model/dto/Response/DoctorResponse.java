package dgtic.core.model.dto.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponse {

    private String nombre;
    private String apellidos;
    private char genero;
    private boolean active;
    private String cedulaProfesional;
    private EspecialidadResponse especialidad;

}
