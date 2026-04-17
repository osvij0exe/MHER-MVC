package dgtic.core.model.dto.Mappers;

import dgtic.core.model.Entities.HistoriaClinica;
import dgtic.core.model.dto.Response.DoctorResponse;
import dgtic.core.model.dto.Response.HistoriaClinicaResponse;
import dgtic.core.model.dto.Response.HistoriaslClinicasPacienteResponse;

import java.util.List;

public class HistoriasClinicasPacienteMapper {

    public static HistoriaslClinicasPacienteResponse ToDto(dgtic.core.model.Entities.HistoriaClinica historiaClinica){
        if (historiaClinica == null) return null;

        HistoriaslClinicasPacienteResponse response = new HistoriaslClinicasPacienteResponse();
        response.setId(historiaClinica.getId());
        response.setAntecedentesMedicos(historiaClinica.getAntecedentesMedicos());
        response.setDiagnostico(historiaClinica.getDiagnostico());
        response.setDescripcionEnfermedad(historiaClinica.getDescripcionEnfermedad());
        response.setTratamientoMedico(historiaClinica.getTratamientoMedico());
        response.setMotivoDeLaConsulta(historiaClinica.getMotivoDeLaConsulta());
//        DoctorResponse doctor = new DoctorResponse();
        if(historiaClinica.getPaciente() != null)
        {
            response.setDoctor(DoctorMapper.ToDto(historiaClinica.getDoctor()));

        }
        return response;
    }

    public static List<HistoriaslClinicasPacienteResponse> ToCollectionDto(List<HistoriaClinica> historiasClinicas)
    {
        List<HistoriaslClinicasPacienteResponse> responses = historiasClinicas
                .stream()
                .map(HistoriasClinicasPacienteMapper ::ToDto)
                .toList();

        return  responses;
    }
}
