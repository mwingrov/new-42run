package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.*;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		
		RawModel floor1 = OBJLoader.loadObjModel("floor", loader);
		RawModel wall1 = OBJLoader.loadObjModel("test_wall2", loader);
		RawModel wall2 = OBJLoader.loadObjModel("test_wall2", loader);
		
		TexturedModel staticModel = new TexturedModel(floor1,new ModelTexture(loader.loadTexture("white")));
		TexturedModel staticModel1 = new TexturedModel(wall1,new ModelTexture(loader.loadTexture("white")));
		TexturedModel staticModel2 = new TexturedModel(wall2,new ModelTexture(loader.loadTexture("white")));
		TexturedModel staticMode1 = new TexturedModel(floor1,new ModelTexture(loader.loadTexture("white")));
		TexturedModel staticModel3 = new TexturedModel(wall1,new ModelTexture(loader.loadTexture("white")));
		TexturedModel staticModel4 = new TexturedModel(wall2,new ModelTexture(loader.loadTexture("white")));

		World floor = new World(staticModel, new Vector3f(-3, 0, -105), 0, 0, 0, 1);
		World walls = new World(staticModel1, new Vector3f(-8, 0, -105), 0, 0, 0, 1);
		World walls1 = new World(staticModel2, new Vector3f(49, 0, -105), 0, 0, 0, 1);
		World floor_1 = new World(staticMode1, new Vector3f(-3, 0, -500), 0, 0, 0, 1);
		World walls2 = new World(staticModel3, new Vector3f(-8, 0, -500), 0, 0, 0, 1);
		World walls3 = new World(staticModel4, new Vector3f(49, 0, -500), 0, 0, 0, 1);
//		List<Entity> entities = new ArrayList<Entity>();
//		Random random = new Random();
//		for(int i=0;i<500;i++){
//			entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3));
//		}
		
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
		
//		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")));
//		Terrain terrain2 = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grass")));
//		Terrain terrain3 = new Terrain(-2,-1,loader,new ModelTexture(loader.loadTexture("grass")));

		MasterRenderer renderer = new MasterRenderer();

		RawModel sonicModel = OBJLoader.loadObjModel("sonic", loader);
		TexturedModel sonic = new TexturedModel(sonicModel, new ModelTexture(loader.loadTexture("chr_sonic_body01_dif")));


		Player player = new Player(sonic, new Vector3f(0, 0, -10), 0, 0, 0, 15);
		Camera camera = new Camera(player);
		
		while(!Display.isCloseRequested()){
			camera.move();
			player.move();
			floor.move();
			walls.move();
			walls1.move();
			floor_1.move();
			walls2.move();
			walls3.move();
			renderer.processEntity(player);
			renderer.processEntity(floor);
			renderer.processEntity(walls);
			renderer.processEntity(walls1);
			renderer.processEntity(floor_1);
			renderer.processEntity(walls2);
			renderer.processEntity(walls3);
//			renderer.processTerrain(terrain);
//			renderer.processTerrain(terrain2);
//			renderer.processTerrain(terrain3);
//			for(Entity entity:entities){
//				renderer.processEntity(entity);
//			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
