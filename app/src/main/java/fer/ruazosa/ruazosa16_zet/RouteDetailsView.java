package fer.ruazosa.ruazosa16_zet;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.ArrayList;

public interface RouteDetailsView extends MvpLceView<ArrayList<String>> {
    void setData(ArrayList<String> data);
}
