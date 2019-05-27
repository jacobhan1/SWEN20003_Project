import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * resource has two type: metal and unobtainium
 * player could mine the resource and its amount will decrease
 */
public abstract class Resource extends Sprite implements Removable{
	private int amountLeft;
	
	/**
	 * resource's constructor
	 * @param x resource's x coordinate
	 * @param y resource's y coordinate
	 * @param imageSrc resource's image address
	 * @param amount resource's amount
	 * @param camera world camera
	 * @throws SlickException
	 */
	public Resource(double x, double y, String imageSrc, int amount, Camera camera) throws SlickException{
		super(x, y, imageSrc, camera);
		this.amountLeft = amount;
	}
	
	/**
	 * get the rest of the resource's amount.
	 * @return rest of the resource's amount
	 */
	public int getAmount() {return amountLeft;}
	
	/**
	 * set the rest of amount of resource value.
	 * @param dx engineer mine amount each time
	 */
	public void setAmount(int dx) {amountLeft = amountLeft - dx;}
	
	/**
	 * if resource's rest amount is 0, it needs to be remove from the world
	 */
	@Override
	public void remove() {
		setImage(null);
	}
	
	/**
	 * update if it needs to be removed
	 */
	@Override
	public void update(World world) throws SlickException {
		if(amountLeft == 0) {
			remove();
		}
	}
	
	/**
	 * render all the resource in the world
	 * @param g Graphics 
	 */
	@Override
	public void render(Graphics g) {
		if(getImage() != null) {
			getImage().drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()) );
		}
	}
}
