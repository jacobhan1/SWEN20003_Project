import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Building extends Sprite implements Selectable{
	private static final String HIGHLIGHT_LARGE = "assets/highlight_large.png";
	private Camera camera;
	private Image selectImage;
	private int selectTime = 0;
	public Building(double x, double y, String imageSrc) throws SlickException {
		super(x, y, imageSrc);
		
	}
	public Camera getCamera() {return camera;}
	
	
	@Override
	public boolean isSelect() {
		return selectImage != null;
	} 
	
	@Override
	public void update(World world) throws SlickException {
		for(Sprite sprite : world.getSprites()) {
			if (sprite instanceof Unit && ((Selectable)sprite).isSelect()) {
				((Selectable)sprite).deSelect();
			}
		}
		select();
//		Input input = world.getInput();
//		
//		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
//			selectTime ++;
//			double mouseX = camera.screenXToGlobalX(input.getMouseX());
//			double mouseY = camera.screenYToGlobalY(input.getMouseY());
//			
//			if(getX() < mouseX + 32 && getX() > mouseX - 32 && getY() < mouseY + 32 
//					&& getY() > mouseY - 32 && selectTime % 2 == 0) {
//					select();
//				}else {deSelect();}
//		}
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
		if(selectImage != null) {
		selectImage.drawCentered((int)getX()-300 , (int)getY()-300 );
		}
		getImage().drawCentered((int)getX()-300, (int)getY()-300);
	}
}
