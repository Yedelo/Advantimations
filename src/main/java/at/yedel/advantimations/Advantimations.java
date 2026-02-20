package at.yedel.advantimations;



import at.yedel.advantimations.config.AdvantimationsConfig;
import net.fabricmc.api.ClientModInitializer;



// Mod?
public class Advantimations implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AdvantimationsConfig.init();
	}
}