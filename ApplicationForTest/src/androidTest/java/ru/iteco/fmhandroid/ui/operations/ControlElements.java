package ru.iteco.fmhandroid.ui.operations;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.anything;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.datasources.Constants;

public class ControlElements {

    public void pickOutMainMenuLine(String menuLineEn, String menuLineRu) {
        ViewInteraction appCompatImageButton = onView(withId(R.id.main_menu_image_button));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());
        ViewInteraction materialTextView = onView(anyOf(withText(menuLineEn), withText(menuLineRu)));
        materialTextView.check(matches(isDisplayed()));
        materialTextView.perform(click());
    }

    public void pickButtonOut(int idElement) {
        ViewInteraction appCompatImageButton = onView(withId(idElement));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());
    }

    public void pickScrollButtonByID(int idElement) {
        ViewInteraction materialButton = onView(
                allOf(withId(idElement)));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(scrollTo(), click());
    }

    public void pickSoftKeyboard () {
        ViewInteraction appCompatImageButton2 = onView(
                allOf(withClassName(Matchers.is("androidx.appcompat.widget.AppCompatImageButton")),
                        anyOf(withContentDescription(Constants.SOFT_KEY_TIME_EN),
                                withContentDescription(Constants.SOFT_KEY_TIME_RU)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());
    }

    public void inputTimeBySofKeyBoard (String time, int positionElement) {
        ViewInteraction appCompatEditText = onView(
                allOf(withClassName(Matchers.is("androidx.appcompat.widget.AppCompatEditText")),
                        childAtPosition(
                                allOf(withClassName(Matchers.is("android.widget.RelativeLayout")),
                                        childAtPosition(
                                                withClassName(Matchers.is("android.widget.TextInputTimePickerView")),
                                                1)),
                                positionElement),
                        isDisplayed()));
        appCompatEditText.perform(replaceText(time), closeSoftKeyboard());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public void pickButtonByClassName(String className, int position) {
        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(withClassName(is(className))).atPosition(position);
        materialTextView2.perform(click());
    }

    public void pickButtonScrollByClassName(String className) {
        ViewInteraction appCompatImageButton = onView(
                allOf(withClassName(is(className))));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(scrollTo(), click());
    }

    public void pickCompatImageButton (String className) {
        ViewInteraction appCompatImageButton = onView(
                allOf(withClassName(Matchers.is(className)),
                        isDisplayed()));
        appCompatImageButton.perform(click());
    }
}

