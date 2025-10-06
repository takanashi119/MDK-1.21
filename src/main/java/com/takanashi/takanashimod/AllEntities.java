package com.takanashi.takanashimod;

import com.mojang.datafixers.DSL;
import com.mojang.serialization.MapCodec;
import io.netty.util.Attribute;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static com.takanashi.takanashimod.Register.*;


public class AllEntities {

    public static final DeferredHolder<Block, MyMachine> MY_MACHINE;//这是方块
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MyMachineEntity>> MY_MACHINE_BLOCK_ENTITY;//这是方块实体
    public static final DeferredHolder<Item,BlockItem> MY_MACHINE_ITEM;//这是方块物品

    public static final String MY_MACHINE_ID = "my_machine" ;


    static {
        MY_MACHINE = BLOCKS.register(MY_MACHINE_ID,
                () -> new MyMachine(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        MY_MACHINE_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(MY_MACHINE_ID,
                () -> BlockEntityType.Builder.of(MyMachineEntity::new, MY_MACHINE.get()).build(DSL.remainderType()));
        MY_MACHINE_ITEM = ITEMS.register(MY_MACHINE_ID,()-> new BlockItem(MY_MACHINE.get(), new Item.Properties()));
    }

    public static final class MyMachine extends BaseEntityBlock {

        public static final MapCodec<MyMachine> CODEC = simpleCodec(MyMachine::new);

        public MyMachine(Properties props) {
            super(props);
        }

        @Override
        protected MapCodec<? extends BaseEntityBlock> codec() {
            return CODEC;
        }

        @NotNull
        @Override
        public RenderShape getRenderShape(@NotNull BlockState state) {
            return RenderShape.MODEL;
        }

        @Override
        public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
            return new MyMachineEntity(pos, state);
        }

        @Override
        public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
            return level.isClientSide() ? null : createTickerHelper(blockEntityType, MY_MACHINE_BLOCK_ENTITY.get(), MyMachineEntity::tick);
        }
    }

    public static final class MyMachineEntity extends BlockEntity {
        public MyMachineEntity(BlockEntityType<MyMachineEntity> type, BlockPos worldPosition, BlockState blockState) {
            super(type, worldPosition, blockState);
        }

        public MyMachineEntity(BlockPos worldPosition, BlockState blockState) {
            this(MY_MACHINE_BLOCK_ENTITY.get(), worldPosition, blockState);
        }

        private int count = 0;

        public static void tick(Level level, BlockPos pos, BlockState state, MyMachineEntity entity) {
            entity.count += 1;
            if (entity.count > 100) {
                entity.count = 0;
                if (level != null && !level.isClientSide()) {
                    var player = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5.0, false);
                    if (player != null) {
                        player.sendSystemMessage(Component.translatable("chat.takanashimod.welcome"));
                    }
                }
            }
        }
    }
    public static void register(){

    }
}



