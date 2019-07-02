package yuma140902.uptodatemod.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import yuma140902.uptodatemod.registry.EnumNoteBlockInstrument;

public class NoteBlockPlayMessage implements IMessage {
	
	public EnumNoteBlockInstrument instrument;
	public int noteId;
	public int dimId, x, y, z;
	
	public NoteBlockPlayMessage() {}
	
	public NoteBlockPlayMessage(EnumNoteBlockInstrument instrument, int noteId, int dimId, int x, int y, int z) {
		this.instrument = instrument;
		this.noteId = noteId;
		this.dimId = dimId;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		dimId = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		instrument = EnumNoteBlockInstrument.fromId(buf.readInt());
		noteId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(dimId);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(instrument.ordinal());
		buf.writeInt(noteId);
	}
	
}
