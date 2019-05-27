import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * select the unit or building if mouse left button is down
 */
public interface Selectable {
	
	/**
	 * select the sprite
	 * @throws SlickException
	 */
	public void select() throws SlickException;
	
	/**
	 * deSelect the sprite in the world 
	 * @throws SlickException
	 */
	public void deSelect() throws SlickException;
	
	/**
	 * if the sprite is selected
	 * @return if is selected, return true, else return false.
	 */
	public boolean isSelect();
}
