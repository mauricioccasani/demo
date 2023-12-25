package com.example.demo.service;

import com.example.demo.commons.Header;
import com.example.demo.commons.HeaderHandler;
import com.example.demo.commons.TextFooterEventHandler;
import com.example.demo.commons.TextHeaderEventHandler;
import com.example.demo.dto.DocumentoTipoEnum;
import com.example.demo.dto.TipoDocumentoDto;
import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DoubleBorder;
import com.itextpdf.layout.borders.GrooveBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DocumentoPdf {


    //public static final String DEST = "./target/sandbox/tables/simple_table.pdf";
    public static final String DEST = "./target/sandbox/tables/simple_table03.pdf";
    public static final String SRC = "./src/main/resources/pdfs/form.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        //listDoc();
        //textoPdf(DEST);
        //documentoPdf(DEST);
        tbl(DEST);

    }

    private static void documentoPdf(String url) throws java.io.IOException {
        PdfWriter pdfWriter = new PdfWriter(url);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument, PageSize.A4);
        String header = "pdfHtml Header and footer example using page-events";

        Header headerHandler = new Header(header);
        pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE,headerHandler);

        //pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new HeaderFooterPageEvent(document));
        //pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new HeaderFooterPageEvent(document));
        //document.setMargins(80, 36, 80, 36);
        //pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new TextHeaderEventHandler());
        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new TextFooterEventHandler());
        float columna = 280F;
        float columnaAncho[] = {columna, columna};
        Table table = new Table(columnaAncho);
        table.setBackgroundColor(new DeviceRgb(15, 148, 132)).setFontColor(ColorConstants.WHITE);
        table.addCell(new Cell().add(new Paragraph("FACTURA")).setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(Border.NO_BORDER)
        );
        table.addCell(new Cell().add(new Paragraph("Mauricio Ccasani Olivares\n #98765432\n Ruc:1234567H"))
                .setTextAlignment(TextAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                //.setFontSize(30f)
                .setBorder(Border.NO_BORDER)
                .setMarginRight(10f)
        );

        float columnaDetalleAncho[] = {80, 150, 100, 300};
        Table tablaClienteInfo = new Table(columnaDetalleAncho);
        tablaClienteInfo.addCell(new Cell(0, 4).add(new Paragraph("Informacion cliente")).setBold().setBorder(Border.NO_BORDER));

        tablaClienteInfo.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Nombre: ")));
        tablaClienteInfo.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Juana Prez")));
        tablaClienteInfo.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Factura Nro: ")));
        tablaClienteInfo.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("48857DHO00")));

        tablaClienteInfo.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Mo. No")));
        tablaClienteInfo.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("3485885958")));
        tablaClienteInfo.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Fecha: ")));
        tablaClienteInfo.addCell(new Cell().add(new Paragraph(LocalDateTime.now().toString())));

        String texto = "\n" +
                "The standard Lorem Ipsum passage, used since the 1500s\n" +
                "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"\n" +
                "\n" +
                "Section 1.10.32 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" +
                "\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\"\n" +
                "\n" +
                "1914 translation by H. Rackham\n" +
                "\"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?\"\n" +
                "\n" +
                "Section 1.10.33 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" +
                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\"\n" +
                "\n" +
                "1914 translation by H. Rackham\n" +
                "\"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.\"" ;

        Paragraph paragraph = new Paragraph(texto);

        document.add(table);
        document.add(new Paragraph(""));
        document.add(tablaClienteInfo);
        document.add(new Paragraph("\n"));
        document.add(paragraph);
       // document.add(footer());

        document.close();


    }
    public static void tbl(String url) throws FileNotFoundException {
        PdfWriter pdfWriter = new PdfWriter(url);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument, PageSize.A4);

        HeaderHandler handler = new HeaderHandler(document);
        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, handler);
        document.setMargins(20 + handler.getTableHeight(), 36, 36, 36);

        document.add(new Paragraph(""));

        Table table = new Table(new float[] { 1.4F, 0.5F, 1.4F, 1.0F, 0.7F })
                .setWidth(UnitValue.createPercentValue(100))
                .addHeaderCell(new Cell().add(new Paragraph("Código:")))
                .addHeaderCell(new Cell().add(new Paragraph("Nombres:")))
                .addHeaderCell(new Cell().add(new Paragraph("Genero:")))
                .addHeaderCell(new Cell().add(new Paragraph("Nro Dni:")))
                .addHeaderCell(new Cell().add(new Paragraph("Estado:")));
        List<TipoDocumentoDto>tipoDocumentoDtos= listTipoDoc();

        for (TipoDocumentoDto tipoDocumentoDto: tipoDocumentoDtos) {
            table.addCell(new Cell().add(new Paragraph(tipoDocumentoDto.getCodigo())));
            table.addCell(new Cell().add(new Paragraph(tipoDocumentoDto.getPersona())));
            table.addCell(new Cell().add(new Paragraph(tipoDocumentoDto.getGenero())));
            table.addCell(new Cell().add(new Paragraph(tipoDocumentoDto.getDni())));
            table.addCell(new Cell().add(new Paragraph(tipoDocumentoDto.getEstado())));
        }
        document.add(table);
        document.add(new AreaBreak());
        document.add(new AreaBreak());
        document.add(new AreaBreak());
        document.close();

    }

    private static Paragraph footer() throws IOException, java.io.IOException {
        PdfFont ffont = PdfFontFactory.createFont();
        Paragraph p = new Paragraph("this is a footer>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
                .setFont(ffont)
                .setFontSize(5)
                .setItalic();
        return p;
    }

    protected static void manipulatePdf2(String dest) throws Exception {
        String[] cab = {"Nombres", "Apellidos", "Genero", "Pais", "Edad", "Estado civil"};
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, PageSize.A4);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Border border = new GrooveBorder(new DeviceRgb(200, 7, 4), 3);
        Table table = new Table(new float[]{10, 5, 2, 4, 5, 1});//(UnitValue.createPercentArray(16)).useAllAvailableWidth();
        for (String cabecera : cab) {
            Cell cell = new Cell().add(new Paragraph(cabecera)
                            .setFont(font)
                            .setFontColor(ColorConstants.GRAY))
                    .setBorder(new DoubleBorder(new DeviceRgb(130, 70, 45), 5f));
            table.addCell(cell);
        }

        doc.add(table);

        doc.close();
    }

    public static void textoPdf(String url) throws FileNotFoundException {
        String texto = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        Paragraph paragraph = new Paragraph(texto);
        PdfWriter pdfWriter = new PdfWriter(url);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();
        //Create event-handlers
        String header = "pdfHtml Header and footer example using page-events";
        Header headerHandler = new Header(header);
        //PageXofY footerHandler = new PageXofY(pdfDocument);

        pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE,headerHandler);
        Document document = new Document(pdfDocument);

        document.add(paragraph);
        document.close();

    }

    private static List<TipoDocumentoDto> listDoc() {
        List<TipoDocumentoDto> newList = new ArrayList<>();
        List<TipoDocumentoDto> list = listTipoDoc();
        list.forEach(tipoDocumentoDto -> {
            DocumentoTipoEnum tipoEnum = DocumentoTipoEnum.getCodigo(tipoDocumentoDto.getCodigo());
            if (tipoEnum.getDescripcion().equals(tipoDocumentoDto.getCodigo())) {
                if (valid(tipoEnum.getPattern(), tipoDocumentoDto.getDni())) {
                    newList.add(tipoDocumentoDto);
                }
            }
        });
        newList.forEach(System.out::println);
        return newList;
    }

    private static Boolean valid(String value, String patter) {
        Pattern pattern = Pattern.compile(value);
        Matcher matcher = pattern.matcher(patter);
        return matcher.matches();
    }

    private static List<TipoDocumentoDto> listTipoDoc() {
        return Arrays.asList(new TipoDocumentoDto("DNI", "juan", "M", "123456780","A"),
                new TipoDocumentoDto("RUC", "Maria", "F", "0987543098","A"),
                new TipoDocumentoDto("DNI", "Doe", "M", "70234567","A")
        );
    }


}
