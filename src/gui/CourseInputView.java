package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CourseInputView extends JPanel {
    private final List<JTextField> courseFields = new ArrayList<>();
    private final CourseController controller;
    private final JPanel coursePanel;
    private final JScrollPane scrollPane;

    public CourseInputView(CourseController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adding titles
        JPanel titlePanel = new JPanel(new GridLayout(2, 1)); // 2 rows, 1 column
        titlePanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Please input your courses", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel);

        JLabel noteLabel = new JLabel("Maximum of 7 courses", JLabel.CENTER);
        noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        noteLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(noteLabel);

        add(titlePanel, BorderLayout.NORTH);



        coursePanel = new JPanel();
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.setBackground(Color.WHITE);

        // Add the first course field by default
        addCourseField();

        // Scroll pane to contain the course panel
        scrollPane = new JScrollPane(coursePanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 120));
        add(scrollPane, BorderLayout.CENTER);

        addButtons();
    }

    private void addCourseField() {
        JTextField courseField = new JTextField(40);
        courseFields.add(courseField);
        courseField.setMaximumSize(new Dimension(Integer.MAX_VALUE, courseField.getPreferredSize().height));

        coursePanel.add(courseField);
        coursePanel.revalidate();
        coursePanel.repaint();

        // Scroll to the bottom after updating the panel
        scrollToBottom();
    }

    private void scrollToBottom() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
    }

    private void addButtons() {
        JButton addButton = createButton("Add Another Course", this::addButtonClicked);
        JButton submitButton = createButton("Submit", this::submitButtonClicked);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addButton);
        buttonPanel.add(submitButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }


    private void addButtonClicked(ActionEvent e) {
        addCourseField();
    }

    private void submitButtonClicked(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource(); // Get the button that was clicked
        sourceButton.setBackground(Color.GREEN);
        sourceButton.setForeground(Color.WHITE);
        sourceButton.setOpaque(true);
        sourceButton.setBorderPainted(false);
        sourceButton.setContentAreaFilled(true);

        List<String> courses = new ArrayList<>();
        for (JTextField courseField : courseFields) {
            String courseCode = courseField.getText().trim();
            if (!courseCode.isEmpty()) {
                courses.add(courseCode);
            }
        }

        controller.execute(courses);
    }

}
