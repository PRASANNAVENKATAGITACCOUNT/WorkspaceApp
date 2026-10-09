package com.learning.features.di

import androidx.room.Room
import com.learning.features.data.Repository
import com.learning.features.data.local.AppDatabase
import com.learning.features.data.local.LocalRepository
import com.learning.features.presentation.notes.NotesViewModel
import com.learning.features.presentation.projects.MainProjectViewModel
import com.learning.features.presentation.todo.ToDoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moreFeatureDataSourceModule = module {

    single<AppDatabase>() {
        Room.databaseBuilder(
                context = androidContext(),
                name = "app_db",
                klass = AppDatabase::class.java
            ).fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    single<Repository> {
        LocalRepository(get())
    }

    viewModel<MainProjectViewModel>{
        MainProjectViewModel(get())
    }

    viewModel<ToDoViewModel>{
        ToDoViewModel(get())
    }

    viewModel<NotesViewModel> {
        NotesViewModel(get())
    }

}
