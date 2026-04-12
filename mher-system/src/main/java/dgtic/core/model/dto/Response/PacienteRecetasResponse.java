package dgtic.core.model.dto.Response;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacienteRecetasResponse {

    private Integer pacienteId;
    private String nombre;
    private String apellidos;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDeNacimiento;
    private char genero;
    private boolean active;
    private String numeroDeSeguridad;

    private List<RecetasPacienteResponse> recetas;


}
