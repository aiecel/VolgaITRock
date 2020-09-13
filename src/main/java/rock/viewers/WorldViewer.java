package rock.viewers;

import rock.world.World;

public interface WorldViewer {
    void initView(World world);

    void updateView();

    void showMessage(String message);

    void showError(String message);
}
