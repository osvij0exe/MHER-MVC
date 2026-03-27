package dgtic.core.model.dto.Mappers;

import dgtic.core.model.Entities.Cita;
import dgtic.core.model.dto.Response.CitasPacienteResponse;
import dgtic.core.model.dto.Response.DoctorResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CitasPacienteMapper {

    public static CitasPacienteResponse ToDto(Cita cita){
        if (cita == null) return null;

        CitasPacienteResponse response = new CitasPacienteResponse();
        response.setCitaId(cita.getId());
        response.setCitaInicio(cita.getCitaInicio());
        response.setCitaFin(cita.getCitaFin());
        response.setCitaEstado(cita.getCitaEstado());
        if (cita.getDoctor() != null) {
            DoctorResponse doctorResponse = DoctorMapper.ToDto(cita.getDoctor());
            response.setDoctor(doctorResponse);
        }
        return response;
    }

    public static List<CitasPacienteResponse> ToDtoCollection(List<Cita> citas)
    {
        List<CitasPacienteResponse> responses = citas
                .stream()
                .map(CitasPacienteMapper ::ToDto)
                .toList();

        return  responses;
    }


}
