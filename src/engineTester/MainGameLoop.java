package engineTester;

import Models.RawModel;
import Models.TexturedModel;

import RenderEngine.*;
import entities.Light;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Textures.ModelTexture;
import entities.Camera;
import entities.Entity;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		RawModel model = OBJLoader.loadObjectModel("dragon", loader);
		MasterRenderer renderer = new MasterRenderer();

		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("download")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);

		Entity entity = new Entity(staticModel, new Vector3f(0,0,-30),0,0,0,1);

		Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

		Camera camera = new Camera();
		while(!Display.isCloseRequested()){
			entity.increaseRotation(0, 1, 0);
			camera.move();
			renderer.processEntity(entity);
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
