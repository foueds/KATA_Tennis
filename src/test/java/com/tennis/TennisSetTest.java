package com.tennis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class TennisSetTest extends AbstractTennisTest {

    @Test
    void should_start_with_score_of_zero_set() {
        assertEquals(0, firstPlayer.getScoreSet());
        assertEquals(0, secondPlayer.getScoreSet());
    }

    @Test
    void should_first_player_win_and_finish_the_game() {
        //GIVEN
        // 1: point for firstPlayer
        // 2: point for secondPlayer
        List<Integer> setPointOrderTab = Arrays.asList(1, 1, 1, 2, 1, 1, 1); // 6-1

        //WHEN
        game.computePoint(setPointOrderTab, true);

        //THEN
        assertTrue(firstPlayer.isWinner());
        assertEquals(GameStatus.FINISHED, game.getGameStatus());
    }

    @Test
    void should_first_player_win_and_finish_the_game_when_second_player_has_score_5() {
        //GIVEN
        // 1: point for firstPlayer
        // 2: point for secondPlayer
        List<Integer> setPointOrderTab = Arrays.asList(2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1); // 7-5

        //WHEN
        game.computePoint(setPointOrderTab, true);

        //THEN
        assertTrue(firstPlayer.isWinner());
        assertEquals(GameStatus.FINISHED, game.getGameStatus());
    }

    @Test
    void should_activate_tie_break_when_two_players_reach_set_6() {
        //GIVEN
        // 1: point for firstPlayer
        // 2: point for secondPlayer
        List<Integer> setPointOrderTab = Arrays.asList(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2); // 6-6

        //WHEN
        game.computePoint(setPointOrderTab, true);

        //THEN
        assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
        assertTrue(game.isTiebreakIsActivated());
    }

    @Test
    void should_first_player_win_set_and_game_still_in_progress_when_tie_break_activated() {
        //GIVEN
        // 1: point for firstPlayer
        // 2: point for secondPlayer
        List<Integer> setPointOrderTab = Arrays.asList(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1); // 6-6 and 1-0 for firstPlayer

        //WHEN
        game.computePoint(setPointOrderTab, true);

        //THEN
        assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
        assertTrue(game.isTiebreakIsActivated());
    }

    @Test
    void should_second_player_win_match_when_he_won_tie_break() {
        //GIVEN
        // 1: point for firstPlayer
        // 2: point for secondPlayer
        List<Integer> setPointOrderTab = Arrays.asList(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2); // 6-6 and 1-3 for secondPlayer

        //WHEN
        game.computePoint(setPointOrderTab, true);

        //THEN
        assertEquals(GameStatus.FINISHED, game.getGameStatus());
        assertTrue(secondPlayer.isWinner());
    }

}
