package Barcode;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
 
import javax.imageio.ImageIO;
 
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
 
public class Test extends BarcodeReader {
    public static void main(String... args){
    	String code = "212121";
    	String name = "testGe.jpg";
        Generator(code, name);
    	File file=new File(name);
    	System.out.println(Scanner(file));
    }
}