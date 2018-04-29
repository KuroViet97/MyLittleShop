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

import javax.imageio.ImageIO;

//import org.bytedeco.javacpp.opencv_core.IplImage;

public class GrabberShow implements Runnable{
    final int INTERVAL = 100;///you may use interval
    CanvasFrame canvas = new CanvasFrame("Barcode Scanner");
    private String code;

    public GrabberShow() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    public String getCode() {
    	return code;
    }
    
    //convert IPL Image to Buffered Image
    public static BufferedImage IplImageToBufferedImage(IplImage src) {
	    OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
	    Java2DFrameConverter paintConverter = new Java2DFrameConverter();
	    Frame frame = grabberConverter.convert(src);
	    return paintConverter.getBufferedImage(frame,1);
	}
    
    public void run() {
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
                } catch (NotFoundException e) {
                	System.out.println("Scanning...");
                }
                //scanner's end
                Thread.sleep(INTERVAL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        GrabberShow gs = new GrabberShow();
        Thread th = new Thread(gs);
        th.start();
    }
}