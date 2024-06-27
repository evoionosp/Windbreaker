import org.gradle.api.JavaVersion

object AppConfig {

    const val applicationId = "com.evoionosp.windbreaker"
    const val compileSdk = 34
    const val minSdk = 24
    const val targetSdk = 34
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val buildToolsVersion = "34.0.0"

    val sourceCompatibility = JavaVersion.VERSION_17
    val targetCompatibility = JavaVersion.VERSION_17
    const val jvmTarget = 17
    const val kotlinCompilerExtensionVersion = "1.5.1"


    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val proguardConsumerRules =  "consumer-rules.pro"
    const val dimension = "environment"
}