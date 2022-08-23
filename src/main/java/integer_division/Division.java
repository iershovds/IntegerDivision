package integer_division;

import java.text.DecimalFormat;

import static integer_division.Constants.Division.*;
import static integer_division.Constants.MessageException.DIVISOR_IS_ZERO;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

public class Division {

    public String divide(int dividend, int divisor) {
        if (divisor == 0) {
            return DIVISOR_IS_ZERO;
        }
        int dividendAbs = Math.abs(dividend);
        int divisorAbs = Math.abs(divisor);
        if (dividendAbs < divisorAbs && dividendAbs != 0) {
            return divideInOneLine(dividend, divisor);
        }
        if (dividendAbs == 0) {
            return divideIfDividendIsZero(divisor);
        }
        if (dividendAbs == divisorAbs) {
            return divideIfDividendAbsAndDivisorAbsAreEquals(dividend, divisor);
        }
        return divide(dividend, divisor, dividendAbs, divisorAbs);
    }

    private String divide(int dividend, int divisor, int dividendAbs, int divisorAbs) {
        StringBuilder result = new StringBuilder();
        result.append(UNDERSCORE)
                .append(dividend)
                .append(VERTICAL_BAR)
                .append(divisor)
                .append(NEXT_LINE);
        String[] digits = Integer.toString(dividendAbs).split(EMPTY);
        VariablesOfDivision bufferNumbers = new VariablesOfDivision();
        for (int i = 0; i < digits.length; i++) {
            bufferNumbers.setDividendNumber(bufferNumbers.getRemainder() * NUMBER_TEN + Integer.valueOf(digits[i]));
            bufferNumbers.setRemainder(bufferNumbers.getDividendNumber());
            if (bufferNumbers.getIteration() == 1) {
                iterateFirstTime(dividend, bufferNumbers, divisorAbs, i, divisor, result);
            } else {
                iterateNextTime(dividend, bufferNumbers, divisorAbs, i, result);
            }
            iterateLastTime(dividend, bufferNumbers, i, digits, result);
        }
        return result.toString();
    }

    private void iterateLastTime(int dividend, VariablesOfDivision bufferNumbers, int i, String[] digits, StringBuilder result) {
        if (i == digits.length - 1) {
            int lenghtLine = digits.length + NUMBER_ONE_FOR_MODIFY_TAB;
            int remainder = bufferNumbers.getRemainder();
            if (dividend < 0) {
                lenghtLine++;
            }
            result.append(formatIntegerValue(remainder, lenghtLine))
                    .append(NEXT_LINE);
        }
    }

    private void iterateNextTime(int dividend, VariablesOfDivision bufferNumbers, int divisorAbs, int i, StringBuilder result) {
        int dividendNumber = bufferNumbers.getDividendNumber();
        if (dividendNumber >= divisorAbs || dividendNumber == 0) {
            int multiplyResult = dividendNumber / divisorAbs * divisorAbs;
            int lenghtTabWithDividendNumber = i + NUMBER_TWO_FOR_MODIFY_TAB;
            if (dividend < 0) {
                lenghtTabWithDividendNumber++;
            }
            result.append(formatDividend(dividendNumber, lenghtTabWithDividendNumber)).append(NEXT_LINE)
                    .append(formatIntegerValue(multiplyResult, lenghtTabWithDividendNumber)).append(NEXT_LINE)
                    .append(formatHorizontalLine(multiplyResult, lenghtTabWithDividendNumber)).append(NEXT_LINE);
            bufferNumbers.setFirstDividend(dividendNumber);
            bufferNumbers.setRemainder(dividendNumber % divisorAbs);
        }
    }

