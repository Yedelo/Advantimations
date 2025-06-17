package at.yedel.advantimations.config;



import at.yedel.advantimations.Advantimations;
import at.yedel.advantimations.utils.AdvantimationsConstants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;



@Config(name = AdvantimationsConstants.MOD_ID)
public class AdvantimationsConfig implements ConfigData {
    public static AdvantimationsConfig getInstance() {
        return Advantimations.getInstance().getConfig();
    }

    @ConfigEntry.Category("firstPerson")
    public boolean cancelFirstPersonSwings = true;

    @ConfigEntry.Category("firstPerson")
    public boolean cancelEatAnimation = false;

    @ConfigEntry.Category("firstPerson")
    public boolean cancelDrinkAnimation = false;

    @ConfigEntry.Category("firstPerson")
    public boolean cancelBlockAnimation = false;

    @ConfigEntry.Category("firstPerson")
    public boolean cancelShieldAnimation = false;

    @ConfigEntry.Category("firstPerson")
    public boolean cancelBowAnimation = false;

    @ConfigEntry.Category("firstPerson")
    public boolean cancelArrowAnimation = false;

    @ConfigEntry.Category("firstPerson")
    public boolean cancelSpearAnimation = false;

    @ConfigEntry.Category("firstPerson")
    public boolean cancelBrushAnimation = false;

    @ConfigEntry.Category("firstPerson")
    public boolean cancelBundleAnimation = false;

    @ConfigEntry.Category("swings")
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public EntityApplicableOption cancelSwings = new EntityApplicableOption()
        .enabled()
        .enabledOnSelf();

    @ConfigEntry.Category("itemResets")
    public boolean cancelBlockInteractReset = true;

    @ConfigEntry.Category("itemResets")
    public boolean cancelItemInteractReset = true;

    @ConfigEntry.Category("itemResets")
    public boolean alwaysSkipHandAnimationOnSwap = true;

    public static class EntityApplicableOption {
        public boolean enabled;
        public boolean enabledOnSelf;
        public boolean enabledOnOtherPlayers;
        public boolean enabledOnOtherEntities;

        public <T> T getResult(Entity entity, T originalValue, T newValue) {
            if (enabled) {
                if (enabledOnSelf && entity instanceof ClientPlayerEntity) {
                    return newValue;
                }
                if (enabledOnOtherPlayers && entity instanceof PlayerEntity player && !player.isMainPlayer()) {
                    return newValue;
                }
                if (enabledOnOtherEntities && !(entity instanceof PlayerEntity)) {
                    return newValue;
                }
                return originalValue;
            }
            return originalValue;
        }

        public EntityApplicableOption enabled() {
            enabled = true;
            return this;
        }

        public EntityApplicableOption enabledOnSelf() {
            enabledOnSelf = true;
            return this;
        }

        public EntityApplicableOption enabledOnOtherPlayers() {
            enabledOnOtherPlayers = true;
            return this;
        }

        public EntityApplicableOption enabledOnOtherEntities() {
            enabledOnOtherEntities = true;
            return this;
        }
    }
}
