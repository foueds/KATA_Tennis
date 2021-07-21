package com.tennis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestTennisGame {

    private Player firstPlayer;
    private Player secondPlayer;

    private Game game;

    @BeforeEach
    public void init() {
        firstPlayer = new Player(1, "Monfils");
        secondPlayer = new Player(2, "Gasquet");
        game = new Game(firstPlayer, secondPlayer);
    }

    @Test
    void should_start_with_score_of_zero() {
        assertEquals(0, firstPlayer.getScore());
        assertEquals(0, secondPlayer.getScore());
    }

    @Test
    void should_first_player_win_and_finish_the_game() {
        //GIVEN
        List<Integer> pointWinnerTab = Arrays.asList(1, 2, 1, 2, 1, 1); // 40-30 and the firstPlayer win the last point

        //WHEN
        game.computePoint(pointWinnerTab);

        //THEN
        assertTrue(firstPlayer.isWinner());
        assertEquals(GameStatus.FINISHED, game.getGameStatus());
    }

    @Test
    void should_activate_deuce_rule_when_two_players_reach_score_40() {
        //GIVEN
        List<Integer> pointWinnerTab = Arrays.asList(1, 2, 1, 2, 1, 2); // 40 - 40

        //WHEN
        game.computePoint(pointWinnerTab);

        //THEN
        assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
        assertTrue(game.isDeuceRuleIsActivated());
    }

    @Test
    void should_player_take_advantage_when_deuce_rule_is_activate() {
        //GIVEN
        List<Integer> pointWinnerTab = Arrays.asList(1, 2, 1, 2, 1, 2, 1); // A - 40

        //WHEN
        game.computePoint(pointWinnerTab);

        //THEN
        assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
        assertTrue(firstPlayer.isHasAdvantage());
    }

    @Test
    void should_player_win_the_game_when_he_has_advantage_and_deuce_rule_is_activate() {
        //GIVEN
        List<Integer> pointWinnerTab = Arrays.asList(1, 2, 1, 2, 1, 2, 2, 2); // 40 - A and the second player win the last point

        //WHEN
        game.computePoint(pointWinnerTab);

        //THEN
        assertEquals(GameStatus.FINISHED, game.getGameStatus());
        assertTrue(secondPlayer.isWinner());
    }

    @Test
    void should_player_lost_advantage_when_he_lost_the_point_deuce_rule_is_activate() {
        //GIVEN
        List<Integer> pointWinnerTab = Arrays.asList(1, 2, 1, 2, 1, 2, 2, 1); // 40 - A and the first player win the last point

        //WHEN
        game.computePoint(pointWinnerTab);

        //THEN
        assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
        assertFalse(secondPlayer.isHasAdvantage());
        assertTrue(game.isDeuceRuleIsActivated());
    }
}
