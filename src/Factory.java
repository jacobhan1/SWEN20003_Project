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
	public Truck create(String input, double x, double y) throws SlickException {
		return new Truck(getX(), getY(), getCamera());
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		if (isSelect()) {
			g.drawString("1- create truck", 32, 100);
			}
	}
}
