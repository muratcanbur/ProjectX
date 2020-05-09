object Configs {
    const val applicationId = "co.icanteach.projectx"
    const val compileSdkVersion = 29
    const val minSdkVersion = 21
    const val targetSdkVersion = 29
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    const val versionCode = versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100
    const val versionName = "${versionMajor}.${versionMinor}.${versionPatch}"
}