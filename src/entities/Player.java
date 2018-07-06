package entities;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;

public class Player extends Entity{


    private static final float GRAVITY = -50;
    private static final float JUMP_POWER = 30;

    private static final float TERRIAN_HEIGHT = 0;

    private float upwardsSpeed = 0;
    private int   playerPos = 0;

    private boolean isInAir = false;

    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    public void move() {
        checkInputs();
        upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
        super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        if (super.getPosition().y < TERRIAN_HEIGHT) {
            upwardsSpeed = 0;
            isInAir = false;
            super.getPosition().y = TERRIAN_HEIGHT;
        }
    }

    private  void jump() {
        if (!isInAir) {
            this.upwardsSpeed = JUMP_POWER;
        }
        isInAir = true;
    }

    private void checkInputs() {


        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            if (playerPos == 1)
            {
                playerPos = 0;
                this.setPosition(new Vector3f(0.0f, 0.0f, -10.0f));
            }
            else if (playerPos == 0)
            {
                playerPos = -1;
                this.setPosition(new Vector3f(-13.0f, 0.0f, -10.0f));
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            if (playerPos == -1)
            {
                playerPos = 0;
                this.setPosition(new Vector3f(0.0f, 0.0f, -10.0f));
            }
            else if (playerPos == 0)
            {
                playerPos = 1;
                this.setPosition(new Vector3f(13.0f, 0.0f, -10.0f));
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            jump();
        }
    }

}