    private void iterateFirstTime(int dividend, VariablesOfDivision bufferNumbers, int divisorAbs, int i, int divisor,
                                  StringBuilder result) {
        int dividendNumber = bufferNumbers.getDividendNumber();
        if (dividendNumber >= divisorAbs) {
            int multiplyResult = dividendNumber / divisorAbs * divisorAbs;
            int quotient = dividend / divisor;
            int lenghtTabWithMultiplyResult = countDigits(dividendNumber)
                    + NUMBER_ONE_FOR_TAB_OF_FIRST_ITERATION;
            if (dividend < 0) {
                lenghtTabWithMultiplyResult++;
            }
            int lenghtOfSuffixAfterDivision = countDigits(dividend) - lenghtTabWithMultiplyResult
                    + NUMBER_ONE_FOR_TAB_OF_FIRST_ITERATION;
            int lenghtHorizontalLine = countSizeHorizontalLine(divisor, quotient);
            result.append(formatIntegerValue(multiplyResult, lenghtTabWithMultiplyResult))
                    .append(assemblySymbolsToString(lenghtOfSuffixAfterDivision, SPACE_SYMBOL))
                    .append(VERTICAL_BAR)
                    .append(defineHorizontalLine(lenghtHorizontalLine))
                    .append(NEXT_LINE)
                    .append(formatHorizontalLine(multiplyResult, lenghtTabWithMultiplyResult))
                    .append(assemblySymbolsToString(lenghtOfSuffixAfterDivision, SPACE_SYMBOL))
                    .append(VERTICAL_BAR)
                    .append(quotient)
                    .append(NEXT_LINE);
            bufferNumbers.setIteration(bufferNumbers.getIteration() + 1);
            bufferNumbers.setFirstDividend(dividendNumber);
            bufferNumbers.setRemainder(dividendNumber % divisorAbs);
        }
    }

    private String divideInOneLine(int dividend, int divisor) {
        double resultDivision = (double) dividend / divisor;
        return dividend + OPERATOR_DIVISION + divisor + OPERATOR_EQUAL + formatDoubleResult(resultDivision);
    }

    private String divideIfDividendIsZero(int divisor) {
        StringBuilder result = new StringBuilder();
        result.append(UNDERSCORE).append(ZERO).append(VERTICAL_BAR).append(divisor).append(NEXT_LINE)
                .append(SPACE).append(ZERO).append(VERTICAL_BAR).append(defineHorizontalLine(countDigits(divisor))).append(NEXT_LINE)
                .append(SPACE).append(MINUS).append(VERTICAL_BAR).append(ZERO).append(NEXT_LINE)
                .append(SPACE).append(ZERO).append(NEXT_LINE);
        return result.toString();
    }

    private String divideIfDividendAbsAndDivisorAbsAreEquals(int dividend, int divisor) {
        int divisorAbs = Math.abs(divisor);
        int lenghtLine = countDigits(dividend) + 1;
        StringBuilder result = new StringBuilder();
        result.append(UNDERSCORE).append(dividend).append(VERTICAL_BAR).append(divisor).append(NEXT_LINE);
        if (dividend < 0) {
            result.append(SPACE).append(SPACE).append(divisorAbs).append(VERTICAL_BAR).append(defineHorizontalLine(countDigits(divisor))).append(NEXT_LINE)
                    .append(SPACE).append(SPACE).append(defineHorizontalLine(countDigits(divisorAbs))).append(VERTICAL_BAR).append(dividend / divisor).append(NEXT_LINE)
                    .append(formatIntegerValue(ZERO, lenghtLine)).append(NEXT_LINE);
        } else {
            result.append(SPACE).append(divisorAbs).append(VERTICAL_BAR).append(defineHorizontalLine(countDigits(divisor))).append(NEXT_LINE)
                    .append(SPACE).append(defineHorizontalLine(countDigits(divisorAbs))).append(VERTICAL_BAR).append(dividend / divisor).append(NEXT_LINE)
                    .append(formatIntegerValue(ZERO, lenghtLine)).append(NEXT_LINE);
        }
        return result.toString();
    }

    private String defineHorizontalLine(int length) {
        StringBuilder result = new StringBuilder();
        while (length > 0) {
            result.append(MINUS);
            length--;
        }
        return result.toString();
    }

    private int countDigits(int i) {
        return String.valueOf(i).length();
    }

    private String formatDoubleResult(double value) {
        DecimalFormat result = new DecimalFormat(DOUBLE_FORMAT);
        return result.format(value);
    }

    private String formatDividend(Integer value, int lenght) {
        return String.format("%" + lenght + "s", UNDERSCORE + value.toString());
    }

    private String formatIntegerValue(int value, int lenght) {
        return String.format("%" + lenght + "d", value);
    }

    private String formatHorizontalLine(int multiplyResult, int lenght) {
        String horizontalLine = assemblySymbolsToString(countDigits(multiplyResult), MINUS);
        return String.format("%" + lenght + "s", horizontalLine);
    }

    private String assemblySymbolsToString(int amountOfSymbols, char symbol) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < amountOfSymbols; i++) {
            string.append(symbol);
        }
        return string.toString();
    }

    private int countSizeHorizontalLine(int divisor, int quotient) {
        return Integer.max(countDigits(divisor), countDigits(quotient));
    }
}
