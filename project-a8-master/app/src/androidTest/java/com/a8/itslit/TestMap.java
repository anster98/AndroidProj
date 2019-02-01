package com.a8.itslit;

import android.os.Handler;
import android.os.Looper;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TestMap{

    @Rule
    public final ActivityTestRule<MapsActivity> mapsActivityTestRule = new ActivityTestRule<>(MapsActivity.class, true, true);

    //  USER STORY #13
    //  SCENARIO 2:
    //==============================================================================================================
    @Test
    public void testMarkerTitle1(){
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Fallout"));
        assertThat(mMarker1, notNullValue());
    }

    @Test
    public void testMarkerTitle2(){
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Fall"));
        assertThat(mMarker1, notNullValue());
    }
    //==============================================================================================================


    //  USER STORY #12
    //  SCENARIO 2:
    //==============================================================================================================
    @Test
    public void testMarkerDescription1(){
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Alternative night club, dress code enforced."));
        assertThat(mMarker1, notNullValue());

        try {
            mMarker1.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMarkerDescription2(){
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Alternative"));
        assertThat(mMarker1, notNullValue());
    }
    //==============================================================================================================
    // ITERATION 2
    //=====================================================================================================

    //user story 8
    //scenario 2
    @Test
    public void testToListPrice()
    {
        onView(withId(R.id.return_btn)).perform(click());

        onView(withId(R.id.rvLocations)).perform(RecyclerViewActions.scrollToPosition(11));
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Price"));
        assertThat(mMarker1, notNullValue());
    }

    //User story 6
    //Scenario 3
    @Test
    public void testToListAge()
    {
        onView(withId(R.id.return_btn)).perform(click());

        onView(withId(R.id.rvLocations)).perform(RecyclerViewActions.scrollToPosition(11));
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Age"));
        assertThat(mMarker1, notNullValue());
    }

    //User Story 4
    //Scenario 3
    @Test
    public void testToListHours()
    {
        onView(withId(R.id.return_btn)).perform(click());

        onView(withId(R.id.rvLocations)).perform(RecyclerViewActions.scrollToPosition(11));
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Hours"));
        assertThat(mMarker1, notNullValue());
    }

    /*=======================================================================================================
                                                 ITERATION 3
                                           USER STORIES #15 AND #10
    **You must set the location on your emulator to: Lat = 37.54650485506589 and Long = -77.44719844311476**
    =======================================================================================================*/
    //User Story 10
    //Scenario 1
    //THIS WILL BE TESTED IN TESTLIST.JAVA

    //User Story 10
    //Scenario 2
    @Test
    public void testDirectionsBtn() {
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mDirections = uiDevice.findObject(new UiSelector().descriptionContains("Directions"));
        Assert.assertNotNull(mDirections);
    }

    //User Story 10
    //Scenario 3
    //Test 1
    @Test
    public void testMarkerLatLng1() {
        MapsActivity mapAct = mapsActivityTestRule.getActivity();
        com.a8.itslit.Location mLocation = mapAct.getLocationList().get(0);
        LatLng location = new LatLng(mLocation.getLat(), mLocation.getLng());
        LatLng fallout = new LatLng(37.534087,-77.426085);
        Assert.assertEquals(fallout, location);
    }
    //Test 2
    @Test
    public void testMarkerLatLng2() {
        MapsActivity mapAct = mapsActivityTestRule.getActivity();
        com.a8.itslit.Location mLocation = mapAct.getLocationList().get(1);
        LatLng location = new LatLng(mLocation.getLat(), mLocation.getLng());
        LatLng fallout = new LatLng(37.534087,-77.426085);
        Assert.assertNotEquals(fallout, location);
    }
    //Test 3
    @Test
    public void testMarkerLatLng3() {
        MapsActivity mapAct = mapsActivityTestRule.getActivity();
        com.a8.itslit.Location mLocation = mapAct.getLocationList().get(1);
        LatLng location = new LatLng(mLocation.getLat(), mLocation.getLng());
        LatLng plush = new LatLng(37.533445,-77.428019);
        Assert.assertEquals(plush, location);
    }

    //User Story 15
    //Scenario 1
    //Check if myLocation is enabled. This is what places down the blue dot
    @Test
    public void testMarkedLocation(){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable(){
            @Override
            public void run() {
                MapsActivity mapAct = mapsActivityTestRule.getActivity();
                GoogleMap map = mapAct.getMap();
                Assert.assertTrue(map.isMyLocationEnabled());
            }
        });
    }
    //User Story 15
    //Scenario 2
    @Test
    public void testIsLocationBtn() {
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mLocation = uiDevice.findObject(new UiSelector().descriptionContains("MyLocationButton"));
        Assert.assertNotNull(mLocation);
    }
    //User Story 15
    //Scenario 3
    @Test
    public void testLastLocationFromList(){
        final MapsActivity mapAct = mapsActivityTestRule.getActivity();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable(){
                         @Override
                         public void run() {
                             LatLng richmond = new LatLng(37.546508044986034,-77.44719844311476);
                             GoogleMap map = mapAct.getMap();
                             CameraPosition pos = map.getCameraPosition();
                             LatLng curLoc = pos.target;
                             Assert.assertEquals(curLoc, richmond);
                         }
                     });
    }
}

