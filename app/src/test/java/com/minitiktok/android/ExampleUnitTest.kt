package com.minitiktok.android

import com.minitiktok.android.extra.funs.movieDecode
import com.minitiktok.android.extra.funs.movieFormat
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testMovieFormat() {
        //assertEquals(4, 2 + 2)
        val list = listOf("波多野结衣", "苍井空", "葵司")
        val movieFormat = list.movieFormat(" ")
        println(movieFormat);
        println(movieFormat.movieDecode(" ")[1])
    }
}