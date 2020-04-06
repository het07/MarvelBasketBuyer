package apch.marvelbasket.ui.shopping;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShoppingViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ShoppingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Shopping fragment..");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
