/**
 *
 *  @author Gołębski Przemysław S16540
 *
 */

package zad1;


import javax.swing.*;

public class Client {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(LoginWindow::new);
  }
}
