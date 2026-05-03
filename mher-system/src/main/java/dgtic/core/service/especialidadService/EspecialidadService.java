package dgtic.core.service.especialidadService;

import dgtic.core.model.Entities.Especialidad;
import dgtic.core.model.dto.Mappers.EspecialidadMapper;
import dgtic.core.model.dto.Response.EspecialidadResponse;
import dgtic.core.repository.especialidades.IEspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService implements IEspecialidadService{

    @Autowired
    IEspecialidadRepository especialidadRepository;

    @Override
    public List<EspecialidadResponse> findAll() {
        return especialidadRepository.findAll()
                .stream()
                .map(e -> new EspecialidadResponse(
                        e.getId(),
                        e.getNombreEspecialidad()
                ))
                .toList();
    }

    @Override
    public EspecialidadResponse findById(Integer id) {
        Optional<Especialidad> especialidad =  especialidadRepository.findById(id);

        if(!especialidad.isPresent())
        {
            return  null;
        }
        EspecialidadResponse response = EspecialidadMapper.ToDto(especialidad.get());
        return  response;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Page<EspecialidadResponse> findByPage(Pageable pageable) {
        return null;
    }
}
