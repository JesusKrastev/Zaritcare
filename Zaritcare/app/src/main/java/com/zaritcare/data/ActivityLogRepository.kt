package com.zaritcare.data

import com.zaritcare.data.room.activitylog.ActivityLogDao
import com.zaritcare.models.ActivityLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

class ActivityLogRepository @Inject constructor(
    private val dao: ActivityLogDao
) {
    suspend fun insert(activityLog: ActivityLog) = withContext(Dispatchers.IO) {
        dao.insert(activityLog.toActivityLogEntity())
    }

    suspend fun update(activityLog: ActivityLog) = withContext(Dispatchers.IO) {
        dao.update(activityLog.toActivityLogEntity())
    }

    suspend fun count() = withContext(Dispatchers.IO) {
        dao.count()
    }

    suspend fun delete(activityLog: ActivityLog) = withContext(Dispatchers.IO) {
        dao.delete(activityLog.toActivityLogEntity())
    }

    suspend fun get(): List<ActivityLog> = withContext(Dispatchers.IO) {
        dao.get().map { it.toActivityLog() }
    }

    suspend fun get(id: Int) = withContext(Dispatchers.IO) {
        dao.get(id)?.toActivityLog()
    }

    fun get(date: LocalDate, user: Int): Flow<List<ActivityLog>> = dao.get(date, user).map { it.map { it.toActivityLog() } }
}