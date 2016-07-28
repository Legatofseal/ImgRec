package com.example.legat.imgrec;

import org.opencv.core.Mat;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Legat on 7/18/2016.
 */
public class MyImgProc {
    static {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
    }
    private Mat originalImage;
    public boolean checkImage (){
        // this.originalImage = Imgcodecs.imread(path);

        Mat frame = Imgcodecs.imread("sad.jpg");
        //image.copyTo(frame);

        // init
        Mat blurredImage = new Mat();
        Mat hsvImage = new Mat();
        Mat mask = new Mat();
        Mat morphOutput = new Mat();

        // remove some noise
        Imgproc.blur(frame, blurredImage, new Size(7, 7));

        // convert the frame to HSV
        Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);

        // get thresholding values from the UI
        // remember: H ranges 0-180, S and V range 0-255
        int hueStart=0;
        int saturationStart = 0;
        int valueStart = 0;
        int hueStop =20;
        int saturationStop=10;
        int valueStop = 10;
        Scalar minValues = new Scalar(hueStart,saturationStart,
                valueStart);
        Scalar maxValues = new Scalar(hueStop, saturationStop,
                valueStop);



        // threshold HSV image to select tennis balls
        Core.inRange(hsvImage, minValues, maxValues, mask);
        // show the partial output


        // morphological operators
        // dilate with large element, erode with small ones
        Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
        Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12, 12));

        Imgproc.erode(mask, morphOutput, erodeElement);
        Imgproc.erode(mask, morphOutput, erodeElement);

        Imgproc.dilate(mask, morphOutput, dilateElement);
        Imgproc.dilate(mask, morphOutput, dilateElement);
        double[] target = mask.get(mask.rows()/2,mask.cols()/2);
        // show the partial output


        // find the tennis ball(s) contours and show them


        // convert the Mat object (OpenCV) to Image (JavaFX)
        return false;

    }

}