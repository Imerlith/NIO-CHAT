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
import java.util.Collections;
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
            Set syset = Collections.synchronizedSet(keys);
            Iterator  iterator = syset.iterator();
            while(iterator.hasNext()){

                SelectionKey key =(SelectionKey) iterator.next();

                if (key.isAcceptable()){

                    SocketChannel socketChannel  = serverChannel.accept();
                    if(socketChannel!=null){
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                    }

                    continue;
                }
                if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)key.channel();
                    String input = CommUtils.readToString(socketChannel);
                    if(input.length() > 0){
                        Message message = new Message(input);
                        broadcastMessage(message.getFormattedMessage());
                    }
                }

            }

        }
    }



    public static void main(String[] args) {

        new Server();

    }
    private void broadcastMessage(String message){
        try {
            selector.select();
            Set keys = selector.selectedKeys();
            for (Object o : keys) {
                SelectionKey key = (SelectionKey) o;
                if (key.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    CommUtils.writeFromString(message, socketChannel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
