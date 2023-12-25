package com.example.demo.service;

import com.example.demo.commons.ReporteHandler;
import com.example.demo.dto.TipoDocumentoDto;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class TipoDocumentoReporteService {
    //public Document document;
    public static final String DEST = "./target/sandbox/tables/simple_table04.pdf";



    public TipoDocumentoReporteService() {

    }

    public ByteArrayOutputStream documentoReporte() throws FileNotFoundException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(outputStream);


        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument, PageSize.A4);
        document.setMargins(70, 36, 36, 36);

        ReporteHandler reporteHandler = new ReporteHandler(document, "Titulo");
        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, reporteHandler);
        Table table = new Table(new float[]{1.4F, 0.5F, 1.4F, 1.0F, 0.7F})
                .setWidth(UnitValue.createPercentValue(100))
                .addHeaderCell(new Cell().add(new Paragraph("Código")).setBackgroundColor(this.color()).setTextAlignment(TextAlignment.CENTER))
                .addHeaderCell(new Cell().add(new Paragraph("Nombres")).setBackgroundColor(this.color()).setTextAlignment(TextAlignment.CENTER))
                .addHeaderCell(new Cell().add(new Paragraph("Genero")).setBackgroundColor(this.color()).setTextAlignment(TextAlignment.CENTER))
                .addHeaderCell(new Cell().add(new Paragraph("Nro Dni")).setBackgroundColor(this.color()).setTextAlignment(TextAlignment.CENTER))
                .addHeaderCell(new Cell().add(new Paragraph("Estado")).setBackgroundColor(this.color()).setTextAlignment(TextAlignment.CENTER));

        List<TipoDocumentoDto> tipoDocumentoDtos = listTipoDoc();

        for (TipoDocumentoDto tipoDocumentoDto: tipoDocumentoDtos) {
            table.addCell(new Cell().add(new Paragraph(tipoDocumentoDto.getCodigo())));
            table.addCell(new Cell().add(new Paragraph(tipoDocumentoDto.getPersona())));
            table.addCell(new Cell().add(new Paragraph(tipoDocumentoDto.getGenero())));
            table.addCell(new Cell().add(new Paragraph(tipoDocumentoDto.getDni())));
            table.addCell(new Cell().add(new Paragraph(tipoDocumentoDto.getEstado())));
        }


        document.add(table);
        document.add(new AreaBreak());

        document.close();

       // String pdf_= String.valueOf(document);
      //  return Base64.getDecoder().decode(pdf_);
        return outputStream;
    }
    public DeviceRgb color(){
        return new DeviceRgb(15, 148, 132);
    }
    private static List<TipoDocumentoDto> listTipoDoc() {
        return Arrays.asList(new TipoDocumentoDto("DNI", "juan", "M", "123456780", "A"),
                new TipoDocumentoDto("RUC", "Maria", "F", "0987543098", "A"),
                new TipoDocumentoDto("DNI", "Doe", "M", "70234567", "A")
        );
    }


}
