import org.newdawn.slick.SlickException;

/**
 * metals were resources of the world
 */
public class Metal extends Resource {
	private static final String METAL_PATH = "assets/resources/metal_mine.png";
	public static final int amount = 500;
	
	/**
	 * Metal constructor
	 * @param x metal x coordinate
	 * @param y metal y coordinate
	 * @param camera world camera
	 * @throws SlickException
	 */
	public Metal(double x, double y, Camera camera) throws SlickException{
		super(x, y, METAL_PATH, amount, camera);
	}

}
