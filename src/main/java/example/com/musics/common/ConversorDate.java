package example.com.musics.common;

import java.util.Date;
import java.text.SimpleDateFormat;

public class ConversorDate {
    public static String converseDateToTimeDate(Date date){
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return formater.format(date);
    }
}
