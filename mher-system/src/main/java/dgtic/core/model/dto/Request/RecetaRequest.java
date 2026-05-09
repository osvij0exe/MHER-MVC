package dgtic.core.model.dto.Request;

import dgtic.core.model.dto.Response.DoctorNameResponse;
import dgtic.core.model.dto.Response.PacienteResponse;
import jakarta.validation.Valid;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetaRequest {

    private Integer Id;

    private String indicacionMedica;

    private String viaDeAdministracion;

    private String duracionDelTratamiento;

    public DoctorNameResponse doctor;

    @Valid
    public EspecialidadRequest especialidad;

    public PacienteResponse paciente;
}
