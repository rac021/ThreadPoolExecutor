
package org.inra.Business;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ryahiaoui
 */
public class Worker extends ThreadPoolExecutor {

    public Worker( int corePoolSize, 
                   int maximumPoolSize, 
                   long keepAliveTime, 
                   TimeUnit unit, 
                   BlockingQueue<Runnable> workQueue )      {
        
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue) ; 
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r) ;
        System.out.println(" +++ Pre Execute Job " + ((Job) r).getJobName() +" // Thread_Name " + t.getName()) ;
    }
    
    @Override
    protected void afterExecute(Runnable r, Throwable t ) {
        super.afterExecute(r, t) ;
        System.out.println(" --- Post Execute Job " + ((Job) r).getJobName()) ;
    }

    @Override
    public void execute(Runnable r) {
        super.execute(r); 
    }

    @Override
    public RejectedExecutionHandler getRejectedExecutionHandler() {
        System.out.println(".......... RejectedExecutionHandler = " );
        return super.getRejectedExecutionHandler(); 
    }
    
    
}
