package gui;

import entity.Session;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class TimetableViewModel {
    private final PropertyChangeSupport support;
    private List<Session> sessions;
    private double totalDistance;

    public TimetableViewModel() {
        support = new PropertyChangeSupport(this);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        List<Session> oldSessions = this.sessions;
        this.sessions = sessions;
        support.firePropertyChange("sessions", oldSessions, sessions);
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        double oldDistance = this.totalDistance;
        this.totalDistance = totalDistance;
        support.firePropertyChange("totalDistance", oldDistance, totalDistance);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}

