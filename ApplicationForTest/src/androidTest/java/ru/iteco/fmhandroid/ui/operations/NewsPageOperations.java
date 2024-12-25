package ru.iteco.fmhandroid.ui.operations;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import android.widget.DatePicker;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;

public class NewsPageOperations {

    ControlElements controlElements = new ControlElements();
    UserAuthorization userAuthorization = new UserAuthorization();

    //выбор категории
    public void pickNewsCategory(int category) {
        controlElements.pickButtonOut(R.id.news_item_category_text_auto_complete_text_view);
        DataInteraction materialTextView = onData(anything())
                .inRoot(isPlatformPopup()).atPosition(category);
        materialTextView.perform(click());
    }

    //фильтр по категории
    public void filterNewsByCategory(int category) {
        controlElements.pickButtonOut(R.id.filter_news_material_button);
        pickNewsCategory(category);
        controlElements.pickButtonOut(R.id.filter_button);
    }

    //ввод текста (для заголовков и дискрипшенов)
    public void textInput(int placeToInputText, String text) {
        ViewInteraction textInputEditText = onView(
                allOf(withId(placeToInputText),
                        isDisplayed()));
        textInputEditText.perform(replaceText(text), closeSoftKeyboard());
    }

    //установка времени
    public void setDateByDataPicker(int datePickerLaunchViewId, int year, int month, int day) {
        controlElements.pickButtonOut(datePickerLaunchViewId);
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month, day));
        onView(withId(android.R.id.button1)).perform(click());
    }

    //ввод времени через клавиатуру
    public void timeInputWithKeyBoard(String hour, String minute) {
        int hourPosition = 0;
        int timePosition = 3;

        controlElements.pickButtonOut(R.id.news_item_publish_time_text_input_edit_text);
        controlElements.pickSoftKeyboard();
        controlElements.inputTimeBySofKeyBoard(hour, hourPosition);
        controlElements.inputTimeBySofKeyBoard(minute, timePosition);
        controlElements.pickScrollButtonByID(android.R.id.button1);
    }

    //добавление новости с текущей датой и текущим временем
    public void addTodayNews(int categoryPosition, String newsTitle, String newsDescription) {
        controlElements.pickButtonOut(R.id.edit_news_material_button);
        controlElements.pickButtonOut(R.id.add_news_image_view);

        pickNewsCategory(categoryPosition); //Выбор катеории
        textInput(R.id.news_item_title_text_input_edit_text, newsTitle); //Заполнение названия новости
        controlElements.pickButtonOut(R.id.news_item_publish_date_text_input_edit_text);
        controlElements.pickScrollButtonByID(android.R.id.button1);
        controlElements.pickButtonOut(R.id.news_item_publish_time_text_input_edit_text);
        controlElements.pickScrollButtonByID(android.R.id.button1);
        textInput(R.id.news_item_description_text_input_edit_text, newsDescription); //Заполнение описание новости
        controlElements.pickScrollButtonByID(R.id.save_button);
    }

    //добавление новости со случайной датой и текущим временем
    public void addNewsWithAnyDate(int categoryPosition, String newsTitle, String newsDescription, int day, int month, int year) {
        controlElements.pickButtonOut(R.id.edit_news_material_button);
        controlElements.pickButtonOut(R.id.add_news_image_view);
        pickNewsCategory(categoryPosition);
        textInput(R.id.news_item_title_text_input_edit_text, newsTitle);
        setDateByDataPicker(R.id.news_item_publish_date_text_input_edit_text, year, month, day);

        controlElements.pickButtonOut(R.id.news_item_publish_time_text_input_edit_text);
        controlElements.pickScrollButtonByID(android.R.id.button1);
        textInput(R.id.news_item_description_text_input_edit_text, newsDescription);
        controlElements.pickScrollButtonByID(R.id.save_button);
    }

    //выбор случайной даты и случайного времени при создании новости
    public void addNewsWithAnyDateAndTime(int categoryPosition, String newsTitle, String newsDescription, int day, int month, int year, String hour, String minute) {
        controlElements.pickButtonOut(R.id.edit_news_material_button);
        controlElements.pickButtonOut(R.id.add_news_image_view);
        pickNewsCategory(categoryPosition);
        textInput(R.id.news_item_title_text_input_edit_text, newsTitle);
        setDateByDataPicker(R.id.news_item_publish_date_text_input_edit_text, year, month, day);

        controlElements.pickButtonOut(R.id.news_item_publish_time_text_input_edit_text);
        timeInputWithKeyBoard(hour, minute);
        textInput(R.id.news_item_description_text_input_edit_text, newsDescription);
        controlElements.pickScrollButtonByID(R.id.save_button);
    }

    //ввод случайных величин "час" и "минуты" с клавиатуры
    public void inputRandomHourAndMinuteWithKeyboard(String hour, String minute) {
        controlElements.pickButtonOut(R.id.edit_news_material_button);
        controlElements.pickButtonOut(R.id.add_news_image_view);
        timeInputWithKeyBoard(hour, minute);
    }
}
