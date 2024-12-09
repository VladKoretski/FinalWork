package ru.iteco.fmhandroid.ui.datasources;

import java.util.Calendar;

public class DataOfNews {

    Calendar calendar = Calendar.getInstance();
    public int month = calendar.get(Calendar.MONTH);
    public int year = calendar.get(Calendar.YEAR);

    public String dataOfNewsString(int day, int month, int year) {

        String dataStart = "";
        String dataSeparator = ".";

        if (month < 10) {
            dataSeparator = ".0";
        }
        if (day < 10) {
            dataStart = "0";
        }
        return dataStart + day + dataSeparator + month + "." + year;
    }
}
