package net.tezla.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.client.util.math.MatrixStack;

public class Render2DEvent implements Event {

    private MatrixStack matrix;

    public Render2DEvent(MatrixStack matrix) {
        this.matrix = matrix;
    }

    public MatrixStack getMatrix() {
        return matrix;
    }
}
