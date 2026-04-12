package dgtic.core.model.dto.Response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetasPacienteResponse {

    private int id;

    private LocalDate fechaEmision;

    private String indicacionMedica;

    private String viaDeAdministracion;

    private String duracionDelTratamiento;

    private DoctorResponse doctor;

}
