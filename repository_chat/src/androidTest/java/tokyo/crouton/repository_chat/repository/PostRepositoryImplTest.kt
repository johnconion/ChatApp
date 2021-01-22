package tokyo.crouton.repository_chat.repository

import androidx.test.platform.app.InstrumentationRegistry
import io.realm.Realm
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tokyo.crouton.base.requireNotNull
import tokyo.crouton.datasource_realm.RealmPost
import tokyo.crouton.domain.chat.PostId
import java.util.Date

class PostRepositoryImplTest {

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Realm.init(context)
        Realm.getDefaultInstance().executeTransaction {
            it.deleteAll()
        }
    }

    @Test
    fun testAddPost() {
        val realm = Realm.getDefaultInstance()
        val repository = PostRepositoryImpl(realm)
        val date = Date()

        assertEquals(0, realm.where(RealmPost::class.java).count())

        repository.addPost("TEST", date, true)

        val results = realm.where(RealmPost::class.java).findAll()

        assertEquals(1, results.count())

        results.first().requireNotNull().let {
            assertEquals("TEST", it.message)
            assertEquals(true, it.isMe)
            assertEquals(date, it.sentAt)
            assertEquals(false, it.isRemoved)
        }

        realm.close()
    }

    @Test
    fun testRemovePost() {
        val realm = Realm.getDefaultInstance()
        val repository = PostRepositoryImpl(realm)
        val date = Date()

        assertEquals(0, realm.where(RealmPost::class.java).count())

        repository.addPost("TEST", date, true)

        val results = realm.where(RealmPost::class.java).findAll()

        assertEquals(1, results.count())

        repository.removePost(PostId(0))

        assertEquals(1, results.count())

        results.first().requireNotNull().let {
            assertEquals("TEST", it.message)
            assertEquals(true, it.isMe)
            assertEquals(date, it.sentAt)
            assertEquals(true, it.isRemoved)
        }

        realm.close()
    }

    @Test
    fun testRemoveAll() {
        val realm = Realm.getDefaultInstance()
        val repository = PostRepositoryImpl(realm)
        val date = Date()

        assertEquals(0, realm.where(RealmPost::class.java).count())

        repository.addPost("TEST", date, true)

        val results = realm.where(RealmPost::class.java).findAll()

        assertEquals(1, results.count())

        repository.removeAll()

        assertEquals(0, results.count())

        realm.close()
    }
}