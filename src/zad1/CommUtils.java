package zad1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class CommUtils {
    private final static int MAX_LENGTH = 512;
    private final static Charset charset = Charset.forName("UTF-8");
    public static void writeFromString(String output, SocketChannel channel){
        output += "\n";
        ByteBuffer byteBuffer  = charset.encode(output);
        while (byteBuffer.hasRemaining()){
            try {
                channel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static String readToString(SocketChannel channel){
        if(!channel.isOpen())
            return "";
        StringBuilder builder = new StringBuilder();
        ByteBuffer byteBuffer = ByteBuffer.allocate(MAX_LENGTH);
        byteBuffer.clear();
        try {
            outerloop:
            while (byteBuffer.hasRemaining()){
                int n = channel.read(byteBuffer);
                if (n>0){
                    byteBuffer.flip();
                    CharBuffer charBuffer =charset.decode(byteBuffer);
                    while (charBuffer.hasRemaining()){
                        char c = charBuffer.get();
                        if (c=='\n'){
                            break outerloop;
                        }
                        builder.append(c);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}
