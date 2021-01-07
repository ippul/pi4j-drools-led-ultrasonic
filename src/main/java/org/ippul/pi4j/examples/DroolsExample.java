package org.ippul.pi4j.examples;

import org.ippul.pi4j.examples.model.Blink;
import org.ippul.pi4j.examples.model.UltrasonicRead;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.io.Closeable;
import java.io.IOException;

public class DroolsExample implements Closeable {

    public static final DroolsExample instance = new DroolsExample();

    private KieSession kieSession;

    private Thread thread;

    private DroolsExample() {
        KieServices kieServices = KieServices.Factory.get();
        kieSession = kieServices.getKieClasspathContainer().newKieSession();
        kieSession.addEventListener(new PI4JRuleRuntimeEventListener());
        kieSession.insert(new Blink());
        thread = new Thread(() -> kieSession.fireUntilHalt());
        thread.start();
    }

    public static DroolsExample getInstance() {
        return instance;
    }

    public void insert(UltrasonicRead read){
        kieSession.insert(read);
    }

    @Override
    public void close() throws IOException {
        kieSession.dispose();
        kieSession.destroy();
    }

}
