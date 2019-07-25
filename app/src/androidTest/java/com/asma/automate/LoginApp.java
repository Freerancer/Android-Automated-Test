package com.asma.automate;


import android.os.SystemClock;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.sourcey.materiallogindemo.LoginData;
import com.sourcey.materiallogindemo.MainActivity;
import com.sourcey.materiallogindemo.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginApp {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void before(){
        //Create Account
        LoginData.name = "Asma";
        LoginData.email = "Asma@gmail.com";
        LoginData.address = "Bangkok";
        LoginData.mobile = "0999999999";
        LoginData.password = "123456";
        LoginData.reEnterPassword= "123456";
    }

    @Test
    public void loginSuccess() {
        //Login
        onView(withId(R.id.input_email)).perform(typeText("Asma@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        //Verify Login Success
        SystemClock.sleep(3000);
        onView(withText("Hello world!")).check(matches(isDisplayed()));
    }

    @Test
    public void loginFailInvalidEmail() {
        //Login wrong e-mail
        onView(withId(R.id.input_email)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_email)).check(matches(hasErrorText("enter a valid email address")));
    }

    @Test
    public void loginFailInvalidPassword() {
        //Login wrong password
        onView(withId(R.id.input_email)).perform(typeText("test@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.input_password)).perform(typeText("passwor!!!!!d"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_password)).check(matches(hasErrorText("between 4 and 10 alphanumeric characters")));
    }

    @Test
    public void loginFailEmailNotExist() {
        //Login wrong password
        onView(withId(R.id.input_email)).perform(typeText("test@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_password)).check(matches(hasErrorText("enter a valid email address or password")));
    }

    @Test
    public void loginFailPasswordNotExist() {
        //Login wrong password
        onView(withId(R.id.input_email)).perform(typeText("Asma@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.input_password)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_password)).check(matches(hasErrorText("enter a valid email address or password")));
    }
}

