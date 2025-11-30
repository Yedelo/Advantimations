package at.yedel.advantimations.config;



import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import java.util.function.Consumer;



public class AdvantimationsConfig {
    public static final ConfigClassHandler<AdvantimationsConfig> HANDLER = ConfigClassHandler.createBuilder(AdvantimationsConfig.class)
        .id(ResourceLocation.fromNamespaceAndPath("advantimations", "advantimations-config"))
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

    private static final Consumer<EntityOption.Configuration> ITEM_MODEL_CONFIGURATOR = EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR.andThen(EntityOption.Configuration::collapsed);

    @SerialEntry
    public EntityOption cancelSwings = new EntityOption()
        .enabled()
        .enabledInFirstPerson()
        .enabledOnSelf();

    @SerialEntry
    public SimpleFirstPersonOption cancelEatingAnimation = SimpleFirstPersonOption.falseOption();

    @SerialEntry
    public SimpleFirstPersonOption cancelDrinkingAnimation = SimpleFirstPersonOption.falseOption();

    @SerialEntry
    public EntityOption cancelBlockingAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelShieldAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelBowAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelCrossbowAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelChargedCrossbowAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelSpyglassAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelHornTootAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelSpearAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelRiptideAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelBrushingAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelBundleAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelBowArrowModel = new EntityOption();

    @SerialEntry
    public EntityOption cancelCrossbowArrowModel = new EntityOption();

    @SerialEntry
    public SimpleFirstPersonOption cancelAttackCooldownResets = SimpleFirstPersonOption.trueOption();

    @SerialEntry
    public SimpleFirstPersonOption cancelBlockInteractResets = SimpleFirstPersonOption.trueOption();

    @SerialEntry
    public SimpleFirstPersonOption cancelItemInteractResets = SimpleFirstPersonOption.trueOption();

    @SerialEntry
    public SimpleFirstPersonOption cancelSlotSwappingResets = SimpleFirstPersonOption.falseOption();

    @SerialEntry
    public EntityOption cancelLimbMovements = new EntityOption();

    @SerialEntry
    public EntityOption weirderLimbMovements = new EntityOption();

    @SerialEntry
    public EntityOption cancelSneaking = new EntityOption();

