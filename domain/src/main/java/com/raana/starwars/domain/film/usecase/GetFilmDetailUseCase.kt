package com.raana.starwars.domain.film.usecase

import com.raana.starwars.domain.base.BaseUseCase
import com.raana.starwars.domain.film.FilmRepository
import com.raana.starwars.domain.film.result.FilmDetail
import javax.inject.Inject
/**
 * Get FilmDetail use case
 *
 * @property repository FilmRepository to connect to Data Layer and get response from Data Sources
 */
class GetFilmDetailUseCase @Inject constructor(private val repository: FilmRepository) :
    BaseUseCase<List<String>, Result<List<FilmDetail>>>() {
    override suspend fun invoke(param: List<String>): Result<List<FilmDetail>> =
        repository.getFilmDetail(param)

}