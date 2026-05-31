package com.example.fortune_horoscope.data.remote.firebase

import com.example.fortune_horoscope.data.model.JournalEntry
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    fun observeUserJournalEntries(userId: Long): Flow<List<JournalEntry>>

    suspend fun addJournalEntry(entry: JournalEntry): Result<Unit>

    suspend fun deleteJournalEntry(entryId: Long): Result<Unit>
}