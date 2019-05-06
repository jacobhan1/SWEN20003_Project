import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Engineer extends Unit implements Movable{
	private static final String ENGINEER_PATH = "assets/units/engineer.png";
	private static final double SPEED = 0.1;
	public Engineer(double x, double y, String imageSrc, Camera camera) throws SlickException {
		super(x, y, ENGINEER_PATH, camera, SPEED);
	}

}
