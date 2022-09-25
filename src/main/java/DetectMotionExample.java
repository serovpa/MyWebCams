import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetectMotionExample implements WebcamMotionListener {

    WebcamMotionDetector detector;
    SimpleDateFormat formatDate;
    Date dateNow;

    public DetectMotionExample() {
        detector = new WebcamMotionDetector(Webcam.getDefault());
        detector.setInterval(1000); // one check per 500 ms
        detector.addMotionListener(this);
        detector.start();
    }

    @Override
    public void motionDetected(WebcamMotionEvent wme) {
        dateNow = new Date();
        formatDate = new SimpleDateFormat("hh-mm-ss_dd-MM-yyyy");
        final Logger log = Logger.getLogger(DetectMotionExample.class);
        log.info("Зафиксировано движение "+ formatDate.format(dateNow));
        try {
            ImageIO.write(detector.getWebcam().getImage(), "PNG", new File("C://test//moving_at_"+formatDate.format(dateNow)+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}