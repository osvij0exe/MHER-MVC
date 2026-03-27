package dgtic.core.model.dto.Request;

import dgtic.core.model.dto.Response.DoctorNameResponse;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteRequest {

//    private Integer id;

    private String nombre;
    private String apellidos;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDeNacimiento;
    private char genero;
    private boolean active;
    private String numeroDeSeguridad;

//    private DoctorRequest doctor;


}
