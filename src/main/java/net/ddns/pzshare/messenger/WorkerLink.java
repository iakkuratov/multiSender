package net.ddns.pzshare.messenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

@Scope(value = "prototype")
public class WorkerLink {
    private static final Logger log = LogManager.getLogger();

    public WorkerLink(String name, Worker w1, Worker w2) {
        try {
            log.info("Creating new link "
                    + name + " "
                    + w1.getClass().getSimpleName()
                    + " <===> " + w2.getClass().getSimpleName()
            );

            w1.start();

            w2.start();

            w1.subscribe(w2);

            w2.subscribe(w1);

        } catch (StartException ex) {
            log.error(ex);

            System.exit(-1);
        }
    }
}
