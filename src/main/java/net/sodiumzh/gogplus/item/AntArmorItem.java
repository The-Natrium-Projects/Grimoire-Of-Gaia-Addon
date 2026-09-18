package net.sodiumzh.gogplus.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogplus.GOGPlus;
import net.sodiumzh.gogplus.registry.GOGPlusTags;
import net.sodiumzh.nfu.entity.component.EntityComponentAPI;
import net.sodiumzh.nfu.registry.NFUEntityComponents;
import net.sodiumzh.nfu.util.NFUInfoStatics;

import javax.annotation.Nullable;
import java.util.List;
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

    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, level, list, tooltipFlag);
        list.add(NFUInfoStatics.createTranslatable("tooltip.gogplus.ant_armor"));
    }

    @Mod.EventBusSubscriber(modid = GOGPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class EventListeners {

        @SubscribeEvent
        public static void onMobChangeTarget(LivingChangeTargetEvent event) {
            if (event.isCanceled()) return;
            if (event.getNewTarget() instanceof ArmorStand) return;
            if (!(event.getEntity() instanceof Mob mob)) return;
            if (!MobType.ARTHROPOD.equals(mob.getMobType())) return;
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
