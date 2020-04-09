package yuma140902.yumalib.api.blocks;

public class CustomSoundTypeWithPlaceSound extends CustomSoundType {

	public CustomSoundTypeWithPlaceSound(String domain, String name, float volume, float frequency) {
		super(domain, name, volume, frequency);
	}
	
	public CustomSoundTypeWithPlaceSound(String domain, String name) {
		super(domain, name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String func_150496_b() {
		return domain + ":" + "place." + soundName;
	}
	
}
