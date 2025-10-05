package com.takanashi.takanashimod;

import com.google.common.collect.Iterables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import org.jetbrains.annotations.NotNull;

import com.takanashi.takanashimod.AllBlocks;
import com.takanashi.takanashimod.AllItems;
import org.jetbrains.annotations.Nullable;

import static com.takanashi.takanashimod.Register.BLOCKS;
import static com.takanashi.takanashimod.TakanashiMod.MOD_ID;

/* Provider 类在runData后自动生成相应的配置
*
* */

public class Providers {


    public static class EnglishLanguageProvider extends LanguageProvider {
        public EnglishLanguageProvider(PackOutput gen){
            super(gen, TakanashiMod.MOD_ID, "en_us");
        }
        protected void addTranslations()
        {
            this.add(AllItems.NIANIA_INGOT_ITEM.get(),"Niania Ingot");
            this.add("ItemGroups."+MOD_ID+".niania_tabs","Niania Group");
        }
    }

    public static class ChineseLanguageProvider extends LanguageProvider{
        public ChineseLanguageProvider(PackOutput gen){
            super(gen,TakanashiMod.MOD_ID,"zh_cn");
        }

        @Override
        protected void addTranslations() {
            this.add(AllItems.NIANIA_INGOT_ITEM.get(),"喵喵锭");
            this.add(AllBlocks.NIANIA_BLOCK_ITEM.get(),"喵喵块");
            this.add("ItemGroups."+MOD_ID+".niania_tabs","喵喵类");
        }
    }

    public static class ModelProvider extends ItemModelProvider {
        public ModelProvider(PackOutput gen, ExistingFileHelper helper) {
            super(gen,TakanashiMod.MOD_ID,helper);
        }
        @Override
        protected void registerModels(){
            this.singleTexture(AllItems.NIANIA_INGOT_ID,
                    ResourceLocation.withDefaultNamespace("item/generated"),
                    "layer0", ResourceLocation.fromNamespaceAndPath
                            (TakanashiMod.MOD_ID, "item/" + AllItems.NIANIA_INGOT_ID));
        }
    }

    public static class StateProvider extends BlockStateProvider {
        public StateProvider(PackOutput gen, ExistingFileHelper helper){
            super(gen,TakanashiMod.MOD_ID,helper);
        }

        // 此处生成方块状态文件和方块模型文件
        // 第一个参数为模型对应的方块，对应的方块状态文件会在 xiaozhong:sulfur 自动生成
        // 第二个参数为模型，对应的模型文件会在 xiaozhong:block/sulfur_block 处自动生成
        // 自动生成的模型文件中，父模型为 minecraft:block/cube_all，并引用 xiaozhong:block/sulfur_block 处的纹理
        @Override
        protected void registerStatesAndModels() {
            this.simpleBlock(AllBlocks.NIANIA_BLOCK.get(),this.cubeAll(AllBlocks.NIANIA_BLOCK.get()));
            this.simpleBlockItem(AllBlocks.NIANIA_BLOCK.get(),this.cubeAll(AllBlocks.NIANIA_BLOCK.get()));
        }
    }


    public static class LootProvider extends LootTableProvider {
        public LootProvider(PackOutput gen, CompletableFuture<HolderLookup.Provider> lookup) {
            super(gen, Set.of(), List.of(new SubProviderEntry(CustomBlockLoot::new, LootContextParamSets.BLOCK)), lookup);
        }

        @Override
        protected void validate(WritableRegistry<LootTable> registry, ValidationContext context, ProblemReporter.Collector collector) {
            // FIXME Need proper migration
        }

    }
    public static class CustomBlockLoot extends BlockLootSubProvider {
        protected CustomBlockLoot(HolderLookup.Provider lookupProvider) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
        }

        @Override
        protected void generate() {
            this.dropSelf(AllBlocks.NIANIA_BLOCK.get());
        /*
        // 如欲在非精准采集的情况下掉落九个 xiaozhong: sulfur_dust，请使用以下代码：
        this.add(SULFUR_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, SULFUR_DUST_ITEM.get(), ConstantValue.exactly(9f)));
        */
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            // 模组自定义的方块战利品表必须覆盖此方法，以绕过对原版方块战利品表的检查（此处返回该模组的所有方块）
            return Iterables.transform(BLOCKS.getEntries(), DeferredHolder::get);
        }
    }
    public static class tagProvider extends BlockTagsProvider{
        public tagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, modId, existingFileHelper);
        }


        @Override
        protected void addTags(HolderLookup.Provider provider){
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(AllBlocks.NIANIA_BLOCK.get());
            this.tag(BlockTags.NEEDS_STONE_TOOL).add(AllBlocks.NIANIA_BLOCK.get());


        }
    }
}
