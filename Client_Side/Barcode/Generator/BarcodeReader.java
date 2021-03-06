package Barcode;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
 
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
 
public class BarcodeReader {
    public static String Scanner(File file) {
    	Result result = null;
        BinaryBitmap binaryBitmap;
        try {
            binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(file)))));
            result = new MultiFormatReader().decode(binaryBitmap);
            //System.out.println(result.getText());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Result:" + result.getText());
        return result.getText();
    }
    public static void Generator(String data, String name) {
    	String format = "jpg";
        File file = new File(name);
        int width = 10;
        int height = 100;
        try {
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix matrix = writer.encode(data, BarcodeFormat.CODE_128, width, height);
            MatrixToImageWriter.writeToFile(matrix, format, file);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("File " + name + " created.");
    }
}