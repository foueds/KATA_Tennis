package com.tennis;

import java.util.List;

public class Game {

    private Player firstPlayer;
    private Player secondPlayer;
    private GameStatus gameStatus;
    private boolean deuceRuleIsActivated;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.deuceRuleIsActivated = false;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public boolean isDeuceRuleIsActivated() {
        return deuceRuleIsActivated;
    }

    public void setDeuceRuleIsActivated(boolean deuceRuleIsActivated) {
        this.deuceRuleIsActivated = deuceRuleIsActivated;
    }

    public void updateScore(Player pointWinner, Player pointLoser) {

        switch (pointWinner.getScore()) {
            case 0:
                pointWinner.setScore(15);
                break;
            case 15:
                pointWinner.setScore(30);
                break;
            case 30:
                pointWinner.setScore(40);
                if (pointLoser.getScore() == 40) {
                    setDeuceRuleIsActivated(true);
                }
                break;
            case 40:
                evaluateFinalPoint(pointWinner, pointLoser);
                break;
            default:
                break;
        }
    }

    public void computePoint(List<Integer> pointWinnerTab) {
        for (int pointWinner : pointWinnerTab) {
            switch (pointWinner) {
                case 1: // first player win the point
                    updateScore(firstPlayer, secondPlayer);
                    break;
                case 2: // second player win the point
                    updateScore(secondPlayer, firstPlayer);
                    break;
                default:
                    break;
            }
        }
    }

    private void evaluateFinalPoint(Player pointWinner, Player pointLoser) {
        if (isDeuceRuleIsActivated()) { //score is 40 - 40
            pointWinner.setHasAdvantage(true);
            setDeuceRuleIsActivated(false);
        } else if (pointWinner.isHasAdvantage()) { // pointWinner has advantage
            pointWinner.setWinner(true);
            setGameStatus(GameStatus.FINISHED);
            resetScore();
        } else if (pointLoser.isHasAdvantage()) { //loser point lost his advantage
            setDeuceRuleIsActivated(true);
            pointLoser.setHasAdvantage(false);
        } else {
            defineTheWinner(pointWinner);
        }
    }

    private void defineTheWinner(Player pointWinner) {
        pointWinner.setWinner(true);
        setGameStatus(GameStatus.FINISHED);
        resetScore();
    }


    private void resetScore() {
        this.firstPlayer.setScore(0);
        this.secondPlayer.setScore(0);
    }
}
