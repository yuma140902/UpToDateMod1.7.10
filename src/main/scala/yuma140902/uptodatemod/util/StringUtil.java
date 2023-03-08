package yuma140902.uptodatemod.util;

import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.yumalib.api.util.NameProvider;

/**
 * @deprecated use {@link ModUpToDateMod#name} instead
 */
@Deprecated
public class StringUtil {
    /**
     * @deprecated use {@link ModUpToDateMod#name} instead
     */
    @Deprecated
    public static final NameProvider name = new NameProvider(ModUpToDateMod.MOD_TEXTURE_DOMAIN, ModUpToDateMod.MOD_UNLOCALIZED_ENTRY_DOMAIN);

    private StringUtil() {
    }

}
