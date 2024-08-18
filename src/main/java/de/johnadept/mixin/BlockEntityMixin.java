package de.johnadept.mixin;

import de.pnku.mjnv.block.MoreJukeboxVariantBlockEntity;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.intellij.lang.annotations.JdkConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin{

    @Inject(method = "toUpdatePacket", at = @At("HEAD"), cancellable = true)
    private void onToUpdatePacket(CallbackInfoReturnable<Packet<?>> cir) {
        BlockEntity blockEntity = (BlockEntity) (Object) this;

        if (FabricLoader.getInstance().isModLoaded("quad-lolmjnv")) {
            if (blockEntity instanceof MoreJukeboxVariantBlockEntity) {
                cir.setReturnValue(BlockEntityUpdateS2CPacket.create((MoreJukeboxVariantBlockEntity) (Object) this));
            }
        }
    }

    @Inject(method = "toInitialChunkDataNbt", at = @At("HEAD"), cancellable = true)
    private void injectToInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup, CallbackInfoReturnable<NbtCompound> info) {
        BlockEntity blockEntity = (BlockEntity) (Object) this;

        if (FabricLoader.getInstance().isModLoaded("quad-lolmjnv")) {
            if (blockEntity instanceof MoreJukeboxVariantBlockEntity) {
                NbtCompound nbt = blockEntity.createNbt(registryLookup);
                info.setReturnValue(nbt);
            }
        }
    }
}