    public static Screen getScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) -> {
            builder.title(Component.literal("Advantimations Config"));

            ConfigCategory.Builder itemModelCategoryBuilder = ConfigCategory.createBuilder()
                .name(Component.literal("Item Model"))
                .tooltip(Component.literal("Options for cancelling item models and animations."));

            itemModelCategoryBuilder
                .group(EntityOption.createGroup(
                    "Cancel Swings", "Cancel hand and item swing animations.", defaults.cancelSwings, config.cancelSwings,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .option(SimpleFirstPersonOption.createOption(
                    "Cancel Eating Animation", "Cancel the first-person eating animation of items with component \nconsumable{animation:'eat'}.", defaults.cancelEatingAnimation, config.cancelEatingAnimation
                ))
                .option(SimpleFirstPersonOption.createOption(
                    "Cancel Drinking Animation", "Cancel the first-person drinking animation of items with component \nconsumable{animation:'drink'}.", defaults.cancelDrinkingAnimation, config.cancelDrinkingAnimation
                ))
                .group(EntityOption.createGroup(
                    "Cancel Blocking Animation", "Cancel the item blocking animation of items with component \nconsumable{animation:'block'} \n(except shields).", defaults.cancelBlockingAnimation, config.cancelBlockingAnimation,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Shield Animation", "Cancel the shield blocking animation.", defaults.cancelShieldAnimation, config.cancelShieldAnimation,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Bow Animation", "Cancel the bow drawing animation of items with component \nconsumable{animation:'bow'}.", defaults.cancelBowAnimation, config.cancelBowAnimation,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Crossbow Animation", "Cancel the crossbow drawing animation of items with component \nconsumable{animation:'crossbow'}.", defaults.cancelCrossbowAnimation, config.cancelCrossbowAnimation,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Charged Crossbow Animation", "Cancel the charged crossbow model.", defaults.cancelChargedCrossbowAnimation, config.cancelChargedCrossbowAnimation,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Spyglass Animation", "Cancel the spyglass animation of items with component \nconsumable{animation:'spyglass'}.", defaults.cancelSpyglassAnimation, config.cancelSpyglassAnimation,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Horn Toot Animation", "Cancel the horn tooting animation of items with component \nconsumable{animation:'toot_horn'}, \nmainly the goat horn.", defaults.cancelHornTootAnimation, config.cancelHornTootAnimation,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Spear Animation", "Cancel the spear drawing animation of items with component \nconsumable{animation:'spear'}, \nmainly the trident.", defaults.cancelSpearAnimation, config.cancelSpearAnimation,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Riptide Animation", "Cancel the trident riptide animation.", defaults.cancelRiptideAnimation, config.cancelRiptideAnimation,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Brushing Animation", "Cancel the brushing animation.", defaults.cancelBrushingAnimation, config.cancelBrushingAnimation,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Bundle Animation", "Cancel the bundle use swing animation.", defaults.cancelBundleAnimation, config.cancelBundleAnimation,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Bow Arrow Model", "Cancel the arrow in a bow being rendered.", defaults.cancelBowArrowModel, config.cancelBowArrowModel,
                    ITEM_MODEL_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Crossbow Arrow Model", "Cancel the arrow in a crossbow being rendered.", defaults.cancelCrossbowArrowModel, config.cancelCrossbowArrowModel,
                    ITEM_MODEL_CONFIGURATOR
                ));

                OptionGroup.Builder itemResetsGroupBuilder = OptionGroup.createBuilder()
                    .name(Component.literal("Item Resets"))
                    .description(OptionDescription.of(Component.literal("Options for cancelling the item reset animation.")))
                    .option(SimpleFirstPersonOption.createOption(
                        "Cancel Attack Cooldown Resets", "Cancel the item reset animation when attacking or swapping items with cooldowns, such as in combat.", defaults.cancelAttackCooldownResets, config.cancelAttackCooldownResets
                    ))
                    .option(SimpleFirstPersonOption.createOption(
                        "Cancel Block Interact Resets", "Cancel the item reset animation when interacting with a block such as a cake.", defaults.cancelBlockInteractResets, config.cancelBlockInteractResets
                    ))
                    .option(SimpleFirstPersonOption.createOption(
                        "Cancel Item Interact Resets", "Cancel the item reset animation when interacting with an item such as a fishing rod.", defaults.cancelItemInteractResets, config.cancelItemInteractResets
                    ))
                    .option(SimpleFirstPersonOption.createOption(
                        "Cancel Slot Swapping Resets", "Cancel the item reset animation when swapping items.", defaults.cancelSlotSwappingResets, config.cancelSlotSwappingResets
                    ));
                itemModelCategoryBuilder.group(itemResetsGroupBuilder.build());

                builder.category(itemModelCategoryBuilder.build());

                ConfigCategory.Builder entityModelCategoryBuilder = ConfigCategory.createBuilder()
                    .name(Component.literal("Entity Model"))
                    .tooltip(Component.literal("Options for cancelling entity model animations."));

                entityModelCategoryBuilder
                    .group(EntityOption.createGroup(
                        "Cancel Limb Movements", "Cancel entity limb movements.", defaults.cancelLimbMovements, config.cancelLimbMovements,
                        EntityOption.Configuration.THIRD_PERSON_OPTION_CONFIGURATOR
                    ))
                    .group(EntityOption.createGroup(
                        "Weirder Limb Movements", "Cancel entity limbs from moving after they already started (or are in the \"top\" of their movement).", defaults.weirderLimbMovements, config.weirderLimbMovements,
                        EntityOption.Configuration.THIRD_PERSON_OPTION_CONFIGURATOR
                    ))
                    .group(EntityOption.createGroup(
                        "Cancel Sneaking", "Cancel the sneaking pose.", defaults.cancelSneaking, config.cancelSneaking,
                        (configuration) -> configuration.canBeEnabledInFirstPerson().canBeEnabledOnSelf().canBeEnabledOnOtherPlayers()
                    ));

                builder.category(entityModelCategoryBuilder.build());
            return builder;
         }
        ).generateScreen(parent);
    }
}