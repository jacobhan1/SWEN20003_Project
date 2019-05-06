import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Metal extends Resource {
	private static final String METAL_PATH = "assets/resources/metal_mine.png";
	
	public Metal(double x, double y) throws SlickException{
		super(x, y, METAL_PATH);
	}

}
