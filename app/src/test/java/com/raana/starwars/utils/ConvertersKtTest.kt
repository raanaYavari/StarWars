package com.raana.starwars.utils

import com.raana.starwars.ui.mock.MockDatabase
import org.junit.Test
import org.junit.Assert


class ConvertersKtTest{

    @Test
    fun cmHeightShouldReturnFeet(){
        val heightInFeet = cmToFeetConverter(MockDatabase.height.cm)
        Assert.assertEquals(heightInFeet, MockDatabase.height.feet)
    }

    @Test
    fun populationShouldReturnBeReformat(){
        val population = populationConverter(MockDatabase.population.populationUnformated)
        Assert.assertEquals(population, MockDatabase.population.populationUnformated)
    }
    @Test
    fun unknownPopulationShouldReturnUnknown(){
        val population = populationConverter(MockDatabase.population.populationUnformated)
        Assert.assertEquals(population, MockDatabase.population.populationUnformated)
    }
}