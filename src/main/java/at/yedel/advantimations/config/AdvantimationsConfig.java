package at.yedel.advantimations.config;



import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;



public class AdvantimationsConfig {
    public static final ConfigClassHandler<AdvantimationsConfig> HANDLER = ConfigClassHandler.createBuilder(AdvantimationsConfig.class)
        .id(Identifier.of("advantimations", "advantimations-config"))
        .serializer(
            config -> GsonConfigSerializerBuilder.create(config)
                .setPath(FabricLoader.getInstance().getConfigDir().resolve("advantimations.json"))
                .setJson5(true)
                .build()
        )
        .build();

    public static AdvantimationsConfig getInstance() {
        return HANDLER.instance();
    }

    @SerialEntry
    public boolean cancelFirstPersonSwings = true;

    @SerialEntry
    public boolean cancelEatAnimation = false;

    @SerialEntry
    public boolean cancelDrinkAnimation = false;

    @SerialEntry
    public boolean cancelBlockAnimation = false;

    @SerialEntry
    public boolean cancelShieldAnimation = false;

    @SerialEntry
    public boolean cancelBowAnimation = false;

    @SerialEntry
    public boolean cancelArrowAnimation = false;

    @SerialEntry
    public boolean cancelSpearAnimation = false;

    @SerialEntry
    public boolean cancelBrushAnimation = false;

    @SerialEntry
    public boolean cancelBundleAnimation = false;

    @SerialEntry
    public EntityApplicableOption cancelSwings = new EntityApplicableOption(true, false, false);

    @SerialEntry
    public boolean cancelBlockInteractReset = true;

    @SerialEntry
    public boolean cancelItemInteractReset = true;

    @SerialEntry
    public boolean alwaysSkipHandAnimationOnSwap = true;

    public static Screen getScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) -> {
            return builder
                .title(Text.literal("Advantimations Config"))
                .category(
                    ConfigCategory.createBuilder()
                        .name(Text.literal("First Person"))
                        .tooltip(Text.literal("Options for canceling first person animations."))
                        .option(
                            Option.<Boolean>createBuilder()
                                .name(Text.literal("Cancel First Person Swings"))
                                .description(OptionDescription.of(Text.literal("")))
                                .binding(
                                    defaults.cancelFirstPersonSwings,
                                    () -> config.cancelFirstPersonSwings,
                                    (cancelFirstPersonSwings) -> config.cancelFirstPersonSwings = cancelFirstPersonSwings
                                )
                                .controller(BooleanControllerBuilder::create)
                                .build()
                        )
                        .group(
                            OptionGroup.createBuilder()
                                .name(Text.literal("Item Use Animations"))
                                .description(OptionDescription.of(Text.literal("Options for cancelling item use animations such as eating and blocking.")))
                                .collapsed(false)
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Eat Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelEatAnimation,
                                            () -> config.cancelEatAnimation,
                                            (cancelEatAnimation) -> config.cancelEatAnimation = cancelEatAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Drink Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelDrinkAnimation,
                                            () -> config.cancelDrinkAnimation,
                                            (cancelDrinkAnimation) -> config.cancelDrinkAnimation = cancelDrinkAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Block Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelBlockAnimation,
                                            () -> config.cancelBlockAnimation,
                                            (cancelBlockAnimation) -> config.cancelBlockAnimation = cancelBlockAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Shield Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelShieldAnimation,
                                            () -> config.cancelShieldAnimation,
                                            (cancelShieldAnimation) -> config.cancelShieldAnimation = cancelShieldAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Bow Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelBowAnimation,
                                            () -> config.cancelBowAnimation,
                                            (cancelBowAnimation) -> config.cancelBowAnimation = cancelBowAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Arrow Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelArrowAnimation,
                                            () -> config.cancelArrowAnimation,
                                            (cancelArrowAnimation) -> config.cancelArrowAnimation = cancelArrowAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Spear Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelSpearAnimation,
                                            () -> config.cancelSpearAnimation,
                                            (cancelSpearAnimation) -> config.cancelSpearAnimation = cancelSpearAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Brush Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelBrushAnimation,
                                            () -> config.cancelBrushAnimation,
                                            (cancelBrushAnimation) -> config.cancelBrushAnimation = cancelBrushAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Bundle Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelBundleAnimation,
                                            () -> config.cancelBundleAnimation,
                                            (cancelBundleAnimation) -> config.cancelBundleAnimation = cancelBundleAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .build()
                        )
                        .group(
                            OptionGroup.createBuilder()
                                .name(Text.literal("Item Resets"))
                                .description(OptionDescription.of(Text.literal("Options for cancelling item reset animations.")))
                                .collapsed(false)
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Block Interact Reset"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelBlockInteractReset,
                                            () -> config.cancelBlockInteractReset,
                                            (cancelBlockInteractReset) -> config.cancelBlockInteractReset = cancelBlockInteractReset
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Item Interact Reset"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelItemInteractReset,
                                            () -> config.cancelItemInteractReset,
                                            (cancelItemInteractReset) -> config.cancelItemInteractReset = cancelItemInteractReset
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Always Skip Hand Animation on Swap"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.alwaysSkipHandAnimationOnSwap,
                                            () -> config.alwaysSkipHandAnimationOnSwap,
                                            (alwaysSkipHandAnimationOnSwap) -> config.alwaysSkipHandAnimationOnSwap = alwaysSkipHandAnimationOnSwap
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .category(
                    ConfigCategory.createBuilder()
                        .name(Text.literal("Model"))
                        .tooltip(Text.literal("Options for canceling entity model animations."))
                        .option(
                            ListOption.<Boolean>createBuilder()
                                .name(Text.literal("Cancel Swings"))
                                .description(OptionDescription.of(Text.literal("")))
                                .binding(
                                    defaults.cancelSwings,
                                    () -> config.cancelSwings,
                                    (cancelSwings) -> config.cancelSwings.handleNewConfiguration(cancelSwings, defaults.cancelSwings)
                                )
                                .minimumNumberOfEntries(3)
                                .maximumNumberOfEntries(3)
                                .controller(BooleanControllerBuilder::create)
                                .initial(true)
                                .build()
                        )
                        .build()
                );
        }).generateScreen(parent);
    }
}
