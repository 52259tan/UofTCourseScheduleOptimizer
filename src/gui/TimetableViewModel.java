package gui;

import data_access.SessionDTO;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class TimetableViewModel {
    private final PropertyChangeSupport support;
    private List<SessionDTO> sessions;

    public TimetableViewModel() {
        support = new PropertyChangeSupport(this);
    }

    public List<SessionDTO> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionDTO> sessions) {
        List<SessionDTO> oldSessions = this.sessions;
        this.sessions = sessions;
        support.firePropertyChange("sessions", oldSessions, sessions);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}

