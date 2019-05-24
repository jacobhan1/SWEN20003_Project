import java.util.ArrayList;

import org.newdawn.slick.Input;

/**
 * This class should be used to restrict the game's view to a subset of the entire world.
 * 
 * You are free to make ANY modifications you see fit.
 * These classes are provided simply as a starting point. You are not strictly required to use them.
 */
public class Camera {
	private double x = 300;
	private double y = 300;
	private Sprite target;
	private final static double SPEED= 0.4;
	
	public void followSprite(Sprite target) {
		this.target = target;
	}
	
	public double globalXToScreenX(double x) {
		return x - this.x;
	}
	public double globalYToScreenY(double y) {
		return y - this.y;
	}

	public double screenXToGlobalX(double x) {
		return x + this.x;
	}
	public double screenYToGlobalY(double y) {
		return y + this.y;
	}
	private double detectX(double x, World world) {
		x = Math.min(x, world.getMapWidth() - App.WINDOW_WIDTH);
		x = Math.max(x, 0);
		return x;
	}
	private double detectY(double y, World world) {
		y = Math.min(y, world.getMapHeight() - App.WINDOW_HEIGHT);
		y = Math.max(y, 0);
		return y;
	}
	
	public void update(World world) {
		Input input = world.getInput();
		int delta = world.getDelta();
		// up, down, left and right
		if (input.isKeyDown(Input.KEY_W)) {
			target = null;
			y -= delta * SPEED;
			y = detectY(y,world);
		}else if (input.isKeyDown(Input.KEY_S)) {
			target = null;
			y += delta * SPEED;
			y = detectY(y,world);
		}else if(input.isKeyDown(Input.KEY_A)) {
			target = null;
			x -= delta * SPEED;
			x = detectX(x,world);
		}else if(input.isKeyDown(Input.KEY_D)) {
			target = null;
			x += delta * SPEED;
			x = detectX(x,world);
		}else if (target != null && ((Selectable)target).isSelect() ){
		
			double targetX = target.getX() - App.WINDOW_WIDTH / 2;
			double targetY = target.getY() - App.WINDOW_HEIGHT / 2;
			
			x = detectX(targetX,world);
			y = detectY(targetY,world);
		}
	}
}
