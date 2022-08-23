package integer_division;

import java.util.Scanner;

import static integer_division.Constants.MessageUI.*;

public class View {
    private final Scanner sourceInput = new Scanner(System.in);

    public void printAskForInput() {
        System.out.println(ASK_USER_TO_ENTER_DATA);
    }

    public String getUserAnswer() {
        return sourceInput.nextLine();
    }

    public void printInputDataForDivision() {
        System.out.println(INPUT_FOR_DIVISION);
    }

    public void printWrongAnswer() {
        System.out.println(INCORRECT_ANSWER);
        System.out.println(ENTER_CORRECT_ANSWER);
    }

    public void printWrongInput() {
        System.out.println(INCORRECT_INPUT);
        System.out.println(ENTER_CORRECT_INPUT);
    }

    public void printResult(String result) {
        System.out.println(result);
    }

    public void printShutDownApp() {
        System.out.println(SHUT_DOWN_APP);
    }

    public void closeInputSource() {
        sourceInput.close();
    }
}
