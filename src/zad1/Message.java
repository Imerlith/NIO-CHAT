package zad1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String content;
    private Date date;
    private String name;

    public Message(String content) {
        this.content = content;
        date = new Date();
    }
    public String getFormattedMessage(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YY HH:MM");
        return"["+ simpleDateFormat.format(date)+"] "+content;
    }
}
