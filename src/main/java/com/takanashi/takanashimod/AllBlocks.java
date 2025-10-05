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


public class
AllBlocks {
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

    }//register是空的，仅仅用于加载类。因为静态块只会在类加载时才会执行
/*          ● 静态代码块类似于是一个方法，但它不可以在方法中调用，也不能存在于任何方法体中；
            ● 静态代码块可以写在类中的任何地方，一个类中也可以有多个静态代码块；
            ● 静态代码块在类加载时执行；
            ● 静态代码块优先于动态代码块执行；
            ● 如果类中包含多个静态代码块，则 JVM虚拟机 会按它们在类中出现的先后顺序依次执行，每个静态代码块只会被执行一次；
            ● 静态代码块与静态方法一样，不能直接访问类的实例变量和实例方法，需要通过类的实例对象来访问
*/
}
