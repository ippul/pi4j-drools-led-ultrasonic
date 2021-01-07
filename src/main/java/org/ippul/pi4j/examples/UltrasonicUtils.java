package org.ippul.pi4j.examples;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.ippul.pi4j.examples.model.UltrasonicRead;

public class UltrasonicUtils {

    public static int calculateDistance(UltrasonicRead read){
        double distance = ((((read.getEndTime()-read.getStartTime())/1e3)/2) / 29.1);
        return Double.valueOf(distance).intValue(); // Math.round(distance * 100.0) / 100.0;
    }

    public static void trigger(GpioPinDigitalOutput sensorTriggerPin) {
        sensorTriggerPin.high();
        try {
            Thread.sleep((long) 0.01);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorTriggerPin.low();
    };

    public static UltrasonicRead readEcho(GpioPinDigitalInput sensorEchoPin) {
        while(sensorEchoPin.isLow()){

        }
        long startTime = System.nanoTime();
        while(sensorEchoPin.isHigh()){

        }
        long endTime= System.nanoTime();
        return new UltrasonicRead(startTime, endTime);
    }
}
