package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Flaky;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.EspressoIdlingResource;
import ru.iteco.fmhandroid.ui.datasources.Constants;
import ru.iteco.fmhandroid.ui.operations.CheckElements;
import ru.iteco.fmhandroid.ui.operations.ControlElements;
import ru.iteco.fmhandroid.ui.operations.UserAuthorization;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ElementsExistenceTest {

    @Rule
    public ActivityTestRule<AppActivity> mActivityScenarioRule =
            new ActivityTestRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.END,
            String.valueOf(System.currentTimeMillis()));

    UserAuthorization userAuthorization = new UserAuthorization();
    CheckElements checkElements = new CheckElements();
    ControlElements controlElements = new ControlElements();
    String hintTextLoginEn = " Login";
    String hintTextLoginRu = "Логин";
    String hintTextPasswordEn = "Password";
    String hintTextPasswordRu = "Пароль";

    @Before
    public void registerIdlingResources() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource);
        try {
            userAuthorization.checkIfNotAuthorized();
        } catch (NoMatchingViewException e) {
            userAuthorization.logOutUser();
        }
        userAuthorization.inputLoginAndPassword(Constants.USER_LOGIN, Constants.USER_PASSWORD);
    }

    @After
    public void unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource);
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Страница авторизации")
    @Story(value = "Проверка наличия строки ввода логина")
    @Test
    public void checkLoginLineExistenceTest() {
        userAuthorization.logOutUser();
        ViewInteraction EnteringLogin = onView(
                anyOf(withHint(hintTextLoginEn), withHint(hintTextLoginRu)));
        EnteringLogin.check(matches(isDisplayed()));
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Страница авторизации")
    @Story(value = "Проверка наличия строки ввода пароля")
    @Test
    public void checkPasswordLineExistenceTest() {
        userAuthorization.logOutUser();
        ViewInteraction EnteringPassword = onView(
                anyOf(withHint(hintTextPasswordEn), withHint(hintTextPasswordRu)));
        EnteringPassword.check(matches(isDisplayed()));
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Страница авторизации")
    @Story(value = "Проверка наличия кнопки ОК")
    @Test
    public void checkLoginButtonExistenceTest() {
        userAuthorization.logOutUser();
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button)));
        materialButton.check(matches(isDisplayed()));
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Главная страница")
    @Story(value = "Наличие на странице главного меню")
    @Test
    public void checkMainMenuExistenceTest() {
        ViewInteraction appCompatImageButton = onView(withId(R.id.main_menu_image_button));
        appCompatImageButton.check(matches(isDisplayed()));
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Главная страница")
    @Story(value = "Наличие на странице кнопки перехада в раздел Наша миссия")
    @Test
    public void checkGoToMissionButtonExistenceTest() {
        ViewInteraction appCompatImageButton = onView(withId(R.id.our_mission_image_button));
        appCompatImageButton.check(matches(isDisplayed()));
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Главная страница")
    @Story(value = "Наличие на странице кнопки авторизации")
    @Test
    public void checkLogoutButtonExistenceTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.authorization_image_button)));
        appCompatImageButton.check(matches(isDisplayed()));
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Страница новости")
    @Story(value = "Наличие на странице кнопки редактирования новостей")
    @Test
    public void checkNewsEditingButtonExistenceTest() {
        controlElements.pickOutMainMenuLine(Constants.MENU_NEWS_EN, Constants.MENU_NEWS_RU);
        controlElements.pickButtonOut(R.id.edit_news_material_button);
        checkElements.checkElementWithIdAndContentDescription(R.id.edit_news_item_image_view,
                Constants.EDIT_BUTTON_CONTENT_DESCRIPTION_EN, Constants.EDIT_BUTTON_CONTENT_DESCRIPTION_RU);
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Страница новости")
    @Story(value = "Наличие на странице кнопки удаления новостей")
    @Test
    public void checkDeleteButtonExistenceTest() {
        controlElements.pickOutMainMenuLine(Constants.MENU_NEWS_EN, Constants.MENU_NEWS_RU);
        controlElements.pickButtonOut(R.id.edit_news_material_button);
        ViewInteraction imageView = onView(
                allOf(withId(R.id.delete_news_item_image_view),
                        anyOf(withContentDescription(Constants.DELETE_BUTTON_CONTENT_DESCRIPTION_EN),
                        withContentDescription(Constants.DELETE_BUTTON_CONTENT_DESCRIPTION_RU)),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Страница новости")
    @Story(value = "Наличие на странице фильтрования новостей")
    @Test
    public void checkFilterNewsCategoryButtonExistenceTest() {
        controlElements.pickOutMainMenuLine(Constants.MENU_NEWS_EN, Constants.MENU_NEWS_RU);
        checkElements.checkControlElements(R.id.filter_news_material_button);
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Страница новости")
    @Story(value = "Наличие на странице сортировки новостей")
    @Test
    public void checkSortNewsCategoryButtonExistenceTest() {
        controlElements.pickOutMainMenuLine(Constants.MENU_NEWS_EN, Constants.MENU_NEWS_RU);
        checkElements.checkElementWithIdAndContentDescription(R.id.sort_news_material_button,Constants.SORT_NEWS_BUTTON_EN, Constants.SORT_NEWS_BUTTON_RU);
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Страница новости")
    @Story(value = "Наличие на странице кнопки редактирования новостей")
    @Test
    public void checkEditNewsButtonExistenceTest() {
        controlElements.pickOutMainMenuLine(Constants.MENU_NEWS_EN, Constants.MENU_NEWS_RU);
        checkElements.checkControlElements(R.id.edit_news_material_button);
    }

    @Flaky
    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Страница новости")
    @Story(value = "Наличие на странице кнопки обновления страницы")
    @Test
    public void checkRetryNewsButtonExistenceTest() {
        controlElements.pickOutMainMenuLine(Constants.MENU_NEWS_EN, Constants.MENU_NEWS_RU);
        checkElements.checkElementByIdAndText(R.id.news_retry_material_button, Constants.BUTTON_REFRESH_NEWS_EN,
                Constants.BUTTON_REFRESH_NEWS_RU);
    }

    @Epic(value = "Существование элементов на страницах")
    @Feature(value = "Проверка наличия меню на странице About")
    @Story(value = "Проверка возможности перехода на страницу авторизации со страницы About с помощью меню")
    @Test
    public void checkMenuOnAboutPageTest() {
        controlElements.pickOutMainMenuLine(Constants.MENU_ABOUT_EN, Constants.MENU_ABOUT_RU);
        checkElements.checkTextWithIDAndClass(R.id.main_menu_image_button, R.id.container_custom_app_bar_include_on_fragment_main,
                Constants.MAIN_MENU_RU, Constants.MAIN_MENU_EN, android.widget.LinearLayout.class);
        userAuthorization.logOutUser();
    }
}