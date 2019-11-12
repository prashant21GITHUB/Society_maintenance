package com.maintenance.reports;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bajpai
 */
public class PdfReportGenerator {
    
    public static void main(String[] args) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("reports/HelloWorld.pdf"));
            document.open();
            document.setPageSize(PageSize.A4);
            addHeader(document);
            
            document.addAuthor("Panoasis Maintenance");
            document.addTitle("Maintenance Requests Report");
           
            Image logo = Image.getInstance("pan-oasis-logo.png");
            logo.setAlignment(Image.ALIGN_RIGHT);
//            logo.setAlignment(Image.ALIGN_TOP);
            document.add(logo);
            generateTable(document);
            document.close();
            System.out.println("pdf created");
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(PdfReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void generateTable(Document document) {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table
        
        //Set Column widths
        float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f};
        try {
            
            table.setWidths(columnWidths);
            boolean grayFill = false;
            for(int i=1; i<=250; i++) {
                
                PdfPCell cell = new PdfPCell();
                cell.setFixedHeight(20f);
                cell.setPadding(2f);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                if(grayFill) {
                    cell.setBackgroundColor(new BaseColor(238, 245, 240));
                }
                grayFill = !grayFill;
                cell.setPhrase(new Phrase(i+"."));
                table.addCell(cell);
                cell.setPhrase(new Phrase("03 Jan, 2017"));
                table.addCell(cell);
                cell.setPhrase(new Phrase("B - 305"));
                table.addCell(cell);
                cell.setPhrase(new Phrase("Plumber"));
                table.addCell(cell);
                cell.setPhrase(new Phrase("New"));
                table.addCell(cell);
                cell.setPhrase(new Phrase("Close"));
                table.addCell(cell);
            }
//            table.addCell(new PdfPCell(new Phrase("03 Jan, 2017")));
//            table.addCell(new PdfPCell(new Phrase("1")));
//            table.addCell(new PdfPCell(new Phrase("B - 305")));
//            table.addCell(new PdfPCell(new Phrase("Plumber")));
//            table.addCell(new PdfPCell(new Phrase("Close")));
            document.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(PdfReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void addHeader(Document document) {
        Paragraph header = new Paragraph();
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
        header.setFont(boldFont);
        header.add("MAINTENANCE REQUESTS");
        header.setAlignment(Paragraph.ALIGN_CENTER);
        
        try {
            document.add(header);
        } catch (DocumentException ex) {
            Logger.getLogger(PdfReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
