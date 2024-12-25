package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.github.javafaker.Faker;

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
import ru.iteco.fmhandroid.ui.datasources.DataGeneration;
import ru.iteco.fmhandroid.ui.operations.CheckElements;
import ru.iteco.fmhandroid.ui.operations.UserAuthorization;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationPageTest {

    @Rule
    public ActivityTestRule<AppActivity> mActivityScenarioRule =
            new ActivityTestRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.END,
            String.valueOf(System.currentTimeMillis()));

    UserAuthorization userAuthorization = new UserAuthorization();
    CheckElements checkElements = new CheckElements();
    DataGeneration inputData = new DataGeneration();

    Faker faker = new Faker();
    String login;
    String password;

    @Before
    public void registerIdlingResources() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource);
        try {
            userAuthorization.checkIfNotAuthorized();
        } catch (NoMatchingViewException e) {
            userAuthorization.logOutUser();
        }
    }

    @After
    public void unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource);
    }

    @Epic(value = "Авторизация")
    @Feature(value = "Открытие страницы")
    @Story(value = "Проверка открытия страницы")
    @Test
    public void checkIfAuthorizationPageLoadedTest() {
        checkElements.checkText(Constants.AUTHORISATION_EN, Constants.AUTHORISATION_RU);
    }

    @Epic(value = "Авторизация")
    @Feature(value = "Незарегистрированный пользователь")
    @Story(value = "Ввод валидных данных")
    @Test
    public void inputValidUnregisteredLoginTest() {
        login = faker.name().username();
        password = faker.internet().password();
        userAuthorization.inputLoginAndPassword(login, password);
        checkElements.checkText(Constants.AUTHORISATION_EN, Constants.AUTHORISATION_RU);
    }

    @Epic(value = "Авторизация")
    @Feature(value = "Незарегистрированный пользователь")
    @Story(value = "Ввод невалидных кириллических данных")
    @Test
    public void inputCyrillicLoginTest() {
        login = inputData.getRandomCyrillicString(Constants.MIN_NORMAL_LENGTH, Constants.MAX_NORMAL_LENGTH);
        password = inputData.getRandomCyrillicString(Constants.MIN_NORMAL_LENGTH, Constants.MAX_NORMAL_LENGTH);
        userAuthorization.inputLoginAndPassword(login, password);
        checkElements.checkText(Constants.AUTHORISATION_EN, Constants.AUTHORISATION_RU);
    }

    @Epic(value = "Авторизация")
    @Feature(value = "Незарегистрированный пользователь")
    @Story(value = "Ввод невалидной длинной строки")
    @Test
    public void inputLongUnregisteredLoginTest() {
        login = inputData.getRandomInvalidLogin(Constants.SUPER_LONG_LENGTH, Constants.SUPER_LONG_LENGTH);
        password = inputData.getRandomCyrillicString(Constants.MIN_NORMAL_LENGTH, Constants.MAX_NORMAL_LENGTH);
        userAuthorization.inputLoginAndPassword(login, password);
        checkElements.checkText(Constants.AUTHORISATION_EN, Constants.AUTHORISATION_RU);
    }

    @Epic(value = "Авторизация")
    @Feature(value = "Зарегистрированный пользователь")
    @Story(value = "Ввод валидных данных зарегистрированного пользователя")
    @Test
    public void inputValidRegisteredLoginAndPasswordTest() {
        userAuthorization.inputLoginAndPassword(Constants.USER_LOGIN, Constants.USER_PASSWORD);
        checkElements.checkControlElements(R.id.authorization_image_button);
    }

    @Epic(value = "Авторизация")
    @Feature(value = "Незарегистрированный пользователь")
    @Story(value = "Ввод пустых логина и пароля")
    @Test
    public void inputEmptyLoginAndPasswordTest() {
        login = "";
        password = "";
        userAuthorization.inputLoginAndPassword(login, password);
        checkElements.checkElementWithContentDescription(Constants.CONTENT_DESCRIPTION_EN, Constants.CONTENT_DESCRIPTION_RU);
    }

    @Epic(value = "Авторизация")
    @Feature(value = "Незарегистрированный пользователь")
    @Story(value = "Ввод пустого логина")
    @Test
    public void inputEmptyLoginTest() {
        login = "";
        password = faker.internet().password();
        userAuthorization.inputLoginAndPassword(login, password);
        checkElements.checkElementWithContentDescription(Constants.CONTENT_DESCRIPTION_EN, Constants.CONTENT_DESCRIPTION_RU);
    }

    @Epic(value = "Авторизация")
    @Feature(value = "Незарегистрированный пользователь")
    @Story(value = "Ввод пустого пароля")
    @Test
    public void inputEmptyPasswordTest() {
        login = faker.name().username();
        password = "";
        userAuthorization.inputLoginAndPassword(login, password);
        checkElements.checkElementWithContentDescription(Constants.CONTENT_DESCRIPTION_EN, Constants.CONTENT_DESCRIPTION_RU);
    }
}
