import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**
 * unobtainium is type of the resource, engineer could mine it.
 */
public class Unobtainium extends Resource {
	private static final String UNOBTAINIUN_PATH = "assets/resources/unobtainium_mine.png";
	private static final int Unobtainium_AMOUNT = 50;
	
	/**
	 * unobtainium constructor
	 * @param x unobtainium x coordinate
	 * @param y unobtainium y coordinate
	 * @param camera world camera
	 * @throws SlickException
	 */
	public Unobtainium(double x, double y, Camera camera) throws SlickException{
		super(x, y, UNOBTAINIUN_PATH, Unobtainium_AMOUNT, camera);
	}

}
