# Bitrise CI Time Explorer

Set of tools to help extraction and analysis of build data in Bitrise.

## Application Setup

In order to be able to fetch build information from bitrise there are a few things that need to be setup.
The configuration is done in a root project file called `bitrise-api-explorer.properties` or by setting the properties as environment variables.

The properties are:
* `bitrise.base_url` - the bitrise api base url that can be get from the documentation.
* `bitrise.app_slug` - identifier that bitrise uses to link to your app, can be get in the app configuration page.
* `bitrise.api_key` - api key used for authenticate the calls, can be generated in bitrise accounts settings page. 

