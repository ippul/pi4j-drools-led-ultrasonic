package org.ippul.pi4j.examples.drools;

import com.pi4j.io.gpio.*;
import org.ippul.pi4j.examples.model.Blink;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

public class PI4JRuleRuntimeEventListener implements RuleRuntimeEventListener {

    final GpioController gpio = GpioFactory.getInstance();

    final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "red LED", PinState.HIGH);

    @Override
    public void objectInserted(ObjectInsertedEvent objectInsertedEvent) {
    }

    @Override
    public void objectUpdated(ObjectUpdatedEvent objectUpdatedEvent) {
        if(objectUpdatedEvent.getObject() instanceof Blink){
            final Blink blink = (Blink) objectUpdatedEvent.getObject();
            pin.blink(blink.getInterval());
        }
    }

    @Override
    public void objectDeleted(ObjectDeletedEvent objectDeletedEvent) {
    }
}
