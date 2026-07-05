package at.yedel.advantimations.config;



import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionAddable;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.gui.controllers.slider.FloatSliderController;
import dev.isxander.yacl3.gui.controllers.string.number.FloatFieldController;
import dev.isxander.yacl3.impl.controller.FloatFieldControllerBuilderImpl;
import net.minecraft.network.chat.Component;



public class SimpleFirstPersonOption implements FirstPersonOption, ScalableOption {
    private boolean enabled;
    private float scalingMultiplier;

    public SimpleFirstPersonOption(boolean enabled) {
        this(enabled, 0);
    }

    public SimpleFirstPersonOption(boolean enabled, float scalingMultiplier) {
        this.enabled = enabled;
        this.scalingMultiplier = scalingMultiplier;
    }

    public static void createOption(String name, String description, SimpleFirstPersonOption defaultValue, SimpleFirstPersonOption configValue, OptionAddable builder) {
        createOption(name, description, defaultValue, configValue, builder, false);
    }

    public static void createOption(String name, String description, SimpleFirstPersonOption defaultValue, SimpleFirstPersonOption configValue, OptionAddable builder, boolean addScalingMultiplier) {
        builder.option(
            Option.<Boolean>createBuilder()
                .name(Component.literal(name))
                .description(OptionDescription.of(Component.literal(description)))
                .binding(
                    defaultValue.isEnabled(),
                    configValue::isEnabled,
                    configValue::setEnabled
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        ).optionIf(addScalingMultiplier, ScalableOption.createScalingMultiplierOption(defaultValue, configValue));
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

    @Override
    public float getScalingMultiplier() {
        return scalingMultiplier;
    }

    @Override
    public void setScalingMultiplier(float scalingMultiplier) {
        this.scalingMultiplier = scalingMultiplier;
    }
}
