package com.example.demo.commons;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;

public class TextFooterEventHandler implements IEventHandler {
    int account=0;

    @Override
    public void handleEvent(Event event) {

        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfCanvas pdfCanvas = new PdfCanvas(docEvent.getPage());

        Rectangle rectangle = new Rectangle(35, 30, 520, 50);
        pdfCanvas.rectangle(rectangle);

        Canvas canvas = new Canvas(pdfCanvas, rectangle).setFontSize(7);
        // bottom line
        canvas.add(new Paragraph("---------------------------------------------------------------------"));

        // footer text
        canvas.add(new Paragraph("My custom footer line goes here."+ account+1));
    }
}
