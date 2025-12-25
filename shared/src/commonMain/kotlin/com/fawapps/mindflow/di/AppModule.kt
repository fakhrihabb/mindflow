package com.fawapps.mindflow.di

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import com.fawapps.mindflow.BuildKonfig

val appModule = module {
    single {
        createSupabaseClient(
            supabaseUrl = BuildKonfig.SUPABASE_URL, 
            supabaseKey = BuildKonfig.SUPABASE_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Storage)
        }
    }
}
