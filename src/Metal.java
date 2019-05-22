import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Metal extends Resource {
	private static final String METAL_PATH = "assets/resources/metal_mine.png";
	private static final int METAL_AMOUNT = 500;
	
	
	public Metal(double x, double y, Camera camera) throws SlickException{
		super(x, y, METAL_PATH, METAL_AMOUNT, camera);
	}

}
