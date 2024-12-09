package ru.iteco.fmhandroid.ui.operations;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class CheckElements {

    @Step("Проверка появления управляющего элемента по ID")
    public void checkControlElements(int elementID) {
        ViewInteraction appCompatImageButton = onView(withId(elementID));
        appCompatImageButton.check(matches(isDisplayed()));
    }

    @Step("Проверка появление текста {expectedText}")
    public void checkTextElements(int elementID, int parentElementID, String expectedText) {
        ViewInteraction textView = onView(
                allOf(withId(elementID), withText(expectedText),
                        withParent(withParent(withId(parentElementID))),
                        isDisplayed()));
        textView.check(matches(withText(expectedText)));
    }

    @Step("Проверка появление текста {expectedTextEn} или {expectedTextRu}")
    public void checkText(String expectedTextEn, String expectedTextRu) {
        ViewInteraction textView = onView(
                anyOf(withText(expectedTextEn), withText(expectedTextRu)));
        textView.check(matches(isDisplayed()));
    }

    @Step("Проверка появление текста {expectedTextEn} или {expectedTextRu}")
    public void checkTextWithIDAndClass(int elementID, int parentID, String expectedTextEn, String expectedTextRu, java.lang.Class classElement) {
        ViewInteraction textView = onView(
                allOf(withId(elementID), anyOf(withText(expectedTextEn), withText(expectedTextRu)),
                        withParent(allOf(withId(parentID),
                                withParent(IsInstanceOf.instanceOf(classElement))))));
        textView.check(matches(isDisplayed()));
    }

    @Step("Проверка появление всплывающего сообщения {expectedTextEn} или {expectedTextRu}")
    public void checkElementWithContentDescription(String expectedTextEn, String expectedTextRu) {
        ViewInteraction imageView = onView(
                allOf(anyOf(withContentDescription(expectedTextEn), withContentDescription(expectedTextRu)),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
    }

    @Step("Проверка появление всплывающего сообщения {expectedTextEn} или {expectedTextRu}")
    public void checkInstanceElementWithText(String expectedTextEn, String expectedTextRu) {
        ViewInteraction textView = onView(
                allOf(IsInstanceOf.instanceOf(android.widget.TextView.class), anyOf(withText(expectedTextEn), withText(expectedTextRu)),
                        withParent(allOf(IsInstanceOf.instanceOf(android.widget.RelativeLayout.class),
                                withParent(IsInstanceOf.instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));
    }

    @Step("Проверка появления управляющего элемента {contentDescriptionEn} или {contentDescriptionRu}")
    public void checkElementWithIdAndContentDescription(int Id, String contentDescriptionEn, String contentDescriptionRu) {
        ViewInteraction imageView = onView(
                allOf(withId(Id), anyOf(withContentDescription(contentDescriptionEn),
                        withContentDescription(contentDescriptionRu))));
        imageView.check(matches(isDisplayed()));
    }

    @Step("Проверка появления управляющего элемента по ID и тексту {textEn} или {textRu}")
    public void checkElementByIdAndText(int id, String textEn, String textRu) {
        ViewInteraction button = onView(
                allOf(withId(id), anyOf(withText(textEn), withText(textRu)),
                        withParent(allOf(withId(R.id.all_news_cards_block_constraint_layout),
                                withParent(withId(R.id.container_list_news_include)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
    }

}