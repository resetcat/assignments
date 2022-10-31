package letterFrequency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetterFrequencyMainTest {

    @Test
    void testFrequency(){
        String test = "AAAB";
        Long expected = 3L;
        Long actual = LetterFrequencyMain.frequencyCounter(test).get('A');
        Assertions.assertEquals(expected,actual);
    }
}