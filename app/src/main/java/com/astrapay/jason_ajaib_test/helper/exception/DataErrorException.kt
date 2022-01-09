package com.astrapay.jason_ajaib_test.helper.exception

import java.io.IOException

class DataErrorException constructor(override val message: String): IOException(message) {
}