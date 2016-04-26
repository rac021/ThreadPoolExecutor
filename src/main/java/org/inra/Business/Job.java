
package org.inra.Business;

/**
 *
 * @author ryahiaoui
 */
public class Job implements Runnable {

    private String jobName = null ;
   
    public Job(String name) {
        this.jobName = name ;
    }
 
    public String getJobName() {
        return this.jobName;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Execution : " + jobName ) ;
    }
    
}
