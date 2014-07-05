package com.transitionsoft;

import java.util.Date;
import java.util.Set;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import java.io.BufferedReader;
import java.io.FileReader;


public class MakePdf extends HttpServlet {
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String DATASHEET = getServletContext().getRealPath("files-site/")  + "/" + "datasheet.pdf";
        String RESULT = getServletContext().getRealPath("files-site/")  + "/" + "datasheet.txt"; 
           
        // Create a writer for the report file
        PrintWriter writer = new PrintWriter(new FileOutputStream(RESULT));
        
        // Create a reader to extract info
        PdfReader reader = new PdfReader(DATASHEET);
        
        // Get the fields from the reader (read-only!!!)
        AcroFields form = reader.getAcroFields();
        
        // Loop over the fields and get info about them
        Set<String> fields = form.getFields().keySet();
        
        for (String key : fields) {
            writer.print(key + ": ");
            switch (form.getFieldType(key)) {
            case AcroFields.FIELD_TYPE_CHECKBOX:
                writer.println("Checkbox");
                break;
            case AcroFields.FIELD_TYPE_COMBO:
                writer.println("Combobox");
                break;
            case AcroFields.FIELD_TYPE_LIST:
                writer.println("List");
                break;
            case AcroFields.FIELD_TYPE_NONE:
                writer.println("None");
                break;
            case AcroFields.FIELD_TYPE_PUSHBUTTON:
                writer.println("Pushbutton");
                break;
            case AcroFields.FIELD_TYPE_RADIOBUTTON:
                writer.println("Radiobutton");
                break;
            case AcroFields.FIELD_TYPE_SIGNATURE:
                writer.println("Signature");
                break;
            case AcroFields.FIELD_TYPE_TEXT:
                writer.println("Text");
                break;
            default:
                writer.println("?");
            }
        }
        
        // Get possible values for field "CP_1"
        writer.println("Possible values for CP_1:");
        String[] states = form.getAppearanceStates("CP_1");
        for (int i = 0; i < states.length; i++) {
            writer.print(" - ");
            writer.println(states[i]);
        }
        
        // Get possible values for field "category"
        writer.println("Possible values for category:");
        states = form.getAppearanceStates("category");
        for (int i = 0; i < states.length - 1; i++) {
            writer.print(states[i]);
            writer.print(", ");
        }
        
        writer.println(states[states.length - 1]);
        
        // flush and close the report file
        writer.flush();
        writer.close();
        
        reader.close();
        
        String everything;
        BufferedReader br = new BufferedReader(new FileReader(RESULT));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            br.close();
        }
        
        response.setContentType("application/pdf");
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph(request.getParameter("name")));
            document.add(new Paragraph(new Date().toString()));
            document.add(new Paragraph(everything));
            document.close();
        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
