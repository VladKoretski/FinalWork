package ru.iteco.fmhandroid.ui.datasources;

import java.util.Objects;
import java.util.Random;

public class DataGeneration {

    public int getRandomIntNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getRandomInvalidLogin(int minLength, int maxLength) {

        int targetStringLength = getRandomIntNumber(minLength, maxLength);
        StringBuilder loginInput = new StringBuilder(targetStringLength);

        for (int i = 0; i < targetStringLength; i++) {
            loginInput.append(Constants.INVALID_SIGNS.charAt(new Random().nextInt(Constants.CYRILLIC_SIGNS.length())));
        }
        return loginInput.toString();
    }

    public String getRandomInvalidString(int minLength, int maxLength) {

        Random random = new Random();
        int targetStringLength = getRandomIntNumber(minLength, maxLength);

        StringBuilder passwordInput = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = Constants.LEFT_LIMIT + (int)
                    (random.nextFloat() * (Constants.RIGHT_LIMIT - Constants.LEFT_LIMIT + 1));
            passwordInput.append((char) randomLimitedInt);
        }
        return passwordInput.toString();
    }

    public String getRandomCyrillicString(int minLength, int maxLength) {

        int targetStringLength = getRandomIntNumber(minLength, maxLength);
        StringBuilder loginInput = new StringBuilder(targetStringLength);

        for (int i = 0; i < targetStringLength; i++) {
            loginInput.append(Constants.INVALID_SIGNS.charAt(new Random().nextInt(Constants.CYRILLIC_SIGNS.length())));
        }
        return loginInput.toString();
    }

    public String getRandomCyrillicText(int numberOfWords) {

        String additionString;
        StringBuilder textInput = new StringBuilder();
        int targetStringLength;

        for (int j = 0; j <= numberOfWords; j++) {
            targetStringLength = getRandomIntNumber(Constants.MIN_LENGTH_OF_WORD, Constants.MAX_LENGTH_OF_WORD);
            additionString = " " + getRandomCyrillicString(Constants.MIN_LENGTH_OF_WORD, targetStringLength);
            textInput.append(additionString);
        }
        return textInput.toString();
    }

    public String getRandomTimeString(boolean valid, String unit) {
        int minValue;
        int maxValue;
        int randomTimeNumber;

        if (Objects.equals(unit, "hour")) {
            if (valid) {
                minValue = 0;
                maxValue = 23;
            } else {
                minValue = 25;
                maxValue = 99;
            }
        } else {
            if (valid) {
                minValue = 0;
                maxValue = 59;
            } else {
                minValue = 61;
                maxValue = 99;
            }
        }
        randomTimeNumber = getRandomIntNumber(minValue, maxValue);
        return String.valueOf(randomTimeNumber);
    }
}