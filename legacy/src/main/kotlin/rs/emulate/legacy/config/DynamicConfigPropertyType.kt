package rs.emulate.legacy.config

import java.util.Objects

/**
 * A [ConfigPropertyType] that can be created at any point during runtime (rather than during the linking process,
 * as part of an enumerated type). Useful when there are lots of similar properties that would result in a huge amount
 * of enumerators (such as original and replacement colours). This class is immutable.
 *
 * @param name The name of the DynamicPropertyType.
 * @param opcode The opcode of the DynamicPropertyType.
 */
data class DynamicConfigPropertyType(override val name: String, override val opcode: Int) : ConfigPropertyType {

    init {
        require(!name.isEmpty()) { "Name of a property cannot be empty." }
        require(opcode.toLong() > 0) { "Opcode must be positive." }
    }

    companion object {

        /**
         * The Map (used as a cache) of opcodes to DynamicPropertyTypes.
         */
        private val cache = mutableMapOf<Int, DynamicConfigPropertyType>()

        /**
         * Creates a DynamicPropertyType with the specified name and opcode, or returns a previously-created value
         * from the cache.
         *
         * @param name The name of the DynamicPropertyType. Must not be empty.
         * @param opcode The opcode of the DynamicPropertyType. Must be positive (i.e. `> 0`).
         */
        fun valueOf(name: String, opcode: Int): DynamicConfigPropertyType {
            require(opcode.toLong() > 0) { "Opcode must be positiv1e." }
            val hash = Objects.hash(name, opcode)

            return cache.computeIfAbsent(hash) { DynamicConfigPropertyType(name, opcode) }
        }
    }

}