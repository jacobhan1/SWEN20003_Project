import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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
	public static final int DISTANCE = 32;
	private static final String MAP_PATH = "assets/main.tmx";
	private static final String SOLID_PROPERTY = "solid";
	private static final int INDEX_OBJECT = 0;
	private static final int INDEX_X = 1;
	private static final int INDEX_Y = 2;
	private static final int DISPLAY = 32;
	// create object needed
	public static final int UNIT_BUILD_TIME = 5;
	public static final int FACTORY_BUILD_TIME = 10;
	public static final int COMMANDCENTRE_BUILD_TIME = 15;
	public static final int FACTORY_COST_METAL = 100;
	public static final int SCOUT_COST_METAL = 5;
	public static final int BUILDER_COST_METAL = 10;
	public static final int ENGINEER_COST_METAL = 20;
	public static final int TRUCK_COST_METAL = 150;
	int count = 1;
	private Sprite createObject;
	// all sprites
	private ArrayList<Sprite> sprites;
	//private Unit scout;
	private TiledMap map;
	private Camera camera = new Camera();
	private Input lastInput;
	private int lastDelta;
	private int metal = 0;
	private int unobtainium = 0;

	public Input getInput() {return lastInput;}
	public int getDelta() {return lastDelta;}
	public Camera getCamera() {return camera;}
	public double getMapWidth() {return map.getWidth() * map.getTileWidth();}
	public double getMapHeight() {return map.getHeight() * map.getTileHeight();}
	public void setMetal(int metal) {this.metal += metal;}
	public void setUnobtainium(int unobtainium) {this.unobtainium += unobtainium;}
	public ArrayList<Sprite> getSprites() {return sprites;}
	public void setSprites(ArrayList<Sprite> sprites) {this.sprites = sprites;}
	public boolean isPositionFree(double x, double y) {
		int tileId = map.getTileId(worldXToTileX(x), worldYToTileY(y), 0);
		return !Boolean.parseBoolean(map.getTileProperty(tileId, SOLID_PROPERTY, "false"));
	}
	
	public World() throws SlickException {
		map = new TiledMap(MAP_PATH);
		readCsv();
	}
	
	//read csv file to init objects
	public void readCsv() {
		sprites = new ArrayList<>();
		try (BufferedReader csvReader = 
				new BufferedReader(new FileReader("assets/objects.csv"))) {
			String line;
			while((line = csvReader.readLine()) != null) {
				String[] data = line.split(",");
				
				int x = Integer.parseInt(data[INDEX_X]);
				int y = Integer.parseInt(data[INDEX_Y]);
				
				switch(data[INDEX_OBJECT]) {
					case("command_centre"):
						sprites.add(new CommandCenter(x, y, camera));
						break;
						
					case("metal_mine"):
						sprites.add(new Metal(x, y, camera));
						break;
						
					case("unobtainium_mine"):
						sprites.add(new Unobtainium(x, y, camera));
						break;
						
					case("pylon"):
						sprites.add(new Pylon(x, y, camera));
						break;
					
					case("engineer"):
						sprites.add(new Engineer(x, y, camera));
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}
	/**
	 * Update the movements of all movable sprites and detect collisions.
	 * @param input The input to control the movement.
	 * @param delta The delta makes sure the same speed with different FPS.
	 * @throws SlickException 
	 * @throws InterruptedException 
	 */
	public void update(Input input, int delta) throws SlickException{
		String keyPress;
		lastInput = input;
		lastDelta = delta;
		// create sprite into the world
		//when press 1:
		if (lastInput.isKeyPressed(Input.KEY_1)) {  
			if (createObject != null) {
				if (createObject instanceof CommandCenter && metal >= SCOUT_COST_METAL) {
					metal -= SCOUT_COST_METAL;
					sprites.add(new Scout(createObject.getX(), createObject.getY(), camera));
				}else if (createObject instanceof Factory && metal >= TRUCK_COST_METAL) {
					metal -= TRUCK_COST_METAL;
					sprites.add(new Truck(createObject.getX(), createObject.getY(), camera));
				}else if (createObject instanceof Builder && metal >= FACTORY_COST_METAL) {
					metal -= FACTORY_COST_METAL;
					sprites.add(new Factory(createObject.getX(), createObject.getY(), camera));
				}else if (createObject instanceof Truck) {
					((Truck) createObject).remove();
					sprites.add(new Factory(createObject.getX(), createObject.getY(), camera));
				}
			}
			// when press 2:
		}else if (lastInput.isKeyPressed(Input.KEY_2)) {
			if (createObject instanceof CommandCenter && metal >= BUILDER_COST_METAL) {
				metal -= BUILDER_COST_METAL;
				sprites.add(new Builder(createObject.getX(), createObject.getY(), camera));
			}
			// when press 3: 
		}else if (lastInput.isKeyPressed(Input.KEY_3)) {
			if (createObject instanceof CommandCenter && metal >= ENGINEER_COST_METAL) {
				metal -= ENGINEER_COST_METAL;
				sprites.add(new Engineer(createObject.getX(), createObject.getY(), camera));
			}
		}
		for (Sprite sprite: sprites) {
			sprite.update(this);
			if ((sprite instanceof Unit || sprite instanceof Building) && ((Selectable)sprite).isSelect()) {
				sprite.getCamera().followSprite(sprite);
				sprite.getCamera().update(this);
			}
			if ( sprite instanceof Creatable && ((Selectable)sprite).isSelect()) {
				createObject = sprite;
			}
		}
	}
		
	public void render(Graphics g) {
		map.render((int)camera.globalXToScreenX(0),
				   (int)camera.globalYToScreenY(0));
		g.drawString("Metal:  <" + metal + ">\nUnobtainium:  <" + unobtainium + ">",
				DISPLAY, DISPLAY);
		for(Sprite sprite: sprites) {
			sprite.render(g);
			}
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
