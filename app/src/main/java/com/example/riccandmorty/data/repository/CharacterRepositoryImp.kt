package com.example.riccandmorty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.riccandmorty.data.local.db.MortyDatabase
import com.example.riccandmorty.data.pagingsource.CharacterRemoteMediator
import com.example.riccandmorty.data.remote.MortyApi
import com.example.riccandmorty.data.remote.models.CharacterDto
import com.example.riccandmorty.domain.models.Character
import com.example.riccandmorty.domain.repository.CharacterRepository
import com.example.riccandmorty.util.Constants.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterRepositoryImp @Inject constructor(
   private val api: MortyApi,
   private val database: MortyDatabase
): CharacterRepository {
    private val dao = database.characterDao()

    override fun getCharacters(): Flow<PagingData<Character>> {
        val pagingSourceFactory = { dao.getCharacters() }
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            remoteMediator = CharacterRemoteMediator(
                api = api,
                database = database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun getSelectedCharacter(id: Int): CharacterDto {
        return dao.getSelectedCharacter(id)
    }
}