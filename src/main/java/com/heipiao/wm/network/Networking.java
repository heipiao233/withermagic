package com.heipiao.wm.network;

import com.heipiao.wm.Utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

public class Networking{
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int id=0;
    private static int newId(){
        return id++;
    }
    public static void register(){
        INSTANCE=NetworkRegistry.newSimpleChannel(new ResourceLocation(Utils.modid, "wm_channel"), ()->VERSION, (ver)->VERSION.equals(ver), (ver)->VERSION.equals(ver));
        INSTANCE.messageBuilder(UpdateTilePacket.class, newId())
            .encoder(UpdateTilePacket::toBytes)
            .decoder(UpdateTilePacket::new)
            .consumer(UpdateTilePacket::handler)
            .add();
        INSTANCE.messageBuilder(UpdatePollutePacket.class, newId())
            .encoder(UpdatePollutePacket::toBytes)
            .decoder(UpdatePollutePacket::new)
            .consumer(UpdatePollutePacket::handler)
            .add();
    }
}
