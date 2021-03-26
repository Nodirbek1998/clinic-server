package uz.cas.demo.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.cas.demo.repository.QueueRepository;

@Component
public class CronJobs {
    @Autowired
    private QueueRepository queueRepository;

    @Scheduled(cron = "0 1 1 * * *")
    public void deleteAllQueue() {
        queueRepository.deleteAll();
    }

//    @Scheduled(fixedDelay = 1000, initialDelay = 2000)
//    private void delete(){
//        System.out.println("hey!!");
//    }
}
