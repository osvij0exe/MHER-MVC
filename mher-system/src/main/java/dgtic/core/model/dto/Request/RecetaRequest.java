package dgtic.core.model.dto.Request;

import dgtic.core.model.dto.Response.DoctorResponse;
import dgtic.core.model.dto.Response.PacienteResponse;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetaRequest {
    private LocalDate fechaEmision;

    private String indicacionMedica;

    private String viaDeAdministracion;

    private String duracionDelTratamiento;

    public Integer doctorId;

    public Integer pacienteId;
}
