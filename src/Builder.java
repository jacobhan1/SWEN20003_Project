import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Builder extends Unit implements Creatable {
	private static final String BUILDER_PATH = "assets/units/builder.png";
	private static final double SPEED = 0.1;
	
	
	
	public Builder(double x, double y, Camera camera) throws SlickException {
		super(x, y, BUILDER_PATH, SPEED, camera );
	}
	
	@Override
	public Factory create(int input, double x, double y) {
			
			try {
				return new Factory(x, y);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	@Override
	public void render(Graphics g) {}
}
