package com.takanashi.takanashimod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.takanashi.takanashimod.AllItems.NIANIA_INGOT_ITEM;
import static com.takanashi.takanashimod.Register.TABS;

public class AllTabs {
    public static final String MAIN_TAB_ID = "main_tab";
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB;

    static {
        MAIN_TAB = TABS.register(MAIN_TAB_ID, () -> CreativeModeTab.builder().icon(() -> new ItemStack(NIANIA_INGOT_ITEM)).build());
    }
    public static void register(){

    }

}
