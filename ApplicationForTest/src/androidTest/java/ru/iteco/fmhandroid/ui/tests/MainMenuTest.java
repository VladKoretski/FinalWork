package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Attachment;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
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
public class MainMenuTest {

    @Rule
    public ActivityTestRule<AppActivity> mActivityScenarioRule =
            new ActivityTestRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.END,
            String.valueOf(System.currentTimeMillis()));

    ControlElements controlElements = new ControlElements();
    UserAuthorization userAuthorization = new UserAuthorization();
    CheckElements checkElements = new CheckElements();

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

    @Epic(value = "Тестирование главного меню")
    @Feature(value = "Переход со страницы About на страницу Main")
    @Story(value = "Проверка перехода на страницу авторизации со страницы About с помощью кнопки НАЗАД")
    @Test
    public void fromAboutToMainPageWithBackButton() {
        controlElements.pickOutMainMenuLine(Constants.MENU_ABOUT_EN, Constants.MENU_ABOUT_RU);
        controlElements.pickButtonOut(R.id.about_back_image_button);
        checkElements.checkTextWithIDAndClass(R.id.all_news_text_view, R.id.container_list_news_include_on_fragment_main,
                Constants.CHECK_ALL_NEWS_EN, Constants.CHECK_ALL_NEWS_RU, android.widget.LinearLayout.class);
        userAuthorization.logOutUser();
    }

    @Epic(value = "Тестирование главного меню")
    @Feature(value = "Переход на страницу News")
    @Story(value = "Проверка перехода на страницу News co страницы авторизации")
    @Test
    public void moveByMainMenuToNewsPageTest() {
        controlElements.pickOutMainMenuLine(Constants.MENU_NEWS_EN, Constants.MENU_NEWS_RU);
        checkElements.checkText(Constants.CHECK_NEWS_EN, Constants.CHECK_NEWS_RU);
        userAuthorization.logOutUser();
    }

    @Epic(value = "Тестирование главного меню")
    @Feature(value = "Переход на страницу Авторизации со страницы News")
    @Story(value = "Проверка перехода на страницу Авторизации со страницы News")
    @Test
    public void moveByMainMenuToAuthorizationPageFromNewsPageTest() {
        controlElements.pickOutMainMenuLine(Constants.MENU_NEWS_EN, Constants.MENU_NEWS_RU);
        controlElements.pickOutMainMenuLine(Constants.MENU_MAIN_EN, Constants.MENU_MAIN_RU);
        checkElements.checkTextWithIDAndClass(R.id.all_news_text_view, R.id.container_list_news_include_on_fragment_main,
                Constants.CHECK_ALL_NEWS_EN, Constants.CHECK_ALL_NEWS_RU, android.widget.LinearLayout.class);
        userAuthorization.logOutUser();
    }

    @Epic(value = "Тестирование главного меню")
    @Feature(value = "Переход на страницу Наша Миссия")
    @Story(value = "Проверка перехода на страницу Наша Миссия после авторизации")
    @Test
    public void moveByMainMenuToMissionPageTest() {
        controlElements.pickButtonOut(R.id.our_mission_image_button);
        checkElements.checkControlElements(R.id.our_mission_title_text_view);
        userAuthorization.logOutUser();
    }
}