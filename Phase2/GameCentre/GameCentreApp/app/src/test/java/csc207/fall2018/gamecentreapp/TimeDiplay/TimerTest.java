package csc207.fall2018.gamecentreapp.TimeDiplay;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Timer mockito Test
 */
public class TimerTest {


    /**
     * Test set up correctly
     */
    @Test
    public void setUpTimer() {
        Timer timer = mock(Timer.class);
        timer.setUpTimer(0);
        when(timer.returnIntTime()).thenReturn(0);
    }


    /**
     * Test return integer time
     */
    @Test
    public void returnIntTime() {
        Timer timer = mock(Timer.class);
        timer.setUpTimer(4);
        timer.start();
        when(timer.returnIntTime()).thenReturn(4);
    }

    /**
     * Test timer starts from a set time
     */
    @Test
    public void start() {
        Timer timer = mock(Timer.class);
        timer.setUpTimer(5);
        Mockito.doNothing().when(timer).start();
    }


    /**
     * Test timer stop function
     */
    @Test
    public void stop() {
        Timer timer = mock(Timer.class);
        timer.setUpTimer(4);
        doNothing().when(timer).stop();
    }
}