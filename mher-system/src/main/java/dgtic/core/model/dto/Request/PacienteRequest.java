package dgtic.core.model.dto.Request;

import dgtic.core.model.dto.Response.DoctorNameResponse;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "El nombre es requerido")
    private String nombre;
    @NotBlank(message = "Los apellidos son requeridos")
    private String apellidos;
    @NotNull(message = "La fecha es requerida")
    @Past(message = "La fecha no pueden ser mayor al día actual")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDeNacimiento;
    private char genero;
    private boolean active;
    @NotBlank(message = "El numero de seguridad social es requerido")
    private String numeroDeSeguridad;

//    private DoctorRequest doctor;


}
