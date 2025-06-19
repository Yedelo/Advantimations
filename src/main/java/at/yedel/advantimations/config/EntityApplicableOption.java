package at.yedel.advantimations.config;



import dev.isxander.yacl3.api.ListOption;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;



public class EntityApplicableOption extends ArrayList<Boolean> {
    public EntityApplicableOption(boolean enabledOnSelf, boolean enabledOnOtherPlayers, boolean enabledOnOtherEntities) {
        add(true);
        add(true);
        add(true);
        configure(enabledOnSelf, enabledOnOtherPlayers, enabledOnOtherEntities);
    }

    private boolean enabledOnSelf() {
        return get(0);
    }

    private boolean enabledOnOtherPlayers() {
        return get(1);
    }

    private boolean enabledOnOtherEntities() {
        return get(2);
    }

    public <T> T getResult(Entity entity, T originalValue, T newValue) {
        if (enabledOnSelf() && entity instanceof ClientPlayerEntity) {
            return newValue;
        }
        if (enabledOnOtherPlayers() && entity instanceof PlayerEntity player && !player.isMainPlayer()) {
            return newValue;
        }
        if (enabledOnOtherEntities() && !(entity instanceof PlayerEntity)) {
            return newValue;
        }
        return originalValue;
    }

    public void handleNewConfiguration(List<Boolean> newConfiguration, List<Boolean> defaultOptions) {
        if (newConfiguration.size() < 3) {
            newConfiguration = defaultOptions;
        }
        configure(newConfiguration.get(0), newConfiguration.get(1), newConfiguration.get(2));
    }

    private void configure(boolean enabledOnSelf, boolean enabledOnOtherPlayers, boolean enabledOnOtherEntities) {
        set(0, enabledOnSelf);
        set(1, enabledOnOtherPlayers);
        set(2, enabledOnOtherEntities);
    }

    public static Option createEntityApplicableOption(String title, EntityApplicableOption defaultValue, Supplier<List<Boolean>> getter, Consumer<List<Boolean>> setter) {
        return ListOption.<Boolean>createBuilder()
            .name(Text.literal(title))
            .description(OptionDescription.of(Text.literal("")))
            .binding(
                defaultValue,
                getter,
                setter
            )
            .minimumNumberOfEntries(3)
            .maximumNumberOfEntries(3)
            .controller(BooleanControllerBuilder::create)
            .initial(true)
            .build();
    }
}
