package yuma140902.yumaessentials.iea;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yuma140902.yumaessentials.ModYumaEssentials;
import yuma140902.yumaessentials.YEConstants;
import yuma140902.yumaessentials.iea.json.MinimumLangSMap;

import javax.annotation.Nonnull;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MinimumLangS implements IIEAFeature {
	
	private final Path wholeArchivePath = ModYumaEssentials.mcGameDir().resolve("resourcepacks/S_lang_files_1.7.x_4.1.0.zip");
	private final Path outputPack = ModYumaEssentials.mcGameDir().resolve("resourcepacks/MinimumLangS");
	
	private final Logger logger = LogManager.getLogger(YEConstants.MOD_NAME + " IEA - " + name());
	
	@Override
	public String name() {
		return "Minimum Lang_S";
	}
	
	@Override
	public String description() {
		return "読み込まれているModをもとに最小限のLang_Sリソースパックを構築します。";
	}
	
	@Nonnull
	@Override
	public Side side() {
		return Side.CLIENT;
	}
	
	@Nonnull
	@Override
	public IEAFeatureResult run() {
		if(Files.exists(outputPack)){
			try {
				Files.delete(outputPack);
			} catch (IOException e) {
				IEAFeatureResult.failed(e);
			}
			//return IEAFeatureResult.skipped();
		}
		
		try {
			_run();
		} catch (IOException e) {
			return IEAFeatureResult.failed(e);
		}
		
		return IEAFeatureResult.succeeded();
	}
	
	private void _run() throws IOException {
		MinimumLangSMap[] modids;
		extractWholeArchive();
		modids = loadDataJson();
		
		Multimap<String, String> modidToDomain = ArrayListMultimap.create();
		for(MinimumLangSMap map : modids){
			modidToDomain.put(map.modid, map.domain);
		}
		
		List<String> domainsToLoad = new ArrayList<>();
		for(final String modid : Loader.instance().getModList().stream().map(ModContainer::getModId).collect(Collectors.toList())){
			domainsToLoad.addAll(modidToDomain.get(modid));
		}
		
		this.logger.info("Extracting LangS translations for the following domains:");
		this.logger.info(Arrays.toString(domainsToLoad.toArray()));
		
		FileSystem archive = FileSystems.newFileSystem(this.wholeArchivePath, null);
		for(final String domain : domainsToLoad){
			String jaJpLangFile = "assets/" + domain + "/lang/" + "ja_JP.lang";
			Path src = archive.getPath(jaJpLangFile);
			Path dst = this.outputPack.resolve(jaJpLangFile);
			if(Files.notExists(src)){
				this.logger.warn(jaJpLangFile + " は存在しません。スキップします。");
				continue;
			}
			if(Files.exists(dst)){
				this.logger.info(jaJpLangFile + " は既に存在するためスキップします。");
				continue;
			}
			try {
				Files.copy(src, dst);
			}catch (IOException e){
				this.logger.error(jaJpLangFile + " のコピー中にエラーが発生しました。");
				this.logger.error(e);
			}
			
			Path packIconSrc = archive.getPath("pack.png");
			Path packIconDst = this.outputPack.resolve("pack.png");
			try {
				Files.copy(packIconSrc, packIconDst);
			}catch (IOException e){
				this.logger.error("pack.png のコピー中にエラーが発生しました。");
				this.logger.error(e);
			}
			
			Path packInfoSrc;
			try {
				packInfoSrc = Paths.get(ModYumaEssentials.class.getResource("/minimumLangSPackMeta.json").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return;
			}
			Path packInfoDest = this.outputPack.resolve("pack.mcmeta");
			try{
				Files.copy(packInfoSrc, packInfoDest);
			} catch (IOException e){
				this.logger.error("pack.mcmeta のコピー中にエラーが発生しました。");
				this.logger.error(e);
			}
		}
	}
	
	private void extractWholeArchive() throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			Class<ModYumaEssentials> clazz = ModYumaEssentials.class;
			String resourcePackName = "/lang_S/S_lang_files_1.7.x_4.1.0.zip";
			in = clazz.getResourceAsStream(resourcePackName);
			out = new BufferedOutputStream(new FileOutputStream(this.wholeArchivePath.toFile()));
			
			byte[] buff = new byte[1024];
			while (in.read(buff) != -1) {
				out.write(buff);
			}
			out.flush();
		}
		finally {
			if(out != null) out.close();
			if(out != null) in.close();
		}
	}
	
	private MinimumLangSMap[] loadDataJson() throws IOException {
		InputStreamReader reader = new InputStreamReader(ModYumaEssentials.class.getResourceAsStream("/lang_S/minimumLangSMap.json"));
		Gson gson = new Gson();
		Type listType = new TypeToken<MinimumLangSMap[]>(){}.getType();
		MinimumLangSMap[] list = gson.fromJson(reader, listType);
		reader.close();
		return list;
	}
}
