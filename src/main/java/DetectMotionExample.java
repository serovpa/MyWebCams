import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetectMotionExample implements WebcamMotionListener {

    WebcamMotionDetector detector;
    SimpleDateFormat formatDate;
    Date dateNow;
    Logger log;

    public DetectMotionExample() {
        detector = new WebcamMotionDetector(Webcam.getDefault());
        detector.setInterval(1000); // one check per 500 ms
        detector.addMotionListener(this);
        log = WebcamViewerExample.getLogger();
        detector.start();
    }

    @Override
    public void motionDetected(WebcamMotionEvent wme) {
        dateNow = new Date();
        formatDate = new SimpleDateFormat("hh-mm-ss_dd-MM-yyyy");
        log.info("Movement is captured");
        try {
            ImageIO.write(detector.getWebcam().getImage(), "PNG", new File("C://test//"+formatDate.format(dateNow)+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}