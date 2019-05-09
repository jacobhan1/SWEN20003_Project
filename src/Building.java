import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Building extends Sprite implements Selectable{
	private static final String HIGHLIGHT_LARGE = "assets/highlight_large.png";
	private Camera camera;
	public Building(double x, double y, String imageSrc) throws SlickException {
		super(x, y, imageSrc);
		
	}
	public Camera getCamera() {return camera;}
	
	@Override
	public void update(World world) {}
	
	@Override
	public void render() {}
	
	@Override
	public void select() throws SlickException {
			setImage(new Image(HIGHLIGHT_LARGE));
	}	
}
