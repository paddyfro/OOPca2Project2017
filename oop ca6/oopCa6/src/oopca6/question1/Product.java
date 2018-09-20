/*
*Patrick McDonnell D00006968
 */
package oopca6.question1;

/**
 *
 * @author patri
 */
public class Product implements Runnable {

    int num;
    long product;

    public Product(int num) {
        this.num = num;
        this.product = 1;
    }

    @Override
    public void run() {        
        for (int i = 1; i <= num; i++) {
            product *= i;
        }

    }

    public long getProduct() {
        return product;
    }

}
