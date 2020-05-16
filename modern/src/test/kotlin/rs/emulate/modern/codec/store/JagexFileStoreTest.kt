package rs.emulate.modern.codec.store

import com.google.common.io.MoreFiles
import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import rs.emulate.modern.ModernCache
import java.nio.file.Paths

class JagexFileStoreTest {

    @Test
    fun `open no data file`() {
        Jimfs.newFileSystem(Configuration.unix()).use { fs ->
            assertThrows(IllegalArgumentException::class.java) {
                JagexFileStore.open(fs.getPath("/"))
            }
        }
    }

    @Test
    fun `open non-existent dir`() {
        Jimfs.newFileSystem(Configuration.unix()).use { fs ->
            assertThrows(IllegalArgumentException::class.java) {
                JagexFileStore.open(fs.getPath("/non-existent"))
            }
        }
    }

    @Test
    fun `open file`() {
        Jimfs.newFileSystem(Configuration.unix()).use { fs ->
            MoreFiles.touch(fs.getPath("/file"))

            assertThrows(IllegalArgumentException::class.java) {
                JagexFileStore.open(fs.getPath("/file"))
            }
        }
    }


    @Test
    fun `opens cache and reference table`() {
        val fs = JagexFileStore.open(
            Paths.get("/home/gtierney/workspace/github/apollo-rsps/vicis/data/resources/181"),
            FileStoreOption.Lenient
        )
        val cache = ModernCache.open(fs)
    }

    // TODO add read/write/etc. tests
}
