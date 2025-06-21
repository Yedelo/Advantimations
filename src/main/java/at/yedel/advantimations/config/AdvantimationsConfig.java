package at.yedel.advantimations.config;



import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;



public class AdvantimationsConfig {
    public static final ConfigClassHandler<AdvantimationsConfig> HANDLER = ConfigClassHandler.createBuilder(AdvantimationsConfig.class)
        .id(Identifier.of("advantimations", "advantimations-config"))
        .serializer(
            config -> GsonConfigSerializerBuilder.create(config)
                .setPath(FabricLoader.getInstance().getConfigDir().resolve("advantimations.json"))
                .setJson5(false)
                .build()
        )
        .build();

    public static AdvantimationsConfig getInstance() {
        return HANDLER.instance();
    }

    public static void init() {
        HANDLER.load();
    }

    @SerialEntry
    public boolean cancelFirstPersonSwings = true;

    @SerialEntry
    public boolean cancelEatingAnimation = false;

    @SerialEntry
    public boolean cancelDrinkingAnimation = false;

    @SerialEntry
    public boolean cancelBlockingAnimation = false;

    @SerialEntry
    public boolean cancelShieldAnimation = false;

    @SerialEntry
    public boolean cancelBowingAnimation = false;

    @SerialEntry
    public boolean cancelBowArrowModel = false;

    @SerialEntry
    public boolean cancelSpearAnimation = false;

    @SerialEntry
    public boolean cancelBrushingAnimation = false;

    @SerialEntry
    public boolean cancelBundleAnimation = false;

    @SerialEntry
    public boolean cancelCrossbowingAnimation = false;

    @SerialEntry
    public boolean cancelChargedCrossbowAnimation = false;

    @SerialEntry
    public boolean cancelCrossbowArrowModel = false;

    @SerialEntry
    public boolean cancelBlockInteractResets = true;

    @SerialEntry
    public boolean cancelItemInteractResets = true;

    @SerialEntry
    public boolean cancelAllItemResets = false;

    @SerialEntry
    public EntityApplicableOption cancelSwings = new EntityApplicableOption()
        .enabled()
        .enabledOnSelf();

    @SerialEntry
    public EntityApplicableOption cancelLimbMovements = new EntityApplicableOption();

    @SerialEntry
    public EntityApplicableOption weirderLimbMovements = new EntityApplicableOption();

    @SerialEntry
    public EntityApplicableOption cancelSneaking = new EntityApplicableOption();

    @SerialEntry
    public EntityAndFirstPersonApplicableOption testOption = new EntityAndFirstPersonApplicableOption()
        .enabled()
        .enabledInFirstPerson()
        .enabledOnSelf();

