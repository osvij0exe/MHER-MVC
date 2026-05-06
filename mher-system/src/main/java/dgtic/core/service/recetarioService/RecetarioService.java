package dgtic.core.service.recetarioService;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import dgtic.core.model.Entities.Recetario;
import dgtic.core.model.dto.Mappers.RecetaMapper;
import dgtic.core.model.dto.Request.RecetaRequest;
import dgtic.core.model.dto.Response.RecetaResponse;
import dgtic.core.repository.recetarios.IRecetarioRepository;
import dgtic.core.util.PdfGenerator;
import dgtic.core.util.common.PdfSections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class RecetarioService implements IRecetarioService{

    @Autowired
    private IRecetarioRepository recetarioRepository;

    @Override
    public Recetario findById(Integer id) {
        Recetario recetario = recetarioRepository.findById(id).orElse(null);

//        return RecetaMapper.ToDto(recetario);
        return  recetario;
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

    @Override
    public byte[] generatePdf(Recetario receta) {
      byte[] pdf = PdfGenerator.recetaPdfGenerator(receta);
      return  pdf;
    }

}
