package tokyo.crouton.chatapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import tokyo.crouton.base.ActivityIntentResolver
import tokyo.crouton.chatapp.ActivityIntentResolverImpl

@Module
@InstallIn(ActivityComponent::class)
class SplashActivityModule {

    @Provides
    fun provideActivityIntentResolver(impl: ActivityIntentResolverImpl): ActivityIntentResolver =
        impl

}