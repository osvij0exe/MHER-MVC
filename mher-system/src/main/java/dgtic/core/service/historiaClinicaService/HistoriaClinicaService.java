package dgtic.core.service.historiaClinicaService;

import dgtic.core.model.Entities.HistoriaClinica;
import dgtic.core.model.dto.Mappers.HistoriaClinicaMapper;
import dgtic.core.model.dto.Request.HistoriaClinicaRequest;
import dgtic.core.model.dto.Response.HistoriaClinicaResponse;
import dgtic.core.auth.repository.historiasClinicas.IHistoriaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoriaClinicaService implements IHistoriaClinicaService{

    @Autowired
    private IHistoriaClinicaRepository historiaClinicaRepository;


    @Override
    public HistoriaClinicaResponse findById(Integer id) {
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(id).orElse(null);
        return HistoriaClinicaMapper.ToDto(historiaClinica);

    }

    @Override
    public HistoriaClinicaResponse save(HistoriaClinicaRequest reqeust) {
        HistoriaClinica historiaClinica = HistoriaClinicaMapper.ToEntity(reqeust);
        HistoriaClinica historiaClinicaSaved = historiaClinicaRepository.save(historiaClinica);
        return HistoriaClinicaMapper.ToDto(historiaClinicaSaved);
    }

    @Override
    public void deleteById(Integer id) {
        historiaClinicaRepository.deleteById(id);
    }

    @Override
    public HistoriaClinicaResponse update(HistoriaClinicaRequest reqeust) {
        HistoriaClinica historiaClinica = HistoriaClinicaMapper.ToEntity(reqeust);
        HistoriaClinica historiaClinicaUpdated = historiaClinicaRepository.save(historiaClinica);
        return HistoriaClinicaMapper.ToDto(historiaClinicaUpdated);
    }
}
