
package org.inra.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.inra.Business.Job;
import org.inra.Business.Worker;

/**
 *
 * @author ryahiaoui
 */
public class Runner {
    
    public static void main(String[] args) throws InterruptedException {
       
      Integer jobCounter = 1 ;
      BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(5) ;
 
      Worker worker = new Worker(2, 5, 100, TimeUnit.SECONDS, blockingQueue) ;
      
      worker.setRejectedExecutionHandler( new RejectedExecutionHandler() {

          @Override
          public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
           
              System.out.println(" __ Job ** " + ((Job) r).getJobName() + " ** Rejeté ");
              System.out.println(" __ Patienter une seconde... ");
              try {
                   Thread.sleep(1000);
              } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              System.out.println(" __ Retenter Job **  "+ ((Job) r).getJobName() + " ** ");
              executor.execute(r);
          }
      });
     
        worker.prestartAllCoreThreads();
      
        while (true) {
            
            System.out.println("Ajout dans Queue Job : " + jobCounter );
            Thread.sleep(100);
            worker.execute(new Job(jobCounter.toString()));
            
            if(jobCounter % 5 == 0 )
              System.out.println(" ------------------------"
                                 + " BlockingQueue.size() = " + blockingQueue.size());
           
            if (jobCounter++ > 10)  {
                worker.shutdown();
                break ;
            }
        }
    }
}
