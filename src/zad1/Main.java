/**
 *
 *  @author Gołębski Przemysław S16540
 *
 */

package zad1;


public class Main {

    public static void main(String[] args) {
        new Thread(Server::new).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(Client::new).start();
        new Thread(Client::new).start();
    }
}
