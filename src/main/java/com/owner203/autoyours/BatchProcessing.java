package com.owner203.autoyours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchProcessing {

    @Autowired
    JobBatch jobBatch;

    @Scheduled(initialDelay = 1000, fixedDelay = 86400000)
    public void runJobBatch() {
        int count = 0;
        while (jobBatch.execute() != 0 && count <5) {
            count = count + 1;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        AutoyoursApplication.getContext().close();
    }

}
