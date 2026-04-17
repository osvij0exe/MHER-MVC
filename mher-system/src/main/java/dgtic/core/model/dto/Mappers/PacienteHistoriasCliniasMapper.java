package dgtic.core.model.dto.Mappers;

import dgtic.core.model.Entities.Paciente;
import dgtic.core.model.dto.Response.HistoriaClinicaResponse;
import dgtic.core.model.dto.Response.PacienteHistoriasClinicasResponse;

import java.util.ArrayList;
import java.util.List;

public class PacienteHistoriasCliniasMapper {

    public static PacienteHistoriasClinicasResponse ToDto(Paciente paciente){
        if (paciente == null) return null;

        PacienteHistoriasClinicasResponse response = new PacienteHistoriasClinicasResponse();
        response.setPacienteId(paciente.getId());
        response.setNombre(paciente.getNombre());
        response.setApellidos(paciente.getApellidos());
        response.setFechaDeNacimiento(paciente.getFechaDeNacimiento());
        response.setGenero(paciente.getGenero());
        response.setActive(true);
        response.setNumeroDeSeguridad(paciente.getNumeroDeSeguridad());

//        List<HistoriaClinicaResponse> historiasClinicas = new ArrayList<>();

        if (paciente.getHistoriasClinicas() != null)
        {
            response.setHistoriasClinicas(HistoriasClinicasPacienteMapper.ToCollectionDto(paciente.getHistoriasClinicas()));
        }
        return response;
    }

}
