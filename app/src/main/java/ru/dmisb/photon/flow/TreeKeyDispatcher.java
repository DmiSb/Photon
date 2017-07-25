package ru.dmisb.photon.flow;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Collections;
import java.util.Map;

import flow.Direction;
import flow.Dispatcher;
import flow.KeyChanger;
import flow.State;
import flow.Traversal;
import flow.TraversalCallback;
import flow.TreeKey;
import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseScreen;

public class TreeKeyDispatcher implements KeyChanger, Dispatcher {
    private Activity activity;
    private Object inKey;
    @Nullable
    private Object outKey;
    private FrameLayout rootFrame;

    public TreeKeyDispatcher(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void dispatch(@NonNull Traversal traversal, @NonNull TraversalCallback callback) {
        Map<Object, Context> contexts;
        State inState = traversal.getState(traversal.destination.top());
        inKey = inState.getKey();
        State outState = traversal.origin == null ? null : traversal.getState(traversal.origin.top());
        outKey = outState == null ? null : outState.getKey();

        rootFrame = (FrameLayout) activity.findViewById(R.id.root_frame);

        if (inKey.equals(outKey)) {
            callback.onTraversalCompleted();
            return;
        }

        Context flowContext = traversal.createContext(inKey, activity);
        Context mortarContext = ScreenScoper.getScreenScope((BaseScreen) inKey).createContext(flowContext);
        contexts = Collections.singletonMap(inKey, mortarContext);
        changeKey(outState, inState, traversal.direction, contexts, callback);
        outKey = null;
    }

    @Override
    public void changeKey(@Nullable State outgoingState, @NonNull State incomingState, @NonNull Direction direction,
                          @NonNull Map<Object, Context> incomingContexts, @NonNull TraversalCallback callback) {
        Context context = incomingContexts.get(inKey);

        if (outgoingState != null) {
            outgoingState.save(rootFrame.getChildAt(0));
        }
        Screen screen;
        screen = inKey.getClass().getAnnotation(Screen.class);
        if (screen == null) {
            throw new IllegalStateException("Блин, опять забыл анатацию @Screen для " + ((BaseScreen) inKey).getScopeName());
        } else {
            int layout = screen.value();

            LayoutInflater inflater = LayoutInflater.from(context);
            View newView = inflater.inflate(layout, rootFrame, false);

            incomingState.restore(newView);

            if (outKey != null && !(inKey instanceof TreeKey)) {
                ((BaseScreen) outKey).unregisterScope();
            }

            if (rootFrame.getChildAt(0) != null) {
                rootFrame.removeView(rootFrame.getChildAt(0));
            }

            rootFrame.addView(newView);
            callback.onTraversalCompleted();
        }
    }
}