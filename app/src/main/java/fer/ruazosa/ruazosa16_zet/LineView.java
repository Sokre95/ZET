package fer.ruazosa.ruazosa16_zet;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.ArrayList;
import java.util.List;

import fer.ruazosa.ruazosa16_zet.model.*;
import fer.ruazosa.ruazosa16_zet.model.Line;

public interface LineView extends MvpLceView<ArrayList<Line>> {
    void setData(ArrayList<Line> data);
}
