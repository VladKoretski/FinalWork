package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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

import java.time.YearMonth;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Attachment;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Flaky;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.EspressoIdlingResource;
import ru.iteco.fmhandroid.ui.datasources.Constants;
import ru.iteco.fmhandroid.ui.datasources.DataGeneration;
import ru.iteco.fmhandroid.ui.datasources.DataOfNews;
import ru.iteco.fmhandroid.ui.operations.CheckElements;
import ru.iteco.fmhandroid.ui.operations.ControlElements;
import ru.iteco.fmhandroid.ui.operations.NewsPageOperations;
import ru.iteco.fmhandroid.ui.operations.UserAuthorization;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsPageFunctionsTest {

    @Rule
    public ActivityTestRule<AppActivity> mActivityScenarioRule =
            new ActivityTestRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.END,
            String.valueOf(System.currentTimeMillis()));

    ControlElements menuLinePickOut = new ControlElements();
    UserAuthorization userAuthorization = new UserAuthorization();
    DataGeneration randomObject = new DataGeneration();
    NewsPageOperations newsPageOperations = new NewsPageOperations();
    CheckElements checkElements = new CheckElements();
    DataOfNews dataOfNews = new DataOfNews();
    String newsTitle;
    String newsDescription;
    int categoryPosition;
    int monthNews = dataOfNews.month + 2;
    int yearNews = dataOfNews.year;
    int dayNews;
    int daysInMonth;

    @Before
    public void registerIdlingResources() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource);
        try {
            userAuthorization.checkIfNotAuthorized();
        } catch (NoMatchingViewException e) {
            userAuthorization.logOutUser();
        }
        if (monthNews == 13) {
            monthNews = 1;
            yearNews = dataOfNews.year + 1;
        }
        YearMonth yearMonthObject = YearMonth.of(yearNews, monthNews);
        daysInMonth = yearMonthObject.lengthOfMonth();
        userAuthorization.inputLoginAndPassword(Constants.USER_LOGIN, Constants.USER_PASSWORD);
        menuLinePickOut.pickOutMainMenuLine(Constants.MENU_NEWS_EN, Constants.MENU_NEWS_RU);
    }

    @After
    public void unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости")
    @Story(value = "Добавление сегодняшней новости")
    @Test
    public void addTodayNewsTest() {
        categoryPosition = randomObject.getRandomIntNumber(0, Constants.NUMBER_OF_CATEGORIES);
        newsTitle = randomObject.getRandomCyrillicString(Constants.MIN_LENGTH_OF_WORD, Constants.MAX_LENGTH_OF_WORD);
        newsDescription = randomObject.getRandomCyrillicText(Constants.LENGTH_OF_TEXT);
        newsPageOperations.addTodayNews(categoryPosition, newsTitle, newsDescription);
        newsPageOperations.filterNewsByCategory(categoryPosition);
        checkElements.checkTextElements(R.id.news_item_title_text_view, R.id.news_item_material_card_view, newsTitle);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости")
    @Story(value = "Добавление новости со случайной датой в следующем месяце")
    @Test
    public void addNextMonthTest() {
        dayNews = randomObject.getRandomIntNumber(1, daysInMonth);
        categoryPosition = randomObject.getRandomIntNumber(0, Constants.NUMBER_OF_CATEGORIES);
        newsTitle = randomObject.getRandomCyrillicString(Constants.MIN_LENGTH_OF_WORD, Constants.MAX_LENGTH_OF_WORD);
        newsDescription = randomObject.getRandomCyrillicText(Constants.LENGTH_OF_TEXT);
        newsPageOperations.addNewsWithAnyDate(categoryPosition, newsTitle, newsDescription, dayNews, monthNews, yearNews); // add news
        newsPageOperations.filterNewsByCategory(categoryPosition);
        checkElements.checkTextElements(R.id.news_item_title_text_view, R.id.news_item_material_card_view, newsTitle);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости")
    @Story(value = "Проверка даты создания добавленной новости")
    @Attachment
    @Test
    public void checkDataOfNewsCreationTest() {
        dayNews = randomObject.getRandomIntNumber(1, daysInMonth);
        DataOfNews dataOfNews = new DataOfNews();
        categoryPosition = randomObject.getRandomIntNumber(0, Constants.NUMBER_OF_CATEGORIES);
        newsTitle = randomObject.getRandomCyrillicString(Constants.MIN_LENGTH_OF_WORD, Constants.MAX_LENGTH_OF_WORD);
        newsDescription = randomObject.getRandomCyrillicText(Constants.LENGTH_OF_TEXT);
        newsPageOperations.addNewsWithAnyDate(categoryPosition, newsTitle, newsDescription, dayNews, monthNews, yearNews); // add news
        newsPageOperations.filterNewsByCategory(categoryPosition);
        String expectedData = dataOfNews.dataOfNewsString(dayNews, monthNews, yearNews);
        checkElements.checkTextElements(R.id.news_item_create_date_text_view, R.id.news_item_material_card_view, expectedData);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости")
    @Story(value = "Добавление новости с вводом валидного времени с клавиатуры")
    @Test
    public void inputValidTimeByKeyboardWhenAddNewsTest() {
        dayNews = randomObject.getRandomIntNumber(1, daysInMonth);
        String hour = randomObject.getRandomTimeString(true, "hour");
        String minute = randomObject.getRandomTimeString(true, "minute");
        categoryPosition = randomObject.getRandomIntNumber(0, Constants.NUMBER_OF_CATEGORIES);
        newsTitle = randomObject.getRandomCyrillicString(Constants.MIN_LENGTH_OF_WORD, Constants.MAX_LENGTH_OF_WORD);
        newsDescription = randomObject.getRandomCyrillicText(Constants.LENGTH_OF_TEXT);
        newsPageOperations.addNewsWithAnyDateAndTime(categoryPosition, newsTitle, newsDescription, dayNews, monthNews, yearNews, hour, minute); // add news
        newsPageOperations.filterNewsByCategory(categoryPosition);
        checkElements.checkTextElements(R.id.news_item_title_text_view, R.id.news_item_material_card_view, newsTitle);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости")
    @Story(value = "Ввод невалидного значения часа и минут с клавиатуры")
    @Test
    public void inputInvalidTimeByKeyboardWhenAddNewsTest() {
        dayNews = randomObject.getRandomIntNumber(1, daysInMonth);
        String hour = randomObject.getRandomTimeString(false, "hour");
        String minute = randomObject.getRandomTimeString(false, "minute");
        newsPageOperations.inputRandomHourAndMinuteWithKeyboard(hour, minute);
        checkElements.checkInstanceElementWithText(Constants.TIME_INPUT_ERROR_RU, Constants.TIME_INPUT_ERROR_EN);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости")
    @Story(value = "Добавление новости с вводом невалидного значения часа и валидного минут с клавиатуры")
    @Test
    public void inputInvalidMinutesByKeyboardWhenAddNewsTest() {
        dayNews = randomObject.getRandomIntNumber(1, daysInMonth);
        String hour = randomObject.getRandomTimeString(true, "hour");
        String minute = randomObject.getRandomTimeString(false, "minute");
        newsPageOperations.inputRandomHourAndMinuteWithKeyboard(hour, minute);
        checkElements.checkInstanceElementWithText(Constants.TIME_INPUT_ERROR_RU, Constants.TIME_INPUT_ERROR_EN);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости")
    @Story(value = "Ввод невалидного значения часа и валидного минут с клавиатуры при создании новости")
    @Test
    public void inputInvalidHoursByKeyboardWhenAddNewsTest() {
        dayNews = randomObject.getRandomIntNumber(1, daysInMonth);
        String hour = randomObject.getRandomTimeString(false, "hour");
        String minute = randomObject.getRandomTimeString(true, "minute");
        newsPageOperations.inputRandomHourAndMinuteWithKeyboard(hour, minute);
        checkElements.checkInstanceElementWithText(Constants.TIME_INPUT_ERROR_RU, Constants.TIME_INPUT_ERROR_EN);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости - граничные условия времени")
    @Story(value = "Ввод значения часа = 24 и валидного минут с клавиатуры при создании новости")
    @Test
    public void inputBoundaryHoursByKeyboardWhenAddNewsTest() {
        String hour = "24";
        String minute = randomObject.getRandomTimeString(true, "minute");
        newsPageOperations.inputRandomHourAndMinuteWithKeyboard(hour, minute);
        checkElements.checkInstanceElementWithText(Constants.TIME_INPUT_ERROR_RU, Constants.TIME_INPUT_ERROR_EN);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости - граничные условия времени")
    @Story(value = "Ввод значения часа = 25 и валидного минут с клавиатуры при создании новости")
    @Test
    public void inputBoundaryHoursPlusOneByKeyboardWhenAddNewsTest() {
        String hour = "25";
        String minute = randomObject.getRandomTimeString(true, "minute");
        newsPageOperations.inputRandomHourAndMinuteWithKeyboard(hour, minute);
        checkElements.checkInstanceElementWithText(Constants.TIME_INPUT_ERROR_RU, Constants.TIME_INPUT_ERROR_EN);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости")
    @Story(value = "Добавление новости с вводом валидного значения часа и значения минут = 60 с клавиатуры")
    @Test
    public void inputBoundaryMinutesByKeyboardWhenAddNewsTest() {
        dayNews = randomObject.getRandomIntNumber(1, daysInMonth);
        String hour = randomObject.getRandomTimeString(true, "hour");
        String minute = "60";
        newsPageOperations.inputRandomHourAndMinuteWithKeyboard(hour, minute);
        checkElements.checkInstanceElementWithText(Constants.TIME_INPUT_ERROR_RU, Constants.TIME_INPUT_ERROR_EN);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости")
    @Story(value = "Добавление новости с вводом валидного значения часа и значения минут = 60 с клавиатуры")
    @Test
    public void inputBoundaryMinutesPlusOneByKeyboardWhenAddNewsTest() {
        dayNews = randomObject.getRandomIntNumber(1, daysInMonth);
        String hour = randomObject.getRandomTimeString(true, "hour");
        String minute = "61";
        newsPageOperations.inputRandomHourAndMinuteWithKeyboard(hour, minute);
        checkElements.checkInstanceElementWithText(Constants.TIME_INPUT_ERROR_RU, Constants.TIME_INPUT_ERROR_EN);
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости")
    @Story(value = "Добавление новости с вводом значения часа и значения минут = 59 с клавиатуры")
    @Test
    public void inputBoundaryMinutesMinusOneByKeyboardWhenAddNewsTest() {
        dayNews = randomObject.getRandomIntNumber(1, daysInMonth);
        String hour = randomObject.getRandomTimeString(true, "hour");
        String minute = "59";
        newsPageOperations.inputRandomHourAndMinuteWithKeyboard(hour, minute);
        ViewInteraction button = onView(withId(R.id.save_button));
        button.check(matches(isDisplayed()));
    }

    @Flaky
    @Epic(value = "Управление новостями")
    @Feature(value = "Добавление новости - граничные условия времени")
    @Story(value = "Ввод значения часа = 23 и валидного минут с клавиатуры при создании новости")
    @Test
    public void inputBoundaryHoursMinusOneByKeyboardWhenAddNewsTest() {
        String hour = "23";
        String minute = randomObject.getRandomTimeString(true, "minute");
        newsPageOperations.inputRandomHourAndMinuteWithKeyboard(hour, minute);
        ViewInteraction button = onView(withId(R.id.save_button));
        button.check(matches(isDisplayed()));    }
}