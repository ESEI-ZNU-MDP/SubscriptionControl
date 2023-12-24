import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SubscriptionViewModel : ViewModel() {
    val subscriptions = MutableLiveData<MutableList<Subscription>>()

    init {
        subscriptions.value = mutableListOf()
    }
}
