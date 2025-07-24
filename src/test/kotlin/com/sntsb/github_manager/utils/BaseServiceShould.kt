package com.sntsb.github_manager.utils

import io.mockk.MockKAnnotations
import org.junit.jupiter.api.BeforeEach

open class BaseServiceShould {

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

}