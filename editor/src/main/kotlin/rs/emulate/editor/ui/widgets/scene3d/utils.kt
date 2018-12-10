package rs.emulate.editor.ui.widgets.scene3d

import org.joml.Vector3f
import rs.emulate.legacy.model.Model
import rs.emulate.legacy.model.Vertex
import rs.emulate.scene3d.Mesh
import java.awt.Color

fun Vertex.toVec3f() = Vector3f(x * 0.01f, -y * 0.01f, z * 0.01f)

fun meshFromModels(models: List<Model>): Mesh {
    val positions = mutableListOf<Vector3f>()
    val colors = mutableListOf<Vector3f>()

    models.forEach { model ->
        val faces = model.faces.sortedBy { it.renderPriority }

        positions += faces.flatMap {
            listOf(
                model.vertices[it.a].toVec3f(),
                model.vertices[it.b].toVec3f(),
                model.vertices[it.c].toVec3f()
            )
        }

        colors += faces
            .flatMap { listOf(it.colour, it.colour, it.colour) }
            .map {
                val colour = it
                val h = (colour shr 10)
                val s = (colour shr 7) and 7
                val l = colour and 0x7F

                val color = Color.getHSBColor(h / 60.0f, s / 8.0f, l / 128.0f)

                Vector3f(color.red / 256.0f, color.green / 256.0f, color.blue / 256.0f)
            }
    }

    val mesh = Mesh()
    mesh.positions = positions
    mesh.colors = colors

    return mesh
}

