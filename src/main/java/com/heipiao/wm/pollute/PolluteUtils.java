package com.heipiao.wm.pollute;

import com.heipiao.wm.network.Networking;
import com.heipiao.wm.network.UpdatePollutePacket;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.network.PacketDistributor;

public class PolluteUtils {
    public static double clientPollute=0;
    public static void updateToClient(Level world, ServerPlayer player){
        Networking.INSTANCE.send(PacketDistributor.PLAYER.with(()->player), new UpdatePollutePacket(get(world)));
    }
    public static void add(Level world, double value){
        if(!world.isClientSide()){
            PolluteSavedData data=PolluteSavedData.get(world);
            data.pollute+=value;
            data.setDirty();
        }else{
            clientPollute+=value;
        }
    }
    public static void set(Level world, double value){
        if(!world.isClientSide()){
            PolluteSavedData data=PolluteSavedData.get(world);
            data.pollute=value;
            data.setDirty();
        }else{
            clientPollute=value;
        }
    }
    public static double get(Level world){
        if(!world.isClientSide()){
            PolluteSavedData data=PolluteSavedData.get(world);
            return data.pollute;
        }else{
            return clientPollute;
        }
    }
}
