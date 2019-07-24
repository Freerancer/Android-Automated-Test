package com.asma.automate;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.repeatedlyUntil;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;


import com.sourcey.materiallogindemo.MainActivity;
import com.sourcey.materiallogindemo.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginApp {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void Before(){
        closeSoftKeyboard();
    }

    @Test
    public void Login() {
        onView(withId(R.id.input_email)).perform(typeText("username"));
        onView(withId(R.id.input_password)).perform(typeText("password"));
        onView(withId(R.id.btn_login)).perform(click());
    }

    @Test
    public void VerifyAccount() {
        onView(withId(R.id.link_signup)).perform(click());

        onView(withId(R.id.input_name)).perform(typeText("test1"));
        onView(withId(R.id.input_address)).perform(typeText("home"));
        onView(withId(R.id.input_email)).perform(typeText("test@gmail.com"));

        onView(withId(R.id.input_email)).perform(repeatedlyUntil(swipeUp(),
                hasDescendant(withId(R.id.link_login)),
                5));

        onView(withId(R.id.input_mobile)).perform(typeText("0999999999"));
        onView(withId(R.id.input_password)).perform(typeText("xxxxxx"));
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("xxxxxx"));
        //onView(withId(R.id.btn_signup)).perform(click());
        onView(withId(R.id.link_login)).check(matches(withText("Already a member? Login")));
    }
}
