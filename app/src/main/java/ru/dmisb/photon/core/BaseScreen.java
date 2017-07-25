package ru.dmisb.photon.core;

import flow.ClassKey;
import ru.dmisb.photon.flow.Screen;
import ru.dmisb.photon.flow.ScreenScoper;

public abstract class BaseScreen<T> extends ClassKey {

    public abstract String getScopeName();

    public abstract String getParentScopeName();

    public abstract Object createScreenComponent(T parentComponent);

    public void unregisterScope() {
        ScreenScoper.destroyScreenScope(getScopeName());
    }

    public int getLayoutResId() {
        int layout;
        Screen screen = this.getClass().getAnnotation(Screen.class);
        if (screen == null) {
            throw new IllegalStateException("Блин, опять забыл анатацию @Screen для " + this.getScopeName());
        } else {
            layout = screen.value();
        }

        return layout;
    }
}
