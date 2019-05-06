import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Factory extends Building implements Creatable {
	private static final String FACTORY_PATH = "assets/buildings/factory.png";
	
	public Factory(double x, double y) throws SlickException {
		super(x, y, FACTORY_PATH);
		
	}

}
