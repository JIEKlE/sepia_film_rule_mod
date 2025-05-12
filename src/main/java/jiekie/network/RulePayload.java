package jiekie.network;

import jiekie.SepiaFilmRuleMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class RulePayload implements CustomPayload {
    public static final Id<RulePayload> ID = new Id<>(Identifier.of(SepiaFilmRuleMod.MOD_ID, "open_rule"));
    public static final PacketCodec<RegistryByteBuf, RulePayload> CODEC =
            PacketCodec.ofStatic(RulePayload::write, RulePayload::read).cast();

    public static void write(RegistryByteBuf buf, RulePayload payload) {}

    public static RulePayload read(RegistryByteBuf buf) {
        return new RulePayload();
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
