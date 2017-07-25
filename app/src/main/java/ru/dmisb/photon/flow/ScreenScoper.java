package ru.dmisb.photon.flow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mortar.MortarScope;
import ru.dmisb.photon.core.BaseScreen;

public class ScreenScoper {
    public static final String SCOPE_SERVICE_NAME = "SCOPE_SERVICE_NAME";

    public static final String APP_SCOPE_NAME = "APP_SCOPE";
    public static final String ROOT_SCOPE_NAME = "ROOT_SCOPE";

    public static final String MAIN_SCOPE_NAME = "MAIN_SCOPE";
    public static final String PHOTO_CARD_SCOPE_NAME = "PHOTO_CARD_SCOPE";
    public static final String PROFILE_SCOPE_NAME = "PROFILE_SCOPE";

    public static final String SELECTOR_SCOPE_NAME = "SELECTOR_SCOPE";
    public static final String SEARCH_SCOPE_NAME = "SEARCH_SCOPE";
    public static final String FILTER_SCOPE_NAME = "FILTER_SCOPE";

    public static final String ALBUM_SCOPE_NAME = "ALBUM_SCOPE";
    public static final String UPLOAD_SCOPE_NAME = "UPLOAD_SCOPE";
    public static final String NEW_CARD_SCOPE_NAME = "NEW_CARD_SCOPE";

    private static Map<String, MortarScope> scopeMap = new HashMap<>();

    public static MortarScope getScreenScope(BaseScreen screen) {
        if (!scopeMap.containsKey(screen.getScopeName())) {
            return createScreenScope(screen);
        } else {
            return scopeMap.get(screen.getScopeName());
        }
    }

    @SuppressWarnings("unchecked")
    private static MortarScope createScreenScope(BaseScreen screen) {
        MortarScope parentScope = scopeMap.get(screen.getParentScopeName());
        if (parentScope != null) {
            Object screenComponent = screen.createScreenComponent(parentScope.getService(SCOPE_SERVICE_NAME));

            MortarScope newScope = parentScope.buildChild()
                    .withService(SCOPE_SERVICE_NAME, screenComponent)
                    .build(screen.getScopeName());
            registerScope(newScope);
            return newScope;
        } else {
            return null;
        }
    }

    public static void registerScope(MortarScope scope) {
        scopeMap.put(scope.getName(), scope);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getComponent(String scopeName) {
        MortarScope scope = scopeMap.get(scopeName);
        return scope == null ? null : (T) scope.getService(SCOPE_SERVICE_NAME);
    }

    public static void destroyScreenScope(String scopeName) {
        MortarScope mortarScope = scopeMap.get(scopeName);
        if (mortarScope != null) {
            mortarScope.destroy();
            cleanScopeMap();
        }
    }

    private static void cleanScopeMap() {
        Iterator<Map.Entry<String, MortarScope>> iterator = scopeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, MortarScope> entry = iterator.next();
            if (entry.getValue().isDestroyed()) {
                iterator.remove();
            }
        }
    }
}
