import org.newdawn.slick.SlickException;
/**
 * @author ASUS
 *
 */
public interface Creatable {
	
	/**
	 * every creatable sprite could create a new sprite
	 * @param input input name
	 * @param x creatable sprite x coordinate
	 * @param y creatable sprite y coordinate
	 * @return created new sprite
	 * @throws SlickException
	 */
	public Sprite create(String input, double x, double y) throws SlickException;
	
}
