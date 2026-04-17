package dgtic.core.service.recetarioService;

import dgtic.core.model.Entities.Recetario;
import dgtic.core.model.dto.Mappers.RecetaMapper;
import dgtic.core.model.dto.Request.RecetaRequest;
import dgtic.core.model.dto.Response.RecetaResponse;
import dgtic.core.repository.recetarios.IRecetarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetarioService implements IRecetarioService{

    @Autowired
    private IRecetarioRepository recetarioRepository;

    @Override
    public RecetaResponse findById(Integer id) {
        Recetario recetario = recetarioRepository.findById(id).orElse(null);

        return RecetaMapper.ToDto(recetario);
    }

    @Override
    public RecetaResponse save(RecetaRequest request) {
        Recetario recetario = RecetaMapper.ToEntity(request);

        Recetario savedRecetario = recetarioRepository.save(recetario);

        return RecetaMapper.ToDto(savedRecetario);
    }

    @Override
    public void deleteById(Integer id) {
        recetarioRepository.deleteById(id);
    }

    @Override
    public RecetaResponse update(RecetaRequest request) {
        Recetario recetario = RecetaMapper.ToEntity(request);

        Recetario updatedRecetario = recetarioRepository.save(recetario);

        return RecetaMapper.ToDto(updatedRecetario);
    }

    @Override
    public RecetaResponse findByPacienteIdAndRecetaId(Integer pacienteId, Integer recetaId) {
        Recetario receta = recetarioRepository.findByPacienteIdAndRecetarioId(pacienteId, recetaId);
        return  RecetaMapper.ToDto(receta);
    }
}
