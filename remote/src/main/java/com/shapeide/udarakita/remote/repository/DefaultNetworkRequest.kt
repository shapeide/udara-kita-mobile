package com.shapeide.udarakita.remote.repository

import com.shapeide.udarakita.remote.domain.base.ApiEndpoint
import com.shapeide.udarakita.remote.domain.base.NetworkRequest
import javax.inject.Inject

class DefaultNetworkRequest @Inject constructor(
    apiEndpoint: ApiEndpoint
): NetworkRequest {

}