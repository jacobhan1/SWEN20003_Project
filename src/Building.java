import org.newdawn.slick.Graphics;
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
	
	public Image getImage() {return selectImage;}
	
	@Override
	public boolean isSelect() {
		return selectImage != null;
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
	public void render(Graphics g) {
		if(isSelect()) {
			selectImage.drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()));
		}
		super.render(g);
	}
}
