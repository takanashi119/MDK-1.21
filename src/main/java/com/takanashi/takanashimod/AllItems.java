package com.takanashi.takanashimod;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.takanashi.takanashimod.Register.ITEMS;
import static com.takanashi.takanashimod.TakanashiMod.MOD_ID;

//该类声明并注册所有的物品(items)
public class AllItems {


    //此处创建物品ID
    public static final String NIANIA_INGOT_ID = "niania_ingot";

    //储存着游戏元素的实例——该实例可通过 get 方法获得。建议将物品和方块对应的 DeferredHolder 以静态字段的方式声明。
    public static final DeferredHolder<Item, Item> NIANIA_INGOT_ITEM;

    //静态实例化（注册）
    static {
        NIANIA_INGOT_ITEM = ITEMS.register(NIANIA_INGOT_ID, () -> new Item(new Item.Properties()));
    }
    public static void register(){

    }
}