package dgtic.core.model.dto.Request;

import dgtic.core.model.dto.Response.DoctorResponse;
import dgtic.core.model.dto.Response.PacienteResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriaClinicaRequest {

    public String motivoDeLaConsulta;

    public String descripcionEnfermedad;

    public String antecedentesMedicos;

    public String diagnostico;

    public String tratamientoMedico;

    public Integer doctorId;

    public Integer pacienteId;

}
