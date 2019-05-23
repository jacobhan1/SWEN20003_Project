import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Metal extends Resource {
	private static final String METAL_PATH = "assets/resources/metal_mine.png";
	public static final int amount = 500;
	
	
	public Metal(double x, double y, Camera camera) throws SlickException{
		super(x, y, METAL_PATH, amount, camera);
	}

}
