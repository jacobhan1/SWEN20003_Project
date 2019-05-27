import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
/**
 * factory is kind of building which could create truck.
 */
public class Factory extends Building implements Creatable {
	private static final String FACTORY_PATH = "assets/buildings/factory.png";
	
	/**
	 * factory constructor
	 * @param x factory x coordinate
	 * @param y factory y coordinate
	 * @param camera world camera
	 * @throws SlickException
	 */
	public Factory(double x, double y, Camera camera) throws SlickException {
		super(x, y, FACTORY_PATH, camera);
	}
	
	/**
	 * factory create truck
	 * @param input user's name
	 * @param x factory x coordinate
	 * @param y factory y coordinate
	 */
	@Override
	public Truck create(String input, double x, double y) throws SlickException {
		return new Truck(getX(), getY(), getCamera());
	}
	
	/**
	 * render the factory and its function
	 * @param g render the factory function
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
		if (isSelect()) {
			g.drawString("1- create truck", 32, 100);
			}
	}
}
