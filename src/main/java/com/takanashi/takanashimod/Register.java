package com.takanashi.takanashimod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.takanashi.takanashimod.TakanashiMod.MOD_ID;


public class Register {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.Blocks.createBlocks(MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.Items.createItems(MOD_ID);
    public static final DeferredRegister<LootItemFunctionType<?>> LOOT_FUNCTION_TYPES =
            DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, TakanashiMod.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE,MOD_ID);



    //将所有延迟注册*deferred register 绑定到事件总线上
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        TABS.register(modEventBus);
    }
    public static void onGatherData(GatherDataEvent event){
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        var helper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();
        //添加语言文件
        gen.addProvider(event.includeClient(),new Providers.EnglishLanguageProvider(packOutput));
        gen.addProvider(event.includeClient(),new Providers.ChineseLanguageProvider(packOutput));
        //添加模型
        gen.addProvider(event.includeClient(),new Providers.ModelProvider(packOutput,helper));
        gen.addProvider(event.includeClient(), new Providers.StateProvider(packOutput, helper));
        gen.addProvider(event.includeServer(),new Providers.LootProvider(packOutput,lookupProvider));
        gen.addProvider(event.includeServer(),new Providers.tagProvider(packOutput,lookupProvider,MOD_ID,helper));

    }
    public static void init(){

    }
}
