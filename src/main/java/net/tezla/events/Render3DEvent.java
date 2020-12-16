package net.tezla.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.client.util.math.MatrixStack;

public class Render3DEvent implements Event {

    private float tickDelta;
    private MatrixStack matrix;

    public Render3DEvent(float tickDelta, MatrixStack matrix) {
        this.tickDelta = tickDelta;
        this.matrix = matrix;
    }

    public float getTickDelta() {
        return tickDelta;
    }

    public MatrixStack getMatrix() {
        return matrix;
    }
}
