package com.minitiktok.android

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.minitiktok.android.extra.funs.movieDecode
import com.minitiktok.android.logic.model.MovieEntity
import com.minitiktok.android.logic.model.toEntity
import com.minitiktok.android.logic.network.MovieNetWork
import com.minitiktok.android.logic.network.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.minitiktok.android", appContext.packageName)
    }

    @Test
    fun getData() {
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val format = sdf.format(1660727851652)
        format.toString()
    }

    @Test
    fun testNetWorkVersion() {
        val token = "clt.e2169518f9fc9ee375579eb7ba0951d9rBqi2qpfnS4MpMAsHtgtST2zKzeI"
        runBlocking {
            val versionResp =
                withContext(Dispatchers.Default) {
                    MovieNetWork.getTvVersions(token, 10)
                }
            val respDate = versionResp.respDate
            val versions = versionResp.respDate.versions
            Repository.insertVersions(
                versions!!,
                versionResp.extraRespData.now,
                10,
                versionResp.respDate.cursor!!
            )
        }
    }

    @Test
    fun testQueryVersions() {
        val versions = Repository.getAllVersions()
        for (version in versions) {
            version.active_time
        }
    }

    @Test
    fun test_query_getMovies_byNet() {
        val versions = Repository.getAllVersions()
        val token = "clt.e2169518f9fc9ee375579eb7ba0951d9rBqi2qpfnS4MpMAsHtgtST2zKzeI"
        runBlocking {
            val version = versions[0].version
            val mvResp =
                withContext(Dispatchers.IO) {
                    MovieNetWork.refreshTvMovies(
                        token,
                        version
                    )
                }
            val movieList = mutableListOf<MovieEntity>()
            val movies = mvResp.respDate.movies!!
            for (movie in movies) {
                movieList.add(movie.toEntity(version))
            }
            Repository.insertMovies(movieList)
        }
    }

    @Test
    fun test_query_all_movies() {
        val movies = Repository.getAllMovies()
        for (movie in movies) {
            movie.actors?.movieDecode(" ")
        }
    }

    @Test
    fun refresh_Client_Token() {
        Repository.clearTokens()
        runBlocking {
            TikTokApplication.refreshClientToken()
            val clientToken = TikTokApplication.client_token
            val accessToken = clientToken?.access_token
        }
    }
}