# AndroidOffilineLibrar

AndroidOffilineLibrar is a library to store json data in file at private location of project and later on parse it again when there is no network available 



:information_source: AndroidOffilineLibrar is currently in maintenance mode; existing bugs will be fixed, but large new features will likely not be added. If you want to add a new feature, please first search for existing GitHub issues, or create a new one to discuss the feature and get feedback.

### Goals
* Provide convenient way for offline storage 
* also deal with showing and hiding retrofit response for DEBUG or RELEASE app  
* Extensive support of Java Generics
* Allow custom representations for objects
* Support arbitrarily complex objects (with deep inheritance hierarchies and extensive use of generic types)

###Example 
With Api Response:
```
 override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String?,
                    throwable: Throwable?
                ) {

                    // Get response and save it with AndroidOffilineLibrar
                    val dataFromFile = OfflineCacheRetrofit.getInstance(
                        //Name of cache file later will access with same Name e.g UserSession.HOME_RESPONSE_FILE_NAME,
                        //Class Ref for parse gson e.g FullHomeContent::class.java
                    ).tryToGetDataClasses()

                }

                override fun onSuccess(statusCode: Int, headers: Headers?, response: String?) {
                   // When Got  response Fails  retrive it with AndroidOffilineLibrar
                        OfflineCacheRetrofit.getInstance(
                                //Name of cache file later will access with same Name e.g UserSession.HOME_RESPONSE_FILE_NAME,
                        //Class Ref for parse gson e.g FullHomeContent::class.java
                        ).writeToFile(newRespon)
                
                }
```
### Download

Gradle:
```
maven { url 'https://jitpack.io' }

gradle
dependencies {
  implementation 'com.github.SmartAppsDevelopment:AndroidOffilineLibrar:1.0.0'
}
```

Maven:
```xml
<dependency>
  <groupId>com.github.SmartAppsDevelopment</groupId>
  <artifactId>AndroidOffilineLibrar</artifactId>
  <version>1.0.0</version>
</dependency>
```