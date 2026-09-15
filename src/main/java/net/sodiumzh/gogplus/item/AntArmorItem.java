package net.sodiumzh.gogplus.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogplus.GOGPlus;
import net.sodiumzh.gogplus.registry.GOGPlusTags;
import net.sodiumzh.nfu.entity.component.EntityComponentAPI;
import net.sodiumzh.nfu.registry.NFUEntityComponents;

import java.util.stream.Stream;

public class AntArmorItem extends ArmorItem {

    public AntArmorItem(ArmorMaterial pMaterial, ArmorItem.Type type, Item.Properties pProperties) {
        super(pMaterial, type, pProperties);
    }

    public AntArmorItem(ArmorMaterial pMaterial, EquipmentSlot slot, Item.Properties pProperties) {
        this(pMaterial, slot == EquipmentSlot.CHEST ? ArmorItem.Type.CHESTPLATE :
            (slot == EquipmentSlot.LEGS ? ArmorItem.Type.LEGGINGS :
                (slot == EquipmentSlot.FEET ? ArmorItem.Type.BOOTS : ArmorItem.Type.HELMET)), pProperties);
    }

    @Mod.EventBusSubscriber(modid = GOGPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class EventListeners {

        @SubscribeEvent
        public static void onMobChangeTarget(LivingChangeTargetEvent event) {
            if (event.isCanceled()) return;
            if (event.getNewTarget() instanceof ArmorStand) return;
            if (!event.getEntity().getType().is(GOGPlusTags.ANTS)) return;
            boolean isAntDisguise = Stream.of(EquipmentSlot.values())
                .map(event.getNewTarget()::getItemBySlot)
                .filter(itemStack -> itemStack.getItem() instanceof AntArmorItem)
                .count() >= 4;
            if (isAntDisguise && EntityComponentAPI.getComponentByPath(event.getEntity(),
                NFUEntityComponents.ACCESSOR_DEFAULT_ANGER_HANDLER)
                .filter(c -> c.isAngryAt(event.getNewTarget())).isEmpty())
            {
                event.setCanceled(true);
            }
        }
    }
}
