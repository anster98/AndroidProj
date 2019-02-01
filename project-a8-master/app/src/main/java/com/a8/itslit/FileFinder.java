package com.a8.itslit;

import android.content.Context;
import android.util.Log;

import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class FileFinder
{
    //Context ctx;
    private static File file;

    //Finds cached locations
    public FileFinder(Context ctx) throws IOException
    {
        InputStream inputStream = ctx.getResources().openRawResource(R.raw.clublist);
        Log.d("0000000", inputStream.toString());
        file = new File("locations");
        OutputStream outputStream = new FileOutputStream(file);
        Log.d("000000000", "FILE FINDER");
        IOUtils.copy(inputStream, outputStream);
        outputStream.close();
    }

    //Returns file
    public static File getFile() {
        return file;
    }
}