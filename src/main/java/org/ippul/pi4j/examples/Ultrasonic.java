package org.ippul.pi4j.examples;

import com.pi4j.io.gpio.*;
import org.ippul.pi4j.examples.model.UltrasonicRead;

import java.util.concurrent.atomic.AtomicInteger;

public class Ultrasonic {

    private static GpioPinDigitalOutput sensorTriggerPin;

    private static GpioPinDigitalInput sensorEchoPin;

    private AtomicInteger counter = new AtomicInteger();

    private final static GpioController gpio = GpioFactory.getInstance();

    public static void main(String [] args) {
        new Ultrasonic().run();
    }

    public void run() {
        sensorTriggerPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
        sensorEchoPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05, PinPullResistance.PULL_DOWN);
        while(true){
            try {
                UltrasonicUtils.trigger(sensorTriggerPin);
                UltrasonicRead read = UltrasonicUtils.readEcho(sensorEchoPin);
                DroolsExample.getInstance().insert(read);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
