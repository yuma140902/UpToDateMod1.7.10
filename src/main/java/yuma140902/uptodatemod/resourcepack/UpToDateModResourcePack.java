package yuma140902.uptodatemod.resourcepack;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import yuma140902.uptodatemod.ModUpToDateMod;

// see: https://mcmodding.jp/modding/index.php/1.7%E4%BB%A5%E9%99%8D%E3%81%AEIResourcePack%E3%81%AE%E5%88%A9%E7%94%A8
public class UpToDateModResourcePack implements IResourcePack {
	public UpToDateModResourcePack() {}
	
	private Path resourceLocationToPath(ResourceLocation location) {
		return ModUpToDateMod.INSTANCE.uptodatemodDirectory.resolve("assets/uptodate").resolve(location.getResourcePath()).toAbsolutePath();
	}

	@Override
	public InputStream getInputStream(ResourceLocation location) throws IOException {
		System.out.println(location.getResourceDomain() + ":" + location.getResourcePath());
		return new BufferedInputStream(new FileInputStream(resourceLocationToPath(location).toFile()));
	}

	@Override
	public boolean resourceExists(ResourceLocation location) {
		if(!ModUpToDateMod.MOD_TEXTURE_DOMAIN.equals(location.getResourceDomain())) {
			return false;
		}
		return Files.exists(resourceLocationToPath(location));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Set getResourceDomains() {
		return ImmutableSet.of(ModUpToDateMod.MOD_TEXTURE_DOMAIN);
	}

	@Override
	public IMetadataSection getPackMetadata(IMetadataSerializer metadataSerializer, String p_135058_2_) throws IOException {
		return null;
	}

	@Override
	public BufferedImage getPackImage() throws IOException {
		return null;
	}

	@Override
	public String getPackName() {
		return "UpToDateModResourcePack";
	}
	
	
}
