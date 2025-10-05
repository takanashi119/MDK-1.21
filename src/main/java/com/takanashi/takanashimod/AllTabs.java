package com.takanashi.takanashimod;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.takanashi.takanashimod.AllItems.NIANIA_INGOT_ITEM;
import static com.takanashi.takanashimod.Register.TABS;
import static com.takanashi.takanashimod.TakanashiMod.MOD_ID;

public class AllTabs {
    public static final String MAIN_TAB_ID = "niania_tabs";
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB;

    static {
        //这是一个
        MAIN_TAB = TABS.register(MAIN_TAB_ID, () -> CreativeModeTab
                .builder()
                .title(Component.translatable("ItemGroups."+MOD_ID+"."+MAIN_TAB_ID))
                .icon(() -> new ItemStack(NIANIA_INGOT_ITEM))
                .build());
    }
    public static void register(){

    }

}
