package objectpool;

import java.util.HashMap;
import java.util.Map;

public abstract class ObjectPool<T> {
    private long expirationTime;
    private Map<T, Long> locked;
    private Map<T, Long> unlocked;

    public ObjectPool() {
	expirationTime = 30000; // 30 seconds
	locked = new HashMap<>();
	unlocked = new HashMap<>();
    }

    protected abstract T create();

    public abstract boolean validate(T o);

    public abstract void expire(T o);

    public synchronized T checkOut() {
	long now = System.currentTimeMillis();
	if (unlocked.size() > 0) {
	    for (T t : unlocked.keySet()) {
		if ((now - unlocked.get(t)) > expirationTime) {
		    // object has expired
		    unlocked.remove(t);
		    expire(t);
		    t = null;
		} else {
		    if (validate(t)) {
			unlocked.remove(t);
			locked.put(t, now);
			return (t);
		    } else {
			// object failed validation
			unlocked.remove(t);
			expire(t);
			t = null;
		    }
		}
	    }
	}
	// no objects available, create a new one
	T t = create();
	locked.put(t, now);
	return (t);
    }

    public synchronized void checkIn(T t) {
	locked.remove(t);
	unlocked.put(t, System.currentTimeMillis());
    }
}
