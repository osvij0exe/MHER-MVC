package dgtic.core.util.common;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class PdfSections {

    public  static void addSection(
            Document document,
            String titulo,
            String contenido,
            Font fuenteTitulo,
            Font fuenteTexto)
        throws Exception{
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
