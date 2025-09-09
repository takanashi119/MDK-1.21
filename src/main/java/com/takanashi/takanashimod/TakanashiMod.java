package com.takanashi.takanashimod;


import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.minecraft.network.chat.Component;

import net.neoforged.neoforge.common.NeoForge;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;



@Mod(TakanashiMod.MOD_ID) //主类
public class TakanashiMod {

    public static final String MOD_ID = "takanashimod";




    public TakanashiMod(IEventBus modEventBus){
        NeoForge.EVENT_BUS.addListener(PlayerLoggedInHandler::OnLoggedIn);
        Register.init();
        AllItems.register();
        AllBlocks.register();
        Register.register(modEventBus);
        modEventBus.addListener(Register::onGatherData);

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


