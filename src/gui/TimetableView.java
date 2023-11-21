package gui;

import entity.Session;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimetableView extends JPanel implements PropertyChangeListener {
    private TimetableViewModel timetableViewModel;

    // UI components
    private JTable timetableTable;
    private JLabel totalDistanceLabel;

    public TimetableView(TimetableViewModel timetableViewModel) {
        this.timetableViewModel = timetableViewModel;
        initializeUI();
        timetableViewModel.addPropertyChangeListener(this);
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Initialize the table model
        TimetableTableModel model = new TimetableTableModel();

        // Timetable table setup
        timetableTable = new JTable(model);
        timetableTable.setFillsViewportHeight(true);

        // Set the custom renderer with the data model
        timetableTable.setDefaultRenderer(Object.class, new CourseCellRenderer(model.getData()));
        // After initializing the timetableTable
        timetableTable.setRowHeight(60); // Choose an appropriate height
        timetableTable.setShowGrid(false);
        timetableTable.setIntercellSpacing(new Dimension(0, 0)); // This will reduce the space between cells

        // Add the table to a scroll pane and then to the panel
        add(new JScrollPane(timetableTable), BorderLayout.CENTER);

        // Total distance label setup
        totalDistanceLabel = new JLabel("Total Distance: 0 km");
        add(totalDistanceLabel, BorderLayout.SOUTH);
    }

    // Inner class for the timetable table model
    class TimetableTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        private Object[][] data; // Fill with course data

        public TimetableTableModel() {
            // Initialize with empty data array with the right size (e.g., 24 slots for a 12-hour day with 30-minute intervals)
            data = new Object[13][6]; // Adjust to match the number of time slots you have
            LocalTime time = LocalTime.of(8, 0); // Adjust start time as needed
            for (int i = 0; i < data.length; i++) {
                data[i][0] = time.format(DateTimeFormatter.ofPattern("HH:mm"));
                time = time.plusHours(1);// Adjust interval as needed
            }
        }
        public Object[][] getData() {
            return data;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }


        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        public void setData(Object[][] newData) {
            data = newData;
            fireTableDataChanged();
            // Update the renderer to use the new data array
            if (timetableTable != null) {
                timetableTable.setDefaultRenderer(Object.class, new CourseCellRenderer(data));
            }
        }
    }

    // This method sets up the slots and fill in the data
    private void updateTimetableDisplay(List<Session> sessions) {
        // Define the start and end times of your schedule
        LocalTime scheduleStart = LocalTime.of(8, 0); // Example: 8 AM
        LocalTime scheduleEnd = LocalTime.of(21, 0); // Example: 8 PM

        // Calculate the number of rows needed (e.g., for 30-minute slots in a 12-hour schedule, we need 24 rows)
        int totalSlots = (int) ChronoUnit.HOURS.between(scheduleStart, scheduleEnd);
        Object[][] timetableData = new Object[totalSlots][6]; // 5 days + 1 for time column

        // Initialize time column
        LocalTime timeIterator = scheduleStart;
        for (int i = 0; i < totalSlots; i++) {
            timetableData[i][0] = timeIterator.toString();
            timeIterator = timeIterator.plusMinutes(60); // Corrected to 30-minute increment
        }

        for (Session session : sessions) {
            processSession(session, timetableData);
        }

        // Now that we've built the data, update the table model
        TimetableTableModel model = (TimetableTableModel) timetableTable.getModel();
        model.setData(timetableData); // Update the model with new data
    }
    private void processSession(Session session, Object[][] timetableData) {
        LocalTime scheduleStart = LocalTime.of(8, 0); // Assuming your timetable starts at 8 AM
        int slotDurationInMinutes = 60; // Assuming each slot is 60 minutes

        List<Integer> startTimes = session.getStartTime(); // Start times in hours
        List<Integer> endTimes = session.getEndTime(); // End times in hours
        List<Integer> days = session.getDay(); // Day indices (1 for Monday, etc.)
        String sessionCode = session.getSessionCode(); // Session code

        for (int i = 0; i < startTimes.size(); i++) {
            // Convert times to 'LocalTime'
            LocalTime startTime = LocalTime.of(startTimes.get(i), 0);
            LocalTime endTime = LocalTime.of(endTimes.get(i), 0);

            // Calculate row indices
            int startRow = (int) ChronoUnit.MINUTES.between(scheduleStart, startTime) / slotDurationInMinutes;
            int endRow = (int) ChronoUnit.MINUTES.between(scheduleStart, endTime) / slotDurationInMinutes;

            // Get the day column index from 0 (Monday) to 4 (Friday)
            int dayColumn = days.get(i); // Assuming days are 1-indexed (1 for Monday, etc.)

            // Place session data into the timetableData array
            for (int row = startRow; row < endRow; row++) {
                if (row >= 0 && row < timetableData.length && dayColumn >= 1 && dayColumn < timetableData[0].length) {
                    timetableData[row][dayColumn] = sessionCode;
                }
            }
        }
    }

    class CourseCellRenderer extends DefaultTableCellRenderer {
        private final Object[][] timetableData;
        private final Map<String, Color> courseColors; // Map to store course-specific colors
        private final Color lightGray = new Color(220, 220, 220);

    public CourseCellRenderer(Object[][] timetableData) {
            this.timetableData = timetableData;
            this.courseColors = new HashMap<>();
            initializeCourseColors();

        }

        private void initializeCourseColors() {
            // Define a list of colors to use for up to 7 courses
            List<Color> colors = Arrays.asList(
                    new Color(255, 204, 204), // Light red
                    new Color(204, 255, 204), // Light green
                    new Color(204, 204, 255), // Light blue
                    new Color(255, 255, 204), // Light yellow
                    new Color(255, 204, 255), // Light purple
                    new Color(204, 255, 255), // Light cyan
                    new Color(255, 223, 186)  // Light orange
            );

            // Loop through each cell in the timetable data and assign a color to each course
            for (Object[] row : timetableData) {
                for (Object cell : row) {
                    if (cell instanceof String && ((String) cell).matches(".*[a-zA-Z]+.*\\d+.*")) {
                        String courseCode = ((String) cell).split(" ")[0];
                        courseColors.putIfAbsent(courseCode, colors.get(courseColors.size() % colors.size()));
                    }
                }
            }
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(CENTER); // Ensure text is centered horizontally
            setVerticalAlignment(CENTER); // Ensure text is centered vertically

            // Check if the row corresponds to a full hour and set a top border if it does

            boolean isFirstColumn = column == 0; // Assuming first column is the hour column

            // Assign a background color based on the course code
            if (isFirstColumn) {
                setBackground(lightGray);
                setForeground(Color.BLACK);
                setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
            } else {
                // Assign a background color based on the course code for course cells
                if (value instanceof String && ((String) value).matches(".*[a-zA-Z]+.*\\d+.*")) {
                    String courseCode = ((String) value).split(" ")[0];
                    Color color = courseColors.getOrDefault(courseCode, table.getBackground());
                    setBackground(color);
                    setForeground(Color.BLACK);

                    // Hide text and remove borders for non-first cells of merged blocks
                    if (row > 0 && value.equals(timetableData[row - 1][column])) {
                        setText("");
                        setBorder(BorderFactory.createEmptyBorder());
                    } else {
                        setText((String) value);
                        setBorder(BorderFactory.createEmptyBorder());
                    }
                } else {
                    // For cells that are not part of a course
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                    setText(value != null ? value.toString() : "");
                    setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, lightGray));
                }
            }

            return this;
        }
    }

    private void updateTotalDistanceDisplay(double totalDistance) {
        totalDistanceLabel.setText("Total Distance: " + totalDistance + " km");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("sessions".equals(evt.getPropertyName())) {
            updateTimetableDisplay((List<Session>) evt.getNewValue());
        } else if ("totalDistance".equals(evt.getPropertyName())) {
            updateTotalDistanceDisplay((Double) evt.getNewValue());
        }
    }
}
