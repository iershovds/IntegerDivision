package integer_division;

public class Constants {
    public static class MessageException {
        public static final String DIVISOR_IS_ZERO = "Divisor can't be 0. ";
    }

    public static class Division {
        public static final String DOUBLE_FORMAT = "#.########";
        public static final String NEXT_LINE = System.lineSeparator();
        public static final String OPERATOR_DIVISION = " / ";
        public static final String OPERATOR_EQUAL = " = ";
        public static final int ZERO = 0;
        public static final int NUMBER_ONE_FOR_MODIFY_TAB = 1;
        public static final int NUMBER_TWO_FOR_MODIFY_TAB = 2;
        public static final int NUMBER_ONE_FOR_TAB_OF_FIRST_ITERATION = 1;
        public static final int NUMBER_TEN = 10;
        public static final char UNDERSCORE = '_';
        public static final char VERTICAL_BAR = '|';
        public static final char SPACE_SYMBOL = ' ';
        public static final char MINUS = '-';
    }

    public static class MessageUI {
        public static final String ASK_USER_TO_ENTER_DATA = "Do you want to enter your DATA for division (y/n)?";
        public static final String INCORRECT_ANSWER = "Your answer is incorrect.";
        public static final String ENTER_CORRECT_ANSWER = "Please, enter correct answer (y/n).";
        public static final String INPUT_FOR_DIVISION = "Please, enter your data for division.";
        public static final String INCORRECT_INPUT = "Your input is incorrect.";
        public static final String ENTER_CORRECT_INPUT = "Please, enter correct input (only numbers).";
        public static final String YES = "Y";
        public static final String NO = "N";
        public static final String SHUT_DOWN_APP = "The application shut down.";
    }

    public static class Controller {
        public static final String REGEX_DIGITS = "[\\x2D,\\x30-\\x39]+";
    }
}
