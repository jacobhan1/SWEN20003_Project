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
//		
//		Input input = world.getInput();
//		
//		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
//			double mouseX = getCamera().screenXToGlobalX(input.getMouseX());
//			double mouseY = getCamera().screenYToGlobalY(input.getMouseY());
//			
//			for (Sprite sprite : world.getSprites()) {
//				if (sprite instanceof Building && sprite.getX() < mouseX + 32
//						&& sprite.getX() > mouseX - 32 && sprite.getY() < mouseY + 32 
//						&& sprite.getY() > mouseY - 32 ) {
//					
//					select();
//				}else if(sprite instanceof Building && isSelect()){
//					deSelect();
//				}
//			}
//		}
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
		if(isSelect()) {
		selectImage.drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()));
		}
		getImage().drawCentered((int)getCamera().globalXToScreenX(getX()), (int)getCamera().globalYToScreenY(getY()));
	}
}
