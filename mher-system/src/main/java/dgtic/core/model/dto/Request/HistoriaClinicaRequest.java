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

    private String motivoDeLaConsulta;

    private String descripcionEnfermedad;

    private String antecedentesMedicos;

    private String diagnostico;

    private String tratamientoMedico;

    private DoctorRequest doctor;

    private PacienteResponse paciente;


}
