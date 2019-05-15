import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Truck extends Unit implements Creatable, Removable {
	
	private static final String TRUCK_PATH = "assets/units/truck.png";
	private static final double SPEED = 0.25;
	
	public Truck(double x, double y, Camera camera) throws SlickException{
		super(x, y, TRUCK_PATH, SPEED, camera);
	}
	
	@Override
	public CommandCenter create(int input, double x, double y) {
		
		//if (input.isKeyDown(Input.KEY_1)) {
			try {
				return new CommandCenter(getX(), getY());
			} catch (SlickException e) {
				e.printStackTrace();
			}
		//}
		return null;
	}
	
	@Override
	public void remove(World world) {}
	
	@Override
	public void render(Graphics g) {}
}
