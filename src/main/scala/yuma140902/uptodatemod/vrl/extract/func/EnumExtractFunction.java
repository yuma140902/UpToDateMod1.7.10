package yuma140902.uptodatemod.vrl.extract.func;

public enum EnumExtractFunction {
    COPY_ONLY(new ExtractFunctionCopyOnly()),
    ROTATE90(new ExtractFunctionRotate(90)),
    ROTATE180(new ExtractFunctionRotate(180)),
    ROTATE270(new ExtractFunctionRotate(270)),
    FLIP(new ExtractFunctionFlip());

    private final IExtractFunction function;

    EnumExtractFunction(IExtractFunction function) {
        this.function = function;
    }

    public IExtractFunction getFunction() {
        return function;
    }
}
