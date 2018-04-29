package Camera;

import org.bytedeco.javacv.*;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import Barcode.BarcodeReader;

import static org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

//import org.bytedeco.javacpp.opencv_core.IplImage;

public class GrabberShowUsesCallable implements Callable<String>{
    final int INTERVAL = 100;///you may use interval
    CanvasFrame canvas = new CanvasFrame("Barcode Scanner");
    String code;

    public GrabberShowUsesCallable() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    
    //convert IPL Image to Buffered Image
    public static BufferedImage IplImageToBufferedImage(IplImage src) {
	    OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
	    Java2DFrameConverter paintConverter = new Java2DFrameConverter();
	    Frame frame = grabberConverter.convert(src);
	    return paintConverter.getBufferedImage(frame,1);
	}
    
    public String call() {
        FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        IplImage img;
        try {
            grabber.start();
            Result result = null;
            while (result == null) {
                Frame frame = grabber.grab();
                img = converter.convert(frame);
                canvas.showImage(converter.convert(img));
                //Scanner
                BufferedImage imgg = IplImageToBufferedImage(img);
                BinaryBitmap binaryBitmap;
                try {
                	binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(imgg)));
                    result = new MultiFormatReader().decode(binaryBitmap);
                    System.out.println(result.getText());
                    code = result.getText();
                } catch (NotFoundException e) {
                	System.out.println("Scanning...");
                }
                //scanner's end
                Thread.sleep(INTERVAL);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	GrabberShowUsesCallable gs = new GrabberShowUsesCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(gs);
        String cc = future.get();
        System.out.println("Final result : " + cc);
    }
}