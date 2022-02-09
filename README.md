# Smart Client
**SmartClient** is a library that contains the default implementations to idenify a client.

## How to use?
It's easy to setup and use. Just call the ```setup``` method of ```SmartClient``` in ```onCreate``` method of your application class.

```KOTLIN
class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SmartClient.setup(this)
    }
}
```
Smart Client provides the following information.
```KOTLIN
val clientId: String // An identifier for the client
val sessionId: String // An identifier for the current client session.
val isEmulator: Boolean // Provides if the device is an emulator or not
val isRooted: Boolean // Provides if the devices is rooted or not
val time: Long // Device current time in milis
val lang: String // Device language
val type: Device.Type // Type of the Device: Phone or tablet
val osVersion: Int // Device's os version.
val brand: String // Brand of the device
val model: String // Model of the device
```

or you can get all these device specific information (and with extras) in a mutable map with calling ```generateHeaders()``` of the **SmartClient**.

## How to customize?
**SmartClient** allows you to customize some of the default implementations.

### Customizing Device Property Implementations
By default for device specific implementations, **SmartClient** uses it's own ```Device``` interface implementation ```CurrentDevice```. 

```KOTLIN
interface Device {
    val isDeviceEmulator: Boolean
    val isDeviceRooted: Boolean
    val deviceTime: Long
    val deviceLang: String
    val osVersion: Int
    val brand: String
    val model: String

    val rootChecker: RootChecker

    fun provideDeviceType(): Type

    enum class Type {
        PHONE, TABLET
    }
}
```

To customize the device specific implementations like root checking, finding the type of device or checking if it's emulator or not vs. you can implement ```Device``` interface by your own and pass it to the ```setup``` method of **SmartClient**

Please visit the ```util``` package to check the implementations of **root checking** and **emulator checking**. Please note that, these implementaions may have the improvement areas or may need a totally different approach to give the healty results for all type of devies or os versions. __Contributions are always welcome!__

### Customizing Local Data implementations
By default for saving and retrieving data from local, **SmartClient** uses it's own ```LocalDataSource``` implementation ```DefaultLocalDataSource```

```KOTLIN
/*
 * LocalDataSource
 * Contains the local data operation definitions.
 */
interface LocalDataSource {
    // Returns the client id
    fun getClientId(): String?

    // Saves the generated client id
    fun saveClientId(clientId: String)
}
```

To customize saving and retrieving client id logic you can pass your own ```LocalDataSource``` implementation to the ```setup``` method of **SmartClient**

### Customizing Id Generations
By default for generating the Id's (client id and session id) **SmartClient** uses it's own default implementations.

```KOTLIN
typealias ClientIdGenerator = () -> String
typealias SessionIdGenerator = () -> String

val defaultClientIdGenerator: ClientIdGenerator = {
    UUID.randomUUID().toString()
}

val defaultSessionIdGenerator: SessionIdGenerator = {
    UUID.randomUUID().toString()
}
```

For changing the id creationg logic, you can pass your own ```ClientIdGenerator``` or ```SessionIdGenerator``` implementations to the ```setup``` method of **SmartClient**

### Cutomizing Headers
**SmartClient** provides the device information (with some extra information) as ```Mutable Map``` with ```generateHeaders()``` method for adding these information to the api request headrs easily.

By default **SmartClient** uses it's own key map for headsrs while generating the map.

```KOTLIN
open class DefaultHeaderKeys(
    override val contentTypeKey: String = "Content-Type",
    override val acceptKey: String = "Accept",
    override val deviceLocaleKey: String = "x-device-locale",
    override val deviceBrandKey: String = "x-device-brand",
    override val deviceModelKey: String = "x-device-model",
    override val deviceOsKey: String = "x-device-os",
    override val deviceOsTypeKey: String = "x-device-os-type",
    override val channelKey: String = "x-channel",
    override val deviceTypeKey: String = "x-device-type",
    override val isEmulatorKey: String = "x-app-is-emulator",
    override val isRootedKey: String = "x-is-rooted",
    override val timeStampKey: String = "x-timestamp",
    override val clientIdKey: String = "x-client-id",
    override val sessionIdKey: String = "x-session-id"
) : HeaderKeyMap
```
To change the keys or use a totally different keymap, you extend ```DefaultHeaderKeys``` class or implement  ```HeaderKeyMap``` and pass it to the ```setup``` method of **SmartClient**

### Dependency<br>
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  ```
  dependencies {
	        implementation 'com.github.savepopulation:smart-client:1.0.0'	
  }
  ```
  ### License
  ```
     Copyright 2022 Taylan SABIRCAN

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 ```


