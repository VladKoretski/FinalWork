package ru.iteco.fmhandroid.ui.datasources;

// useful constants
public class Constants {
    public static final String USER_LOGIN = "login2";
    public static final String USER_PASSWORD = "password2";
    public static final String[] NEWS_CATEGORY = {"Объявление", "День рождения", "Зарплата", "Профсоюз", "Праздник", "Массаж", "Благодарность", "Нужна помощь"};

    //constants for random texts generation
    public static final String INVALID_SIGNS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя!№;%:?*()_+=-[]{}'/.,<>";
    public static final String CYRILLIC_SIGNS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static final int NUMBER_OF_CATEGORIES = 7;
    public static final int LENGTH_OF_TEXT = 5;
    public static final int MIN_LENGTH_OF_WORD = 1;
    public static final int MAX_LENGTH_OF_WORD = 7;
    public static final int LEFT_LIMIT = 97; // letter 'a'
    public static final int RIGHT_LIMIT = 122; // letter 'z'

    //lengths of logins and passwords
    public static final int MIN_NORMAL_LENGTH = 5; //normal length of password or login
    public static final int MAX_NORMAL_LENGTH = 12;
    public static final int SUPER_LONG_LENGTH = 50;  //really long login

    //control elements
    public static String MENU_NEWS_EN = "News";
    public static String MENU_NEWS_RU = "Новости";
    public static String MENU_MAIN_EN = "Main";
    public static String MENU_MAIN_RU = "Главная";
    public static String MENU_ABOUT_EN = "About";
    public static String MENU_ABOUT_RU = "О приложении";
    public static String LOG_OUT_EN = "Log out";
    public static String LOG_OUT_RU = "Выйти";
    public static String MAIN_MENU_EN = "Main menu";
    public static String MAIN_MENU_RU = "Главное меню";
    public static String SOFT_KEY_TIME_EN = "Switch to text input mode for the time input.";
    public static String SOFT_KEY_TIME_RU = "Перейдите в режим ввода текста для установки времени.";
    public static String EDIT_BUTTON_CONTENT_DESCRIPTION_EN = "News editing button";
    public static String EDIT_BUTTON_CONTENT_DESCRIPTION_RU = "Кнопка редактирования новости";
    public static String DELETE_BUTTON_CONTENT_DESCRIPTION_EN = "News delete button";
    public static String DELETE_BUTTON_CONTENT_DESCRIPTION_RU = "Кнопка удаления новости";
    public static String BUTTON_REFRESH_NEWS_EN = "REFRESH";
    public static String BUTTON_REFRESH_NEWS_RU = "ОБНОВИТЬ";
    public static String SORT_NEWS_BUTTON_EN = "Sort news list button";
    public static String SORT_NEWS_BUTTON_RU = "Кнопка сортировки списка новостей";

    //URL
    public static String URL_PRIVACY_POLICY = "https://vhospice.org/#/privacy-policy";
    public static String URL_TERMS_OF_USE = "https://vhospice.org/#/terms-of-use";

    //check strings
    public static String CHECK_ALL_NEWS_EN = "ALL NEWS";
    public static String CHECK_ALL_NEWS_RU = "ВСЕ НОВОСТИ";
    public static String CHECK_NEWS_EN = "News";
    public static String CHECK_NEWS_RU = "Новости";
    public static String CONTENT_DESCRIPTION_EN = "app background image";
    public static String CONTENT_DESCRIPTION_RU = "Подложка основных экранов приложения";
    public static String AUTHORISATION_EN = "Authorization";
    public static String AUTHORISATION_RU = "Авторизация";
    public static String TIME_INPUT_ERROR_EN = "Enter a valid time";
    public static String TIME_INPUT_ERROR_RU = "Введите действительное время";
}
