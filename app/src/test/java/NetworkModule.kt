@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor { apiKeyAsQuery(it) }
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .addInterceptor { apiKeyAsQuery(it) }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.openweathermap.org/")
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideWeatherDetailsApi(retrofit: Retrofit): WeatherDetailsApi =
        retrofit.create(WeatherDetailsApi::class.java)
}