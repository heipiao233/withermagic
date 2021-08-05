package com.heipiao.wm.network;

import java.util.function.Supplier;

import com.heipiao.wm.tileentity.ITileEntityUpdateable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class UpdateTilePacket {
    private final CompoundTag data;
    private final BlockPos block;
    private static final Logger LOGGER = LogManager.getLogger();

    public UpdateTilePacket(FriendlyByteBuf buffer) {
        data = buffer.readNbt();
        block = buffer.readBlockPos();
    }

    public UpdateTilePacket(CompoundTag data,BlockPos block) {
        this.data = data;
        this.block = block;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(data);
        buf.writeBlockPos(block);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientLevel world = Minecraft.getInstance().level;
            if (world != null) {
                BlockEntity tile = world.getBlockEntity(block);
                if(tile instanceof ITileEntityUpdateable){
                    ((ITileEntityUpdateable)tile).update(data);
                }else{
                    LOGGER.warn("Tile not found: {}",block);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}