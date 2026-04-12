package dgtic.core.model.dto.Mappers;

import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.Entities.Paciente;
import dgtic.core.model.Entities.Recetario;
import dgtic.core.model.dto.Request.RecetaRequest;
import dgtic.core.model.dto.Response.DoctorResponse;
import dgtic.core.model.dto.Response.RecetaResponse;

import java.util.List;

public class RecetaMapper {

    public static Recetario ToEntity(RecetaRequest request)
    {
        if(request == null) return null;

        Recetario recetario = new Recetario();
        recetario.setFechaEmision(request.getFechaEmision());
        recetario.setIndicacionMedica(request.getIndicacionMedica());
        recetario.setViaDeAdministracion(request.getViaDeAdministracion());
        recetario.setDuracionDelTratamiento(request.getDuracionDelTratamiento());

        Doctor doctor = new Doctor();
        doctor.setId(request.getDoctorId());
        recetario.setDoctor(doctor);
        Paciente paciente = new Paciente();
        paciente.setId(request.getPacienteId());
        recetario.setPaciente(paciente);

        return recetario;
    }

    public static RecetaResponse ToDto(Recetario recetario)
    {
        if(recetario == null) return null;

        RecetaResponse receta = new RecetaResponse();
        receta.setId(recetario.getId());
        receta.setFechaEmision(recetario.getFechaEmision());
        receta.setIndicacionMedica(recetario.getIndicacionMedica());
        receta.setViaDeAdministracion(recetario.getViaDeAdministracion());
        receta.setDuracionDelTratamiento(recetario.getDuracionDelTratamiento());
        if(recetario.getDoctor() != null)
        {
            receta.setDoctor(DoctorMapper.ToDto(recetario.getDoctor()));
        }
        if(recetario.getPaciente() != null)
        {
            receta.setPaciente(PacienteMapper.ToDto(recetario.getPaciente()));
        }
        return  receta;
    }


    public static List<RecetaResponse> ToDtoCollection(List<Recetario> recetarios)
    {
        List<RecetaResponse> responses = recetarios
                .stream()
                .map(RecetaMapper ::ToDto)
                .toList();

        return responses;
    }



}
