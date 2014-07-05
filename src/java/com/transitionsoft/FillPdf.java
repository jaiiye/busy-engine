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
import com.itextpdf.text.pdf.PdfStamper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;


public class FillPdf extends HttpServlet {
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String DATASHEET = getServletContext().getRealPath("files-site/")  + "/" + "datasheet.pdf";
        String RESULT = getServletContext().getRealPath("files-site/")  + "/" + "result.pdf"; 
           
        PdfReader reader;
        PdfStamper stamper;
        // Fill out the data sheet form with data
        response.setContentType("application/pdf");
        try {
            reader = new PdfReader(DATASHEET);
            //stamper = new PdfStamper(reader, new FileOutputStream(RESULT)); //writes to file
            stamper = new PdfStamper(reader, response.getOutputStream()); //writes to the page!
            
            stamper.getAcroFields().setField("title", request.getParameter("name"));

            stamper.close();
            reader.close(); 
            
            
//            InputStream is = getServletContext().getResourceAsStream(RESULT);
//            PdfReader reader2 = new PdfReader(is);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            PdfStamper stamper2 = new PdfStamper(reader2, baos);
//            stamper2.close();
//            reader2.close();
//            OutputStream os = response.getOutputStream();
//            baos.writeTo(os);
//            os.flush();

        }
        catch (DocumentException de) {
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
