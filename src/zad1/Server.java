/**
 *
 *  @author Gołębski Przemysław S16540
 *
 */

package zad1;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
  private boolean listening ;
  private ServerSocketChannel serverChannel;
  private Selector selector;

  public Server(){
    try {
       serverChannel  = ServerSocketChannel.open();
      serverChannel.socket().bind(new InetSocketAddress(9999));
      serverChannel.configureBlocking(false);
       selector  = Selector.open();
      serverChannel.register(selector, SelectionKey.OP_ACCEPT);
      listening = true;
      listenForConnections();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void listenForConnections() throws IOException {
    while (listening){
      selector.select();
      Set keys = selector.selectedKeys();
      Iterator  iterator = keys.iterator();
      while(iterator.hasNext()){
        SelectionKey key =(SelectionKey) iterator.next();
        iterator.remove();
        if (key.isAcceptable()){
          SocketChannel socketChannel  = serverChannel.accept();
          socketChannel.configureBlocking(false);
          serverChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
          continue;
        }
        if (key.isReadable()){
          SocketChannel socketChannel = (SocketChannel)key.channel();


          continue;
        }
        if (key.isWritable()){
          SocketChannel socketChannel = (SocketChannel)key.channel();


        }
      }

    }
  }
  private void serviceRequest(SocketChannel socketChannel){
    if (!socketChannel.isOpen()) return;


  }


  public static void main(String[] args) {



  }
  private void broadcastMessega(String message){

  }
}
