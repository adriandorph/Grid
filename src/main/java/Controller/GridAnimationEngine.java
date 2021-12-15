package Controller;

import Model.GridAnimation;
import View.Grid;
import View.RenderGrid;

public class GridAnimationEngine extends Engine<RenderGrid>{

    public GridAnimationEngine(Grid grid, GridAnimation gridAnimation, int FPS) {
        super(grid, gridAnimation, FPS);
    }

    @Override
    protected void stopped() {

    }

    @Override
    protected boolean stopCondition() {
        return false;
    }
}
