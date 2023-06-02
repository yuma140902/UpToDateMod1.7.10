package yuma140902.yumaessentials.iea;

import cpw.mods.fml.relauncher.Side;
import yuma140902.yumaessentials.ModYumaEssentials;

import javax.annotation.Nonnull;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class LangSResourcePackExtractor implements IIEAFeature {
	
	private final Path resourcePacksDir;
	private final Path resourcePackDest;
	
	public LangSResourcePackExtractor(){
		this.resourcePacksDir = ModYumaEssentials.mcGameDir().resolve("resourcepacks");
		this.resourcePackDest = this.resourcePacksDir.resolve("lang_S_4.1.0.zip");
	}
	
	@Override
	public String name() {
		return "lang_S ResourcePackExtractor";
	}
	
	@Override
	public String description() {
		return "lang_Sリソースパックバージョンをリソースパックフォルダに配置します。";
	}
	
	@Nonnull
	@Override
	public Side side() {
		return Side.CLIENT;
	}
	
	@Override
	public IEAFeatureResult run() {
		if(Files.exists(this.resourcePackDest)){
			return IEAFeatureResult.skipped();
		}
		
		try {
			extract();
		} catch (IOException e) {
			return IEAFeatureResult.failed(e);
		}
		return IEAFeatureResult.succeeded();
	}
	
	private void extract() throws IOException {
		Class<ModYumaEssentials> clazz = ModYumaEssentials.class;
		String resourcePackName = "/lang_S/S_lang_files_1.7.x_4.1.0.zip";
		InputStream in = clazz.getResourceAsStream(resourcePackName);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(this.resourcePackDest.toFile()));
		
		byte[] buff = new byte[1024];
		while(in.read(buff) != -1){
			out.write(buff);
		}
		out.flush();
		out.close();
		in.close();
	}
}