    public static Screen getScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) ->
            builder
                .title(Text.literal("Advantimations Config"))
                .category(
                    ConfigCategory.createBuilder()
                        .name(Text.literal("First Person"))
                        .tooltip(Text.literal("Options for cancelling first person animations."))
                        .option(
                            Option.<Boolean>createBuilder()
                                .name(Text.literal("Cancel First Person Swings"))
                                .description(OptionDescription.of(Text.literal("Cancel all swing animations in first person.")))
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
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Eating Animation"))
                                        .description(OptionDescription.of(Text.literal("Cancel first person eating animations of items with").append(Text.literal("\nconsumable={animation:'eat'}.").formatted(Formatting.ITALIC))))
                                        .binding(
                                            defaults.cancelEatingAnimation,
                                            () -> config.cancelEatingAnimation,
                                            (cancelEatingAnimation) -> config.cancelEatingAnimation = cancelEatingAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Drinking Animation"))
                                        .description(OptionDescription.of(Text.literal("Cancel first person drinking animations of items with").append(Text.literal("\nconsumable={animation:'drink'}.").formatted(Formatting.ITALIC))))
                                        .binding(
                                            defaults.cancelDrinkingAnimation,
                                            () -> config.cancelDrinkingAnimation,
                                            (cancelDrinkingAnimation) -> config.cancelDrinkingAnimation = cancelDrinkingAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Blocking Animation"))
                                        .description(OptionDescription.of(Text.literal("Cancel first person blocking animations of items with").append(Text.literal("\nconsumable={animation:'block'}.").formatted(Formatting.ITALIC))))
                                        .binding(
                                            defaults.cancelBlockingAnimation,
                                            () -> config.cancelBlockingAnimation,
                                            (cancelBlockingAnimation) -> config.cancelBlockingAnimation = cancelBlockingAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Shield Animation"))
                                        .description(OptionDescription.of(Text.literal("Cancel first person shield animations of items that use the shield model.")))
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
                                        .name(Text.literal("Cancel Bowing Animation"))
                                        .description(OptionDescription.of(Text.literal("Cancel first person bowing animations of items with").append(Text.literal("\nconsumable={animation:'bow'}.").formatted(Formatting.ITALIC))))
                                        .binding(
                                            defaults.cancelBowingAnimation,
                                            () -> config.cancelBowingAnimation,
                                            (cancelBowingAnimation) -> config.cancelBowingAnimation = cancelBowingAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Bow Arrow Model"))
                                        .description(OptionDescription.of(Text.literal("Cancel the model of the arrow being charged in a bow.")))
                                        .binding(
                                            defaults.cancelBowArrowModel,
                                            () -> config.cancelBowArrowModel,
                                            (cancelArrowAnimation) -> config.cancelBowArrowModel = cancelArrowAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Spear Animation"))
                                        .description(OptionDescription.of(Text.literal("Cancel first person spear animations of items with").append(Text.literal("\nconsumable={animation:'spear'},").formatted(Formatting.ITALIC).append(Text.literal("\nmainly tridents.")))))
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
                                        .name(Text.literal("Cancel Brushing Animation"))
                                        .description(OptionDescription.of(Text.literal("Cancel first person brushing animations of items with").append(Text.literal("\nconsumable={animation:'brush'}.").formatted(Formatting.ITALIC))))
                                        .binding(
                                            defaults.cancelBrushingAnimation,
                                            () -> config.cancelBrushingAnimation,
                                            (cancelBrushingAnimation) -> config.cancelBrushingAnimation = cancelBrushingAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Bundle Animation"))
                                        .description(OptionDescription.of(Text.literal("Cancel the first person swinging animation of using bundles.")))
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
                                .name(Text.literal("Crossbow Animations"))
                                .description(OptionDescription.of(Text.literal("Options for cancelling crossbow animations.")))
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Crossbowing Animation"))
                                        .description(OptionDescription.of(Text.literal("Cancel the first person crossbowing animation of items with").append(Text.literal("\nconsumable={animation:'crossbow'}.").formatted(Formatting.ITALIC))))
                                        .binding(
                                            defaults.cancelCrossbowingAnimation,
                                            () -> config.cancelCrossbowingAnimation,
                                            (cancelCrossbowingAnimation) -> config.cancelCrossbowingAnimation = cancelCrossbowingAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Charged Crossbow Animation"))
                                        .description(OptionDescription.of(Text.literal("Cancel the first person charged crossbow animation when holding a charged crossbow.")))
                                        .binding(
                                            defaults.cancelChargedCrossbowAnimation,
                                            () -> config.cancelChargedCrossbowAnimation,
                                            (cancelChargedCrossbowAnimation) -> config.cancelChargedCrossbowAnimation = cancelChargedCrossbowAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Crossbow Arrow Model"))
                                        .description(OptionDescription.of(Text.literal("Cancel the model of the arrow being charged in a crossbow.")))
                                        .binding(
                                            defaults.cancelCrossbowArrowModel,
                                            () -> config.cancelCrossbowArrowModel,
                                            (cancelCrossbowArrowModel) -> config.cancelCrossbowArrowModel = cancelCrossbowArrowModel
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
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Block Interact Resets"))
                                        .description(OptionDescription.of(Text.literal("Cancel item reset animations when interacting with a block, such as eating a cake.")))
                                        .binding(
                                            defaults.cancelBlockInteractResets,
                                            () -> config.cancelBlockInteractResets,
                                            (cancelBlockInteractResets) -> config.cancelBlockInteractResets = cancelBlockInteractResets
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Item Interact Resets"))
                                        .description(OptionDescription.of(Text.literal("Cancel item reset animations when interacting with an item, such as casting a fishing rod.")))
                                        .binding(
                                            defaults.cancelItemInteractResets,
                                            () -> config.cancelItemInteractResets,
                                            (cancelItemInteractResets) -> config.cancelItemInteractResets = cancelItemInteractResets
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel All Item Resets"))
                                        .description(OptionDescription.of(Text.literal("Cancel all item reset animations, including swapping slots or experiencing item changes like with durability.")))
                                        .binding(
                                            defaults.cancelAllItemResets,
                                            () -> config.cancelAllItemResets,
                                            (cancelAllItemResets) -> config.cancelAllItemResets = cancelAllItemResets
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
                        .group(
                            EntityApplicableOption.createGroup(
                                "Cancel Swings",
                                "Cancel swinging animations on entities.",
                                defaults.cancelSwings, config.cancelSwings
                            )
                        )
                        .group(
                            EntityApplicableOption.createGroup(
                                "Cancel Limb Movements",
                                "Cancel limb movements on entities.",
                                defaults.cancelLimbMovements, config.cancelLimbMovements
                            )
                        )
                        .group(
                            EntityApplicableOption.createGroup(
                                "Weirder Limb Movements",
                                "Somewhat cancel limb movements on entities. Limbs will look normal until an entity is fully moving, to which point they will stay still.",
                                defaults.weirderLimbMovements, config.weirderLimbMovements
                            )
                        )
                        .group(
                            EntityApplicableOption.createGroup(
                                "Cancel Sneaking",
                                "Cancel sneaking animations on entities.",
                                defaults.cancelSneaking, config.cancelSneaking
                            )
                        )
                        .group(
                            EntityAndFirstPersonApplicableOption.createGroup(
                                "Test Option",
                                "Test! Expected order:\n- Enabled\n- Enabled in First Person\n- Enabled on Self\n- Enabled on Other Players\n- Enabled on Other Entities",
                                defaults.testOption,
                                config.testOption
                            )
                        )
                        .build()
                )).generateScreen(parent);
    }
}