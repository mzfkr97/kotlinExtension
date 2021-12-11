
fun checkTheme(sharedPreferences: SharedPreferences) {
    val themeName = sharedPreferences.getString(
        Constants.THEME_KEY,
        Constants.THEME_DEFAULT_FOLLOW_SYSTEM
    )
    if (BuildConfig.DEBUG && themeName == null) {
        error("Assertion failed")
    }
    applyTheme(themeName)
}

fun applyTheme(themeName: String?) {
    when (themeName) {
        Constants.THEME_LIGHT -> {
            setDefaultNightMode(MODE_NIGHT_NO)
        }
        Constants.THEME_DARK -> {
            setDefaultNightMode(MODE_NIGHT_YES)
        }
        Constants.THEME_DEFAULT_FOLLOW_SYSTEM -> {
            when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                true -> setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
                false -> setDefaultNightMode(MODE_NIGHT_AUTO_BATTERY)
            }
        }
    }
}

    const val THEME_KEY = "list_theme"
    const val THEME_LIGHT = "Theme_Light"
    const val THEME_DARK = "Theme_Night"
    const val THEME_DEFAULT_FOLLOW_SYSTEM = "Theme_Follow_System"
