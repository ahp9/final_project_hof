package hi.verkefni.vinnsla;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Player player;

    @Before
    public void createPlayer(){
        player = new Player();
    }

    @Test
    public void hasNotTwentyOne(){
        assertEquals(player.getScore(), 0);
    }
    
}
