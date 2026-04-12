package dgtic.core.model.dto.Mappers;

import dgtic.core.model.Entities.Recetario;
import dgtic.core.model.dto.Response.DoctorResponse;
import dgtic.core.model.dto.Response.RecetasPacienteResponse;

import java.util.List;

public class RecetasPacienteMapper {


    public static RecetasPacienteResponse ToDto(Recetario recetario)
    {
        if( recetario == null) return null;

        RecetasPacienteResponse receta = new RecetasPacienteResponse();

        receta.setId(recetario.getId());
        receta.setFechaEmision(recetario.getFechaEmision());
        receta.setViaDeAdministracion(recetario.getViaDeAdministracion());
        receta.setDuracionDelTratamiento(recetario.getDuracionDelTratamiento());
        receta.setIndicacionMedica(recetario.getIndicacionMedica());
        if(recetario.getDoctor() != null)
        {
            DoctorResponse doctorResposne = DoctorMapper.ToDto(recetario.getDoctor());
            receta.setDoctor(doctorResposne);
        }

        return receta;
    }

    public static List<RecetasPacienteResponse> ToDtoCollection(List<Recetario> recetarios)
    {
        List<RecetasPacienteResponse> responses = recetarios
                .stream()
                .map(RecetasPacienteMapper ::ToDto)
                .toList();

        return responses;
    }



}
