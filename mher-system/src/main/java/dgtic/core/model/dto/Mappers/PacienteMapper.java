package dgtic.core.model.dto.Mappers;


import dgtic.core.model.Entities.Paciente;
import dgtic.core.model.dto.Request.PacienteRequest;
import dgtic.core.model.dto.Response.PacienteResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class PacienteMapper {
    public static Paciente ToEntity(PacienteRequest request)
    {
        Paciente paciente = new Paciente();
//        paciente.setId(request.getId());
        paciente.setNombre(request.getNombre());
        paciente.setApellidos(request.getApellidos());
        paciente.setGenero(request.getGenero());
        paciente.setFechaDeNacimiento(request.getFechaDeNacimiento());
        paciente.setActive(true);
        paciente.setNumeroDeSeguridad(request.getNumeroDeSeguridad());
        return paciente;

    };

    public static PacienteResponse ToDto(Paciente paciente)
    {
        PacienteResponse response = new PacienteResponse();
        response.setPacienteId(paciente.getId());
        response.setNombre(paciente.getNombre());
        response.setApellidos(paciente.getApellidos());
        response.setGenero(paciente.getGenero());
        response.setActive(paciente.isActive());
        response.setFechaDeNacimiento(paciente.getFechaDeNacimiento());
        response.setNumeroDeSeguridad(paciente.getNumeroDeSeguridad());
        return response;
    }

    public static List<PacienteResponse> ToDtoCollection(List<Paciente> pacientes)
    {
        List<PacienteResponse> response = pacientes.stream()
                .map(PacienteMapper::ToDto)
                .collect(Collectors.toList());
        return  response;
    };
}
