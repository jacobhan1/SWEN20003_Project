import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Builder extends Unit implements Creatable {
	private static final String BUILDER_PATH = "assets/units/builder.png";
	private static final double SPEED = 0.1;
	public Builder(double x, double y, String imageSrc, Camera camera) throws SlickException {
		super(x, y, BUILDER_PATH, camera, SPEED);
	}

}
