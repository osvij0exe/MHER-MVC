package dgtic.core.model.dto.Mappers;

import dgtic.core.model.Entities.Paciente;
import dgtic.core.model.dto.Response.PacienteRecetasResponse;
import dgtic.core.model.dto.Response.RecetasPacienteResponse;

import java.util.List;

public class PacienteRecetasMapper {

    public static PacienteRecetasResponse ToDto(Paciente paciente)
    {
        if(paciente == null) return  null;

        PacienteRecetasResponse response = new PacienteRecetasResponse();

        response.setPacienteId(paciente.getId());
        response.setNombre(paciente.getNombre());
        response.setApellidos(paciente.getApellidos());
        response.setActive(true);
        response.setFechaDeNacimiento(paciente.getFechaDeNacimiento());
        response.setGenero(paciente.getGenero());
        response.setNumeroDeSeguridad(paciente.getNumeroDeSeguridad());
        if(paciente.getRecetas() != null)
        {
            List<RecetasPacienteResponse> recetas = RecetasPacienteMapper.ToDtoCollection(paciente.getRecetas());
            response.setRecetas(recetas);
        }

        return response;
    }
}
