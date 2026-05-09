package dgtic.core.service.historiaClinicaService;

import dgtic.core.model.Entities.HistoriaClinica;
import dgtic.core.model.dto.Request.HistoriaClinicaRequest;
import dgtic.core.model.dto.Response.HistoriaClinicaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IHistoriaClinicaService {

    HistoriaClinica findById(Integer id);
    HistoriaClinicaResponse findHistoriaById(Integer id);
    HistoriaClinicaResponse save(HistoriaClinicaRequest reqeust);
    void deleteById(Integer id);
    HistoriaClinicaResponse update(HistoriaClinicaRequest reqeust);

    public byte[] generarPdf(HistoriaClinica historiaClinica);
}
