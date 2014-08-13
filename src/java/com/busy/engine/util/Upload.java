
package com.busy.engine.util;

import com.busy.engine.data.Database;
import com.busy.engine.entity.Site;
import com.oreilly.servlet.multipart.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Upload 
{
    public static String UploadActivity(MultipartParser parserFormData, String associationType, String fileType, String filePath, Site site)
    {    
        byte[] fileDataArray = null;
        com.oreilly.servlet.multipart.Part p = null;
        String id = null;
        
        try
        { 
            System.out.println("Uploading " + fileType + " for " + associationType);
            while((p = parserFormData.readNextPart()) != null)
            {
                if(p.isParam() && p.getName().equals("id"))
                {
                  id = ((ParamPart)p).getStringValue();
                }

                if(p.isFile())
                {
                    FilePart fp = (FilePart)p;
                    InputStream fip = fp.getInputStream();
                    ByteArrayOutputStream bias = new ByteArrayOutputStream(); 

                    if(fp.getFileName() != null)
                    { 
                        int intSize = 0;
                        while((intSize = fip.read()) != -1) 
                        {
                            bias.write((byte)intSize);
                        }
                        if(p.getName().toLowerCase().contains("file"))
                        {
                            if(!fp.getFileName().equals(""))
                            {                                
                                String fileName = fp.getFileName().replaceAll(" ", "_");
                                fileName = (int)(Math.random()*999) + fileName;  //randomize filename to reduce collision
                             
                                fileDataArray = bias.toByteArray();
                                if(fileDataArray != null && fileDataArray.length > 0)
                                {
                                    UploadFile(fileDataArray, fileName, filePath); 
                                    if(associationType.equals("item") && fileType.equals("image"))
                                    {
                                        SaveThumbnail(160, "sm_", fileName, filePath );
                                    }
                                } 
                                
                                //determine where to save the file info in the database
                                if(associationType.equals("item") && fileType.equals("image")){                                    
                                    Database.addItemImage(id , fileName, "sm_" + fileName);
                                }
                                if(associationType.equals("site") && fileType.equals("image")){                                    
                                    Database.addSiteImage("1", fileName, "none", site);
                                }
                                if(associationType.equals("item") && fileType.equals("file")){                                    
                                    Database.addItemFile(id , fileName, "none");
                                }
                                if(associationType.equals("site") && fileType.equals("file")){                                    
                                    Database.addSiteFile(id , fileName, "none", site);
                                }
                            }                    
                        }
                    }
                }
            }
        }
        catch (Exception e) 
        {            
            System.out.println("UploadActivity Error : " + e.getMessage() );
            return null;
        }
        return id;
    }
    
    
    public static boolean UploadFile(byte[] data, String fileName, String filePath)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(filePath  + "/" + fileName);
            System.out.println("wrote file: " + fileName + " to " +  filePath );
            fileOut.write(data);
            fileOut.flush();        
            fileOut.close();        
        }
        catch (Exception e) 
        {            
            System.out.println("Error writing file: " + fileName + " to " +  filePath );
            System.out.println("UploadFile Error : " + e.getMessage() );
            return false;
        }
        return true;
    }
    
    public static boolean SaveThumbnail(int size, String filePrefix, String fileName, String filePath)
    {
        //create a corresponding thumbnail for the file and save it
        try
        {
            //call the sacleImage method.
            BufferedImage newImage = scaleImage(ImageIO.read(new File(filePath , fileName)), size);

            File file = new File(filePath, filePrefix + fileName);
            ImageIO.write(newImage, "JPG", file);
        }
        catch (Exception e)
        {
            System.out.println("Unable to save the " + fileName + " image to " + filePath );
            System.out.println("saveThumbnail Error : " + e.getMessage() );
            return false;
        }
        return true;
    }
    
    private static BufferedImage scaleImage(BufferedImage bufferedImage, int size)
    {
        double boundSize = size;
        int origWidth = bufferedImage.getWidth();
        int origHeight = bufferedImage.getHeight();
        double scale;

        if (origHeight > origWidth)
        {
            scale = boundSize / origHeight;
        }
        else
        {
            //Don't scale up small images
            scale = boundSize / origWidth;
        }
        if (scale > 1.0)
        {
            return (null);
        }
        int scaledWidth = (int) (scale * origWidth);
        int scaledHeight = (int) (scale * origHeight);

        Image scaledImage = bufferedImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH); 

        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = scaledBI.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(scaledImage, 0, 0, null);
        g.dispose();

        return (scaledBI);
    }
}
