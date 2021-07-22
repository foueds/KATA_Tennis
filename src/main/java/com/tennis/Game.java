package com.tennis;

import java.util.List;

public class Game {

    private Player firstPlayer;
    private Player secondPlayer;
    private GameStatus gameStatus;
    private boolean deuceRuleIsActivated;
    private boolean tiebreakIsActivated;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.deuceRuleIsActivated = false;
        this.tiebreakIsActivated = false;
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

    public boolean isTiebreakIsActivated() {
        return tiebreakIsActivated;
    }

    public void setTiebreakIsActivated(boolean tiebreakIsActivated) {
        this.tiebreakIsActivated = tiebreakIsActivated;
    }

    public void updateGameScore(Player pointWinner, Player pointLoser) {

        switch (pointWinner.getGameScore()) {
            case 0:
                pointWinner.setGameScore(15);
                break;
            case 15:
                pointWinner.setGameScore(30);
                break;
            case 30:
                pointWinner.setGameScore(40);
                if (pointLoser.getGameScore() == 40) {
                    setDeuceRuleIsActivated(true);
                }
                break;
            case 40:
                evaluateGameFinalPoint(pointWinner, pointLoser);
                break;
            default:
                break;
        }
    }

    public void updateScoreSet(Player pointWinner, Player pointLoser) {

        switch (pointWinner.getScoreSet()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                pointWinner.setScoreSet(pointWinner.getScoreSet() + 1);
                break;
            case 5:
                if (pointLoser.getScoreSet() < 5) {
                    defineTheWinner(pointWinner);
                } else if (pointLoser.getScoreSet() == 5) {
                    pointWinner.setScoreSet(pointWinner.getScoreSet() + 1);
                    break;
                } else if (pointLoser.getScoreSet() == 6) {
                    setTiebreakIsActivated(true);
                    pointWinner.setScoreSet(pointWinner.getScoreSet() + 1);
                    break;
                }
                break;
            case 6:
                if (pointLoser.getScoreSet() > 5) {
                    pointWinner.setScoreSet(pointWinner.getScoreSet() + 1);
                    break;
                }
                defineTheWinner(pointWinner);
                break;
            default:
                evaluateSetFinalPoint(pointWinner, pointLoser);
                break;
        }
    }

    public void computePoint(List<Integer> pointWinnerTab, Boolean isSetScoreCompute) {
        for (int pointWinner : pointWinnerTab) {
            switch (pointWinner) {
                case 1: // first player win the point
                    if (isSetScoreCompute) {
                        updateScoreSet(firstPlayer, secondPlayer);
                        break;
                    }
                    updateGameScore(firstPlayer, secondPlayer);
                    break;
                case 2: // second player win the point
                    if (isSetScoreCompute) {
                        updateScoreSet(secondPlayer, firstPlayer);
                        break;
                    }
                    updateGameScore(secondPlayer, firstPlayer);
                    break;
                default:
                    break;
            }
        }
    }

    private void evaluateGameFinalPoint(Player pointWinner, Player pointLoser) {
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
        this.firstPlayer.setGameScore(0);
        this.secondPlayer.setGameScore(0);
    }

    private void evaluateSetFinalPoint(Player pointWinner, Player pointLoser) {
        pointWinner.setScoreSet(pointWinner.getScoreSet() + 1);
        if (pointWinner.getScoreSet() == pointLoser.getScoreSet() + 2) {
            defineTheWinner(pointWinner);
        }
    }
}
