package android.com.rest.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidades {

    public static Date getDateFromString(String text) {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = format.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getStringFormatddMMyyyyGuiones(Date date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");//yyyyMMddHHmmss
        return df.format(date);
    }

}
