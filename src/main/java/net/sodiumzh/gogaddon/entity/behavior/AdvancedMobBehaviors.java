package net.sodiumzh.gogaddon.entity.behavior;

import gaia.entity.AbstractGaiaEntity;

public abstract class AdvancedMobBehaviors<T extends AbstractGaiaEntity> implements IAdvancedMobBehaviors<T> {

    private final T mob;
    private final AdvancedMobBehaviorComponent component;

    public AdvancedMobBehaviors(AdvancedMobBehaviorComponent component) {
        this.mob = (T)(component.getEntity());
        this.component = component;
    }

    @Override
    public T getMob() {
        return mob;
    }

    @Override
    public AdvancedMobBehaviorComponent getComponent() {
        return component;
    }

}
