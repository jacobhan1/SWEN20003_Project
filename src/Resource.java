
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Resource extends Sprite implements Removable{
	private int amountLeft;
	
	public Resource(double x, double y, String imageSrc, int amount, Camera camera) throws SlickException{
		super(x, y, imageSrc, camera);
		this.amountLeft = amount;
	}
	
	public int getAmount() {return amountLeft;}
	public void setAmount(int dx) {amountLeft = amountLeft - dx;}
	
	
	@Override
	public void remove() {
		setImage(null);
	}
	
	@Override
	public void update(World world) throws SlickException {
		if(amountLeft == 0) {
			remove();
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(getImage() != null) {
			getImage().drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()) );
		}
	}
}
