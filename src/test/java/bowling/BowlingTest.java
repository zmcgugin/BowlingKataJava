package bowling;

import org.junit.Test;

import static bowling.Frame.frame;
import static bowling.Bowling.bowl;
import static org.junit.Assert.*;

public class BowlingTest {

    @Test
    public void shouldReturnTotalOfFrames() {
        assertEquals(18, bowl(frame(1, 7), frame(8, 2), frame(0, 0)));
        assertEquals(6, bowl(frame(1, 0), frame(0, 2), frame(3, 0)));
        assertEquals(0, bowl());
    }

    @Test //Spare is 10 + the first frame of the next bowl. (max of 20)
    public void shouldTotalFramesWithSpairs() {
        assertEquals(22, bowl(frame(5, "/"), frame(5, 2)));
        assertEquals(17, bowl(frame(5, 2), frame(5, "/")));
        assertEquals(12, bowl(frame(5, "/"), frame(0, 2)));
    }

    @Test //Strike is 10 + the first and second frame of the next bowl. (max of 30)
    public void shouldTotalFramesWithStrike() {
        assertEquals(17, bowl(frame(5, 2), frame("X")));
        assertEquals(24, bowl(frame("X"), frame(5, 2)));
        assertEquals(49, bowl(frame("X"), frame("X"), frame(5, 2)));
        assertEquals(60, bowl(frame("X"), frame("X"), frame("X")));
    }

    @Test
    public void shouldWorkWithACombinationOfStrikesAndSpares() {
        assertEquals(53, bowl(frame("X"), frame(3, "/"), frame(6, 1), frame("X")));
    }

    @Test
    public void shouldIncludeTheBonusRoleOn10thFrame() {
        assertEquals(300, bowl(frame("X"), frame("X"), frame("X"), frame("X"), frame("X"), frame("X"), frame("X"), frame("X"), frame("X"), frame("X", "X", "X")));
        assertEquals(150, bowl(frame(5,"/"), frame(5,"/"), frame(5,"/"), frame(5,"/"), frame(5,"/"), frame(5,"/"), frame(5,"/"), frame(5,"/"), frame(5,"/"), frame(5, "/", 5)));
        //TODO need more test cases
    }
}