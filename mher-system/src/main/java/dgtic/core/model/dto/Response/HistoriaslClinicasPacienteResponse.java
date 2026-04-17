package dgtic.core.model.dto.Response;

import dgtic.core.model.Entities.Doctor;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoriaslClinicasPacienteResponse {

    private int id;

    public String motivoDeLaConsulta;
    public String descripcionEnfermedad;
    public String antecedentesMedicos;
    public String diagnostico;
    public String tratamientoMedico;

    public DoctorResponse doctor;
}
