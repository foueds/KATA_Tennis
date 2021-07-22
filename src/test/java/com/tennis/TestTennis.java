package com.tennis;

import org.junit.jupiter.api.BeforeEach;

public class TestTennis {

    protected Player firstPlayer;
    protected Player secondPlayer;
    protected Game game;

    @BeforeEach
    public void init() {
        firstPlayer = new Player(1, "Monfils");
        secondPlayer = new Player(2, "Gasquet");
        game = new Game(firstPlayer, secondPlayer);
    }
}
