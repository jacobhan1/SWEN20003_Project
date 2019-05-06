import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class should be used to contain all the different objects in your game world, and schedule their interactions.
 * 
 * You are free to make ANY modifications you see fit.
 * These classes are provided simply as a starting point. You are not strictly required to use them.
 */
public class World {
	private static final String MAP_PATH = "assets/main.tmx";
	private static final String SOLID_PROPERTY = "solid";
	
	private Sprite sprite;
	
	private TiledMap map;
	private Camera camera = new Camera();
	
	private Input lastInput;
	private int lastDelta;

	public Input getInput() {
		return lastInput;
	}
	public int getDelta() {
		return lastDelta;
	}
	
	public boolean isPositionFree(double x, double y) {
		int tileId = map.getTileId(worldXToTileX(x), worldYToTileY(y), 0);
		return !Boolean.parseBoolean(map.getTileProperty(tileId, SOLID_PROPERTY, "false"));
	}
	
	public double getMapWidth() {
		return map.getWidth() * map.getTileWidth();
	}
	
	public double getMapHeight() {
		return map.getHeight() * map.getTileHeight();
	}
	
	public World() throws SlickException {
		map = new TiledMap(MAP_PATH);
		sprite = new Sprite(camera);
	}
	
	public void update(Input input, int delta) {
		lastInput = input;
		lastDelta = delta;
		
		camera.update(this);
		sprite.update(this);
	}
	
	public void render(Graphics g) {
		map.render((int)camera.globalXToScreenX(0),
				   (int)camera.globalYToScreenY(0));
		sprite.render();
	}
	
	// This should probably be in a separate static utilities class, but it's a bit excessive for one method.
	public static double distance(double x1, double y1, double x2, double y2) {
		return (double)Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
	
	private int worldXToTileX(double x) {
		return (int)(x / map.getTileWidth());
	}
	private int worldYToTileY(double y) {
		return (int)(y / map.getTileHeight());
	}
}
