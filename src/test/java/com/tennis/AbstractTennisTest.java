package com.tennis;

import org.junit.jupiter.api.BeforeEach;

abstract class AbstractTennisTest {

    protected Player firstPlayer;
    protected Player secondPlayer;
    protected Game game;

    @BeforeEach
    void setUp() {
        firstPlayer = new Player(1, "Monfils");
        secondPlayer = new Player(2, "Gasquet");
        game = new Game(firstPlayer, secondPlayer);
    }
}
