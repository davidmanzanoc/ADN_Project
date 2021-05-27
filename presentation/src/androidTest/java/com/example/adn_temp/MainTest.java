package com.example.adn_temp;

import androidx.test.espresso.action.ViewActions;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainTest {

    public static void clickButton(Integer idButton) {
        onView(withId(idButton)).perform(click());
    }

    public static void editText(Integer idEditText, String text) {
        onView(withId(idEditText)).perform(typeText(text), ViewActions.closeSoftKeyboard());
    }
}
