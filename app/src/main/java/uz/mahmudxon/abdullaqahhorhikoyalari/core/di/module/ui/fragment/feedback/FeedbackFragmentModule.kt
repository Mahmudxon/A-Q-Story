package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.feedback

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail
import dagger.Module
import dagger.Provides
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.feefback.FeedbackPresenterImpl
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.feefback.IFeedback
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.feedback.FeedbackFragment

@Module
class FeedbackFragmentModule {

    @Provides
    fun provideView(f: FeedbackFragment): IFeedback.IView = f

    @Provides
    fun providePresenter(p: FeedbackPresenterImpl): IFeedback.IPresenter = p

    @Provides
    fun provideEmailBuilder(f: FeedbackFragment): BackgroundMail.Builder =
        BackgroundMail.newBuilder(f.requireContext()).withUsername("noreply.mahmudxon@gmail.com")
            .withPassword("07021998yil")
}