package com.zaritcare.data

import com.zaritcare.data.room.song.SongDao
import com.zaritcare.models.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SongRepository @Inject constructor(
    private val dao: SongDao
) {
    suspend fun get(): List<Song> = withContext(Dispatchers.IO) {
        dao.get().map { it.toSong() }
    }

    suspend fun insert(song: Song) = withContext(Dispatchers.IO) {
        dao.insert(song.toSongEntity())
    }

    suspend fun get(id: Int): Song = withContext(Dispatchers.IO) {
        dao.get(id).toSong()
    }

    suspend fun count(): Int = withContext(Dispatchers.IO) {
        dao.count()
    }
}