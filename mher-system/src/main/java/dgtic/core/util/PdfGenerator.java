package dgtic.core.util;


import com.lowagie.text.*;
import com.lowagie.text.Font;

import com.lowagie.text.pdf.PdfWriter;
import dgtic.core.model.Entities.HistoriaClinica;
import dgtic.core.model.Entities.Recetario;
import dgtic.core.util.common.PdfSections;


import java.io.ByteArrayOutputStream;

public class PdfGenerator {


    public static byte[] historiaClinicaPdfGenerator(HistoriaClinica historiaClinica) {

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();


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

            PdfSections.addSection(document, "Motivo de consulta", historiaClinica.getMotivoDeLaConsulta(), subtitulo, texto);
            PdfSections.addSection(document, "Descripción enfermedad", historiaClinica.getDescripcionEnfermedad(), subtitulo, texto);
            PdfSections.addSection(document, "Antecedentes médicos", historiaClinica.getAntecedentesMedicos(), subtitulo, texto);
            PdfSections.addSection(document, "Diagnóstico", historiaClinica.getDiagnostico(), subtitulo, texto);
            PdfSections.addSection(document, "Tratamiento médico", historiaClinica.getTratamientoMedico(), subtitulo, texto);

            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public  static  byte[] recetaPdfGenerator(Recetario receta){
        Document document = new Document(PageSize.A4,50,50,50,50);

        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

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

            PdfSections.addSection(document,"Paciente",receta.getPaciente().getNombre() + " " + receta.getPaciente().getApellidos(),subtitulo,texto);
            PdfSections.addSection(document,"indicacion Medica",receta.getIndicacionMedica(), subtitulo,texto);
            PdfSections.addSection(document, "Via de administracion", receta.getViaDeAdministracion(),subtitulo,texto);
            PdfSections.addSection(document,"Duracion del tratamiento",receta.getDuracionDelTratamiento(),subtitulo,texto);
            PdfSections.addSection(document,"Medico tratante",receta.getDoctor().getNombre() + " " +receta.getDoctor().getApellidos(),subtitulo,texto);
            PdfSections.addSection(document,"Cedula profesional",receta.getDoctor().getCedulaProfesional(),subtitulo,texto);

            document.close();

            return  outputStream.toByteArray();


        } catch (Exception ex){
            throw new RuntimeException("Error generando PDF", ex);
        }
    }

}
