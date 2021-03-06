package capstone.boardgame.GUI;

import capstone.boardgame.GUI.Component.BGComponent;
import capstone.boardgame.main.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Kyle on 3/14/2016.
 */
public class BGContainer extends BGComponent {
    public BGContainer() { super(); }
    public BGContainer(int x, int y, int width, int height) { super(x, y, width, height); }

    protected ArrayList<BGComponent> drawables = new ArrayList<BGComponent>();

    public boolean add(BGComponent drawable) {
        if (drawable != null) {
            drawable.setParent(this);
            return drawables.add(drawable);
        }
        return false;
    }
    public boolean remove(BGComponent drawable) {
        return this.drawables.remove(drawable);
    }
    public boolean remove(String id){
        return remove(getViewByID(id));
    }
    public void clear() { drawables.clear(); }

    public BGComponent getViewByID(String id) {
        BGComponent found = null;
        for (BGComponent drawable : drawables) {
            //check this drawable
            if (drawable.getId().equals(id)) {
                found = drawable;
                break;
            }
            //check children drawables if container
            if (drawable instanceof BGContainer) {
                found = ((BGContainer)drawable).getViewByID(id);
                if (found != null) {
                    break;
                }
            }
        }

        return found;
    }

    public int size() {
        return drawables.size();
    }

    public BGComponent get(int index) {
        return drawables.get(index);
    }

    public BGComponent findViewWithFlag(String flag, Object value) {
        return findViewsWithFlag(flag, value).get(0);
    }

    public BGContainer findViewsWithFlag(String flag, Object value) {
        BGContainer found = new BGContainer();
        for (BGComponent drawable : drawables) {
            //check this drawable
            try {
                if (drawable.getFlag(flag).equals(value)) {
                    found.add(drawable);
                }
            } catch (Exception e) {
                //drawable doesn't have this flag
            }
            //check children drawables if container
            if (drawable instanceof BGContainer) {
                BGContainer innerFound = ((BGContainer)drawable).findViewsWithFlag(flag, value);
                for (int i = 0; i < innerFound.size(); i++) {
                    found.add(innerFound.get(i));
                }
            }
        }

        return found;
    }

    @Override
    public void renderComponent(Graphics2D g) {
        for (BGComponent drawable : drawables) {
            drawable.render(g);
        }
    }

    @Override
    public void tick(double deltaTime) {
        for (BGComponent drawable : drawables) {
            drawable.tick(deltaTime);
        }
    }

    protected JSONObject convertJson() {
        JSONObject obj = new JSONObject();
        obj.put("type","container");

        JSONArray arr = new JSONArray();
        for (BGComponent drawable : drawables) {
            if (drawable.isVisible())
                arr.put(drawable.toJson());
        }
        obj.put("components", arr);

        return obj;
    }
}
