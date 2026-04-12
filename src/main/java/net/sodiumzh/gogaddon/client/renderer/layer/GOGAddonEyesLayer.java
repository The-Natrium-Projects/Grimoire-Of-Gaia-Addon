package net.sodiumzh.gogaddon.client.renderer.layer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.world.entity.Entity;

public class GOGAddonEyesLayer<E extends Entity, R extends EntityModel<E>> extends EyesLayer<E, R> {

    private final RenderType renderType;

    public GOGAddonEyesLayer(RenderLayerParent<E, R> layerParent, RenderType renderType) {
        super(layerParent);
        this.renderType = renderType;
    }

    public RenderType renderType() {
        return renderType;
    }
}
