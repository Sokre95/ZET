package fer.ruazosa.ruazosa16_zet;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import fer.ruazosa.ruazosa16_zet.wrappers.Line;

public interface LineView extends MvpLceView<List<fer.ruazosa.ruazosa16_zet.service.Line>> {
    void setData(List<fer.ruazosa.ruazosa16_zet.service.Line> data);
}
