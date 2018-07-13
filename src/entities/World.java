package entities;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;

public class World extends Player{
    private static final float RUN_SPEED = 100;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;

    public World(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    public void move() {
        checkInputs();
        super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
        super.increasePosition(dx, 0, dz);
    }
    private void checkInputs() {
        this.currentSpeed = RUN_SPEED;
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            this.currentSpeed = RUN_SPEED * 3;
        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            this.currentSpeed = 40;
        }
    }
}
