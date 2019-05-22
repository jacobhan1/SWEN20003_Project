import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Building extends Sprite implements Selectable{
	private static final String HIGHLIGHT_LARGE = "assets/highlight_large.png";
	
	private Image selectImage;
	private int selectTime = 0;
	public Building(double x, double y, String imageSrc, Camera camera) throws SlickException {
		super(x, y, imageSrc, camera);
		
	}
	
	
	
	@Override
	public boolean isSelect() {
		return selectImage != null;
	} 
	
	@Override
	public void update(World world) throws SlickException {

	}
	
	
	@Override
	public void deSelect() throws SlickException {
		selectImage = null;
	}
	@Override
	public void select() throws SlickException {
		 selectImage = new Image(HIGHLIGHT_LARGE);
	}	
	
	@Override
	public void render() {
		if(isSelect()) {
		selectImage.drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()));
		}
		getImage().drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()));
	}
}
