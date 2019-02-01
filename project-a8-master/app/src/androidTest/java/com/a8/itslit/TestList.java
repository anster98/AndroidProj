package com.a8.itslit;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.support.v7.widget.RecyclerView;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)

public class TestList {

    @Rule
    public ActivityTestRule<ListActivity> listActivityActivityTestRule = new ActivityTestRule<>(ListActivity.class, true, true);

    private int getCount() {
        RecyclerView rv = (RecyclerView) listActivityActivityTestRule.getActivity().findViewById(R.id.rvLocations);
        return rv.getAdapter().getItemCount();
    }

    // USER STORY #13
    // SCENARIO 3
    @Test
    public void testToMapName() {
        onView(withId(R.id.map_btn)).perform(click());

        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Fallout"));
        assertThat(mMarker1, notNullValue());
    }

    // USER STORY #12
    // SCENARIO 2
    @Test
    public void testToMapDescription() {
        onView(withId(R.id.map_btn)).perform(click());
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Alternative night club, dress code enforced."));
        assertThat(mMarker1, notNullValue());
    }

    // USER STORY #13
    // SCENARIO 1
    @Test
    public void scrolltoItem(){
        if(getCount() > 0) {
            onView(withId(R.id.rvLocations))
                    .perform(RecyclerViewActions.scrollToPosition(11));
        }
    }

    // USER STORY #13
    // SCENARIO 1
    @Test
    public void clickItem() {
        if(getCount() > 0){
            onView(withId(R.id.rvLocations))
                    .perform(RecyclerViewActions.scrollToPosition(10));
            onView(withId(R.id.rvLocations)).perform(RecyclerViewActions.actionOnItemAtPosition(10, longClick()));
        }
    }

    // USER STORY #12
    // SCENARIO 1
    @Test
    public void checkLocationContent() {
        onView(withId(R.id.rvLocations)).perform(RecyclerViewActions.scrollToPosition(11));
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Cha Cha's"));
        assertThat(mMarker1, notNullValue());
    }

    // USER STORY #12
    // SCENARIO 3
    @Test
    public void checkLocationDescription() {
        onView(withId(R.id.rvLocations)).perform(RecyclerViewActions.scrollToPosition(11));
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Southwestern restaurant and bar known for its late-night scene."));
        assertThat(mMarker1, notNullValue());
    }

    @Test
    public void checkLocationAddress() {
        onView(withId(R.id.rvLocations)).perform(RecyclerViewActions.scrollToPosition(11));
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("1419 E Cary St Richmond"));
        assertThat(mMarker1, notNullValue());
    }

    @Test
    public void locationsdisplayed() {
        onView(withId(R.id.rvLocations)).check(matches(isDisplayed()));
    }

    // ====================================================================================
    // ITERATION 2
    //==========================================================================================

    //User Story 8
    // Scenario 1
    @Test
    public void checkLocationPrice()
    {
        onView(withId(R.id.rvLocations)).perform(RecyclerViewActions.scrollToPosition(11));
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Price"));
        assertThat(mMarker1, notNullValue());
    }

    //User story 6
    //Scenario 1
    @Test
    public void checkLocationAgeLimit() {
        onView(withId(R.id.rvLocations)).perform(RecyclerViewActions.scrollToPosition(11));
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("21"));
        assertThat(mMarker1, notNullValue());
    }

    //User story 4
    //Scenario 1
    @Test
    public void checkLocationHours() {
        onView(withId(R.id.rvLocations)).perform(RecyclerViewActions.scrollToPosition(11));
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Hours"));
        assertThat(mMarker1, notNullValue());
    }

    // user story 14
    // Scenario 2
    // Feature: View more info
    // Scenario: View locations website
    // Given I am on the List page
    // When I click a location in the list
    // Then I should be taken to the website of that location

    @Test
    public void checkViewLocationWebsite() {

        //create Instrumentation object to listen the web browser
        Instrumentation instrumentation = getInstrumentation();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_VIEW);
        intentFilter.addDataScheme("http");
        intentFilter.addCategory(Intent.CATEGORY_APP_BROWSER);
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(intentFilter, null, false);

        //waiting for web browser
        assertEquals(0, monitor.getHits());

        //click the first position of list view
        onView(withId(R.id.rvLocations))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //wait for browser
        monitor.waitForActivityWithTimeout(5000);

        //check if browser is opened
        assertEquals(1, monitor.getHits());

        //remove monitor
        instrumentation.removeMonitor(monitor);

    }

    ///////////////////////////////////////
    // Iteration 3
    ///////////////////////////////////////

//    //user story 11 scenario 3
//    @Test
//    public void checkUnfavorite() {
//        onView(withId(R.id.rvLocations))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));
//        assertFalse(ListActivity.getLocationNames().get(0).getFavorite());
//    }
//
//    //user story 11 scenario 1
//    @Test
//    public void checkFavorite() {
//        onView(withId(R.id.rvLocations))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));
//        assertTrue(ListActivity.getLocationNames().get(0).getFavorite());
//    }
//
//
//    //user story 11  scenario 2
//    @Test
//    public void checkFavToast() {
//        onView(withId(R.id.rvLocations))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));
//        //onView(withText("favorited")).inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
//        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
//        UiObject mMarker1 = uiDevice.findObject(new UiSelector().descriptionContains("Location favorited"));
//        assertThat(mMarker1, notNullValue());
//    }

    //User Story 10
    //Scenario 1
    @Test
    public void testToMapDirections(){
        onView(withId(R.id.map_btn)).perform(click());
        UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mDirections = uiDevice.findObject(new UiSelector().descriptionContains("Directions"));
        Assert.assertNotNull(mDirections);
    }
   //user story 11 scenario 3
    @Test
    public void checkUnfavorite() {

        ListActivity.getLocationNames().get(0).setFavorite(false);

        onView(withId(R.id.rvLocations))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));
        assertFalse(ListActivity.getLocationNames().get(0).getFavorite());
    }

    //user story 11 scenario 1
    @Test
    public void checkFavorite() {

        ListActivity.getLocationNames().get(0).setFavorite(false);

        onView(withId(R.id.rvLocations))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));
        assertTrue(ListActivity.getLocationNames().get(0).getFavorite());
    }


    //user story 11  scenario 2
    @Test
    public void checkFavToast() {

        ListActivity.getLocationNames().get(0).setFavorite(false);

        //click favorite at item 0, the favorite attribute of item is set to true
        onView(withId(R.id.rvLocations))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));

        //check toast is display

        onView(withText("Fallout added to Favorites")).inRoot(
                withDecorView(not(is(listActivityActivityTestRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void checkUnMarkedFavToast() {

        ListActivity.getLocationNames().get(0).setFavorite(false);

        //click favorite at item 0, the favorite attribute of item is set to true
        onView(withId(R.id.rvLocations))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));
        onView(withId(R.id.rvLocations))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));

        //check toast is display

        onView(withText("Fallout removed from Favorites")).inRoot(
                withDecorView(not(is(listActivityActivityTestRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));


    }
    
}
