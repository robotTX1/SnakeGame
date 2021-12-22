package com.snakegame.snakegame.util.observable;

import java.util.LinkedList;
import java.util.List;

public class Property<T> {
    private T value;
    private final List<ChangeListener<T>> listeners = new LinkedList<>();

    public Property(T value) {
        this.value = value;
    }

    public Property() {
        this(null);
    }

    public void listen(ChangeListener<T> listener) {
        this.listeners.add(listener);
    }

    public T get() {
        return value;
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public void set(T value) {
        this.value = value;
        notifyListeners();
    }

    private void notifyListeners() {
        for (ChangeListener<T> listener : listeners) {
            listener.onChanged(this.value);
        }
    }
}
