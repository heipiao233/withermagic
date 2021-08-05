package com.heipiao.wm.network;

import java.util.function.Supplier;

import com.heipiao.wm.pollute.PolluteUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class UpdatePollutePacket {
    private final double data;
    private static final Logger LOGGER = LogManager.getLogger();

    public UpdatePollutePacket(FriendlyByteBuf buffer) {
        data = buffer.readDouble();
    }

    public UpdatePollutePacket(double data) {
        this.data = data;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeDouble(data);
    }
    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            LOGGER.debug("updating pollute:{}", data);
            PolluteUtils.clientPollute=data;
        });
        ctx.get().setPacketHandled(true);
    }
}