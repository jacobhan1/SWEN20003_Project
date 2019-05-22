import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Factory extends Building implements Creatable {
	private static final String FACTORY_PATH = "assets/buildings/factory.png";
	
	
	public Factory(double x, double y, Camera camera) throws SlickException {
		super(x, y, FACTORY_PATH, camera);
		
	}
	
	@Override
	public Truck create(int input, double x, double y) {
		
//			try {
//				return new Truck(getX(), getY()£¬Camera camera);
//			} catch (SlickException e) {
//				e.printStackTrace();
//			}
		return null;
	}
	
	@Override
	public void render(Graphics g) {}
}
