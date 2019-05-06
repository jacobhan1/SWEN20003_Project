import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Truck extends Unit implements Creatable {
	
	private static final String TRUCK_PATH = "assets/units/truck.png";
	private static final double SPEED = 0.25;
	
	public Truck(double x, double y, String imageSrc, Camera camera) throws SlickException{
		super(x, y, TRUCK_PATH, camera, SPEED);
	}

}
