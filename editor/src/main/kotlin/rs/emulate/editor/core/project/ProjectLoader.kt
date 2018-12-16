package rs.emulate.editor.core.project

import rs.emulate.editor.core.project.storage.ProjectStorage
import rs.emulate.editor.core.project.tasks.LoadProjectIndexTaskFactory
import rs.emulate.editor.core.task.TaskRunner
import rs.emulate.editor.vfs.LegacyFileLoader
import rs.emulate.editor.vfs.index.LegacyFileIndexer
import rs.emulate.legacy.AccessMode
import rs.emulate.legacy.IndexedFileSystem
import java.nio.file.Files
import java.nio.file.Path
import javax.inject.Inject

//}

class ProjectLoader @Inject constructor(val taskRunner: TaskRunner, val loadTask: LoadProjectIndexTaskFactory) {
    suspend fun loadProject(name: String, vfsPath: Path, ty: ProjectType): Project {
        val vfsRoot = if (!Files.isDirectory(vfsPath)) vfsPath.parent else vfsPath
        val storage = ProjectStorage.resolve(vfsRoot)

        val (loader, indexer) = when (ty) {
            ProjectType.Legacy -> {
                val cache = IndexedFileSystem(vfsRoot, AccessMode.READ_WRITE)
                val loader = LegacyFileLoader(cache)
                val indexer = LegacyFileIndexer(cache, ProjectMetadataStore())

                loader to indexer
            }
            ProjectType.Modern -> {
                TODO("Unsupported")
            }
        }

        val index = taskRunner.run(loadTask.create(storage, indexer))

        storage.load(ProjectMetadata.serializer()).apply {
            this.name = name
            this.type = ty
        }
        storage.save()

        return Project(name, loader, index)
    }
}
