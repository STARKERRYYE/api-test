package com.example.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JJ {
    @Test
    public void f() {
        String[] jing = new String[]{"OX ","OX ","O  "};
        Jing j = new Jing();
        assertEquals("Pending", j.tictactoe(jing));
    }
}
