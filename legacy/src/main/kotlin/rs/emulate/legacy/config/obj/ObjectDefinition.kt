package rs.emulate.legacy.config.obj

class ObjectDefinition(
    val id: Int,
    var modelId: Int = 0,
    var name: String = "",
    var description: String = "",
    var spriteScale: Int = 2000,
    var spriteRoll: Int = 0,
    var spriteYaw: Int = 0,
    var spriteTranslateX: Int = 0,
    var spriteTranslateY: Int = 0,
    var stackable: Boolean = false,
    var value: Int = 1,
    var members: Boolean = false,
    var primaryMaleEquipmentId: Int = -1,
    var maleTranslateY: Int = 0,
    var secondaryMaleEquipmentId: Int = -1,
    var primaryFemaleEquipmentId: Int = -1,
    var femaleTranslateY: Int = 0,
    var secondaryFemaleEquipmentId: Int = -1,
    var groundActions: Array<String?> = arrayOfNulls(size = 5),
    var widgetActions: Array<String?> = arrayOfNulls(size = 5),
    var colours: MutableMap<Int, Int> = mutableMapOf(),
    var tertiaryMaleEquipmentId: Int = -1,
    var tertiaryFemaleEquipmentId: Int = -1,
    var primaryMaleHeadId: Int = -1,
    var primaryFemaleHeadId: Int = -1,
    var secondaryMaleHeadId: Int = -1,
    var secondaryFemaleHeadId: Int = -1,
    var spriteCameraPitch: Int = 0,
    var noteInfoId: Int = -1,
    var noteTemplateId: Int = -1,
    var stacks: Array<ObjectStack> = Array(size = 10) { ObjectStack.EMPTY },
    var groundScaleX: Int = 128,
    var groundScaleY: Int = 128,
    var groundScaleZ: Int = 128,
    var brightness: Int = 0,
    var diffusion: Int = 0,
    var team: Int = 0
)
