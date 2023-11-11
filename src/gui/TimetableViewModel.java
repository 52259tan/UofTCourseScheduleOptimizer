package gui;

import entity.Course;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class TimetableViewModel {
    private final PropertyChangeSupport support;
    private List<Course> courses;
    private double totalDistance;

    public TimetableViewModel() {
        support = new PropertyChangeSupport(this);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        List<Course> oldCourses = this.courses;
        this.courses = courses;
        support.firePropertyChange("courses", oldCourses, courses);
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

