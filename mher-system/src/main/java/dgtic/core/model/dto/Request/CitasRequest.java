package dgtic.core.model.dto.Request;


import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.Entities.Paciente;
import dgtic.core.model.dto.Response.DoctorNameResponse;
import dgtic.core.model.dto.Response.EspecialidadResponse;
import dgtic.core.model.dto.Response.PacienteNameResposne;
import dgtic.core.model.dto.Response.PacienteResponse;
import dgtic.core.validation.citaValidations.HorarioNoValido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitasRequest {
    private Integer citaId;

    @NotNull(message = "La fecha y hora son requeridas")
    @Future(message = "La fecha y horas no pueden ser anteriores al día actual")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime citaInicio;

    @NotNull(message = "La duración en minutos es requerida")
    @HorarioNoValido
    private Integer addMinutes;


    @Valid
    private EspecialidadRequest especialidad;

    @Valid
    private DoctorNameResponse doctor;

    @NotNull(message = "El paciente es obligatorio")
    @Valid
    private PacienteResponse paciente;

}
