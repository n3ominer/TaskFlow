package fr.esgi.taskflow.data.repository

import org.junit.Assert.assertEquals
import org.junit.Test

class InMemoryTaskRepositoryTest {
    @Test
    fun createAndMarkImportant() {
        val repository = InMemoryTaskRepository()
        repository.addTask("  Réviser  ", "  Compose  ", important = false)
        val created = repository.tasks.value.last()

        assertEquals("Réviser", created.title)
        assertEquals("Compose", created.description)
        assertEquals(4, created.id)

        repository.setImportant(created.id, true)
        assertEquals(true, repository.tasks.value.last().important)
    }

    @Test
    fun blankTitleDoesNotCreateTask() {
        val repository = InMemoryTaskRepository()
        repository.addTask("   ", "Texte", important = false)

        assertEquals(3, repository.tasks.value.size)
    }

    @Test
    fun completeOnlySelectedTask() {
        val repository = InMemoryTaskRepository()
        repository.setCompleted(2, true)

        assertEquals(false, repository.tasks.value.first { it.id == 1 }.completed)
        assertEquals(true, repository.tasks.value.first { it.id == 2 }.completed)
    }
}
