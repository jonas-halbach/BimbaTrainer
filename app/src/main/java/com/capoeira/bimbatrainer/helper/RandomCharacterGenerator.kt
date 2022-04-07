package com.capoeira.bimbatrainer.helper

import kotlin.random.Random

class RandomCharacterGenerator {

    private var possibleCharacters : Array<Char>

    constructor(possibleCharacters : Array<Char>) {
        this.possibleCharacters = possibleCharacters
    }

    fun getRandomChar() : Char {
         var randomCharIndex = Random.nextInt(0,possibleCharacters.size)
        return possibleCharacters[randomCharIndex]
    }
}