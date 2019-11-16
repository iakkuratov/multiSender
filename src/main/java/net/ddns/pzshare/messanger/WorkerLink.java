package net.ddns.pzshare.messanger;

import org.springframework.context.annotation.Scope;

@Scope(value = "Prototype")
public class WorkerLink {
    public WorkerLink(Worker w1, Worker w2){
        w1.start();
        w2.start();
        w1.subscribe(w2);
        w2.subscribe(w1);
    }
}
