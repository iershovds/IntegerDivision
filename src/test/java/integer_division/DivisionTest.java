package integer_division;

import org.junit.jupiter.api.Test;

import static integer_division.Constants.MessageException.DIVISOR_IS_ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionTest {
    Division modelDivision = new Division();

    @Test
    public void longDivisionOperationn_shouldReturnMessageDivisorIsZero_whenInputDivisorIsZero() {
        String expected = DIVISOR_IS_ZERO;
        assertEquals(expected, modelDivision.divide(78945, 0));
    }

    @Test
    public void longDivisionOperation_shouldReturnDivisionInOneLine_whenInputDividendAbsIsLessThanDivisorAbs() {
        String expected = "4 / 78945 = 0.00005067";
        assertEquals(expected, modelDivision.divide(4, 78945));
    }

    @Test
    public void longDivisionOperation_shouldReturnLongDivision_whenInputIsTwoPositiveNumbersAndDivisorIsOneDigitLong() {

        String expected = "_18945|4\n" +
                " 16   |----\n" +
                " --   |4736\n" +
                " _29\n" +
                "  28\n" +
                "  --\n" +
                "  _14\n" +
                "   12\n" +
                "   --\n" +
                "   _25\n" +
                "    24\n" +
                "    --\n" +
                "     1\n";
        assertEquals(expected, modelDivision.divide(18945, 4));
    }

    @Test
    public void longDivisionOperation_shouldReturnLongDivision_whenInputDividendIsNegativeAndDivisorIsPositive() {
        String expected = "_-78945|4\n" +
                "  4    |------\n" +
                "  -    |-19736\n" +
                " _38\n" +
                "  36\n" +
                "  --\n" +
                "  _29\n" +
                "   28\n" +
                "   --\n" +
                "   _14\n" +
                "    12\n" +
                "    --\n" +
                "    _25\n" +
                "     24\n" +
                "     --\n" +
                "      1\n";
        assertEquals(expected, modelDivision.divide(-78945, 4));
    }

    @Test
    public void longDivisionOperation_shouldReturnLongDivision_whenInputIsTwoPositiveNumbersOneDigitLong() {
        String expected = "_9|4\n" +
                " 8|-\n" +
                " -|2\n" +
                " 1\n";
        assertEquals(expected, modelDivision.divide(9, 4));
    }

    @Test
    public void longDivisionOperation_shouldReturnLongDivision_whenInputIsTwoNegativeNumbersOneDigitLong() {
        String expected = "_-9|-4\n" +
                "  8|--\n" +
                "  -|2\n" +
                "  1\n";
        assertEquals(expected, modelDivision.divide(-9,-4));
    }

    @Test
    public void longDivisionOperation_shouldReturnLongDivision_whenInputDividendIsZeroAndDivisorIsNegativeTwoDigitsLong() {
        String expected = "_0|-40\n" +
                " 0|---\n" +
                " -|0\n" +
                " 0\n";
        assertEquals(expected, modelDivision.divide(0, -40));
    }

    @Test
    public void longDivisionOperation_shouldReturnLongDivision_whenInputDividendIsZeroAndDivisorIsPositiveTwoDigitsLong() {
        String expected = "_0|40\n" +
                " 0|--\n" +
                " -|0\n" +
                " 0\n";
        assertEquals(expected, modelDivision.divide(0, 40));
    }

    @Test
    public void longDivisionOperation_shouldReturnLongDivision_whenDividendAbsAndDivisorAbsAreEqualsOneDigitLongAndDivisorIsLessThanZero() {
        String expected = "_4|-4\n" +
                " 4|--\n" +
                " -|-1\n" +
                " 0\n";
        assertEquals(expected, modelDivision.divide(4, -4));
    }

    @Test
    public void longDivisionOperation_shouldReturnLongDivision_whenDividendAndDivisorAreEqualsThreeDigitLong() {
        String expected = "_456|456\n" +
                " 456|---\n" +
                " ---|1\n" +
                "   0\n";
        assertEquals(expected, modelDivision.divide(456, 456));
    }

    @Test
    public void longDivisionOperation_shouldReturnLongDivision_whenDividendIsLessThanZeroAndEqualsToDivisorBothNumbersAreThreeDigitLong() {
        String expected = "_-456|456\n" +
                "  456|---\n" +
                "  ---|-1\n" +
                "    0\n";
        assertEquals(expected, modelDivision.divide(-456, 456));
    }

    @Test
    public void longDivisionOperation_shouldReturnLongDivision_whenInputDividendContainsMoreThanTwoZerosAndDivisorOneDigitLong() {
        String expected = "_38005|4\n" +
                " 36   |----\n" +
                " --   |9501\n" +
                " _20\n" +
                "  20\n" +
                "  --\n" +
                "   _0\n" +
                "    0\n" +
                "    -\n" +
                "    _5\n" +
                "     4\n" +
                "     -\n" +
                "     1\n" ;                          ;
        assertEquals(expected, modelDivision.divide(38005, 4));
    }
}
