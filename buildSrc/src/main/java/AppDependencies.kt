import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {

    private const val kotlinStandardLibrary =
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    private const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtx}"
    private const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"

    private const val jUnit = "junit:junit:${Versions.junit}"
    private const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"

    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val mockito = "org.mockito:mockito-android:${Versions.mockito}"

    val appLibraries = mutableListOf<String>().apply {
        add(kotlinStandardLibrary)
        add(coreKtx)
        add(appCompat)
        add(material)
        add(constraintLayout)
        add(viewModelKtx)
        add(coroutinesAndroid)
    }

    val testLibraries = mutableListOf<String>().apply {
        add(jUnit)
        add(mockitoInline)
    }

    val androidTestLibraries = mutableListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
        add(mockito)
    }
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach {
        add("kapt", it)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach {
        add("testImplementation", it)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach {
        add("androidTestImplementation", it)
    }
}
