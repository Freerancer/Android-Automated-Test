package com.asma.automate;

import android.os.SystemClock;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.sourcey.materiallogindemo.MainActivity;
import com.sourcey.materiallogindemo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignUp {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void createAccountSuccess() {
        onView(withId(R.id.link_signup)).perform(click());

        onView(withId(R.id.input_name)).perform(typeText("Asma"));
        onView(withId(R.id.input_address)).perform(typeText("Bangkok"));
        onView(withId(R.id.input_email)).perform(typeText("Asma@gmail.com"));

        onView( withId( R.id.link_login)).perform(scrollTo());

        onView(withId(R.id.input_mobile)).perform(typeText("1111111111"));
        onView(withId(R.id.input_password)).perform(typeText("123456"));
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("123456"));
        onView(withId(R.id.btn_signup)).perform(click());

        SystemClock.sleep(3000);
        onView(withText("Hello world!")).check(matches(isDisplayed()));
        //onView(withId(R.id.btn_logout)).perform(click());
    }

    @Test
    public void createAccountFailNameIsEmpty() {

        onView(withId(R.id.link_signup)).perform(click());

        onView(withId(R.id.input_address)).perform(typeText("Bangkok"));
        onView(withId(R.id.input_email)).perform(typeText("Asma@gmail.com"));
        onView( withId( R.id.link_login)).perform(scrollTo());
        onView(withId(R.id.input_mobile)).perform(typeText("1111111111"));
        onView(withId(R.id.input_password)).perform(typeText("123456"));
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("123456"));
        onView(withId(R.id.btn_signup)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_name)).check(matches(hasErrorText("at least 3 characters")));
    }

    @Test
    public void createAccountFailAddressIsEmpty() {

        onView(withId(R.id.link_signup)).perform(click());

        onView(withId(R.id.input_name)).perform(typeText("Asma"));
        onView(withId(R.id.input_email)).perform(typeText("Asma@gmail.com"));
        onView( withId( R.id.link_login)).perform(scrollTo());
        onView(withId(R.id.input_mobile)).perform(typeText("1111111111"));
        onView(withId(R.id.input_password)).perform(typeText("123456"));
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("123456"));
        onView(withId(R.id.btn_signup)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_address)).check(matches(hasErrorText("Enter Valid Address")));
    }

    @Test
    public void createAccountFailEmailIsEmpty() {

        onView(withId(R.id.link_signup)).perform(click());

        onView(withId(R.id.input_name)).perform(typeText("Asma"));
        onView(withId(R.id.input_address)).perform(typeText("Bangkok"));
        onView( withId( R.id.link_login)).perform(scrollTo());
        onView(withId(R.id.input_mobile)).perform(typeText("1111111111"));
        onView(withId(R.id.input_password)).perform(typeText("123456"));
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("123456"));
        onView(withId(R.id.btn_signup)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_email)).check(matches(hasErrorText("enter a valid email address")));
    }

    @Test
    public void createAccountFailInvalidEmail() {

        onView(withId(R.id.link_signup)).perform(click());

        onView(withId(R.id.input_name)).perform(typeText("Asma"));
        onView(withId(R.id.input_address)).perform(typeText("Bangkok"));
        onView(withId(R.id.input_email)).perform(typeText("test"));
        onView( withId( R.id.link_login)).perform(scrollTo());
        onView(withId(R.id.input_mobile)).perform(typeText("1111111111"));
        onView(withId(R.id.input_password)).perform(typeText("123456"));
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("123456"));
        onView(withId(R.id.btn_signup)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_email)).check(matches(hasErrorText("enter a valid email address")));
    }

    @Test
    public void createAccountFailMobileNumberMoreThan10Digits() {

        onView(withId(R.id.link_signup)).perform(click());

        onView(withId(R.id.input_name)).perform(typeText("Asma"));
        onView(withId(R.id.input_address)).perform(typeText("Bangkok"));
        onView(withId(R.id.input_email)).perform(typeText("Asma@gmail.com"));
        onView( withId( R.id.link_login)).perform(scrollTo());
        onView(withId(R.id.input_mobile)).perform(typeText("01234567890"));
        onView(withId(R.id.input_password)).perform(typeText("123456"));
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("123456"));
        onView(withId(R.id.btn_signup)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_mobile)).check(matches(hasErrorText("Enter Valid Mobile Number")));
    }

    @Test
    public void createAccountFailMobileNumberLessThan10Digits() {

        onView(withId(R.id.link_signup)).perform(click());

        onView(withId(R.id.input_name)).perform(typeText("Asma"));
        onView(withId(R.id.input_address)).perform(typeText("Bangkok"));
        onView(withId(R.id.input_email)).perform(typeText("Asma@gmail.com"));
        onView( withId( R.id.link_login)).perform(scrollTo());
        onView(withId(R.id.input_mobile)).perform(typeText("01234"));
        onView(withId(R.id.input_password)).perform(typeText("123456"));
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("123456"));
        onView(withId(R.id.btn_signup)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_mobile)).check(matches(hasErrorText("Enter Valid Mobile Number")));
    }

    @Test
    public void createAccountFailPasswordLessThan4Digits() {

        onView(withId(R.id.link_signup)).perform(click());

        onView(withId(R.id.input_name)).perform(typeText("Asma"));
        onView(withId(R.id.input_address)).perform(typeText("Bangkok"));
        onView(withId(R.id.input_email)).perform(typeText("Asma@gmail.com"));
        onView( withId( R.id.link_login)).perform(scrollTo());
        onView(withId(R.id.input_mobile)).perform(typeText("0123456789"));
        onView(withId(R.id.input_password)).perform(typeText("123"));
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("123"));
        onView(withId(R.id.btn_signup)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_password)).check(matches(hasErrorText("between 4 and 10 alphanumeric characters")));
    }

    public void createAccountFailPasswordMoreThan10Digits() {

        onView(withId(R.id.link_signup)).perform(click());

        onView(withId(R.id.input_name)).perform(typeText("Asma"));
        onView(withId(R.id.input_address)).perform(typeText("Bangkok"));
        onView(withId(R.id.input_email)).perform(typeText("Asma@gmail.com"));
        onView(withId( R.id.link_login)).perform(scrollTo());
        onView(withId(R.id.input_mobile)).perform(typeText("0123456789"));
        onView(withId(R.id.input_password)).perform(typeText("01234567890"));
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("01234567890"));
        onView(withId(R.id.btn_signup)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_password)).check(matches(hasErrorText("between 4 and 10 alphanumeric characters")));
    }

    public void createAccountFailPasswordNotMatch() {

        onView(withId(R.id.link_signup)).perform(click());

        onView(withId(R.id.input_name)).perform(typeText("Asma"));
        onView(withId(R.id.input_address)).perform(typeText("Bangkok"));
        onView(withId(R.id.input_email)).perform(typeText("Asma@gmail.com"));
        onView( withId( R.id.link_login)).perform(scrollTo());
        onView(withId(R.id.input_mobile)).perform(typeText("0123456789"));
        onView(withId(R.id.input_password)).perform(typeText("123456"));
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("456789"));
        onView(withId(R.id.btn_signup)).perform(click());

        //Verify Msg Error
        onView(withId(R.id.input_reEnterPassword)).check(matches(hasErrorText("Password Do not match")));
    }
}
