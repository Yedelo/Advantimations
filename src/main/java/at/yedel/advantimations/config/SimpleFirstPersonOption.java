package at.yedel.advantimations.config;



import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import net.minecraft.network.chat.Component;



public class SimpleFirstPersonOption implements FirstPersonOption {
    private boolean enabled;

    private SimpleFirstPersonOption(boolean enabled) {
        this.enabled = enabled;
    }

    public static Option<Boolean> createOption(String name, String description, SimpleFirstPersonOption defaultValue, SimpleFirstPersonOption configValue) {
        return Option.<Boolean>createBuilder()
            .name(Component.literal(name))
            .description(OptionDescription.of(Component.literal(description)))
            .binding(
                defaultValue.isEnabled(),
                configValue::isEnabled,
                configValue::setEnabled
            )
            .controller(BooleanControllerBuilder::create)
            .build();
    }

    public static SimpleFirstPersonOption trueOption() {
        return new SimpleFirstPersonOption(true);
    }

    public static SimpleFirstPersonOption falseOption() {
        return new SimpleFirstPersonOption(false);
    }

    @Override
    public boolean shouldApplyInFirstPerson() {
        return enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
