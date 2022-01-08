package com.astrapay.jason_ajaib_test.helper.exception

import java.io.IOException

class UnAuthorizedException constructor(override val message: String): IOException(message) {
}