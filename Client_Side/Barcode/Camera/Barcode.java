package Camera;       


import java.io.File;                                                                                                                                         
import java.io.IOException;                                            
import com.google.zxing.BarcodeFormat;                                                                                                                             
import com.google.zxing.MultiFormatWriter;                                                                                                                         
import com.google.zxing.WriterException;                                                                                                                           
import com.google.zxing.client.j2se.MatrixToImageWriter;                                                                                                           
import com.google.zxing.common.BitMatrix;                                                                                                                    
                                                                                                                                                                   
public class Barcode {                                                                                                                                       
    public static void Generator(String data, String name) {                                                                                                       
    	//variable for Image format
    	String format = "jpg";                                                                                                                                     
        File file = new File(name);
        //variable for Image's size
        int width = 10;                                                                                                                                            
        int height = 100;
        try {                                                                                                                                                      
            MultiFormatWriter writer = new MultiFormatWriter();
            //encode input data to Barcode of code 128
            BitMatrix matrix = writer.encode(data, BarcodeFormat.CODE_128, width, height);
            //write the Barcode image to file
            MatrixToImageWriter.writeToFile(matrix, format, file);                                                                                                 
        } catch (WriterException e) {                                                                                                                              
            e.printStackTrace();                                                                                                                                   
        } catch (IOException e){
            e.printStackTrace();
        }                                                                                                                                                          
        System.out.println("File " + name + " created.");                                                                                                          
    }                                                                                                                                                              
}