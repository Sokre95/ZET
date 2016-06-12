package fer.ruazosa.ruazosa16_zet;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

public interface LineView extends MvpLceView<List<fer.ruazosa.ruazosa16_zet.model.Line>> {
    void setData(List<fer.ruazosa.ruazosa16_zet.model.Line> data);
}
