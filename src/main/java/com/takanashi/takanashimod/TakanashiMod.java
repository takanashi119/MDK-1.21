package com.takanashi.takanashimod;


import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.minecraft.network.chat.Component;

import net.neoforged.neoforge.common.NeoForge;

import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import static com.takanashi.takanashimod.AllBlocks.NIANIA_BLOCK_ITEM;
import static com.takanashi.takanashimod.AllBlocks.NIANIA_CHEST_ITEM;
import static com.takanashi.takanashimod.AllItems.NIANIA_INGOT_ITEM;
import static com.takanashi.takanashimod.AllTabs.MAIN_TAB;


@Mod(TakanashiMod.MOD_ID) //主类
public class TakanashiMod {

    public static final String MOD_ID = "takanashimod";




    public TakanashiMod(IEventBus modEventBus){
        NeoForge.EVENT_BUS.addListener(PlayerLoggedInHandler::OnLoggedIn);
        Register.init();
        AllItems.register();
        AllBlocks.register();
        AllEntities.register();
        Register.register(modEventBus);
        modEventBus.addListener(Register::onGatherData);
        AllTabs.register();
        modEventBus.addListener(TakanashiMod::buildCreativeTabContent);

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

    public static void buildCreativeTabContent(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == MAIN_TAB.get()) {
            event.accept(NIANIA_INGOT_ITEM.get());
            event.accept(NIANIA_BLOCK_ITEM.get());
            event.accept(NIANIA_CHEST_ITEM.get());
        }
    }
}


