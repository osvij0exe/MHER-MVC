package dgtic.core.service.historiaClinicaService;

import dgtic.core.model.Entities.HistoriaClinica;
import dgtic.core.model.dto.Mappers.HistoriaClinicaMapper;
import dgtic.core.model.dto.Request.HistoriaClinicaRequest;
import dgtic.core.model.dto.Response.HistoriaClinicaResponse;
import dgtic.core.repository.historiasClinicas.IHistoriaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import dgtic.core.model.Entities.HistoriaClinica;

import java.io.ByteArrayOutputStream;


@Service
public class HistoriaClinicaService implements IHistoriaClinicaService{

    @Autowired
    private IHistoriaClinicaRepository historiaClinicaRepository;


    @Override
    public HistoriaClinica findById(Integer id) {
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(id).orElse(null);
//        return HistoriaClinicaMapper.ToDto(historiaClinica);

        return historiaClinica;
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


    public byte[] generarPdf(HistoriaClinica historiaClinica) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, out);

            document.open();

            Font titulo = new Font(Font.HELVETICA, 20, Font.BOLD);
            Font subtitulo = new Font(Font.HELVETICA, 13, Font.BOLD);
            Font texto = new Font(Font.HELVETICA, 11);

            Paragraph header = new Paragraph("HISTORIA CLÍNICA", titulo);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            document.add(new Paragraph("Folio: HC-" + historiaClinica.getId(), texto));
            document.add(new Paragraph(" "));

            agregarSeccion(document, "Motivo de consulta", historiaClinica.getMotivoDeLaConsulta(), subtitulo, texto);
            agregarSeccion(document, "Descripción enfermedad", historiaClinica.getDescripcionEnfermedad(), subtitulo, texto);
            agregarSeccion(document, "Antecedentes médicos", historiaClinica.getAntecedentesMedicos(), subtitulo, texto);
            agregarSeccion(document, "Diagnóstico", historiaClinica.getDiagnostico(), subtitulo, texto);
            agregarSeccion(document, "Tratamiento médico", historiaClinica.getTratamientoMedico(), subtitulo, texto);

            document.close();

            return out.toByteArray();

        } catch (Exception ex) {
            throw new RuntimeException("Error generando PDF", ex);
        }
    }

    private void agregarSeccion(
            Document document,
            String titulo,
            String contenido,
            Font fuenteTitulo,
            Font fuenteTexto
    ) throws Exception {

        PdfPTable tabla = new PdfPTable(1);
        tabla.setWidthPercentage(100);

        PdfPCell tituloCell = new PdfPCell(new Phrase(titulo, fuenteTitulo));
        tituloCell.setPadding(8);
        tabla.addCell(tituloCell);

        PdfPCell contenidoCell = new PdfPCell(new Phrase(contenido, fuenteTexto));
        contenidoCell.setPadding(10);
        tabla.addCell(contenidoCell);

        document.add(tabla);
        document.add(new Paragraph(" "));
    }
}
