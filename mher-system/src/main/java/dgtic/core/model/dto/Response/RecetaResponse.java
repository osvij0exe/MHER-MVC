package dgtic.core.model.dto.Response;

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

    public DoctorNameResponse doctor;

    public PacienteResponse paciente;

    public  EspecialidadResponse especialidad;


}
