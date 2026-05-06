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
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4,50,50,50,50);

            PdfWriter.getInstance(document,outputStream);

            document.open();

            Font titulo = new Font(Font.HELVETICA, 20, Font.BOLD);
            Font subtitulo = new Font(Font.HELVETICA, 13, Font.BOLD);
            Font texto = new Font(Font.HELVETICA, 11);

            Paragraph header = new Paragraph("Receta Médica", titulo);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            document.add(new Paragraph("Folio: R-"+ receta.getId(), texto));
            document.add(new Paragraph(" "));

            agregarSeccion(document,"Paciente",receta.getPaciente().getNombre() + " " + receta.getPaciente().getApellidos(),subtitulo,texto);
            agregarSeccion(document,"indicacion Medica",receta.getIndicacionMedica(), subtitulo,texto);
            agregarSeccion(document, "Via de administracion", receta.getViaDeAdministracion(),subtitulo,texto);
            agregarSeccion(document,"Duracion del tratamiento",receta.getDuracionDelTratamiento(),subtitulo,texto);
            agregarSeccion(document,"Medico tratante",receta.getDoctor().getNombre() + " " +receta.getDoctor().getApellidos(),subtitulo,texto);
            agregarSeccion(document,"Cedula profesional",receta.getDoctor().getCedulaProfesional(),subtitulo,texto);

            document.close();

            return  outputStream.toByteArray();


        } catch (Exception ex){
            throw new RuntimeException("Error generando PDF", ex);
        }


    }

    private void  agregarSeccion(
            Document document,
            String titulo,
            String contenido,
            Font fuenteTitulo,
            Font fuenteTexto
    ) throws Exception{
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell tituloCell = new PdfPCell(new Phrase(titulo,fuenteTitulo));
        tituloCell.setPadding(8);
        table.addCell(tituloCell);

        PdfPCell contenidoCell = new PdfPCell(new Phrase(contenido,fuenteTexto));
        contenidoCell.setPadding(10);
        table.addCell(contenidoCell);
        document.add(table);
        document.add(new Paragraph(" "));
    }

}
