package com.takanashi.takanashimod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.takanashi.takanashimod.Register.BLOCKS;
import static com.takanashi.takanashimod.Register.ITEMS;
import static com.takanashi.takanashimod.TakanashiMod.MOD_ID;


public class AllBlocks {
    public static final DeferredHolder<Block,Block> NIANIA_BLOCK;
    public static final DeferredHolder<Item, BlockItem>NIANIA_BLOCK_ITEM;

    public static final String NIANIA_BLOCK_ID = "niania_block";
    static {
        NIANIA_BLOCK = BLOCKS.register(NIANIA_BLOCK_ID,()->
                new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(2F,1.5F)));
//        NIANIA_BLOCK = BLOCKS.register(NIANIA_BLOCK_ID,()->
//                new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));
        NIANIA_BLOCK_ITEM = ITEMS.register(NIANIA_BLOCK_ID,()->new BlockItem(NIANIA_BLOCK.get(),new Item.Properties()));
    }

    public static void register(){

    }
}
