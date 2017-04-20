package com.pxlim.bitparse;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.pxlim.bitparse", appContext.getPackageName());
    }

    @Test
    public void bitFragment_sameActivity() throws Exception {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_bit));
        onView(withId(R.id.editTextA)).perform(typeText("11"), closeSoftKeyboard());
        onView(withId(R.id.editTextB)).perform(typeText("15"), closeSoftKeyboard());
        onView(withId(R.id.calculate)).perform(click());
        onView(withId(R.id.textViewResult)).check(matches(withText("1")));

    }

    @Test
    public void parseFragmentApplePie_sameActivity() throws Exception {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_parse));
        onView(withId(R.id.editTextInput)).perform(typeText("applepieshoe"), closeSoftKeyboard());
        onView(withId(R.id.parse)).perform(click());
        onView(withId(R.id.textViewResult)).check(matches(withText("apple\npie\nshoe\n")));

    }

    @Test
    public void parseFragmentSecondary_sameActivity() throws Exception {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_parse));
        onView(withId(R.id.editTextInput)).perform(typeText("secondary"), closeSoftKeyboard());
        onView(withId(R.id.parse)).perform(click());
        onView(withId(R.id.textViewResult)).check(matches(withText("second\nsecondary\n")));

    }

}
