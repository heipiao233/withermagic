package com.heipiao.wm.pollute;

import com.heipiao.wm.network.Networking;
import com.heipiao.wm.network.UpdatePollutePacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.fmllegacy.network.PacketDistributor;

public class PolluteSavedData extends SavedData{
    public double pollute;
    private PolluteSavedData(CompoundTag nbt) {
        this.pollute=nbt.getDouble("pollute");
    }
    @Override
    public CompoundTag save(CompoundTag compound) {
        compound.putDouble("pollute",this.pollute);
        return compound;
    }
    public static PolluteSavedData get(Level world){
        if (!(world instanceof ServerLevel)) {
            throw new IllegalArgumentException("Attempted to get the data from a client world.");
        }
        ServerLevel serverWorld=(ServerLevel)world;
        return serverWorld.getDataStorage().get(PolluteSavedData::new, "pollute");
    }
    @Override
    public void setDirty() {
        Networking.INSTANCE.send(PacketDistributor.ALL.noArg(), new UpdatePollutePacket(this.pollute));
        super.setDirty();
    }
}
