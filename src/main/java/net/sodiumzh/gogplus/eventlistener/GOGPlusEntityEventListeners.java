package net.sodiumzh.gogplus.eventlistener;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogplus.GOGPlus;
import net.sodiumzh.gogplus.entity.mob.IGOGAddonMob;
import net.sodiumzh.nfu.mixin.event.entity.LivingEntityDamageTakenEvent;

@Mod.EventBusSubscriber(modid = GOGPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GOGPlusEntityEventListeners {

    @SubscribeEvent
    public static void onDamage(LivingEntityDamageTakenEvent event) {
        if (event.getDamageSource().getEntity() instanceof IGOGAddonMob gogAddonMob) {
            gogAddonMob.onDealDamage(event.getEntity(), event.getAmount(), event.getDamageSource());
        }
    }

}
