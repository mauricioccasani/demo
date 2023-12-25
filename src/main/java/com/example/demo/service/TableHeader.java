package com.example.demo.service;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.renderer.TableRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

public class TableHeader {
    public static final String DEST = "./target/sandbox/tables/simple_table_2.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableHeader().manipulatePdf(DEST);
        // test();
    }

    public static String test() {
        int i;
        String value = null;
        for (i = 0; i < 100; i++) {
            value = String.format("%03", i + 42);
            System.out.println(value);
        }
        return value;
    }


    public void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, PageSize.A4);

        TableHeaderEventHandler handler = new TableHeaderEventHandler(doc);
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, handler);
        doc.setMargins(20 + handler.getTableHeight(), 36, 36, 36);
        for (int i = 0; i < 50; i++) {
            doc.add(new Paragraph("Hello World!"));
        }
        doc.add(new AreaBreak());
        doc.add(new Paragraph("Hello World>>>>>>>>>>>>>>>>>>>>>>>>!"));
        doc.add(new AreaBreak());
        doc.add(new Paragraph("Hello World!#############################"));

        doc.close();
    }


    public static class TableHeaderEventHandler implements IEventHandler {
        protected Table table;
        protected float tableHeight;
        protected Document doc;

        public TableHeaderEventHandler(Document doc) {
            this.doc = doc;
            table = new Table(2);
            table.setBackgroundColor(new DeviceRgb(15, 148, 132)).
                    setFontColor(ColorConstants.WHITE);

            table.setWidth(523);
            table.addCell(new Cell(2, 0).add(new Paragraph("Mauricio"))
                    .setBorder(Border.NO_BORDER));

            table.addCell(new Cell().add(new Paragraph(LocalDate.now().toString()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBorder(Border.NO_BORDER));

            table.addCell(new Cell().add(new Paragraph("Nro: " + UUID.randomUUID().getMostSignificantBits()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBorder(Border.NO_BORDER));

            TableRenderer renderer = (TableRenderer) table.createRendererSubTree();
            renderer.setParent(new Document(new PdfDocument(new PdfWriter(new ByteArrayOutputStream()))).getRenderer());
            tableHeight = renderer.layout(new LayoutContext(new LayoutArea(0, PageSize.A4))).getOccupiedArea().getBBox().getHeight();
        }

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
            Rectangle rect = new Rectangle(pdfDoc.getDefaultPageSize().getX() + doc.getLeftMargin(),
                    pdfDoc.getDefaultPageSize().getTop() - doc.getTopMargin(), 100, getTableHeight());
            new Canvas(canvas, rect, true)
                    .add(table);
        }

        public float getTableHeight() {
            return tableHeight;
        }
    }
}