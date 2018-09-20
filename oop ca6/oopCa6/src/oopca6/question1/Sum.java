/*
*Patrick McDonnell D00006968
 */
package oopca6.question1;

/**
 *
 * @author patri
 */
public class Sum implements Runnable{
        int num;
        int sum;

    public Sum(int num) {
        this.num = num;
        this.sum = 0;
    }

    @Override
    public void run() {
        for(int i = 1; i <= num; i++)
        {
            sum += i;
        }
        
    }
    
    public int getSum(){
        return sum;
    }
    
}
