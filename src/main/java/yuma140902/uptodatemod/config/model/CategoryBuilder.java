package yuma140902.uptodatemod.config.model;

import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class CategoryBuilder {
	
	private List<PropertyBuilder> properties = new ArrayList<PropertyBuilder>();
	private List<String> order = new ArrayList<String>();
	private String name;
	private boolean requireMcRestart = false;
	private boolean requireWorldRestart = false;
	private LangKey langKey;
	private MultiLingualString comment;
	
	public CategoryBuilder(String name) {
		this.name = name;
	}
	
	public String name() {
		return this.name;
	}
	
	public List<PropertyBuilder> properties() {
		return this.properties;
	}
	
	public CategoryBuilder add(PropertyBuilder property) {
		property.category(name());
		this.properties.add(property);
		this.order.add(property.name());
		return this;
	}
	
	public CategoryBuilder requireMcRestart() {
		this.requireMcRestart = true;
		return this;
	}
	
	public CategoryBuilder requireWorldRestart() {
		this.requireWorldRestart = true;
		return this;
	}
	
	public CategoryBuilder langKey(LangKey langKey) {
		this.langKey = langKey;
		return this;
	}
	
	public CategoryBuilder comment(MultiLingualString comment) {
		this.comment = comment;
		return this;
	}
	
	public CategoryBuilder comment(String enMessage, String jaMessage) {
		this.comment = MultiLingualString.en_ja(enMessage, jaMessage);
		return this;
	}
	
	public CategoryBuilder comment(String comment) {
		this.comment = MultiLingualString.single(comment);
		return this;
	}
	
	// initConfig()から呼び出す
	public void registerToForge(Configuration cfg) {
		if(comment != null) cfg.setCategoryComment(name, comment.toString());
		cfg.setCategoryRequiresMcRestart(name, requireMcRestart);
		cfg.setCategoryRequiresWorldRestart(name, requireWorldRestart);
		if(langKey != null) cfg.setCategoryLanguageKey(name, langKey.toString());
	}
	
	// syncConfig()から呼び出す
	public void registerPropertiesToForge(Configuration cfg) {
		for(PropertyBuilder property : properties) {
			property.registerToForge(cfg);
		}
		cfg.setCategoryPropertyOrder(name, order);
	}
	
	public Property get(String propertyName, Configuration cfg) {
		return cfg.getCategory(name).get(propertyName);
	}
}
