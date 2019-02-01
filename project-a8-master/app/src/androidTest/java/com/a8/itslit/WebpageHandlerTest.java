package com.a8.itslit;

import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getContext;

public class WebpageHandlerTest {

    @Test
    public void openWebpage() {

        WebpageHandler handler = new WebpageHandler(getContext());
        handler.openWebpage("https://github.com/");



    }
    @Test
    public void openOneWebpage() {
        WebpageHandler handler = new WebpageHandler(getContext());
        handler.openWebpage("https://www.facebook.com/");

    }

    @Test
    public void openMoreWebpage() {
        WebpageHandler handler = new WebpageHandler(getContext());
        handler.openWebpage("https://www.google.com/maps");

    }
}