package com.example.pheonixii.sonus;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    GameActivity game = new GameActivity();
    MainActivity main = new MainActivity();
    Stats stats = new Stats();
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void intervalsNotEmpty() {
        assertTrue(stats.intervals.size() > 0);
    }
    @Test
    public void scoreCorrect() {
        assertTrue(stats.score >= 0 && stats.score <= 10);
    }
    @Test
    public void roundsCorrect() {
        assertTrue(game.getRoundNum() >= 0 && game.getRoundNum() < 11);
    }
}