package com.disruption.bakingapp;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(JUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void beforeActivityLaunched() {
        Intents.init();
    }

    @After
    public void afterActivityFinished() {
        Intents.release();
    }

    @Test
    public void onClickMenuItemOpenSettings() {
        onView(withId(R.id.action_test_setting)).perform(click());
        intended(hasComponent(SettingsActivity.class.getName()));
    }

}