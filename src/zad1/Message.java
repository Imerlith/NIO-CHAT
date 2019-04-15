package zad1;

import java.util.Date;

public class Message {
    private String content;
    private Date date;

    public Message(String content) {
        this.content = content;
        date = new Date();
    }
    public String getFormattedMessage(){
        return date.getTime()+" : "+content;
    }
}
