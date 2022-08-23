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
        result.append(UNDERSCORE).append(dividend)
              .append(VERTICAL_BAR).append(divisor).append(NEXT_LINE);
        String[] digits = Integer.toString(dividendAbs).split(EMPTY);
        VariablesOfDivision bufferNumbers = new VariablesOfDivision();
        for (int i = 0; i < digits.length; i++) {
            bufferNumbers.setDividendNumber(bufferNumbers.getRemainder() * NUMBER_TEN + Integer.parseInt(digits[i]));
            bufferNumbers.setRemainder(bufferNumbers.getDividendNumber());
            if (bufferNumbers.getIteration() == 1) {
                iterateFirstTime(dividend, bufferNumbers, divisorAbs, divisor, result);
            } else {
                iterateNextTime(dividend, bufferNumbers, divisorAbs, i, result);
            }
            iterateLastTime(dividend, bufferNumbers, i, digits, result);
        }
        return result.toString();
    }

    private void iterateLastTime(int dividend, VariablesOfDivision bufferNumbers, int i, String[] digits, StringBuilder result) {
        if (i == digits.length - 1) {
            int lengthLine = digits.length + NUMBER_ONE_FOR_MODIFY_TAB;
            int remainder = bufferNumbers.getRemainder();
            if (dividend < 0) {
                lengthLine++;
            }
            result.append(formatIntegerValue(remainder, lengthLine))
                    .append(NEXT_LINE);
        }
    }

    private void iterateNextTime(int dividend, VariablesOfDivision bufferNumbers, int divisorAbs, int i, StringBuilder result) {
        int dividendNumber = bufferNumbers.getDividendNumber();
        if (dividendNumber >= divisorAbs || dividendNumber == 0) {
            int multiplyResult = dividendNumber / divisorAbs * divisorAbs;
            int lengthTabWithDividendNumber = i + NUMBER_TWO_FOR_MODIFY_TAB;
            if (dividend < 0) {
                lengthTabWithDividendNumber++;
            }
            result.append(formatDividend(dividendNumber, lengthTabWithDividendNumber)).append(NEXT_LINE)
                  .append(formatIntegerValue(multiplyResult, lengthTabWithDividendNumber)).append(NEXT_LINE)
                  .append(formatHorizontalLine(multiplyResult, lengthTabWithDividendNumber)).append(NEXT_LINE);
            bufferNumbers.setFirstDividend();
            bufferNumbers.setRemainder(dividendNumber % divisorAbs);
        }
    }

    private void iterateFirstTime(int dividend, VariablesOfDivision bufferNumbers, int divisorAbs, int divisor,
                                  StringBuilder result) {
        int dividendNumber = bufferNumbers.getDividendNumber();
        if (dividendNumber >= divisorAbs) {
            int multiplyResult = dividendNumber / divisorAbs * divisorAbs;
            int quotient = dividend / divisor;
            int lengthTabWithMultiplyResult = countDigits(dividendNumber)
                    + NUMBER_ONE_FOR_TAB_OF_FIRST_ITERATION;
            if (dividend < 0) {
                lengthTabWithMultiplyResult++;
            }
            int lengthOfSuffixAfterDivision = countDigits(dividend) - lengthTabWithMultiplyResult
                    + NUMBER_ONE_FOR_TAB_OF_FIRST_ITERATION;
            int lengthHorizontalLine = countSizeHorizontalLine(divisor, quotient);
            result.append(formatIntegerValue(multiplyResult, lengthTabWithMultiplyResult))
                    .append(assemblySymbolsToString(lengthOfSuffixAfterDivision, SPACE_SYMBOL))
                    .append(VERTICAL_BAR)
                    .append(defineHorizontalLine(lengthHorizontalLine))
                    .append(NEXT_LINE)
                    .append(formatHorizontalLine(multiplyResult, lengthTabWithMultiplyResult))
                    .append(assemblySymbolsToString(lengthOfSuffixAfterDivision, SPACE_SYMBOL))
                    .append(VERTICAL_BAR)
                    .append(quotient)
                    .append(NEXT_LINE);
            bufferNumbers.setIteration(bufferNumbers.getIteration() + 1);
            bufferNumbers.setFirstDividend();
            bufferNumbers.setRemainder(dividendNumber % divisorAbs);
        }
    }

    private String divideInOneLine(int dividend, int divisor) {
        double resultDivision = (double) dividend / divisor;
        return dividend + OPERATOR_DIVISION + divisor + OPERATOR_EQUAL + formatDoubleResult(resultDivision);
    }

    private String divideIfDividendIsZero(int divisor) {
        return String.valueOf(UNDERSCORE) + ZERO + VERTICAL_BAR + divisor + NEXT_LINE +
                SPACE + ZERO + VERTICAL_BAR + defineHorizontalLine(countDigits(divisor)) + NEXT_LINE +
                SPACE + MINUS + VERTICAL_BAR + ZERO + NEXT_LINE +
                SPACE + ZERO + NEXT_LINE;
    }

    private String divideIfDividendAbsAndDivisorAbsAreEquals(int dividend, int divisor) {
        int divisorAbs = Math.abs(divisor);
        int lengthLine = countDigits(dividend) + 1;
        StringBuilder result = new StringBuilder();
        result.append(UNDERSCORE).append(dividend).append(VERTICAL_BAR).append(divisor).append(NEXT_LINE);
        if (dividend < 0) {
            result.append(SPACE).append(SPACE).append(divisorAbs).append(VERTICAL_BAR).append(defineHorizontalLine(countDigits(divisor))).append(NEXT_LINE)
                  .append(SPACE).append(SPACE).append(defineHorizontalLine(countDigits(divisorAbs))).append(VERTICAL_BAR).append(dividend / divisor).append(NEXT_LINE)
                  .append(formatIntegerValue(ZERO, lengthLine)).append(NEXT_LINE);
        } else {
            result.append(SPACE).append(divisorAbs).append(VERTICAL_BAR).append(defineHorizontalLine(countDigits(divisor))).append(NEXT_LINE)
                  .append(SPACE).append(defineHorizontalLine(countDigits(divisorAbs))).append(VERTICAL_BAR).append(dividend / divisor).append(NEXT_LINE)
                  .append(formatIntegerValue(ZERO, lengthLine)).append(NEXT_LINE);
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

    private String formatDividend(Integer value, int length) {
        return String.format("%" + length + "s", UNDERSCORE + value.toString());
    }

    private String formatIntegerValue(int value, int length) {
        return String.format("%" + length + "d", value);
    }

    private String formatHorizontalLine(int multiplyResult, int length) {
        String horizontalLine = assemblySymbolsToString(countDigits(multiplyResult), MINUS);
        return String.format("%" + length + "s", horizontalLine);
    }

    private String assemblySymbolsToString(int amountOfSymbols, char symbol) {
        return String.valueOf(symbol).repeat(Math.max(0, amountOfSymbols));
    }

    private int countSizeHorizontalLine(int divisor, int quotient) {
        return Integer.max(countDigits(divisor), countDigits(quotient));
    }
}
