package dgtic.core.util;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import dgtic.core.model.Entities.HistoriaClinica;


import java.awt.*;

import java.io.FileOutputStream;

public class HistoriaClinicaPdfGenerator {


    public static void generarPDF(HistoriaClinica historiaClinica) {

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream("historia_clinica.pdf"));

            document.open();

            // Fuentes
            Font titulo = new Font(Font.HELVETICA, 20, Font.BOLD);
            Font subtitulo = new Font(Font.HELVETICA, 13, Font.BOLD);
            Font texto = new Font(Font.HELVETICA, 11);
            Font diagnosticoFont = new Font(Font.HELVETICA, 12, Font.BOLD);

            // Header
            Paragraph header = new Paragraph("HISTORIA CLÍNICA", titulo);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            document.add(new Paragraph("Folio: HC-" + historiaClinica.getId(), texto));
            document.add(new Paragraph(" "));

            // Motivo
            agregarSeccion(document, "MOTIVO DE CONSULTA",
                    historiaClinica.getMotivoDeLaConsulta(), subtitulo, texto);

            // Descripción
            agregarSeccion(document, "DESCRIPCIÓN DE ENFERMEDAD",
                    historiaClinica.getDescripcionEnfermedad(), subtitulo, texto);

            // Antecedentes
            agregarSeccion(document, "ANTECEDENTES MÉDICOS",
                    historiaClinica.getAntecedentesMedicos(), subtitulo, texto);

            // Diagnóstico
            PdfPTable tablaDiagnostico = new PdfPTable(1);
            tablaDiagnostico.setWidthPercentage(100);

            PdfPCell cellTitulo = new PdfPCell(
                    new Phrase("DIAGNÓSTICO", subtitulo));
            cellTitulo.setBackgroundColor(Color.LIGHT_GRAY);
            cellTitulo.setPadding(8);
            tablaDiagnostico.addCell(cellTitulo);

            PdfPCell cellContenido = new PdfPCell(
                    new Phrase(historiaClinica.getDiagnostico(), diagnosticoFont));
            cellContenido.setPadding(10);
            tablaDiagnostico.addCell(cellContenido);

            document.add(tablaDiagnostico);
            document.add(new Paragraph(" "));

            // Tratamiento
            agregarSeccion(document, "TRATAMIENTO MÉDICO",
                    historiaClinica.getTratamientoMedico(), subtitulo, texto);

            // Firma
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph(
                    "Doctor Responsable: ___________________________", texto));
            document.add(new Paragraph(
                    "Firma: _______________________________________", texto));

            document.close();

            System.out.println("PDF generado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void agregarSeccion(
            Document document,
            String titulo,
            String contenido,
            Font fuenteTitulo,
            Font fuenteTexto) throws Exception {

        PdfPTable tabla = new PdfPTable(1);
        tabla.setWidthPercentage(100);

        PdfPCell header = new PdfPCell(new Phrase(titulo, fuenteTitulo));
        header.setBackgroundColor(new Color(230,230,230));
        header.setPadding(8);
        tabla.addCell(header);

        PdfPCell body = new PdfPCell(new Phrase(contenido, fuenteTexto));
        body.setPadding(10);
        tabla.addCell(body);

        document.add(tabla);
        document.add(new Paragraph(" "));
    }




}
