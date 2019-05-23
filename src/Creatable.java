
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
/**
 * @author ASUS
 *
 */
public interface Creatable {
	
	
	public Sprite create(int input, double x, double y);
	
	public void render(Graphics g);
}
