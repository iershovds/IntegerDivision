package integer_division;

import static integer_division.Constants.Controller.REGEX_DIGITS;
import static integer_division.Constants.MessageUI.NO;
import static integer_division.Constants.MessageUI.YES;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class Controller {
    private View app;
    private Division division;

    public void runApp() {
        startApp();
        continueApp();
        app.closeInputSource();
    }

    private void continueApp() {
        askUserForInput();
        while (checkUserAnswer(app.getUserAnswer().toUpperCase())) {
            int dividend = processInput(app.getUserAnswer());
            int divisor = processInput(app.getUserAnswer());
            app.printResult(division.divide(dividend, divisor));
            askUserForInput();
        }
    }

    private void startApp() {
        app = new View();
        division = new Division();
    }

    private void askUserForInput() {
        app.printAskForInput();
    }

    private boolean checkUserAnswer(String answerInput) {
        String answer = processUserAnswer(answerInput);
        if (YES.equals(answer)) {
            app.printInputDataForDivision();
            return true;
        }
        app.printShutDownApp();
        return false;
    }

    private String processUserAnswer(String answer) {
        if (YES.equals(answer) || NO.equals(answer)) {
            return answer;
        }
        return processIncorrectAnswer();
    }

    private String processIncorrectAnswer() {
        app.printWrongAnswer();
        return processUserAnswer(app.getUserAnswer());
    }

    private int processInput(String input) {
        validateInput(input);
        return Integer.parseInt(input);
    }

    private void validateInput(String input) {
        if (input.equals(null) || input.equals(EMPTY) || !input.matches(REGEX_DIGITS)) {
            app.printWrongInput();
            continueApp();
        }
    }
}
