
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * @author ASUS
 *
 */
public interface Creatable {
	
	
	public Sprite create(String input, double x, double y) throws SlickException;
	
	public void render(Graphics g);
}
