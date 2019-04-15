/**
 *
 *  @author Gołębski Przemysław S16540
 *
 */

package zad1;


import javax.swing.*;


public class Client {


  public Client(){
    SwingUtilities.invokeLater(LoginWindow::new);
  }



  public static void main(String[] args) {
   new Client();
  }
}
