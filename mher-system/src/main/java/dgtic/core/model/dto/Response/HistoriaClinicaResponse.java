package dgtic.core.model.dto.Response;

import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.Entities.HistoriaClinica;
import dgtic.core.model.Entities.Paciente;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriaClinicaResponse {

    private int id;

    public String motivoDeLaConsulta;

    public String descripcionEnfermedad;

    public String antecedentesMedicos;

    public String diagnostico;

    public String tratamientoMedico;

    public DoctorResponse doctor;

    public PacienteResponse paciente;



}
