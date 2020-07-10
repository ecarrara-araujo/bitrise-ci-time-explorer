package br.com.ecarrara.core.config

import com.natpryce.konfig.*
import java.io.File

private val BITRISE_BASE_URL_CONFIG_KEY = Key("bitrise.base_url", stringType)
private val BITRISE_APP_SLUG_CONFIG_KEY = Key("bitrise.app_slug", stringType)
private val BITRISE_API_KEY_CONFIG_KEY = Key("bitrise.api_key", stringType)

internal val config = ConfigurationProperties
    .systemProperties() overriding
        EnvironmentVariables() overriding
        ConfigurationProperties.fromFile(File("bitrise-api-explorer.properties"))

internal val BITRISE_BASE_URL = config[BITRISE_BASE_URL_CONFIG_KEY]
internal val BITRISE_APP_SLUG = config[BITRISE_APP_SLUG_CONFIG_KEY]
internal val BITRISE_API_KEY = config[BITRISE_API_KEY_CONFIG_KEY]
