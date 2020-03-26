package yuma140902.uptodatemod.config.model;

import java.util.Arrays;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class PropertyBuilder {
	
	private String name;
	private String category;
	
	private Type valueType;
	private boolean defaultBool;
	private String defaultString;
	private int defaultInt;
	private String[] defaultStringList;
	private int[] defaultIntList;
	
	private String[] validStrings;
	private Pattern validationPattern;
	
	private boolean requireMcRestart = false;
	private boolean requireWorldRestart = false;
	
	private String langKey;
	private MultiLingualString comment;
	
	public PropertyBuilder(String name) {
		this.name = name;
	}
	
	public String name() {
		return this.name;
	}
	
	public PropertyBuilder category(String category) {
		this.category = category;
		return this;
	}
	
	public PropertyBuilder defaultBool(boolean value) {
		this.defaultBool = value;
		this.valueType = Type.BOOL;
		return this;
	}
	
	public PropertyBuilder defaultString(String value) {
		this.defaultString = value;
		this.valueType = Type.STRING;
		return this;
	}
	
	public PropertyBuilder defaultInt(int value) {
		this.defaultInt = value;
		this.valueType = Type.INT;
		return this;
	}
	
	public PropertyBuilder defaultStringList(String[] values) {
		this.defaultStringList = values;
		this.valueType = Type.STRING_LIST;
		return this;
	}
	
	public PropertyBuilder defaultIntList(int[] values) {
		this.defaultIntList = values;
		this.valueType = Type.INT_LIST;
		return this;
	}
	
	public PropertyBuilder validStrings(String[] strings) {
		this.validStrings = strings;
		return this;
	}
	
	public PropertyBuilder validationPattern(Pattern pattern) {
		this.validationPattern = pattern;
		return this;
	}
	
	public PropertyBuilder requireMcRestart() {
		this.requireMcRestart = true;
		return this;
	}
	
	public PropertyBuilder requireWorldRestart() {
		this.requireWorldRestart = true;
		return this;
	}
	
	public PropertyBuilder langKey(String langKey) {
		this.langKey = langKey;
		return this;
	}
	
	public PropertyBuilder comment(MultiLingualString comment) {
		this.comment = comment;
		return this;
	}
	
	public PropertyBuilder comment(String enMessage, String jaMessage) {
		this.comment = MultiLingualString.en_ja(enMessage, jaMessage);
		return this;
	}
	
	public PropertyBuilder comment(String comment) {
		this.comment = MultiLingualString.single(comment);
		return this;
	}
	
	public void registerToForge(Configuration cfg) {
		Property prop = null;
		switch(this.valueType) {
			case BOOL:
				prop = cfg.get(category, name, defaultBool);
				prop.comment = toString(comment) + " [default: " + defaultBool + "]";
				break;
			case STRING:
				prop = cfg.get(category, name, defaultString);
				prop.comment = toString(comment) + " [default: " + defaultString + "]";
				break;
			case INT:
				prop = cfg.get(category, name, defaultInt);
				prop.comment = toString(comment) + " [default: " + defaultInt + "]";
				break;
			case STRING_LIST:
				prop = cfg.get(category, name, defaultStringList);
				prop.comment = toString(comment) + " [default: " + String.join(", ", defaultStringList) + "]";
				break;
			case INT_LIST:
				prop = cfg.get(category, name, defaultIntList);
				prop.comment = toString(comment) + " [default: " + Arrays.toString(defaultIntList) + "]";
				break;
		}
		
		if(prop != null) {
			prop.setRequiresMcRestart(requireMcRestart);
			prop.setRequiresWorldRestart(requireWorldRestart);
			if(langKey != null) prop.setLanguageKey(langKey);
			if(validStrings != null) prop.setValidValues(validStrings);
			if(validationPattern != null) prop.setValidationPattern(validationPattern);
		}
	}
	
	private static enum Type {
		BOOL, STRING, INT, STRING_LIST, INT_LIST
	}
	
	private static String toString(@Nullable MultiLingualString mls) {
		if(mls == null) return "";
		return mls.toString();
	}
}
