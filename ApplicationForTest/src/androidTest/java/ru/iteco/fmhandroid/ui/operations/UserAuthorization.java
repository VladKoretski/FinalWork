package ru.iteco.fmhandroid.ui.operations;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.datasources.Constants;

public class UserAuthorization {

    String hintTextLoginEn = "Login";
    String hintTextLoginRu = "Логин";
    String hintTextPasswordEn = "Password";
    String hintTextPasswordRu = "Пароль";

    public void inputLoginAndPassword(String loginInput, String passwordInput) {

        ViewInteraction EnteringLogin = onView(
                anyOf(withHint(hintTextLoginEn), withHint(hintTextLoginRu)));
        EnteringLogin.check(matches(isDisplayed()));
        EnteringLogin.perform(replaceText(loginInput), closeSoftKeyboard());

        ViewInteraction EnteringPassword = onView(
                anyOf(withHint(hintTextPasswordEn), withHint(hintTextPasswordRu)));
        EnteringLogin.check(matches(isDisplayed()));
        EnteringPassword.perform(replaceText(passwordInput), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button)));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
    }

    public void logOutUser() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.authorization_image_button)));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());
        ViewInteraction materialTextView = onView(
                anyOf(withText(Constants.LOG_OUT_EN), withText(Constants.LOG_OUT_RU)));
        materialTextView.perform(click());
    }

    public void checkIfNotAuthorized() {
        ViewInteraction checkTextAuthorization = onView(
                anyOf(withText(Constants.AUTHORISATION_EN), withText(Constants.AUTHORISATION_RU)));
        checkTextAuthorization.check(matches(isDisplayed()));
    }

    public void cancelFaultyNewsAndLogOutUser() {
        ControlElements cancelClick = new ControlElements();
        cancelClick.pickScrollButtonByID(android.R.id.button2);
        cancelClick.pickScrollButtonByID(R.id.cancel_button);
        cancelClick.pickScrollButtonByID(android.R.id.button1);
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.authorization_image_button)));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());
        ViewInteraction materialTextView = onView(
                anyOf(withText(Constants.LOG_OUT_EN), withText(Constants.LOG_OUT_RU)));
        materialTextView.perform(click());
        materialTextView.check(matches(isDisplayed()));
        materialTextView.perform(click());
    }

    public void checkFirstPlaceAndLogout() {
        UserAuthorization userAuthorization = new UserAuthorization();
        try {
            userAuthorization.checkIfNotAuthorized();
        } catch (NoMatchingViewException e) {
            userAuthorization.logOutUser();
        }
    }
}
