package yuma140902.yumaessentials.iea;

import cpw.mods.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yuma140902.yumaessentials.YEConstants;

import java.util.Arrays;
import java.util.List;

public class InitialEnvironmentArranger {
	public static final InitialEnvironmentArranger INSTANCE = new InitialEnvironmentArranger();
	
	private static final List<IIEAFeature> features = Arrays.asList(
					new MinimumLangS()
	);
	
	private InitialEnvironmentArranger(){}
	public final Logger LOGGER = LogManager.getLogger(YEConstants.MOD_NAME + " IEA");
	
	public void arrangeServer(){
		features.stream().filter(feature -> feature.side() == Side.SERVER).forEach(feature -> {
			LOGGER.info(feature.name() + "を実行中...");
			IEAFeatureResult result = feature.run();
			if(result.isSuccess()){
				LOGGER.info("完了");
			}
			else if(result.isSkipped()){
				LOGGER.info("スキップされました");
			}
			else{
				LOGGER.error("失敗");
				if(result.getReason() != null)
					LOGGER.error(result.getReason());
			}
		});
	}
	
	public void arrangeClient(){
		features.stream().filter(feature -> feature.side() == Side.CLIENT).forEach(feature -> {
			LOGGER.info(feature.name() + "を実行中...");
			IEAFeatureResult result = feature.run();
			if(result.isSuccess()){
				LOGGER.info("完了");
			}
			else if(result.isSkipped()){
				LOGGER.info("スキップされました");
			}
			else{
				LOGGER.info("失敗");
				if(result.getReason() != null)
					LOGGER.info(result.getReason());
			}
		});
	}
}
