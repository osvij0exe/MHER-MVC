package dgtic.core.model.dto.Response;

import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.Entities.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetaResponse {

    private int id;

    private LocalDate fechaEmision;

    private String indicacionMedica;

    private String viaDeAdministracion;

    private String duracionDelTratamiento;

    public DoctorResponse doctor;

    public PacienteResponse paciente;
}
