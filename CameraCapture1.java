package Barcode;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.FrameGrabber.Exception;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.opencv_core.CvPoint;
import org.bytedeco.javacpp.opencv_core.CvScalar;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_calib3d.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
public class CameraCapture1 extends BarcodeReader {

 	/**
	 * @param args
	 * @throws Exception 
	 * @throws org.bytedeco.javacv.FrameRecorder.Exception 
 	 * @throws InterruptedException 
	 */
	public static BufferedImage IplImageToBufferedImage(IplImage src) {
	    OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
	    Java2DFrameConverter paintConverter = new Java2DFrameConverter();
	    Frame frame = grabberConverter.convert(src);
	    return paintConverter.getBufferedImage(frame,1);
	}
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		/*OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();
        Frame frame = grabber.grab();
        IplImage grabbedImage = converter.convert(frame);
        cvSaveImage("cc.jpg", grabbedImage);
        grabber.stop();*/
		OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
		FrameGrabber grabber = new VideoInputFrameGrabber(0);
		try {
		    while (true) {
		      grabber.start();
		      Thread.sleep(1000);
		      Frame frame = grabber.grab();
		      IplImage  img = converter.convert(frame);

		      if (img != null) {
		        //cvSaveImage("cc.jpg", img);
		    	//BufferedImage imgg = IplImageToBufferedImage(img);
		        grabber.stop();
		      }
		    }
		} catch (Exception e) {
		      e.printStackTrace();
		}
	}


}