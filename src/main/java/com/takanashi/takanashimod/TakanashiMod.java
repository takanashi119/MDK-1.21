package com.takanashi.takanashimod;


import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;



@Mod(TakanashiMod.MOD_ID) //主类
public class TakanashiMod {

    public static final String MOD_ID = "takanashimod";
    //DeferredRegister实现模组物品注册
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.Items.createItems(MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.Blocks.createBlocks(MOD_ID);

    public static final String NIANIA_INGOT_ID = "niania_ingot";
    public static final String NIANIA_BLOCK_ID = "niania_block";


    //Minecraft 中不同的方块类型或物品类型对应不同的实例：方块类型对应 Block 类，物品类型对应 Item 类。
    //开发者可直接使用这两个类构造方块类型或物品类型，也可继承它们，实现自己的方块类或物品类。
    //一个值得注意的物品类是 BlockItem 类：它是 Item 的子类，用于代表方块对应的物品。
    // BlockItem 实现了作为方块的物品需要实现的特性——右键放置方块，因此在声明 Block 时通常会一并声明其对应的 BlockItem。


    //DeferredHolder 储存着游戏元素的实例——该实例可通过 get 方法获得。建议将物品和方块对应的 DeferredHolder 以静态字段的方式声明。
    //T: 代表具体要注册的类型。T extends R 表示 T 必须是 R 的子类型或就是 R 本身。
    //例如，如果 R 是 Item，那么 T 可以是 Item、BlockItem、SwordItem 等。这个设计允许你注册更具体的类型，但仍然将其视为注册表的基础类型。
    public static final DeferredHolder<Item,Item> NIANIA_INGOT_ITEM;
    public static final DeferredHolder<Block,Block> NIANIA_BLOCK;
    public static final DeferredHolder<Item,BlockItem>NIANIA_BLOCK_ITEM;
    //例如这里的BlockItem是Item的子类


    //静态注册
    static {
        NIANIA_INGOT_ITEM = ITEMS.register(NIANIA_INGOT_ID,()->new Item(new Item.Properties()));
        NIANIA_BLOCK = BLOCKS.register(NIANIA_BLOCK_ID,()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(2F,1.5F)));
        NIANIA_BLOCK_ITEM = ITEMS.register(NIANIA_BLOCK_ID,()->new BlockItem(NIANIA_BLOCK.get(),new Item.Properties()));
    }


    public TakanashiMod(IEventBus modEventBus){
        NeoForge.EVENT_BUS.addListener(PlayerLoggedInHandler::OnLoggedIn);
        AllItems.register(modEventBus);
        BLOCKS.register(modEventBus);
        modEventBus.addListener(TakanashiMod::onGatherData);

    }


    public static void onGatherData(GatherDataEvent event){
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        var helper = event.getExistingFileHelper();
        //添加语言文件
        gen.addProvider(event.includeClient(),new EnglishLanguageProvider(packOutput));
        gen.addProvider(event.includeClient(),new ChineseLanguageProvider(packOutput));
        //添加模型
        gen.addProvider(event.includeClient(),new ModelProvider(packOutput,helper));
        gen.addProvider(event.includeClient(), new StateProvider(packOutput, helper));

        //

    }

    public static class EnglishLanguageProvider extends LanguageProvider{
        public EnglishLanguageProvider(PackOutput gen){
            super(gen, MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(NIANIA_INGOT_ITEM.get(),"niania ingot");
            add(NIANIA_BLOCK_ITEM.get(),"niania block");
        }

    }

    public static class ChineseLanguageProvider extends LanguageProvider{
        public ChineseLanguageProvider(PackOutput gen){
            super(gen,MOD_ID,"zh_cn");
        }

        @Override
        protected void addTranslations() {
            this.add(NIANIA_INGOT_ITEM.get(),"喵喵锭");
            this.add(NIANIA_BLOCK_ITEM.get(),"喵喵块");
        }
    }

    public static class ModelProvider extends ItemModelProvider{
        public ModelProvider(PackOutput gen, ExistingFileHelper helper) {
            super(gen,MOD_ID,helper);
        }
        @Override
        protected void registerModels(){
            this.singleTexture(NIANIA_INGOT_ID,ResourceLocation.withDefaultNamespace("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/" + NIANIA_INGOT_ID));
        }
    }
    public static class StateProvider extends BlockStateProvider{
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




    public static class PlayerLoggedInHandler{
        public static void OnLoggedIn(PlayerEvent.PlayerLoggedInEvent event){
            var player = event.getEntity();
            var ironBars = Component.translatable("block.minecraft.iron_bars");
            var ironIngot = Component.translatable("item.minecraft.iron_ingot");
            player.sendSystemMessage(Component.literal("16 x ").append(ironBars).append(" <= 6 x ").append(ironIngot));
            player.sendSystemMessage(Component.literal("welcome to takanashi's world"));
        }
    }
}


