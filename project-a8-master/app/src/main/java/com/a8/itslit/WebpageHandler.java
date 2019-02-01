package com.a8.itslit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * WebpageHandler open web browser with initial URL
 * URL must start with http:// or https://
 */

public class WebpageHandler
{
    private Context context;

    //Constructor
    public WebpageHandler(Context context){
        this.context = context;
    }

    //Open the web page
    //Url must start with http:// or https://
    public void openWebpage(String url)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }
}
