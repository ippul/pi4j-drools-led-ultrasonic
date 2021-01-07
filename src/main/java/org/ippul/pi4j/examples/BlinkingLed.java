package org.ippul.pi4j.examples;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class BlinkingLed {

    public static void main(String[] args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "red LED", PinState.HIGH);
        pin.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println(event.getPin().getName() +" state is " + event.getState());
            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        pin.setShutdownOptions(true, PinState.LOW);
        Thread.sleep(5000);
        pin.low();
        Thread.sleep(5000);
        pin.toggle();
        Thread.sleep(5000);
        pin.toggle();
        Thread.sleep(5000);
        pin.pulse(1000, true);
        gpio.shutdown();
        System.out.println("Exiting ControlGpioExample");
    }
}
