package yuma140902.uptodatemod.vrl.extract;

public enum EnumTextureStyle {
    BEFORE12("before12");

    private final String text;

    EnumTextureStyle(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
