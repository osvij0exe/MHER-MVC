package dgtic.core.service.especialidadService;

import dgtic.core.model.dto.Response.EspecialidadResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEspecialidadService {
    List<EspecialidadResponse> findAll();
    EspecialidadResponse findById(Integer id);
//    EspecialidadResponse save(EspecialidadRequest clienteDTO);
    void deleteById(Integer id);
    Page<EspecialidadResponse> findByPage(Pageable pageable);
}
