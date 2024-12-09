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
public class OurMissionPageTest {

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
        userAuthorization.logOutUser();
    }

    @Epic(value = "Тестирование перехода страницу Our Mission c разных страниц")
    @Feature(value = "Переход со страницы Авторизация")
    @Story(value = "Проверка перехода со страницы авторизации на страницу Our Mission")
    @Test
    public void moveToMissionPageFromAuthorizationPageTest() {
        controlElements.pickButtonOut(R.id.our_mission_image_button);
        checkElements.checkControlElements(R.id.our_mission_title_text_view);
    }

    @Epic(value = "Тестирование перехода страницу Our Mission c разных страниц")
    @Feature(value = "Переход со страницы About")
    @Story(value = "Проверка перехода со страницы о приложении на страницу Our Mission")
    @Test
    public void moveToMissionPageFromAboutPageTest() {
        controlElements.pickOutMainMenuLine(Constants.MENU_ABOUT_EN, Constants.MENU_ABOUT_RU);
        controlElements.pickButtonOut(R.id.about_back_image_button);
        controlElements.pickButtonOut(R.id.our_mission_image_button);
        checkElements.checkControlElements(R.id.our_mission_title_text_view);
    }

    @Epic(value = "Тестирование перехода страницу Our Mission c разных страниц")
    @Feature(value = "Переход со страницы News")
    @Story(value = "Проверка перехода со страницы о News на страницу Our Mission")
    @Test
    public void moveToMissionPageFromNewsPageTest() {
        controlElements.pickOutMainMenuLine(Constants.MENU_NEWS_EN, Constants.MENU_NEWS_RU);
        controlElements.pickButtonOut(R.id.our_mission_image_button);
        checkElements.checkControlElements(R.id.our_mission_title_text_view);
    }
}
