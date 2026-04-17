package dgtic.core.service.recetarioService;

import dgtic.core.model.dto.Request.RecetaRequest;
import dgtic.core.model.dto.Response.RecetaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRecetarioService {

    RecetaResponse findById(Integer id);
    RecetaResponse save(RecetaRequest request);
    void deleteById(Integer id);
    RecetaResponse update(RecetaRequest request);
    RecetaResponse findByPacienteIdAndRecetaId(Integer pacienteId, Integer recetaId);
}
