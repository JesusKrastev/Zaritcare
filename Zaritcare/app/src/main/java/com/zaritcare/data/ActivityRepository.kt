package com.zaritcare.data

import com.zaritcare.data.room.activity.ActivityDao
import com.zaritcare.data.room.activity.ActivityEntity
import com.zaritcare.models.Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActivityRepository @Inject constructor(
    private val dao: ActivityDao
) {
    suspend fun insert(activity: Activity) = withContext(Dispatchers.IO) {
        dao.insert(activity.toActivityEntity())
    }
    suspend fun update(activity: Activity) = withContext(Dispatchers.IO) {
        dao.update(activity.toActivityEntity())
    }
    suspend fun count() = withContext(Dispatchers.IO) {
        dao.count()
    }
    suspend fun delete(activity: Activity) = withContext(Dispatchers.IO) {
        dao.delete(activity.toActivityEntity())
    }
    suspend fun get() = withContext(Dispatchers.IO) {
        dao.get().map { it.toActivity() }
    }
    suspend fun get(id: Int) = withContext(Dispatchers.IO) {
        dao.get(id)?.toActivity()
    }
}