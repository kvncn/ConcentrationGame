import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testAddScore() {
		
		Player p1 = new Player("p1");
		Player p2 = new Player("p2");
		
		p1.addScore();
		p1.addScore();
		p1.addScore();
		p1.addScore();
		p2.addScore();
		p2.addScore();
		p2.addScore();
		p2.addScore();
		p2.addScore();
		p2.addScore();
		p2.addScore();
		p2.addScore();
		p2.addScore();
		assertEquals("p1 scores wrong", 40, p1.getScore());
		assertEquals("p2 scores wrong", 90, p2.getScore());
	}

}
