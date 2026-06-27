package at.yedel.advantimations;



import at.yedel.advantimations.config.AdvantimationsConfig;
/*? if fabric {*/
/*import net.fabricmc.api.ClientModInitializer;
*//*?} else if neoforge {*/
import net.neoforged.fml.common.Mod;
/*?}*/



// Maud
/*? if neoforge */ @Mod("advantimations")
public class Advantimations /*? if fabric {*//*implements ClientModInitializer*//*?}*/ {
	/*? if fabric {*/
	/*@Override
	public void onInitializeClient() {
		AdvantimationsConfig.init();
	}
	*//*?} elif neoforge {*/
	public Advantimations() {
		AdvantimationsConfig.init();
	}
	/*?}*/
}