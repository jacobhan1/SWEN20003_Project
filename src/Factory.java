import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Factory extends Building implements Creatable {
	private static final String FACTORY_PATH = "assets/buildings/factory.png";
	
	
	public Factory(double x, double y) throws SlickException {
		super(x, y, FACTORY_PATH);
		
	}
	
	@Override
	public Truck create(int input, double x, double y) {
		
			try {
				return new Truck(getX(), getY());
			} catch (SlickException e) {
				e.printStackTrace();
			}
		return null;
	}
}
