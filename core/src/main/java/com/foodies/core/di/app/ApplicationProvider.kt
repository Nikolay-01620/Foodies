package com.foodies.core.di.app

import com.foodies.core.di.network.NetworkProvider

interface ApplicationProvider:
    AndroidDependenciesProvider,
    NetworkProvider