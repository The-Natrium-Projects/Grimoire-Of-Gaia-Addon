package net.sodiumzh.gogaddon.entity.behavior;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.nbt.CompoundTag;
import net.sodiumzh.nfu.entity.component.EntityComponentBase;
import net.sodiumzh.nfu.entity.component.EntityComponentType;
import net.sodiumzh.nfu.entity.component.IEntityComponent;

public class GOGAddonMobBehaviorComponent extends EntityComponentBase<AbstractGaiaEntity> {

    public GOGAddonMobBehaviorComponent(AbstractGaiaEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {

    }

    @Override
    public EntityComponentType<AbstractGaiaEntity, ? extends IEntityComponent<AbstractGaiaEntity>> getType() {
        return null;
    }

    @Override
    public CompoundTag serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }
}
