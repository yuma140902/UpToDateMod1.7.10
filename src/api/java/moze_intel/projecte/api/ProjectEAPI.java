package moze_intel.projecte.api;
/*    */ 
/*    */ import cpw.mods.fml.common.FMLLog;
/*    */ import moze_intel.projecte.api.proxy.IBlacklistProxy;
/*    */ import moze_intel.projecte.api.proxy.IConversionProxy;
/*    */ import moze_intel.projecte.api.proxy.IEMCProxy;
/*    */ import moze_intel.projecte.api.proxy.ITransmutationProxy;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ProjectEAPI {
/*    */    private static IEMCProxy emcProxy;
/*    */    private static ITransmutationProxy transProxy;
/*    */    private static IBlacklistProxy blacklistProxy;
/*    */    private static IConversionProxy recipeProxy;
/*    */ 
/*    */    public static IEMCProxy getEMCProxy() {
/* 24 */       if(emcProxy == null) {
/*    */ 
/*    */ 
/*    */          try {
/* 28 */             Class ex = Class.forName("moze_intel.projecte.impl.EMCProxyImpl");
/* 29 */             emcProxy = (IEMCProxy)ex.getField("instance").get((Object)null);
/* 30 */          } catch (ReflectiveOperationException arg0) {
/*    */ 
/* 32 */             FMLLog.warning("[ProjectEAPI] Error retrieving EMCProxyImpl, ProjectE may be absent, damaged, or outdated.", new Object[0]);
/*    */          }      }
/*    */ 
/* 35 */       return emcProxy;
/*    */    }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */    public static IConversionProxy getConversionProxy() {
/* 44 */       if(recipeProxy == null) {
/*    */ 
/*    */ 
/*    */          try {
/* 48 */             Class ex = Class.forName("moze_intel.projecte.impl.ConversionProxyImpl");
/* 49 */             recipeProxy = (IConversionProxy)ex.getField("instance").get((Object)null);
/* 50 */          } catch (ReflectiveOperationException arg0) {
/*    */ 
/* 52 */             FMLLog.warning("[ProjectEAPI] Error retrieving ConversionProxyImpl, ProjectE may be absent, damaged, or outdated.", new Object[0]);
/*    */          }      }
/*    */ 
/* 55 */       return recipeProxy;
/*    */    }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */    public static ITransmutationProxy getTransmutationProxy() {
/* 64 */       if(transProxy == null) {
/*    */ 
/*    */ 
/*    */          try {
/* 68 */             Class ex = Class.forName("moze_intel.projecte.impl.TransmutationProxyImpl");
/* 69 */             transProxy = (ITransmutationProxy)ex.getField("instance").get((Object)null);
/* 70 */          } catch (ReflectiveOperationException arg0) {
/*    */ 
/* 72 */             FMLLog.warning("[ProjectEAPI] Error retrieving TransmutationProxyImpl, ProjectE may be absent, damaged, or outdated.", new Object[0]);
/*    */          }      }
/*    */ 
/* 75 */       return transProxy;
/*    */    }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */    public static IBlacklistProxy getBlacklistProxy() {
/* 84 */       if(blacklistProxy == null) {
/*    */ 
/*    */ 
/*    */          try {
/* 88 */             Class ex = Class.forName("moze_intel.projecte.impl.BlacklistProxyImpl");
/* 89 */             blacklistProxy = (IBlacklistProxy)ex.getField("instance").get((Object)null);
/* 90 */          } catch (ReflectiveOperationException arg0) {
/*    */ 
/* 92 */             FMLLog.warning("[ProjectEAPI] Error retrieving BlacklistProxyImpl, ProjectE may be absent, damaged, or outdated.", new Object[0]);
/*    */          }      }
/*    */ 
/* 95 */       return blacklistProxy;
/*    */    }
/*    */ }

/*
	DECOMPILATION REPORT

	Decompiled from: Z:\dev\mcmod\UpToDateMod\UpToDateMod1.7.10\libs\ProjectE-1.7.10-PE1.10.1-deobf.jar
	Total time: 243 ms
	
	Decompiled with FernFlower.
*/