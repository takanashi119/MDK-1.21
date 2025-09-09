package com.takanashi.takanashimod;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.takanashi.takanashimod.TakanashiMod.MOD_ID;

public class Register {
    public static final DeferredRegister<Item> ITEMS = AllItems.ITEMS;
    //静态实例化（注册）
    public static class EnglishLanguageProvider extends LanguageProvider {
        public EnglishLanguageProvider(PackOutput gen){
            super(gen, MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(NIANIA_INGOT_ITEM.get(),"niania ingot");
//            add(NIANIA_BLOCK_ITEM.get(),"niania block");
        }

    }
    public static class ChineseLanguageProvider extends LanguageProvider{
        public ChineseLanguageProvider(PackOutput gen){
            super(gen,MOD_ID,"zh_cn");
        }

        @Override
        protected void addTranslations() {
            this.add(NIANIA_INGOT_ITEM.get(),"喵喵锭");
//            this.add(NIANIA_BLOCK_ITEM.get(),"喵喵块");
        }
    }

    public static class ModelProvider extends ItemModelProvider {
        public ModelProvider(PackOutput gen, ExistingFileHelper helper) {
            super(gen,MOD_ID,helper);
        }
        @Override
        protected void registerModels(){
            this.singleTexture(NIANIA_INGOT_ID, ResourceLocation.withDefaultNamespace("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/" + NIANIA_INGOT_ID));
        }
    }
    public static class StateProvider extends BlockStateProvider {
        public StateProvider(PackOutput gen, ExistingFileHelper helper){
            super(gen,MOD_ID,helper);
        }

        // 此处生成方块状态文件和方块模型文件
        // 第一个参数为模型对应的方块，对应的方块状态文件会在 xiaozhong:sulfur 自动生成
        // 第二个参数为模型，对应的模型文件会在 xiaozhong:block/sulfur_block 处自动生成
        // 自动生成的模型文件中，父模型为 minecraft:block/cube_all，并引用 xiaozhong:block/sulfur_block 处的纹理
        @Override
        protected void registerStatesAndModels() {
            this.simpleBlock(NIANIA_BLOCK.get(),this.cubeAll(NIANIA_BLOCK.get()));
            this.simpleBlockItem(NIANIA_BLOCK.get(),this.cubeAll(NIANIA_BLOCK.get()));
        }
    }



    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }


}
