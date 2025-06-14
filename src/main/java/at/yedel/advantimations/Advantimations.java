package at.yedel.advantimations;



import at.yedel.advantimations.config.AdvantimationsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;



public class Advantimations implements ClientModInitializer {
	private static Advantimations INSTANCE;

	public static Advantimations getInstance() {
		return INSTANCE;
	}

	public Advantimations() {
		INSTANCE = this;
	}

	private static ConfigHolder<AdvantimationsConfig> configHolder;

	@Override
	public void onInitializeClient() {
		configHolder = AutoConfig.register(AdvantimationsConfig.class, GsonConfigSerializer::new);
	}

	public ConfigHolder<AdvantimationsConfig> getConfigHolder() {
		return configHolder;
	}

	public AdvantimationsConfig getConfig() {
		return getConfigHolder().getConfig();
	}
}