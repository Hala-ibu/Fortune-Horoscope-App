package com.example.fortune_horoscope.data.remote.firebase

import com.example.fortune_horoscope.data.model.JournalEntry
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirestoreRepositoryImpl(
    private val firestore: FirebaseFirestore
) : FirestoreRepository {

    private val collection = firestore.collection("journal_entries")

    override fun observeUserJournalEntries(userId: Long): Flow<List<JournalEntry>> = callbackFlow {
        val subscription = collection
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val entries = snapshot.documents.mapNotNull { doc ->
                        try {
                            JournalEntry(
                                id = doc.getLong("id") ?: 0L,
                                userId = doc.getLong("userId") ?: 0L,
                                title = doc.getString("title") ?: "",
                                body = doc.getString("body") ?: "",
                                createdAt = doc.getLong("createdAt") ?: 0L
                            )
                        } catch (e: Exception) {
                            null
                        }
                    }
                    trySend(entries)
                }
            }

        awaitClose { subscription.remove() }
    }

    override suspend fun addJournalEntry(entry: JournalEntry): Result<Unit> {
        return try {
            val documentId = entry.id.toString()

            val data = hashMapOf(
                "id" to entry.id,
                "userId" to entry.userId,
                "title" to entry.title,
                "body" to entry.body,
                "createdAt" to entry.createdAt
            )

            collection.document(documentId).set(data).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteJournalEntry(entryId: Long): Result<Unit> {
        return try {
            collection.document(entryId.toString()).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}