package net.sodiumzh.gogaddon.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.nfu.block.BlockMaterial;

public class GOGAddonBlocks {

    public static final DeferredRegister<Block> REG = DeferredRegister.create(ForgeRegistries.BLOCKS, GOGAddon.MOD_ID);

    public static final RegistryObject<Block> ENCHANTED_COBBLESTONE = REG.register("enchanted_cobblestone", () ->
        new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(2.0F, 6.0F)
            .sound(SoundType.STONE)
            .lightLevel(bs -> 15)));

}
