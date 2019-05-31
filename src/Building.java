import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Building could be selected and has three tyeps: commandcenter, pylon and factory.
 */
public abstract class Building extends Sprite implements Selectable{
	private static final String HIGHLIGHT_LARGE = "assets/highlight_large.png";
	private Image selectImage;
	
	/**
	 * Building common constructor
	 * @param x building's x coordinate
	 * @param y building's y coordinate
	 * @param imageSrc building's image
	 * @param camera camera
	 * @throws SlickException
	 */
	public Building(double x, double y, String imageSrc, Camera camera) throws SlickException {
		super(x, y, imageSrc, camera);
		
	}
	
	/**
	 * get Image of building
	 * @return selectImage
	 */
	public Image getImage() {return selectImage;}
	
	/**
	 * if the building is selected 
	 * @return return true if building is selected 
	 */
	@Override
	public boolean isSelect() {
		return selectImage != null;
	} 
	
	/**
	 * deSelect the building
	 */
	@Override
	public void deSelect() throws SlickException {
		selectImage = null;
	}
	
	/**
	 * select the building
	 */
	@Override
	public void select() throws SlickException {
		 selectImage = new Image(HIGHLIGHT_LARGE);
	}	
	
	/**
	 * render the building and selected image
	 * @param g Graphics
	 */
	@Override
	public void render(Graphics g) {
		if(isSelect()) {
			selectImage.drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()));
		}
		super.render(g);
	}
}
