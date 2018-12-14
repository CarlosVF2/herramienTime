package android.com.rest.utils;

import android.content.Context;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.IOException;
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

    public static void hideKeyBoard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String getStringFormatddMMyyyyGuiones(Date date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");//yyyyMMddHHmmss
        return df.format(date);
    }

    public static File takePhoto(Context context, String account) throws IOException {
        return createMediaFile(context, MediaStore.ACTION_IMAGE_CAPTURE, account);
    }

    public static File takeVideo(Context context, String account) throws IOException {
        return createMediaFile(context, MediaStore.ACTION_VIDEO_CAPTURE, account);
    }

    private static File createMediaFile(Context context, String action, String account) throws IOException {
        // Create an image file name
        String suffix = "";
        String name = "";
        String destination = "";
        if (TextUtils.equals(action, MediaStore.ACTION_IMAGE_CAPTURE)) {
            suffix = ".jpg";
            name = "JPEG_";
            destination = Environment.DIRECTORY_PICTURES;
        } else if (TextUtils.equals(action, MediaStore.ACTION_VIDEO_CAPTURE)) {
            suffix = ".mp4";
            name = "MP4_";
            destination = Environment.DIRECTORY_MOVIES;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = name + timeStamp + "_";
        File storageDir = new File(context.getExternalFilesDir(destination).getAbsoluteFile() + "/" + account);
        storageDir.mkdirs();
        File image = File.createTempFile(
                fileName,  /* prefix */
                suffix,         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

}
