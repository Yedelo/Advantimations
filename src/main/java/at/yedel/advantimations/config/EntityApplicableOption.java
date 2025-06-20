package at.yedel.advantimations.config;



import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;



public class EntityApplicableOption {
    private boolean enabled;
    private boolean enabledOnSelf;
    private boolean enabledOnOtherPlayers;
    private boolean enabledOnOtherEntities;

    public EntityApplicableOption enabled() {
        this.enabled = true;
        return this;
    }

    public EntityApplicableOption enabledOnSelf() {
        this.enabledOnSelf = true;
        return this;
    }

    public EntityApplicableOption enabledOnOtherPlayers() {
        this.enabledOnOtherPlayers = true;
        return this;
    }

    public EntityApplicableOption enabledOnOtherEntities() {
        this.enabledOnOtherEntities = true;
        return this;
    }

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
        }
        return originalValue;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabledOnSelf() {
        return enabledOnSelf;
    }

    public void setEnabledOnSelf(boolean enabledOnSelf) {
        this.enabledOnSelf = enabledOnSelf;
    }

    public boolean isEnabledOnOtherPlayers() {
        return enabledOnOtherPlayers;
    }

    public void setEnabledOnOtherPlayers(boolean enabledOnOtherPlayers) {
        this.enabledOnOtherPlayers = enabledOnOtherPlayers;
    }

    public boolean isEnabledOnOtherEntities() {
        return enabledOnOtherEntities;
    }

    public void setEnabledOnOtherEntities(boolean enabledOnOtherEntities) {
        this.enabledOnOtherEntities = enabledOnOtherEntities;
    }
}
