package com.example.demo.commons;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;

public class TextHeaderEventHandler implements IEventHandler {

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfCanvas pdfCanvas = new PdfCanvas(docEvent.getPage());

        Rectangle rectangle = new Rectangle(35, 740, 520, 100);
        pdfCanvas.rectangle(rectangle);

        Canvas canvas = new Canvas(pdfCanvas, rectangle).setFontSize(7);
        // load logo image here and add
        // canvas.add(image);

        canvas.add(new Paragraph("My custom header line goes here."));

        // bottom line
        canvas.add(new Paragraph("---------------------------------------------------------------------"));
    }
}
